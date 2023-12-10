package com.oxology.weedcraft.item;

import com.oxology.weedcraft.WeedCraft;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeType;

import javax.annotation.Nullable;

public class WeedJointItem extends Item {
    public WeedJointItem() {
        super(new Properties().tab(WeedCraft.creativeTab));
    }

    @Override
    public int getBurnTime(ItemStack itemStack, @Nullable IRecipeType<?> recipeType) {
        return 25;
    }
}
