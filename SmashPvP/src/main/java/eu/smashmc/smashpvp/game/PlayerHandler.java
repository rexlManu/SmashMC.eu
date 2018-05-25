/*
 * © Copyright - Emmanuel Lampe aka. rexlManu 2018.
 */
package eu.smashmc.smashpvp.game;

import com.google.common.collect.Lists;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import eu.smashmc.smashpvp.SmashPvP;
import eu.smashmc.smashpvp.kit.Kit;
import eu.smashmc.smashpvp.kit.impl.LeetKit;
import lombok.Getter;
import lombok.Setter;
import net.smashmc.api.API;
import net.smashmc.api.builder.ItemBuilder;
import net.smashmc.api.builder.ScoreboardBuilder;
import org.bson.Document;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/******************************************************************************************
 *    Urheberrechtshinweis                                                       
 *    Copyright © Emmanuel Lampe 2018                                       
 *    Erstellt: 22.05.2018 / 16:14                           
 *
 *    Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.                    
 *    Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,       
 *    bei Emmanuel Lampe. Alle Rechte vorbehalten.                      
 *
 *    Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,        
 *    öffentlichen Zugänglichmachung oder andere Nutzung           
 *    bedarf der ausdrücklichen, schriftlichen Zustimmung von Emmanuel Lampe.  
 ******************************************************************************************/

public final class PlayerHandler {

    private Player player;
    @Getter
    private List<String> ownedKits;
    @Getter
    @Setter
    private Kit selectedKit;

    @Getter
    @Setter
    private float knockback = 10;

    @Getter
    @Setter
    private int lives;

    public PlayerHandler(Player player) {
        this.player = player;
        this.ownedKits = Lists.newArrayList();
        this.lives = 5;
        readPlayer();
    }

    public void sendLobbyScoreboard() {
        Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        final Objective objective = scoreboard.registerNewObjective("lobby", "dummy");
        objective.setDisplayName("§8» §aSmashPvP §8× §7" + "hrnshn");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);

        objective.getScore("§d").setScore(9);
        objective.getScore(" §8⬛ §aDeine Coins").setScore(8);
        objective.getScore("   §8» §7-1337").setScore(7);
        objective.getScore("§c").setScore(6);
        objective.getScore(" §8⬛ §aDein Kit").setScore(5);
        objective.getScore("   §8» §7" + (selectedKit == null ? "Keins" : selectedKit.getKitname())).setScore(4);
        objective.getScore("§b").setScore(3);
        objective.getScore(" §8⬛ §aTeamspeak").setScore(2);
        objective.getScore("   §8» §7ts.SmashMC.eu").setScore(1);
        objective.getScore("§a").setScore(0);

