/*
 * © Copyright - Emmanuel Lampe aka. rexlManu 2018.
 */
package eu.smashmc.smashpvp.game;

import com.google.common.collect.Lists;
import lombok.Getter;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/******************************************************************************************
 *    Urheberrechtshinweis                                                       
 *    Copyright © Emmanuel Lampe 2018                                       
 *    Erstellt: 22.05.2018 / 16:23                           
 *
 *    Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.                    
 *    Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,       
 *    bei Emmanuel Lampe. Alle Rechte vorbehalten.                      
 *
 *    Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,        
 *    öffentlichen Zugänglichmachung oder andere Nutzung           
 *    bedarf der ausdrücklichen, schriftlichen Zustimmung von Emmanuel Lampe.  
 ******************************************************************************************/

public final class PlayerManager {

    @Getter
    private Map<Player, PlayerHandler> handlers;

    @Getter
    private List<Player> alivePlayers;

    public PlayerManager() {
        this.handlers = new HashMap<>();
        this.alivePlayers = Lists.newArrayList();
    }

    public PlayerHandler getHandler(Player player) {
        if (!handlers.containsKey(player)) handlers.put(player, new PlayerHandler(player));
        return handlers.get(player);
    }
}
