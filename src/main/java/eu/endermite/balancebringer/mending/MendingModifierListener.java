package eu.endermite.balancebringer.mending;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemMendEvent;

import java.util.Random;

public class MendingModifierListener implements Listener {

    private final double mod;
    private final Random random = new Random();

    public MendingModifierListener(double mod) {
        this.mod = mod;
    }

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onMending(PlayerItemMendEvent event) {
        double value = random.nextDouble();
        if (value >= mod) event.setCancelled(true);
    }
}