        player.setScoreboard(scoreboard);
    }

    public void teleportToSpawn() {
        teleportToWarp("Spawn");
    }

    public void giveLobbyItems() {
        player.getInventory().setItem(0, new ItemBuilder(Material.CHEST, 1, 0).setDisplayName("§8» §aKitauswahl §8× §7Rechtsklick").build());
        player.getInventory().setItem(4, new ItemBuilder(Material.NETHER_STAR, 1, 0).setDisplayName("§8» §aAchievement §8× §7Rechtsklick").build());
        player.getInventory().setItem(8, new ItemBuilder(Material.SLIME_BALL, 1, 0).setDisplayName("§8» §aVerlassen §8× §7Rechtsklick").build());
    }

    public void teleportToWarp(String key) {
        player.teleport(SmashPvP.getInstance().getData().getLocationManager().getLocation(key));
    }

    public void clearPlayer() {
        player.setHealth(20.0);
        player.setFoodLevel(20);
        player.setLevel(0);
        player.setExp(0.0f);
        player.setFireTicks(0);
        player.getInventory().clear();
        player.getInventory().setArmorContents(null);
        for (final PotionEffect effect : player.getActivePotionEffects()) {
            player.removePotionEffect(effect.getType());
        }
        player.setGameMode(GameMode.ADVENTURE);
    }

    public void createPlayer() {
//        final Document document = new Document("uuid", player.getUniqueId());
//        document.put("kits", Collections.singleton(SmashPvP.getInstance().getData().getKitManager().getKits().get(0).getKitname()));
//        SmashPvP.getInstance().getKits().insertOne(document);
    }

    public void readPlayer() {
//        final MongoCollection<Document> kits = SmashPvP.getInstance().getKits();
//        FindIterable<Document> find = kits.find(Filters.eq("uuid", this.player.getUniqueId().toString()));
//        if (find.first() == null) {
//            createPlayer();
//            find = kits.find(Filters.eq("uuid", this.player.getUniqueId().toString()));
//        }
//        final Document document = find.first();
//        final List<String> ownedKits = document.get("kits", List.class);
//        for (String ownedKit : ownedKits) {
//            for (Kit kit : SmashPvP.getInstance().getData().getKitManager().getKits()) {
//                if (kit.getKitname().equalsIgnoreCase(ownedKit)) {
//                    this.ownedKits.add(kit);
//                }
//            }
//        }
        this.ownedKits.add(new LeetKit().getKitname());
    }

    public void insertKit(Kit kit) {
//        List<String> kits = new ArrayList<>();
//        for (Kit ownedKit : ownedKits) {
//            kits.add(ownedKit.getKitname());
//        }
//        kits.add(kit.getKitname());
//        API.instance.getDatabaseConnector().getCollection("smashpvp").updateOne(new Document("uuid", this.player.getUniqueId()), new Document("$set", new Document("kits", kits)));
    }


    public void sendIngameScoreboard() {
        Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        final Objective objective = scoreboard.registerNewObjective("lobby", "dummy");
        objective.setDisplayName("§8» §aSmashPvP §8× §7" + "hrnshn");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);

        objective.getScore("§d").setScore(9);
        objective.getScore(" §8⬛ §aDeine Coins").setScore(5);
        objective.getScore("   §8» §7-1337").setScore(4);
        objective.getScore("§c").setScore(6);
        objective.getScore(" §8⬛ §aDeine Leben").setScore(8);
        objective.getScore("   §8» §a§7" + lives).setScore(7);
        objective.getScore("§b").setScore(3);
        objective.getScore(" §8⬛ §aTeamspeak").setScore(2);
        objective.getScore("   §8» §7ts.SmashMC.eu").setScore(1);
        objective.getScore("§a").setScore(0);

        player.setScoreboard(scoreboard);
    }

    public void giveSpectatorItems() {
        player.getInventory().setItem(0, new ItemBuilder(Material.PAPER, 1, 0).setDisplayName("§8» §bNächste Runde §8× §7Rechtsklick").build());
        player.getInventory().setItem(3, new ItemBuilder(Material.NETHER_STAR, 1, 0).setDisplayName("§8» §aAchievement §8× §7Rechtsklick").build());
        player.getInventory().setItem(5, new ItemBuilder(Material.COMPASS, 1, 0).setDisplayName("§8» §eSpectator §8× §7Rechtsklick").build());
        player.getInventory().setItem(8, new ItemBuilder(Material.SLIME_BALL, 1, 0).setDisplayName("§8» §aVerlassen §8× §7Rechtsklick").build());
    }

    public void removeLive() {
        lives--;
    }

    public void setSpectator() {
        for (Player all : Bukkit.getOnlinePlayers()) {
            if (SmashPvP.getInstance().getData().getPlayerManager().getAlivePlayers().contains(all)) {
                all.hidePlayer(player);
            } else all.showPlayer(player);
        }

        player.setAllowFlight(true);
        player.setGameMode(GameMode.ADVENTURE);
        player.playSound(player.getLocation(), Sound.CAT_MEOW, 1F, 3F);
    }

    public void addKnockback() {
        this.knockback++;
        player.setExp(knockback);
        player.setLevel((int) knockback);
    }

    public void resetKnockback() {
        knockback = 10;
        player.setExp(knockback);
        player.setLevel((int) knockback);
    }
}
