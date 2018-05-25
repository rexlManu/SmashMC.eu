/*
 * © Copyright - Emmanuel Lampe aka. rexlManu 2018.
 */
package eu.smashmc.smashpvp.listener.impl;

import eu.smashmc.smashpvp.SmashPvP;
import eu.smashmc.smashpvp.data.Data;
import eu.smashmc.smashpvp.game.PlayerHandler;
import eu.smashmc.smashpvp.gamestate.impl.LobbyState;
import eu.smashmc.smashpvp.listener.Listener;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.management.GarbageCollectorMXBean;

/******************************************************************************************
 *    Urheberrechtshinweis                                                       
 *    Copyright © Emmanuel Lampe 2018                                       
 *    Erstellt: 22.05.2018 / 15:19                           
 *
 *    Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.                    
 *    Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,       
 *    bei Emmanuel Lampe. Alle Rechte vorbehalten.                      
 *
 *    Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,        
 *    öffentlichen Zugänglichmachung oder andere Nutzung           
 *    bedarf der ausdrücklichen, schriftlichen Zustimmung von Emmanuel Lampe.  
 ******************************************************************************************/

public final class PlayerJoinListener extends Listener {

    public PlayerJoinListener(JavaPlugin javaPlugin) {
        super(javaPlugin);
    }

    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent event) {
        event.setJoinMessage(null);
        final Player player = event.getPlayer();
        final Data data = SmashPvP.getInstance().getData();
        final PlayerHandler handler = data.getPlayerManager().getHandler(player);
        if (data.getGameManager().getCurrentGameState() instanceof LobbyState) {
            Bukkit.broadcastMessage("§8» §aSmashPvP §8× §7Der Spieler §a"+ player.getDisplayName()+"§7 hat die Runde betreten.");
            data.getPlayerManager().getAlivePlayers().add(player);
            if (Bukkit.getOnlinePlayers().size() >= 2) {
                data.getLobbyCountdown().start();
            } else {
                data.getLobbyCountdown().stop();
            }
            handler.clearPlayer();
            handler.sendLobbyScoreboard();
            handler.teleportToSpawn();
            handler.giveLobbyItems();
        }else {
            handler.clearPlayer();
            handler.teleportToWarp("Spectator");
            handler.giveSpectatorItems();
        }
    }
}
