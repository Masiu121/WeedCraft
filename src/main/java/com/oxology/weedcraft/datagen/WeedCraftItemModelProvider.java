package com.oxology.weedcraft.datagen;

import com.oxology.weedcraft.WeedCraft;
import com.oxology.weedcraft.util.WeedVariant;
import net.minecraft.data.DataGenerator;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class WeedCraftItemModelProvider extends ItemModelProvider {
    public WeedCraftItemModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, WeedCraft.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        for(WeedVariant weedVariant : WeedVariant.values()) {
            if(weedVariant != WeedVariant.NONE) singleTexture("seeds_" + weedVariant.toString(), new ResourceLocation("item/generated"), "layer0", new ResourceLocation(WeedCraft.MOD_ID, "item/seeds"));
        }

        for(WeedVariant weedVariant : WeedVariant.values()) {
            if(weedVariant != WeedVariant.NONE) singleTexture("leaf_" + weedVariant.toString(), new ResourceLocation("item/generated"), "layer0", new ResourceLocation(WeedCraft.MOD_ID, "item/leaf_" + weedVariant));
        }

        for(WeedVariant weedVariant : WeedVariant.values()) {
            if(weedVariant != WeedVariant.NONE) singleTexture("flower_" + weedVariant.toString(), new ResourceLocation("item/generated"), "layer0", new ResourceLocation(WeedCraft.MOD_ID, "item/flower_" + weedVariant));
        }

        for(WeedVariant weedVariant : WeedVariant.values()) {
            if(weedVariant != WeedVariant.NONE) singleTexture("plant_" + weedVariant.toString(), new ResourceLocation("item/generated"), "layer0", new ResourceLocation(WeedCraft.MOD_ID, "item/plant_" + weedVariant));
        }

        for(WeedVariant weedVariant : WeedVariant.values()) {
            if(weedVariant != WeedVariant.NONE) singleTexture("grinded_" + weedVariant.toString(), new ResourceLocation("item/generated"), "layer0", new ResourceLocation(WeedCraft.MOD_ID, "item/grinded_" + weedVariant));
        }

        singleTexture("hook", new ResourceLocation("item/generated"), "layer0", new ResourceLocation(WeedCraft.MOD_ID, "item/hook"));
        singleTexture("grinder", new ResourceLocation("item/generated"), "layer0", new ResourceLocation(WeedCraft.MOD_ID, "item/grinder"));
        withExistingParent("pot", new ResourceLocation(WeedCraft.MOD_ID, "block/pot"));

    }
}
