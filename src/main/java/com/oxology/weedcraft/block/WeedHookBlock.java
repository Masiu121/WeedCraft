package com.oxology.weedcraft.block;

import com.oxology.weedcraft.item.WeedCraftItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.apache.logging.log4j.Level;

public class WeedHookBlock extends Block {
    protected static final VoxelShape SHAPE = Block.box(6.5D, 8.0D, 6.5D, 9.5D, 16.0D, 9.5D);
    private static final BooleanProperty EMPTY = BooleanProperty.create("empty");

    public WeedHookBlock(Properties properties) {
        super(properties);
    }

    public VoxelShape getShape(BlockState state, IBlockReader block, BlockPos pos, ISelectionContext context) {
        return SHAPE;
    }

    @SubscribeEvent
    public static void onRightClick(PlayerInteractEvent.RightClickBlock event) {
        BlockState block = event.getWorld().getBlockState(event.getPos());
        if(block.is(WeedCraftBlocks.WEED_HOOK.get())) {
            if(block.getValue(EMPTY).booleanValue()) {
                if(event.getPlayer().getItemInHand(Hand.MAIN_HAND).sameItem(new ItemStack(WeedCraftItems.WEED_PLANT.get()))) {
                    if(!event.getPlayer().isCreative())
                        event.getPlayer().getItemInHand(Hand.MAIN_HAND).shrink(1);

                    event.getWorld().setBlock(event.getPos(), block.getBlockState().setValue(EMPTY, false), 2);
                }
            } else {
                if(event.getPlayer().getItemInHand(Hand.MAIN_HAND).sameItem(new ItemStack(Items.SHEARS))) {
                    event.getWorld().addFreshEntity(new ItemEntity(
                            event.getWorld(),
                            event.getPos().getX(),
                            event.getPos().getY(),
                            event.getPos().getZ(),
                            new ItemStack(WeedCraftItems.WEED_FLOWER.get())
                    ));
                    event.getWorld().addFreshEntity(new ItemEntity(
                            event.getWorld(),
                            event.getPos().getX(),
                            event.getPos().getY(),
                            event.getPos().getZ(),
                            new ItemStack(WeedCraftItems.WEED_LEAF.get(), 2)
                    ));

                    event.getWorld().setBlock(event.getPos(), block.getBlockState().setValue(EMPTY, true), 2);
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
        pBuilder.add(EMPTY);
    }
}
