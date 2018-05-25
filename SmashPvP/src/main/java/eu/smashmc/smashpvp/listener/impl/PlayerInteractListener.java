/*
 * © Copyright - Emmanuel Lampe aka. rexlManu 2018.
 */
package eu.smashmc.smashpvp.listener.impl;

import eu.smashmc.smashpvp.SmashPvP;
import eu.smashmc.smashpvp.game.PlayerHandler;
import eu.smashmc.smashpvp.kit.Kit;
import eu.smashmc.smashpvp.listener.Listener;
import net.smashmc.api.builder.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

/******************************************************************************************
 *    Urheberrechtshinweis                                                       
 *    Copyright © Emmanuel Lampe 2018                                       
 *    Erstellt: 22.05.2018 / 20:09                           
 *
 *    Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.                    
 *    Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,       
 *    bei Emmanuel Lampe. Alle Rechte vorbehalten.                      
 *
 *    Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,        
 *    öffentlichen Zugänglichmachung oder andere Nutzung           
 *    bedarf der ausdrücklichen, schriftlichen Zustimmung von Emmanuel Lampe.  
 ******************************************************************************************/

public final class PlayerInteractListener extends Listener {

    public PlayerInteractListener(JavaPlugin javaPlugin) {
        super(javaPlugin);
    }

    @EventHandler
    public void onPlayerInteractEvent(PlayerInteractEvent event) {
        final Player player = event.getPlayer();
        if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {

            if (event.getItem() == null) return;
            if (!event.getItem().hasItemMeta()) return;
            final String displayName = event.getItem().getItemMeta().getDisplayName();
            if (displayName.equalsIgnoreCase("§8» §aKitauswahl §8× §7Rechtsklick")) {
                event.setCancelled(true);
                openKitSelectory(player);
            } else if (displayName.equalsIgnoreCase("§8» §aVerlassen §8× §7Rechtsklick")) {
                event.setCancelled(true);
                player.playSound(player.getLocation(), Sound.BAT_DEATH, 1F, 2F);
                player.kickPlayer("");
            } else if (displayName.equalsIgnoreCase("§8» §aAchievement §8× §7Rechtsklick")) {
                event.setCancelled(true);
            }

        }
    }

    @EventHandler
    public void on(InventoryClickEvent event) {
        final Player player = (Player) event.getWhoClicked();
        if (event.getClickedInventory() == null) return;
        if (event.getClickedInventory().getTitle() == null) return;
        if (event.getCurrentItem() == null) return;
        if (!event.getCurrentItem().hasItemMeta()) return;

        final PlayerHandler handler = SmashPvP.getInstance().getData().getPlayerManager().getHandler(player);
        if (event.getClickedInventory().getTitle().equalsIgnoreCase("§8» §aKitauswahl")) {
            event.setCancelled(true);
            final String displayName = event.getCurrentItem().getItemMeta().getDisplayName();
            for (Kit kit : SmashPvP.getInstance().getData().getKitManager().getKits()) {
                if (kit.getDisplayItem().getItemMeta().getDisplayName().equalsIgnoreCase(displayName)) {
                    if (handler.getOwnedKits().contains(kit.getKitname())) {
                        handler.setSelectedKit(kit);
                        handler.sendLobbyScoreboard();
                        player.playSound(player.getLocation(), Sound.LEVEL_UP, 1F, 3F);
                        player.closeInventory();
                    } else {
                        player.sendMessage("§8» §aSmashPvP §8× §7Du besitzt das Kit §a" + kit.getKitname() + " §7nicht.");
                        player.playSound(player.getLocation(), Sound.ANVIL_BREAK, 1F, 3F);
                        player.closeInventory();
                    }
                }
            }
        }
    }

    private void openKitSelectory(Player player) {
        player.playSound(player.getLocation(), Sound.CHEST_OPEN, 1F, 3F);
        final Inventory inventory = Bukkit.createInventory(null, 3 * 9, "§8» §aKitauswahl");
        final ItemStack glassItem = new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 15).setDisplayName("§r").build();
        for (int i = 0; i < 9; i++) {
            inventory.setItem(i, glassItem);
            inventory.setItem(i + 9 + 9, glassItem);
        }
        inventory.setItem(9, glassItem);
        inventory.setItem(17, glassItem);
        for (Kit kit : SmashPvP.getInstance().getData().getKitManager().getKits()) {
            inventory.addItem(kit.getDisplayItem());
        }
        player.openInventory(inventory);
    }
}
