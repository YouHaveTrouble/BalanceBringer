package eu.endermite.balancebringer.villagers;

import eu.endermite.balancebringer.BalanceBringer;
import net.pl3x.purpur.event.world.StructureGenerateEvent;
import org.bukkit.StructureType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

public class VillageGenerationListener implements Listener {

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onVillageGenerated(StructureGenerateEvent event) {
        if (BalanceBringer.getConfigCache().villagerWorldBlacklist.contains(event.getWorld().getUID())) return;
        if (event.getStructureType() != StructureType.VILLAGE) return;
        event.setCancelled(true);
    }

}
