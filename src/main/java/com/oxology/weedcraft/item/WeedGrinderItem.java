package com.oxology.weedcraft.item;

import com.oxology.weedcraft.WeedCraft;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.Random;

public class WeedGrinderItem extends Item {
    public WeedGrinderItem() {
        super(new Item.Properties().defaultDurability(250).tab(WeedCraft.creativeTab));
    }

    @Override
    public ItemStack getContainerItem(ItemStack itemStack) {
        ItemStack container = itemStack.copy();
        if(container.hurt(1, new Random(), null))
            return ItemStack.EMPTY;
        else
            return container;
    }

    @Override
    public boolean hasContainerItem(ItemStack stack) {
        return true;
    }
}
