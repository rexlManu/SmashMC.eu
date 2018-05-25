/*
 * © Copyright - Emmanuel Lampe aka. rexlManu 2018.
 */
package eu.smashmc.smashpvp.listener.impl;

import eu.smashmc.smashpvp.SmashPvP;
import eu.smashmc.smashpvp.data.Data;
import eu.smashmc.smashpvp.gamestate.impl.IngameState;
import eu.smashmc.smashpvp.listener.Listener;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerVelocityEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

/******************************************************************************************
 *    Urheberrechtshinweis                                                       
 *    Copyright © Emmanuel Lampe 2018                                       
 *    Erstellt: 23.05.2018 / 13:43                           
 *
 *    Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.                    
 *    Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,       
 *    bei Emmanuel Lampe. Alle Rechte vorbehalten.                      
 *
 *    Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,        
 *    öffentlichen Zugänglichmachung oder andere Nutzung           
 *    bedarf der ausdrücklichen, schriftlichen Zustimmung von Emmanuel Lampe.  
 ******************************************************************************************/

public final class KnockbackListener extends Listener {
    public KnockbackListener(JavaPlugin javaPlugin) {
        super(javaPlugin);
    }

    @EventHandler
    public void on(EntityDamageByEntityEvent event) {
        if (!(event.getDamager() instanceof Player)) return;
        if (!(event.getEntity() instanceof Player)) return;
        final Data data = SmashPvP.getInstance().getData();
        if (data.getGameManager().getCurrentGameState() instanceof IngameState) {
            final Player player = (Player) event.getEntity();
            data.getPlayerManager().getHandler(player).addKnockback();
        }
    }

    @EventHandler
    public void on(final PlayerVelocityEvent event) {
        final Player player = event.getPlayer();
        final Vector velocity = player.getVelocity();
        float knockback = SmashPvP.getInstance().getData().getPlayerManager().getHandler(player).getKnockback();
        knockback = knockback/100;
        event.setVelocity(new Vector(knockback*velocity.getX(), velocity.getY(), knockback*velocity.getZ()));
    }
}
