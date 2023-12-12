package com.oxology.weedcraft.util;

import com.oxology.weedcraft.item.WeedCraftItems;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class WeedCraftCreativeTab extends ItemGroup {
    public WeedCraftCreativeTab(int index, String label) {
        super(index, label);
    }

    @Override
    public ItemStack makeIcon() {
        return new ItemStack(WeedCraftItems.LEMON_HAZE_LEAF.get());
    }
}
