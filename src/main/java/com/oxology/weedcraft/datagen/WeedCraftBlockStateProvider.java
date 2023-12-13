package com.oxology.weedcraft.datagen;

import com.oxology.weedcraft.WeedCraft;
import com.oxology.weedcraft.block.WeedLightBlock;
import com.oxology.weedcraft.util.WeedVariant;
import com.oxology.weedcraft.block.WeedCraftBlocks;
import com.oxology.weedcraft.block.WeedHookBlock;
import com.oxology.weedcraft.block.WeedPotBlock;
import net.minecraft.data.DataGenerator;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.BlockModelBuilder;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.common.data.ExistingFileHelper;

public class WeedCraftBlockStateProvider extends BlockStateProvider {
    public WeedCraftBlockStateProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, WeedCraft.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        potBlock((WeedPotBlock) WeedCraftBlocks.POT.get());
        hookBlock((WeedHookBlock) WeedCraftBlocks.HOOK.get());
    }

    public void potBlock(WeedPotBlock block) {
        getVariantBuilder(block).forAllStates(state -> {
            BlockModelBuilder modelBuilder;

            if(state.getValue(WeedPotBlock.VARIANT) == WeedVariant.NONE) {
                modelBuilder = models().withExistingParent("pot_empty", new ResourceLocation(WeedCraft.MOD_ID, "block/pot"));
                return new ConfiguredModel[] { new ConfiguredModel(modelBuilder) };
            }

            String modelName = "pot_" + state.getValue(WeedPotBlock.VARIANT) + "_" + state.getValue(WeedPotBlock.AGE);
            modelBuilder = models().withExistingParent(modelName, new ResourceLocation(WeedCraft.MOD_ID, "block/pot"));

            String textureName = state.getValue(WeedPotBlock.VARIANT) + "_" + state.getValue(WeedPotBlock.AGE);
            modelBuilder.texture("crop_bottom", new ResourceLocation(WeedCraft.MOD_ID, "block/" + state.getValue(WeedPotBlock.VARIANT) + "/" + textureName));
            if(state.getValue(WeedPotBlock.AGE) >= 3) {
                textureName = state.getValue(WeedPotBlock.VARIANT) + "_" + (state.getValue(WeedPotBlock.AGE) + 5);
                modelBuilder.texture("crop_top", new ResourceLocation(WeedCraft.MOD_ID, "block/" + state.getValue(WeedPotBlock.VARIANT) + "/" + textureName));
            }

            return new ConfiguredModel[] { new ConfiguredModel(modelBuilder) };
        });
    }

    public void hookBlock(WeedHookBlock block) {
        getVariantBuilder(block).forAllStates(state -> {
            BlockModelBuilder modelBuilder;

            if(state.getValue(WeedPotBlock.VARIANT) == WeedVariant.NONE) {
                modelBuilder = models().withExistingParent("hook_empty", new ResourceLocation(WeedCraft.MOD_ID, "block/hook"));
                return new ConfiguredModel[] { new ConfiguredModel(modelBuilder) };
            }

            String modelName = "hook_" + state.getValue(WeedPotBlock.VARIANT);
            modelBuilder = models().withExistingParent(modelName, new ResourceLocation(WeedCraft.MOD_ID, "block/hook"));

            String textureName = state.getValue(WeedPotBlock.VARIANT) + "_7";
            modelBuilder.texture("crop_bottom", new ResourceLocation(WeedCraft.MOD_ID, "block/" + state.getValue(WeedPotBlock.VARIANT) + "/" + textureName));
            textureName = state.getValue(WeedPotBlock.VARIANT) + "_12";
            modelBuilder.texture("crop_top", new ResourceLocation(WeedCraft.MOD_ID, "block/" + state.getValue(WeedPotBlock.VARIANT) + "/" + textureName));

            return new ConfiguredModel[] { new ConfiguredModel(modelBuilder) };
        });
    }
}
