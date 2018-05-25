/*
 * © Copyright - Emmanuel Lampe aka. rexlManu 2018.
 */
package eu.smashmc.smashpvp.commands;

import eu.smashmc.smashpvp.SmashPvP;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/******************************************************************************************
 *    Urheberrechtshinweis                                                       
 *    Copyright © Emmanuel Lampe 2018                                       
 *    Erstellt: 22.05.2018 / 17:21                           
 *
 *    Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.                    
 *    Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,       
 *    bei Emmanuel Lampe. Alle Rechte vorbehalten.                      
 *
 *    Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,        
 *    öffentlichen Zugänglichmachung oder andere Nutzung           
 *    bedarf der ausdrücklichen, schriftlichen Zustimmung von Emmanuel Lampe.  
 ******************************************************************************************/

public final class SetupCommand extends Command {
    public SetupCommand() {
        super("setup");
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }
        if (!sender.hasPermission("smashpvp.setup")) {
            sender.sendMessage("§8» §aSmashPvP §8× §7Du hast keine Berechtigung dazu.");
            return true;
        }

        final Player player = (Player) sender;
        if (args.length == 2) {
            if (args[0].equalsIgnoreCase("setwarp")) {
                final String wrapName = args[1];
                SmashPvP.getInstance().getData().getLocationManager().saveLocation(wrapName, player.getLocation());
                player.sendMessage("§8» §aSmashPvP §8× §7Du hast die Location §a" + wrapName + "§7 gesetzt.");
            }
        }

        return false;
    }
}
