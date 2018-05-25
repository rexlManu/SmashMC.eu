/*
 * © Copyright - Emmanuel Lampe aka. rexlManu 2018.
 */
package eu.smashmc.smashpvp;

import com.mongodb.client.MongoCollection;
import eu.smashmc.smashpvp.commands.SetupCommand;
import eu.smashmc.smashpvp.data.Data;
import eu.smashmc.smashpvp.gamestate.GameManager;
import eu.smashmc.smashpvp.listener.impl.*;
import lombok.Getter;
import net.smashmc.api.database.Credential;
import net.smashmc.api.database.DatabaseConnector;
import org.bson.Document;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.craftbukkit.v1_8_R3.CraftServer;
import org.bukkit.plugin.java.JavaPlugin;

/******************************************************************************************
 *    Urheberrechtshinweis                                                       
 *    Copyright © Emmanuel Lampe 2018                                       
 *    Erstellt: 22.05.2018 / 15:04                           
 *
 *    Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.                    
 *    Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,       
 *    bei Emmanuel Lampe. Alle Rechte vorbehalten.                      
 *
 *    Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,        
 *    öffentlichen Zugänglichmachung oder andere Nutzung           
 *    bedarf der ausdrücklichen, schriftlichen Zustimmung von Emmanuel Lampe.  
 ******************************************************************************************/

public final class SmashPvP extends JavaPlugin {

    @Getter
    private static SmashPvP instance;

    @Getter
    private Data data;

    @Getter
    private DatabaseConnector connector;

    @Getter
    private MongoCollection<Document> kits;

    @Override
    public void onEnable() {
        instance = this;
        this.data = new Data(this);
        this.data.getGameManager().changeGameState(this.data.getGameManager().getGameStates().get(GameManager.LOBBY));

//        this.connector = new DatabaseConnector();
//        this.connector.openConnection(new Credential("", 27017, "smashPVP"));
//        this.connector.registerCollection("kits");
//
//        this.kits = this.connector.getCollection("kits");

        new PlayerDeathListener(this);
        new PlayerQuitListener(this);
        new PlayerJoinListener(this);
        new PreventGriefingListener(this);
        new PlayerInteractListener(this);
        new EntityDamageListener(this);
        new DoubleJumpListener(this);
        new KnockbackListener(this);

        registerCommand(new SetupCommand());
    }

    private void registerCommand(Command command) {
        ((CraftServer) Bukkit.getServer()).getCommandMap().register("smashpvp", command);
    }

}
