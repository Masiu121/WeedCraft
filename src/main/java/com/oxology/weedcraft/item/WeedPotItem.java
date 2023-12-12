package com.oxology.weedcraft.item;

import com.oxology.weedcraft.WeedCraft;
import com.oxology.weedcraft.util.WeedVariant;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeType;

import javax.annotation.Nullable;

public class WeedPotItem extends BlockItem {
    public WeedPotItem(Block block) {
        super(block, new Properties().tab(WeedCraft.creativeTab));
    }
}
