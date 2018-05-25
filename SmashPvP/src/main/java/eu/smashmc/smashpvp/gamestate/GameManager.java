/*
 * © Copyright - Emmanuel Lampe aka. rexlManu 2018.
 */
package eu.smashmc.smashpvp.gamestate;

import com.google.common.collect.Lists;
import eu.smashmc.smashpvp.gamestate.impl.EndState;
import eu.smashmc.smashpvp.gamestate.impl.IngameState;
import eu.smashmc.smashpvp.gamestate.impl.LobbyState;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/******************************************************************************************
 *    Urheberrechtshinweis                                                       
 *    Copyright © Emmanuel Lampe 2018                                       
 *    Erstellt: 22.05.2018 / 15:07                           
 *
 *    Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.                    
 *    Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,       
 *    bei Emmanuel Lampe. Alle Rechte vorbehalten.                      
 *
 *    Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,        
 *    öffentlichen Zugänglichmachung oder andere Nutzung           
 *    bedarf der ausdrücklichen, schriftlichen Zustimmung von Emmanuel Lampe.  
 ******************************************************************************************/

public final class GameManager {

    public static final int LOBBY = 0, INGAME = 1, END = 2;

    @Getter
    private List<GameState> gameStates;

    @Getter
    @Setter
    private GameState currentGameState;

    public GameManager() {
        this.gameStates = Lists.newArrayList();

        this.gameStates.add(new LobbyState());
        this.gameStates.add(new IngameState());
        this.gameStates.add(new EndState());
    }

    public void changeGameState(GameState gameState) {
        if (currentGameState != null) {
            currentGameState.stop();
        }
        currentGameState = gameState;
        currentGameState.start();
    }
}
