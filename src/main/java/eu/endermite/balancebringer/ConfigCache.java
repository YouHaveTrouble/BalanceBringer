package eu.endermite.balancebringer;

import eu.endermite.balancebringer.mending.MendingDenierListener;
import eu.endermite.balancebringer.villagers.VillageGenerationListener;
import eu.endermite.balancebringer.villagers.VillagerActiveRemoverListener;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.HandlerList;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

public class ConfigCache {

    private final FileConfiguration config;
    private final BalanceBringer plugin;
    public final boolean debug, villagerActivelyRemove, mendingActivelyRemove, villagesBlockGeneration;
    public final HashSet<UUID> villagerWorldBlacklist = new HashSet<>();

    public ConfigCache() {
        plugin = BalanceBringer.plugin;
        plugin.saveDefaultConfig();
        plugin.reloadConfig();
        config = plugin.getConfig();

        HandlerList.unregisterAll(plugin);

        debug = config.getBoolean("debug", false);

        villagerActivelyRemove = getBoolean("villagers.actively-remove", false);
        if (villagerActivelyRemove)
            plugin.getServer().getPluginManager().registerEvents(new VillagerActiveRemoverListener(), plugin);

        villagesBlockGeneration = getBoolean("villagers.generate-villages", true);
        if (villagesBlockGeneration)
            plugin.getServer().getPluginManager().registerEvents(new VillageGenerationListener(), plugin);

        mendingActivelyRemove = getBoolean("mending.actively-remove", false);
        if (mendingActivelyRemove)
            plugin.getServer().getPluginManager().registerEvents(new MendingDenierListener(), plugin);

        // If none of the villager features are on don't populate the ignored worlds list
        if (villagerActivelyRemove || villagesBlockGeneration) {
            for (String w : getStringList("villagers.ignored-worlds", new ArrayList<>())) {
                World world = Bukkit.getWorld(w);
                if (world != null)
                    villagerWorldBlacklist.add(world.getUID());
            }
        }

    }

    private boolean getBoolean(String path, boolean fallback) {
        config.addDefault(path, fallback);
        plugin.saveConfig();
        return config.getBoolean(path, fallback);
    }

    private int getInt(String path, int fallback) {
        config.addDefault(path, fallback);
        plugin.saveConfig();
        return config.getInt(path, fallback);
    }

    private List<String> getStringList(String path, List<String> fallback) {
        config.addDefault(path, fallback);
        plugin.saveConfig();
        return config.getStringList(path);
    }

}
