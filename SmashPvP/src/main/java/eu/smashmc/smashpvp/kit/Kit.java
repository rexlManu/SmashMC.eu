/*
 * © Copyright - Emmanuel Lampe aka. rexlManu 2018.
 */
package eu.smashmc.smashpvp.kit;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

/******************************************************************************************
 *    Urheberrechtshinweis                                                       
 *    Copyright © Emmanuel Lampe 2018                                       
 *    Erstellt: 22.05.2018 / 17:45                           
 *
 *    Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.                    
 *    Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,       
 *    bei Emmanuel Lampe. Alle Rechte vorbehalten.                      
 *
 *    Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,        
 *    öffentlichen Zugänglichmachung oder andere Nutzung           
 *    bedarf der ausdrücklichen, schriftlichen Zustimmung von Emmanuel Lampe.  
 ******************************************************************************************/


public interface Kit {

    String getKitname();

    ItemStack getDisplayItem();

    void onEquip(Player player);
}
