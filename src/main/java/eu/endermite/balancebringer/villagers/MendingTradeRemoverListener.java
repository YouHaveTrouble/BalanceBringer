package eu.endermite.balancebringer.villagers;

import com.destroystokyo.paper.event.entity.EntityAddToWorldEvent;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Merchant;
import org.bukkit.inventory.MerchantInventory;
import org.bukkit.inventory.MerchantRecipe;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;

import java.util.ArrayList;
import java.util.List;

public class MendingTradeRemoverListener implements Listener {

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onVillagerJoinWorld(EntityAddToWorldEvent event) {
        if (!event.getEntityType().equals(EntityType.VILLAGER)) return;
        removeMendingTrade((Merchant) event.getEntity());
    }

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onVillagerJoinWorld(InventoryOpenEvent event) {
        if (!(event.getInventory() instanceof MerchantInventory merchantInventory)) return;
        removeMendingTrade(merchantInventory.getMerchant());
    }

    private void removeMendingTrade(Merchant merchant) {
        List<MerchantRecipe> trades = new ArrayList<>(merchant.getRecipes());
        trades.removeIf(recipe -> {
            if (!recipe.getResult().getType().equals(Material.ENCHANTED_BOOK)) return false;
            EnchantmentStorageMeta storage = (EnchantmentStorageMeta) recipe.getResult().getItemMeta();
            return storage.hasStoredEnchant(Enchantment.MENDING);
        });
        merchant.setRecipes(trades);
    }

}
