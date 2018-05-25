/*
 * © Copyright - Emmanuel Lampe aka. rexlManu 2018.
 */
package eu.smashmc.smashpvp.location;

import com.google.common.collect.Maps;
import eu.smashmc.smashpvp.utils.JsonConfig;
import org.bukkit.Location;

import java.io.File;
import java.io.IOException;
import java.util.Map;

/******************************************************************************************
 *    Urheberrechtshinweis                                                       
 *    Copyright © Emmanuel Lampe 2018                                       
 *    Erstellt: 22.05.2018 / 16:43                           
 *
 *    Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.                    
 *    Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,       
 *    bei Emmanuel Lampe. Alle Rechte vorbehalten.                      
 *
 *    Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,        
 *    öffentlichen Zugänglichmachung oder andere Nutzung           
 *    bedarf der ausdrücklichen, schriftlichen Zustimmung von Emmanuel Lampe.  
 ******************************************************************************************/

public final class LocationManager {

    private Map<String, Location> locations;

    private JsonConfig jsonConfig;

    public LocationManager() {
        this.locations = Maps.newHashMap();
        this.jsonConfig = new JsonConfig("locations", new File("./"));
        this.jsonConfig.load();
    }

    public Location getLocation(String key){
        return this.jsonConfig.getLocation(key);
    }
    public void saveLocation(String key, Location location){
        this.jsonConfig.setLocation(key, location);
        try {
            this.jsonConfig.save();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
