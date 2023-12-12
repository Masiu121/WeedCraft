package com.oxology.weedcraft.block;

import com.oxology.weedcraft.util.WeedVariant;
import com.oxology.weedcraft.item.WeedCraftItems;
import com.oxology.weedcraft.item.WeedPlantItem;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class WeedHookBlock extends Block {
    protected static final VoxelShape SHAPE = Block.box(6.5D, 8.0D, 6.5D, 9.5D, 16.0D, 9.5D);
    public static final EnumProperty<WeedVariant> VARIANT = EnumProperty.create("variant", WeedVariant.class);

    public WeedHookBlock(Properties properties) {
        super(properties);
    }

    public VoxelShape getShape(BlockState state, IBlockReader block, BlockPos pos, ISelectionContext context) {
        return SHAPE;
    }

    @SubscribeEvent
    public static void onRightClick(PlayerInteractEvent.RightClickBlock event) {
        BlockState state = event.getWorld().getBlockState(event.getPos());
        if(state.is(WeedCraftBlocks.HOOK.get())) {
            if(state.getValue(VARIANT) == WeedVariant.NONE) {
                Item item = event.getPlayer().getItemInHand(Hand.MAIN_HAND).getItem();
                if(item instanceof WeedPlantItem) {
                    if(!event.getPlayer().isCreative())
                        event.getPlayer().getItemInHand(Hand.MAIN_HAND).shrink(1);

                    event.getWorld().setBlock(event.getPos(), state.setValue(VARIANT, ((WeedPlantItem) item).getWeedVariant()), 2);
                }
            } else {
                if(event.getPlayer().getItemInHand(Hand.MAIN_HAND).sameItem(new ItemStack(Items.SHEARS))) {
                    event.getWorld().addFreshEntity(new ItemEntity(
                            event.getWorld(),
                            event.getPos().getX(),
                            event.getPos().getY(),
                            event.getPos().getZ(),
                            new ItemStack(WeedCraftItems.getFlowerByVariant(state.getValue(VARIANT)).get())
                    ));
                    event.getWorld().addFreshEntity(new ItemEntity(
                            event.getWorld(),
                            event.getPos().getX(),
                            event.getPos().getY(),
                            event.getPos().getZ(),
                            new ItemStack(WeedCraftItems.getLeafByVariant(state.getValue(VARIANT)).get(), 2)
                    ));

                    event.getWorld().setBlock(event.getPos(), state.getBlockState().setValue(VARIANT, WeedVariant.NONE), 2);
                }
            }
        }
    }

    @Override
    public Block getBlock() {
        return super.getBlock();
    }

    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(VARIANT);
    }
}
