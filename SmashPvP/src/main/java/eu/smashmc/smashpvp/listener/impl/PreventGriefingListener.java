/*
 * © Copyright - Emmanuel Lampe aka. rexlManu 2018.
 */
package eu.smashmc.smashpvp.listener.impl;

import eu.smashmc.smashpvp.listener.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerArmorStandManipulateEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.plugin.java.JavaPlugin;

/******************************************************************************************
 *    Urheberrechtshinweis                                                       
 *    Copyright © Emmanuel Lampe 2018                                       
 *    Erstellt: 22.05.2018 / 16:09                           
 *
 *    Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.                    
 *    Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,       
 *    bei Emmanuel Lampe. Alle Rechte vorbehalten.                      
 *
 *    Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,        
 *    öffentlichen Zugänglichmachung oder andere Nutzung           
 *    bedarf der ausdrücklichen, schriftlichen Zustimmung von Emmanuel Lampe.  
 ******************************************************************************************/

public final class PreventGriefingListener extends Listener {

    public PreventGriefingListener(JavaPlugin javaPlugin) {
        super(javaPlugin);
    }

    @EventHandler
    public void onBlockBreakEvent(BlockBreakEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onBlockPlaceEvent(BlockPlaceEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onFoodLevelChangeEvent(FoodLevelChangeEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onWeatherChangeEvent(WeatherChangeEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onPlayerArmorStandManipulateEvent(PlayerArmorStandManipulateEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onPlayerDropItemEvent(PlayerDropItemEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onPlayerPickupItemEvent(PlayerPickupItemEvent event) {
        event.setCancelled(true);
    }
}
