/*
 * © Copyright - Emmanuel Lampe aka. rexlManu 2018.
 */
package eu.smashmc.smashpvp.listener.impl;

import eu.smashmc.smashpvp.SmashPvP;
import eu.smashmc.smashpvp.game.PlayerHandler;
import eu.smashmc.smashpvp.game.WinDecetion;
import eu.smashmc.smashpvp.listener.Listener;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.java.JavaPlugin;

/******************************************************************************************
 *    Urheberrechtshinweis                                                       
 *    Copyright © Emmanuel Lampe 2018                                       
 *    Erstellt: 22.05.2018 / 20:51                           
 *
 *    Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.                    
 *    Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,       
 *    bei Emmanuel Lampe. Alle Rechte vorbehalten.                      
 *
 *    Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,        
 *    öffentlichen Zugänglichmachung oder andere Nutzung           
 *    bedarf der ausdrücklichen, schriftlichen Zustimmung von Emmanuel Lampe.  
 ******************************************************************************************/

public final class PlayerDeathListener extends Listener {

    public PlayerDeathListener(JavaPlugin javaPlugin) {
        super(javaPlugin);
    }

    @EventHandler
    public void on(PlayerDeathEvent event) {
        event.setDeathMessage(null);
        event.setKeepInventory(true);
        final Player player = event.getEntity();
        if (player.getKiller() == null) {
            Bukkit.broadcastMessage("§8» §aSmashPvP §8× §7Der Spieler §a" + player.getDisplayName() + " §7ist gestorbem.");
        } else {
            Bukkit.broadcastMessage("§8» §aSmashPvP §8× §7Der Spieler §a" + player.getDisplayName() + " §7wurde von §a" + player.getKiller().getDisplayName() + "§7 getötet.");
            SmashPvP.getInstance().getData().getPlayerManager().getHandler(event.getEntity().getKiller()).sendIngameScoreboard();
        }
        player.spigot().respawn();
        final PlayerHandler handler = SmashPvP.getInstance().getData().getPlayerManager().getHandler(player);
        if (handler.getLives() == 0) {
            Bukkit.broadcastMessage("§8» §aSmashPvP §8× §7Der Spieler §a" + player.getDisplayName() + " §7ist ausgeschieden.");
            SmashPvP.getInstance().getData().getPlayerManager().getAlivePlayers().remove(player);
            player.setAllowFlight(false);
            player.setFlying(false);
            handler.clearPlayer();
            handler.teleportToWarp("Spectator");
            handler.giveSpectatorItems();
            handler.setSpectator();
        } else {
            handler.removeLive();
            handler.clearPlayer();
            handler.teleportToWarp("Ingame");
            handler.getSelectedKit().onEquip(player);
            player.playSound(player.getLocation(), Sound.WITHER_SPAWN, 10f, 10f);
            handler.sendIngameScoreboard();
            player.setAllowFlight(true);
            player.setFlying(false);
        }
        WinDecetion.checkForWin();
        handler.resetKnockback();
    }
}
