package com.oxology.weedcraft.item;

import com.oxology.weedcraft.WeedCraft;
import com.oxology.weedcraft.util.WeedVariant;
import com.oxology.weedcraft.block.WeedCraftBlocks;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class WeedCraftItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, WeedCraft.MOD_ID);

    public static final RegistryObject<Item> WEED_JOINT = ITEMS.register("weed_joint", WeedJointItem::new);
    public static final RegistryObject<Item> WEED_SKIN = ITEMS.register("weed_skin", WeedSkinItem::new);

    public static final RegistryObject<Item> PURPLE_HAZE_SEEDS = ITEMS.register("seeds_purple_haze", () -> new WeedSeedsItem(WeedVariant.PURPLE_HAZE));
    public static final RegistryObject<Item> PURPLE_HAZE_LEAF = ITEMS.register("leaf_purple_haze", () -> new WeedLeafItem(WeedVariant.PURPLE_HAZE));
    public static final RegistryObject<Item> PURPLE_HAZE_FLOWER = ITEMS.register("flower_purple_haze", () -> new WeedFlowerItem(WeedVariant.PURPLE_HAZE));
    public static final RegistryObject<Item> PURPLE_HAZE_PLANT = ITEMS.register("plant_purple_haze", () -> new WeedPlantItem(WeedVariant.PURPLE_HAZE));
    public static final RegistryObject<Item> PURPLE_HAZE_GRINDED = ITEMS.register("grinded_purple_haze", () -> new WeedGrindedItem(WeedVariant.PURPLE_HAZE));

    public static final RegistryObject<Item> LEMON_HAZE_SEEDS = ITEMS.register("seeds_lemon_haze", () -> new WeedSeedsItem(WeedVariant.LEMON_HAZE));
    public static final RegistryObject<Item> LEMON_HAZE_LEAF = ITEMS.register("leaf_lemon_haze", () -> new WeedLeafItem(WeedVariant.LEMON_HAZE));
    public static final RegistryObject<Item> LEMON_HAZE_FLOWER = ITEMS.register("flower_lemon_haze", () -> new WeedFlowerItem(WeedVariant.LEMON_HAZE));
    public static final RegistryObject<Item> LEMON_HAZE_PLANT = ITEMS.register("plant_lemon_haze", () -> new WeedPlantItem(WeedVariant.LEMON_HAZE));
    public static final RegistryObject<Item> LEMON_HAZE_GRINDED = ITEMS.register("grinded_lemon_haze", () -> new WeedGrindedItem(WeedVariant.LEMON_HAZE));

    public static final RegistryObject<Item> GRINDER = ITEMS.register("grinder", WeedGrinderItem::new);
    public static final RegistryObject<Item> HOOK = ITEMS.register("hook", () -> new WeedHookItem(WeedCraftBlocks.HOOK.get()));
    public static final RegistryObject<Item> POT = ITEMS.register("pot", () -> new WeedPotItem(WeedCraftBlocks.POT.get()));
    public static final RegistryObject<Item> LIGHT = ITEMS.register("light", () -> new WeedLightItem(WeedCraftBlocks.LIGHT.get()));

    public static RegistryObject<Item> getSeedsByVariant(WeedVariant weedVariant) {
        for(RegistryObject<Item> item : ITEMS.getEntries()) {
            if(item.get() instanceof WeedSeedsItem && ((WeedSeedsItem) item.get()).getWeedVariant() == weedVariant) return item;
        }

        return null;
    }

    public static RegistryObject<Item> getFlowerByVariant(WeedVariant weedVariant) {
        for(RegistryObject<Item> item : ITEMS.getEntries()) {
            if(item.get() instanceof WeedFlowerItem && ((WeedFlowerItem) item.get()).getWeedVariant() == weedVariant) return item;
        }

        return null;
    }

    public static RegistryObject<Item> getLeafByVariant(WeedVariant weedVariant) {
        for(RegistryObject<Item> item : ITEMS.getEntries()) {
            if(item.get() instanceof WeedLeafItem && ((WeedLeafItem) item.get()).getWeedVariant() == weedVariant) return item;
        }

        return null;
    }

    public static RegistryObject<Item> getPlantByVariant(WeedVariant weedVariant) {
        for(RegistryObject<Item> item : ITEMS.getEntries()) {
            if(item.get() instanceof WeedPlantItem && ((WeedPlantItem) item.get()).getWeedVariant() == weedVariant) return item;
        }

        return null;
    }
}
