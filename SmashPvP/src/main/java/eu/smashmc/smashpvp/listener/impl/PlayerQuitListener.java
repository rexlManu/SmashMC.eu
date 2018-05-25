/*
 * © Copyright - Emmanuel Lampe aka. rexlManu 2018.
 */
package eu.smashmc.smashpvp.listener.impl;

import eu.smashmc.smashpvp.SmashPvP;
import eu.smashmc.smashpvp.game.WinDecetion;
import eu.smashmc.smashpvp.gamestate.impl.LobbyState;
import eu.smashmc.smashpvp.listener.Listener;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

/******************************************************************************************
 *    Urheberrechtshinweis                                                       
 *    Copyright © Emmanuel Lampe 2018                                       
 *    Erstellt: 22.05.2018 / 20:56                           
 *
 *    Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.                    
 *    Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,       
 *    bei Emmanuel Lampe. Alle Rechte vorbehalten.                      
 *
 *    Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,        
 *    öffentlichen Zugänglichmachung oder andere Nutzung           
 *    bedarf der ausdrücklichen, schriftlichen Zustimmung von Emmanuel Lampe.  
 ******************************************************************************************/

public final class PlayerQuitListener extends Listener {

    public PlayerQuitListener(JavaPlugin javaPlugin) {
        super(javaPlugin);
    }

    @EventHandler
    public void on(PlayerQuitEvent event) {
        event.setQuitMessage(null);
        final Player player = event.getPlayer();
        SmashPvP.getInstance().getData().getPlayerManager().getAlivePlayers().remove(player);
        if (SmashPvP.getInstance().getData().getGameManager().getCurrentGameState() instanceof LobbyState) {
            Bukkit.broadcastMessage("§8» §aSmashPvP §8× §7Der Spieler §a"+ player.getDisplayName()+"§7 hat die Runde verlassen.");
        }else {
            Bukkit.broadcastMessage("§8» §aSmashPvP §8× §7Der Spieler §a"+ player.getDisplayName()+"§7 hat die Runde verlassen.");
            Bukkit.broadcastMessage("§8» §aSmashPvP §8× §7Der Spieler §a" + player.getDisplayName() + " §7ist ausgeschieden.");
            WinDecetion.checkForWin();
        }
    }
}
