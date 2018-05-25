/*
 * © Copyright - Emmanuel Lampe aka. rexlManu 2018.
 */
package eu.smashmc.smashpvp.gamestate.impl;

import eu.smashmc.smashpvp.SmashPvP;
import eu.smashmc.smashpvp.countdowns.SchutzCountdown;
import eu.smashmc.smashpvp.data.Data;
import eu.smashmc.smashpvp.game.PlayerHandler;
import eu.smashmc.smashpvp.gamestate.GameState;
import lombok.Getter;
import org.bukkit.Bukkit;

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

public final class IngameState implements GameState {

    @Override
    public void start() {
        final Data data = SmashPvP.getInstance().getData();
        data.getSchutzCountdown().start();
        Bukkit.getOnlinePlayers().forEach(player -> {
            data.getLobbyCountdown().stop();
            final PlayerHandler handler = data.getPlayerManager().getHandler(player);
            handler.clearPlayer();
            if(handler.getSelectedKit() == null) handler.setSelectedKit(data.getKitManager().getKits().get(0));
            handler.getSelectedKit().onEquip(player);
            handler.sendIngameScoreboard();
            handler.teleportToWarp("Ingame");
            player.setAllowFlight(true);
            player.setFlying(false);
        });
    }

    @Override
    public void stop() {

    }
}
