package eu.endermite.balancebringer;

import eu.endermite.balancebringer.mending.ItemFixListener;
import eu.endermite.balancebringer.mending.MendingDenierListener;
import eu.endermite.balancebringer.villagers.VillagerDenierListener;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public final class BalanceBringer extends JavaPlugin {

    public static BalanceBringer plugin;
    private static ConfigCache configCache;

    @Override
    public void onEnable() {
        plugin = this;
        configCache = new ConfigCache();

        getServer().getPluginManager().registerEvents(new VillagerDenierListener(), this);
        getServer().getPluginManager().registerEvents(new MendingDenierListener(), this);
        getServer().getPluginManager().registerEvents(new ItemFixListener(), this);
        BBCommand command = new BBCommand();
        getCommand("balancebringer").setExecutor(command);
        getCommand("balancebringer").setTabCompleter(command);

    }

    public void reloadConfigCache(CommandSender sender) {
        Bukkit.getScheduler().runTaskAsynchronously(this, () -> {
            configCache = new ConfigCache();
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&f"+plugin.getDescription().getName()+"&7] &fConfiguration reloaded"));
        });

    }

    public static ConfigCache getConfigCache() {
        return configCache;
    }
}
