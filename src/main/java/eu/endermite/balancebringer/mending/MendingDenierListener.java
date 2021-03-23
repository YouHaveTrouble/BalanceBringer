package eu.endermite.balancebringer.mending;

import eu.endermite.balancebringer.BalanceBringer;
import org.bukkit.Bukkit;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;

public class MendingDenierListener implements Listener {

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onMendingItemPickup(org.bukkit.event.entity.EntityPickupItemEvent event) {
        if (!BalanceBringer.getConfigCache().mending_actively_remove)
            return;
        replaceMendingOnItem(event.getItem().getItemStack());
    }

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onInvOpen(org.bukkit.event.inventory.InventoryOpenEvent event) {
        if (!BalanceBringer.getConfigCache().mending_actively_remove)
            return;

        for (ItemStack itemStack : event.getInventory().getContents()) {
            replaceMendingOnItem(itemStack);
        }
    }

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onLogin(org.bukkit.event.player.PlayerJoinEvent event) {
        if (!BalanceBringer.getConfigCache().mending_actively_remove)
            return;
        Bukkit.getScheduler().runTaskAsynchronously(BalanceBringer.plugin, () -> {
            if (!event.getPlayer().isOnline())
                return;
            for (ItemStack itemStack : event.getPlayer().getInventory()) {
                replaceMendingOnItem(itemStack);
            }
        });
    }

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onInvClick(org.bukkit.event.inventory.InventoryClickEvent event) {
        if (!BalanceBringer.getConfigCache().mending_actively_remove)
            return;
        replaceMendingOnItem(event.getCurrentItem());
        replaceMendingOnItem(event.getCursor());
    }


    private void replaceMendingOnItem(ItemStack itemStack) {
        if (itemStack == null)
            return;
        if (itemStack.containsEnchantment(Enchantment.MENDING)) {
            itemStack.removeEnchantment(Enchantment.MENDING);
            if (itemStack.containsEnchantment(Enchantment.DURABILITY))
                itemStack.removeEnchantment(Enchantment.DURABILITY);
            itemStack.addEnchantment(Enchantment.DURABILITY, 3);
            return;
        }
        if (!itemStack.hasItemMeta())
            return;
        try {
            EnchantmentStorageMeta enchstorage = (EnchantmentStorageMeta) itemStack.getItemMeta();
            if (!enchstorage.hasStoredEnchant(Enchantment.MENDING))
                return;
            enchstorage.removeStoredEnchant(Enchantment.MENDING);
            enchstorage.removeStoredEnchant(Enchantment.DURABILITY);
            enchstorage.addStoredEnchant(Enchantment.DURABILITY, 3, false);
            itemStack.setItemMeta(enchstorage);
        } catch (Exception ignored) {}
    }

}
