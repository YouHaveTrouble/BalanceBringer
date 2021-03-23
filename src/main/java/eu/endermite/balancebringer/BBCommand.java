package eu.endermite.balancebringer;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import java.util.Collections;
import java.util.List;

public class BBCommand implements TabExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (args.length > 0 && args[0].equalsIgnoreCase("reload") && sender.hasPermission("balancebringer.reload")) {
            BalanceBringer.plugin.reloadConfigCache(sender);
            return true;
        } else if (BalanceBringer.getConfigCache().debug && args.length > 0 && args[0].equalsIgnoreCase("debug") && sender.hasPermission("balancebringer.debug")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                player.getInventory().getItemInMainHand().setDamage(1560);
            }
            return true;
        }

        sender.sendMessage("BalanceBringer "+BalanceBringer.plugin.getDescription().getVersion());
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {

        if (args.length > 0 && "reload".startsWith(args[0]) && sender.hasPermission("balancebringer.reload")) {
            return Collections.singletonList("reload");
        }

        return null;
    }
}
