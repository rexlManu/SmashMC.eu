/*
 * © Copyright - Emmanuel Lampe aka. rexlManu 2018.
 */
package eu.smashmc.smashpvp.game;

import eu.smashmc.smashpvp.SmashPvP;
import eu.smashmc.smashpvp.data.Data;
import eu.smashmc.smashpvp.gamestate.GameManager;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;

/******************************************************************************************
 *    Urheberrechtshinweis                                                       
 *    Copyright © Emmanuel Lampe 2018                                       
 *    Erstellt: 23.05.2018 / 13:13                           
 *
 *    Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.                    
 *    Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,       
 *    bei Emmanuel Lampe. Alle Rechte vorbehalten.                      
 *
 *    Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,        
 *    öffentlichen Zugänglichmachung oder andere Nutzung           
 *    bedarf der ausdrücklichen, schriftlichen Zustimmung von Emmanuel Lampe.  
 ******************************************************************************************/

public final class WinDecetion {

    @Getter
    @Setter
    private static Player winner;

    public WinDecetion() {

    }

    public static void checkForWin() {
        final Data data = SmashPvP.getInstance().getData();
        if (data.getPlayerManager().getAlivePlayers().size() == 1) {
            final Player winner = data.getPlayerManager().getAlivePlayers().get(0);
            setWinner(winner);
            data.getGameManager().changeGameState(data.getGameManager().getGameStates().get(GameManager.END));
        }
    }
}
