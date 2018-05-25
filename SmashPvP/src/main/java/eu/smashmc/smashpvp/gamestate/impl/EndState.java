/*
 * © Copyright - Emmanuel Lampe aka. rexlManu 2018.
 */
package eu.smashmc.smashpvp.gamestate.impl;

import eu.smashmc.smashpvp.SmashPvP;
import eu.smashmc.smashpvp.game.WinDecetion;
import eu.smashmc.smashpvp.gamestate.GameState;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

/******************************************************************************************
 *    Urheberrechtshinweis                                                       
 *    Copyright © Emmanuel Lampe 2018                                       
 *    Erstellt: 22.05.2018 / 15:10                           
 *
 *    Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.                    
 *    Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,       
 *    bei Emmanuel Lampe. Alle Rechte vorbehalten.                      
 *
 *    Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,        
 *    öffentlichen Zugänglichmachung oder andere Nutzung           
 *    bedarf der ausdrücklichen, schriftlichen Zustimmung von Emmanuel Lampe.  
 ******************************************************************************************/

public final class EndState implements GameState {

    @Override
    public void start() {
        final Player winner = WinDecetion.getWinner();
        Bukkit.broadcastMessage("§r");
        Bukkit.broadcastMessage("§8» §aSmashPvP §8× §7Der Gewinner der Runde ist §a" + winner.getDisplayName() + "§7.");
        Bukkit.broadcastMessage("§r");
        winner.setAllowFlight(true);
        broadcastSound(Sound.LEVEL_UP);

        new BukkitRunnable() {
            int sec = 10;

            @Override
            public void run() {
                if (sec == 0) {
                    Bukkit.shutdown();
                    cancel();
                    return;
                }
                broadcastActionbar("§8» §aSmashPvP §8× §7Der Server startet in §a" + sec + "§7 neu.");
                broadcastSound(Sound.CHICKEN_EGG_POP);
                sec--;
            }
        }.runTaskTimer(SmashPvP.getInstance(), 0, 20);
    }

    private void broadcastActionbar(String message) {
        Bukkit.getOnlinePlayers().forEach(p -> sendActionbar(p, message));
    }


    private void broadcastSound(Sound levelUp) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.playSound(player.getLocation(), levelUp, 5F, 3F);
        }
    }

    private void sendActionbar(Player p, String message) {
        IChatBaseComponent icbc = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + message + "\"}");
        PacketPlayOutChat bar = new PacketPlayOutChat(icbc, (byte) 2);
        ((CraftPlayer) p).getHandle().playerConnection.sendPacket(bar);
    }

    @Override
    public void stop() {

    }
}
