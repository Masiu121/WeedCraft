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

    public static final RegistryObject<Block> WEED_FLOWER = BLOCKS.register("weed_crop", () -> new WeedCropBlock(AbstractBlock.Properties.copy(Blocks.WHEAT).noOcclusion().noCollission()));
}
