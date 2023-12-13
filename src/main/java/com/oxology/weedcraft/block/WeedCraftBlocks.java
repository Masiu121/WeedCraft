package com.oxology.weedcraft.block;

import com.oxology.weedcraft.WeedCraft;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class WeedCraftBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, WeedCraft.MOD_ID);

    public static final RegistryObject<Block> HOOK = BLOCKS.register("hook", () -> new WeedHookBlock(AbstractBlock.Properties.copy(Blocks.CHAIN).noOcclusion().noCollission()));
    public static final RegistryObject<Block> POT = BLOCKS.register("pot", () -> new WeedPotBlock(AbstractBlock.Properties.copy(Blocks.FLOWER_POT).noOcclusion()));
    public static final RegistryObject<Block> LIGHT = BLOCKS.register("light", () -> new WeedLightBlock(AbstractBlock.Properties.copy(Blocks.REDSTONE_LAMP).noOcclusion()));
}
