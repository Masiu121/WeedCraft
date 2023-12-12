package com.oxology.weedcraft.item;

import com.oxology.weedcraft.WeedCraft;
import com.oxology.weedcraft.util.WeedVariant;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeType;

import javax.annotation.Nullable;

public class WeedFlowerItem extends Item {
    private final WeedVariant weedVariant;

    public WeedFlowerItem(WeedVariant weedVariant) {
        super(new Properties().tab(WeedCraft.creativeTab));
        this.weedVariant = weedVariant;
    }

    @Override
    public int getBurnTime(ItemStack itemStack, @Nullable IRecipeType<?> recipeType) {
        return 50;
    }

    public WeedVariant getWeedVariant() {
        return weedVariant;
    }
}
