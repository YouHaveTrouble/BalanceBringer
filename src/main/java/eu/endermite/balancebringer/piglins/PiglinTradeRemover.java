package eu.endermite.balancebringer.piglins;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PiglinBarterEvent;

public class PiglinTradeRemover implements Listener {

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onPiglinBarter(PiglinBarterEvent event) {
        event.setCancelled(true);
    }

}
