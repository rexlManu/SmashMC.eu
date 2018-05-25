/*
 * © Copyright - Emmanuel Lampe aka. rexlManu 2018.
 */
package eu.smashmc.smashpvp.countdowns;

import lombok.Getter;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import net.smashmc.api.game.Countdown;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

/******************************************************************************************
 *    Urheberrechtshinweis                                                       
 *    Copyright © Emmanuel Lampe 2018                                       
 *    Erstellt: 22.05.2018 / 20:31                           
 *
 *    Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.                    
 *    Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,       
 *    bei Emmanuel Lampe. Alle Rechte vorbehalten.                      
 *
 *    Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,        
 *    öffentlichen Zugänglichmachung oder andere Nutzung           
 *    bedarf der ausdrücklichen, schriftlichen Zustimmung von Emmanuel Lampe.  
 ******************************************************************************************/

public final class SchutzCountdown extends Countdown {

    @Getter
    private boolean running;

    public SchutzCountdown(JavaPlugin plugin) {
        super(10, 20, plugin);
    }

    public void start() {
        if (!running) {
            this.running = true;
            this.run();
        }
    }

    public void stop() {
        Bukkit.getScheduler().cancelTask(getTask());
        setSeconds(60);
        this.running = false;
    }

    @Override
    public void onTick() {
        Bukkit.getOnlinePlayers().forEach(p -> p.setLevel(getSeconds()));
        Bukkit.getOnlinePlayers().forEach(p -> p.setExp((float) ((double) getSeconds() / (double) 60)));

        switch (getSeconds()) {
            case 10:
            case 9:
            case 8:
            case 7:
            case 6:
            case 5:
            case 4:
            case 3:
            case 2:
            case 1:
                broadcastActionbar("§8» §aSmashPvP §8× §7Schutzphase endet in §a" + getSeconds() + " §7Sekunden.");
                broadcastSound(Sound.CHICKEN_EGG_POP);
                break;
        }

    }

    private void broadcastSound(Sound chickenEggPop) {
        Bukkit.getOnlinePlayers().forEach(o -> o.playSound(o.getLocation(), chickenEggPop, 1F, 3F));
    }

    private void broadcastMessage(String messsage) {
        Bukkit.getOnlinePlayers().forEach(p -> p.sendMessage(messsage));
    }

    private void broadcastActionbar(String message) {
        Bukkit.getOnlinePlayers().forEach(p -> sendActionbar(p, message));
    }

    private void sendActionbar(Player p, String message) {
        IChatBaseComponent icbc = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + message + "\"}");
        PacketPlayOutChat bar = new PacketPlayOutChat(icbc, (byte) 2);
        ((CraftPlayer) p).getHandle().playerConnection.sendPacket(bar);
    }

    @Override
    public void onStop() {
        this.running = false;
        broadcastMessage("§r");
        broadcastMessage("§8» §aSmashPvP §8× §7Die Schutzphase endet nun.");
        broadcastMessage("§r");
        broadcastSound(Sound.GHAST_SCREAM);
        Bukkit.getOnlinePlayers().forEach(o -> o.setLevel(0));
    }
}
