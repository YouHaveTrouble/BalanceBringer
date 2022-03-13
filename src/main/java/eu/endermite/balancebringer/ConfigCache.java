package eu.endermite.balancebringer;

import eu.endermite.balancebringer.mending.MendingDenierListener;
import eu.endermite.balancebringer.mending.MendingModifierListener;
import eu.endermite.balancebringer.piglins.PiglinTradeRemover;
import eu.endermite.balancebringer.villagers.MendingTradeRemoverListener;
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
    public final boolean debug;
    public final HashSet<UUID> villagerWorldBlacklist = new HashSet<>();

    public ConfigCache() {
        plugin = BalanceBringer.plugin;
        plugin.saveDefaultConfig();
        plugin.reloadConfig();
        config = plugin.getConfig();

        HandlerList.unregisterAll(plugin);

        debug = config.getBoolean("debug", false);

        boolean villagerActivelyRemove = getBoolean("villagers.actively-remove", false);
        if (villagerActivelyRemove)
            plugin.getServer().getPluginManager().registerEvents(new VillagerActiveRemoverListener(), plugin);

        boolean villagesBlockGeneration = getBoolean("villagers.generate-villages", true);
        try {
            Class.forName("org.purpurmc.purpur.event.world.StructureGenerateEvent");
            if (villagesBlockGeneration)
                plugin.getServer().getPluginManager().registerEvents(new VillageGenerationListener(), plugin);
        } catch (ClassNotFoundException exception) {
            plugin.getLogger().warning("Cannot prevent village generation. org.purpurmc.purpur.event.world.StructureGenerateEvent doesn't exist!");
        }

        boolean removeMendingTrade = getBoolean("villagers.remove-mending-trade", false);
        if (removeMendingTrade)
            plugin.getServer().getPluginManager().registerEvents(new MendingTradeRemoverListener(), plugin);

        // If none of the villager features are on don't populate the ignored worlds list
        if (villagerActivelyRemove || villagesBlockGeneration || removeMendingTrade) {
            for (String w : getStringList("villagers.ignored-worlds", new ArrayList<>())) {
                World world = Bukkit.getWorld(w);
                if (world != null) villagerWorldBlacklist.add(world.getUID());
            }
        }

        boolean disablePiglinBarters = getBoolean("piglins.disable-bartering", false);
        if (disablePiglinBarters)
            plugin.getServer().getPluginManager().registerEvents(new PiglinTradeRemover(), plugin);


        boolean mendingActivelyRemove = getBoolean("mending.actively-remove", false);
        if (mendingActivelyRemove)
            plugin.getServer().getPluginManager().registerEvents(new MendingDenierListener(), plugin);

        double mendingMod = getDouble("mending.modifier", 1.0);
        if (mendingMod < 1.0) {
            mendingMod = Math.abs(mendingMod);
            plugin.getServer().getPluginManager().registerEvents(new MendingModifierListener(mendingMod), plugin);
        }
    }

    private boolean getBoolean(String path, boolean fallback) {
        config.addDefault(path, fallback);
        plugin.saveConfig();
        return config.getBoolean(path, fallback);
    }

    private double getDouble(String path, double fallback) {
        config.addDefault(path, fallback);
        plugin.saveConfig();
        return config.getDouble(path, fallback);
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
