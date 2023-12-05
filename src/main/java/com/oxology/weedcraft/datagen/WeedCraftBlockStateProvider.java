package com.oxology.weedcraft.datagen;

import com.oxology.weedcraft.WeedCraft;
import com.oxology.weedcraft.block.WeedCraftBlocks;
import com.oxology.weedcraft.block.WeedCropBlock;
import net.minecraft.block.BlockState;
import net.minecraft.data.DataGenerator;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.function.Function;

public class WeedCraftBlockStateProvider extends BlockStateProvider {
    public WeedCraftBlockStateProvider(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, WeedCraft.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        makeCornCrop(((WeedCropBlock) WeedCraftBlocks.WEED_CROP.get()), "weed_crop_", "weed_crop_");
    }

    public void makeCornCrop(WeedCropBlock block, String modelName, String textureName) {
        Function<BlockState, ConfiguredModel[]> function = state -> cornStates(state, block, modelName, textureName);

        getVariantBuilder(block).forAllStates(function);
    }

    private ConfiguredModel[] cornStates(BlockState state, WeedCropBlock block, String modelName, String textureName) {
        ConfiguredModel[] models = new ConfiguredModel[1];
        models[0] = new ConfiguredModel(models().crop(modelName + state.getValue(((WeedCropBlock) block).getAgeProperty()),
                new ResourceLocation(WeedCraft.MOD_ID, "block/" + textureName + state.getValue(((WeedCropBlock) block).getAgeProperty()))));

        return models;
    }
}
