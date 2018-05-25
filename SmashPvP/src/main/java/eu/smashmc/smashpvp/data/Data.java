/*
 * © Copyright - Emmanuel Lampe aka. rexlManu 2018.
 */
package eu.smashmc.smashpvp.data;

import eu.smashmc.smashpvp.countdowns.LobbyCountdown;
import eu.smashmc.smashpvp.countdowns.SchutzCountdown;
import eu.smashmc.smashpvp.game.PlayerManager;
import eu.smashmc.smashpvp.gamestate.GameManager;
import eu.smashmc.smashpvp.kit.KitManager;
import eu.smashmc.smashpvp.location.LocationManager;
import org.bukkit.plugin.java.JavaPlugin;

/******************************************************************************************
 *    Urheberrechtshinweis                                                       
 *    Copyright © Emmanuel Lampe 2018                                       
 *    Erstellt: 22.05.2018 / 15:16                           
 *
 *    Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.                    
 *    Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,       
 *    bei Emmanuel Lampe. Alle Rechte vorbehalten.                      
 *
 *    Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,        
 *    öffentlichen Zugänglichmachung oder andere Nutzung           
 *    bedarf der ausdrücklichen, schriftlichen Zustimmung von Emmanuel Lampe.  
 ******************************************************************************************/

@lombok.Data
public final class Data {

    private GameManager gameManager;
    private LobbyCountdown lobbyCountdown;
    private PlayerManager playerManager;
    private LocationManager locationManager;
    private KitManager kitManager;
    private SchutzCountdown schutzCountdown;

    public Data(JavaPlugin javaPlugin) {
        this.gameManager = new GameManager();
        this.lobbyCountdown = new LobbyCountdown(javaPlugin);
        this.playerManager = new PlayerManager();
        this.locationManager = new LocationManager();
        this.kitManager = new KitManager();
        this.schutzCountdown = new SchutzCountdown(javaPlugin);
    }
}
