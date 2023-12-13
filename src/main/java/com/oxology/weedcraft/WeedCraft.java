package com.oxology.weedcraft;

import com.oxology.weedcraft.block.WeedCraftBlocks;
import com.oxology.weedcraft.block.WeedHookBlock;
import com.oxology.weedcraft.block.WeedPotBlock;
import com.oxology.weedcraft.item.WeedCraftItems;
import com.oxology.weedcraft.util.WeedCraftCreativeTab;
import com.oxology.weedcraft.util.WeedVariant;
import net.minecraft.block.ComposterBlock;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;

import java.util.Random;

@Mod(WeedCraft.MOD_ID)
public class WeedCraft {
    public static final String MOD_ID = "weedcraft";
    public static final WeedCraftCreativeTab creativeTab = new WeedCraftCreativeTab(ItemGroup.TABS.length, "WeedCraft");

    public WeedCraft() {
        WeedCraftItems.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        WeedCraftBlocks.BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

        MinecraftForge.EVENT_BUS.register(WeedHookBlock.class);
        MinecraftForge.EVENT_BUS.register(WeedPotBlock.class);
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event)
    {
        ComposterBlock.COMPOSTABLES.put(WeedCraftItems.PURPLE_HAZE_SEEDS.get(), 0.5f);
        ComposterBlock.COMPOSTABLES.put(WeedCraftItems.PURPLE_HAZE_FLOWER.get(), 0.5f);
        ComposterBlock.COMPOSTABLES.put(WeedCraftItems.PURPLE_HAZE_LEAF.get(), 0.5f);
        ComposterBlock.COMPOSTABLES.put(WeedCraftItems.PURPLE_HAZE_PLANT.get(), 0.75f);
        ComposterBlock.COMPOSTABLES.put(WeedCraftItems.PURPLE_HAZE_GRINDED.get(), 0.25f);

        ComposterBlock.COMPOSTABLES.put(WeedCraftItems.LEMON_HAZE_SEEDS.get(), 0.5f);
        ComposterBlock.COMPOSTABLES.put(WeedCraftItems.LEMON_HAZE_FLOWER.get(), 0.5f);
        ComposterBlock.COMPOSTABLES.put(WeedCraftItems.LEMON_HAZE_LEAF.get(), 0.5f);
        ComposterBlock.COMPOSTABLES.put(WeedCraftItems.LEMON_HAZE_PLANT.get(), 0.75f);
        ComposterBlock.COMPOSTABLES.put(WeedCraftItems.LEMON_HAZE_GRINDED.get(), 0.25f);

        ComposterBlock.COMPOSTABLES.put(WeedCraftItems.AMNESIA_HAZE_SEEDS.get(), 0.5f);
        ComposterBlock.COMPOSTABLES.put(WeedCraftItems.AMNESIA_HAZE_FLOWER.get(), 0.5f);
        ComposterBlock.COMPOSTABLES.put(WeedCraftItems.AMNESIA_HAZE_LEAF.get(), 0.5f);
        ComposterBlock.COMPOSTABLES.put(WeedCraftItems.AMNESIA_HAZE_PLANT.get(), 0.75f);
        ComposterBlock.COMPOSTABLES.put(WeedCraftItems.AMNESIA_HAZE_GRINDED.get(), 0.25f);
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        RenderTypeLookup.setRenderLayer(WeedCraftBlocks.HOOK.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(WeedCraftBlocks.POT.get(), RenderType.cutout());
    }
}
