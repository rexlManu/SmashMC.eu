/*
 * © Copyright - Emmanuel Lampe aka. rexlManu 2018.
 */
package eu.smashmc.smashpvp.listener.impl;

import eu.smashmc.smashpvp.SmashPvP;
import eu.smashmc.smashpvp.data.Data;
import eu.smashmc.smashpvp.gamestate.impl.LobbyState;
import eu.smashmc.smashpvp.listener.Listener;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.plugin.java.JavaPlugin;

/******************************************************************************************
 *    Urheberrechtshinweis                                                       
 *    Copyright © Emmanuel Lampe 2018                                       
 *    Erstellt: 22.05.2018 / 20:47                           
 *
 *    Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.                    
 *    Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,       
 *    bei Emmanuel Lampe. Alle Rechte vorbehalten.                      
 *
 *    Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,        
 *    öffentlichen Zugänglichmachung oder andere Nutzung           
 *    bedarf der ausdrücklichen, schriftlichen Zustimmung von Emmanuel Lampe.  
 ******************************************************************************************/

public final class EntityDamageListener extends Listener {

    public EntityDamageListener(JavaPlugin javaPlugin) {
        super(javaPlugin);
    }

    @EventHandler
    public void on(EntityDamageEvent event) {
        final Data data = SmashPvP.getInstance().getData();
        if (data.getGameManager().getCurrentGameState() instanceof LobbyState) {
            event.setCancelled(true);
        } else if (data.getSchutzCountdown().isRunning()) {
            event.setCancelled(true);
        } else if (event.getEntity() instanceof Player && !SmashPvP.getInstance().getData().getPlayerManager().getAlivePlayers().contains(((Player) event.getEntity()))) {
            event.setCancelled(true);
        }
    }
}
