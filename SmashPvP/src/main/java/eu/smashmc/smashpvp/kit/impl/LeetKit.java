/*
 * © Copyright - Emmanuel Lampe aka. rexlManu 2018.
 */
package eu.smashmc.smashpvp.kit.impl;

import eu.smashmc.smashpvp.kit.Kit;
import net.smashmc.api.builder.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

/******************************************************************************************
 *    Urheberrechtshinweis                                                       
 *    Copyright © Emmanuel Lampe 2018                                       
 *    Erstellt: 22.05.2018 / 18:18                           
 *
 *    Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.                    
 *    Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,       
 *    bei Emmanuel Lampe. Alle Rechte vorbehalten.                      
 *
 *    Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,        
 *    öffentlichen Zugänglichmachung oder andere Nutzung           
 *    bedarf der ausdrücklichen, schriftlichen Zustimmung von Emmanuel Lampe.  
 ******************************************************************************************/

public final class LeetKit implements Kit {
    @Override
    public String getKitname() {
        return "Leet";
    }

    @Override
    public ItemStack getDisplayItem() {
        return new ItemBuilder(Material.DRAGON_EGG, 1, 0).setDisplayName("§8» §31337").build();
    }

    @Override
    public void onEquip(Player player) {
        final PlayerInventory inventory = player.getInventory();
        inventory.setHelmet(new ItemStack(Material.DIAMOND_CHESTPLATE));
        inventory.setChestplate(new ItemStack(Material.DIAMOND_CHESTPLATE));
        inventory.setLeggings(new ItemStack(Material.DIAMOND_CHESTPLATE));
        inventory.setBoots(new ItemStack(Material.DIAMOND_CHESTPLATE));
        inventory.setBoots(new ItemStack(Material.CARROT_ITEM));
    }
}
