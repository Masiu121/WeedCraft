package com.oxology.weedcraft.item;

import com.oxology.weedcraft.WeedCraft;
import com.oxology.weedcraft.block.WeedCraftBlocks;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class WeedCraftItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, WeedCraft.MOD_ID);

    public static final RegistryObject<Item> WEED_FLOWER = ITEMS.register("weed_flower", WeedFlowerItem::new);
    public static final RegistryObject<Item> WEED_LEAF = ITEMS.register("weed_leaf", WeedLeafItem::new);
    public static final RegistryObject<Item> WEED_JOINT = ITEMS.register("weed_joint", WeedJointItem::new);
    public static final RegistryObject<Item> WEED_SKIN = ITEMS.register("weed_skin", WeedSkinItem::new);
    public static final RegistryObject<Item> WEED_PLANT = ITEMS.register("weed_plant", WeedPlantItem::new);
    public static final RegistryObject<Item> WEED_GRINDER = ITEMS.register("weed_grinder", WeedGrinderItem::new);
    public static final RegistryObject<Item> WEED_GRINDED = ITEMS.register("weed_grinded", WeedGrindedItem::new);

    public static final RegistryObject<Item> WEED_SEEDS = ITEMS.register("weed_seeds", () -> new WeedSeedsItem(WeedCraftBlocks.WEED_CROP.get()));
    public static final RegistryObject<Item> WEED_HOOK = ITEMS.register("weed_hook", () -> new WeedHookItem(WeedCraftBlocks.WEED_HOOK.get()));
}
