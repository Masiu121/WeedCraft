package com.oxology.weedcraft;

import com.oxology.weedcraft.item.WeedCraftItems;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class WeedcraftCreativeTab extends ItemGroup {
    public WeedcraftCreativeTab(int index, String label) {
        super(index, label);
    }

    @Override
    public ItemStack makeIcon() {
        return new ItemStack(WeedCraftItems.WEED_LEAF.get());
    }
}
