package com.oxology.weedcraft.item;

import com.oxology.weedcraft.WeedCraft;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class WeedCraftItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, WeedCraft.MOD_ID);

    public static final RegistryObject<Item> WEED_FLOWER = ITEMS.register("weed_flower", WeedFlowerItem::new);
    public static final RegistryObject<Item> WEED_LEAF = ITEMS.register("weed_leaf", WeedLeafItem::new);
    public static final RegistryObject<Item> WEED_FLOWER_DRIED = ITEMS.register("weed_flower_dried", WeedFlowerDriedItem::new);
    public static final RegistryObject<Item> WEED_LEAF_DRIED = ITEMS.register("weed_leaf_dried", WeedLeafDriedItem::new);
    public static final RegistryObject<Item> WEED_JOINT = ITEMS.register("weed_joint", WeedJointItem::new);
    public static final RegistryObject<Item> WEED_SEEDS = ITEMS.register("weed_seeds", WeedSeedsItem::new);
    public static final RegistryObject<Item> WEED_SKIN = ITEMS.register("weed_skin", WeedSkinItem::new);
}
