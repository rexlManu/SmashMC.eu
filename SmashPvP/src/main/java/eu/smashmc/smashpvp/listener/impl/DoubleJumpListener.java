/*
 * © Copyright - Emmanuel Lampe aka. rexlManu 2018.
 */
package eu.smashmc.smashpvp.listener.impl;

import eu.smashmc.smashpvp.listener.Listener;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;


/******************************************************************************************
 *    Urheberrechtshinweis                                                       
 *    Copyright © Emmanuel Lampe 2018                                       
 *    Erstellt: 23.05.2018 / 13:32                           
 *
 *    Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.                    
 *    Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,       
 *    bei Emmanuel Lampe. Alle Rechte vorbehalten.                      
 *
 *    Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,        
 *    öffentlichen Zugänglichmachung oder andere Nutzung           
 *    bedarf der ausdrücklichen, schriftlichen Zustimmung von Emmanuel Lampe.  
 ******************************************************************************************/

public final class DoubleJumpListener extends Listener {
    public DoubleJumpListener(JavaPlugin javaPlugin) {
        super(javaPlugin);
    }

    @EventHandler
    public void on(PlayerToggleFlightEvent event) {
        final Player player = event.getPlayer();
        if (player.getGameMode() == GameMode.ADVENTURE) {
            event.setCancelled(true);
            player.setFlying(false);
            player.setAllowFlight(false);
            player.setVelocity(player.getLocation().getDirection().multiply(1.5).add(new Vector(0f, 1f, 0f)));
        }
    }

    @EventHandler
    public void onPlayerMoveEvent(final PlayerMoveEvent event) {
        final Player player = event.getPlayer();
        if (player.getGameMode() == GameMode.ADVENTURE && player.getLocation().add(0.0, -1.0, 0.0).getBlock().getType() != Material.AIR) {
            player.setFlying(false);
            player.setAllowFlight(true);
        }
    }

}
