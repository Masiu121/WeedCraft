package com.oxology.weedcraft.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;

import javax.annotation.Nullable;

public class WeedLightBlock extends Block {
    protected static final VoxelShape[] SHAPE = new VoxelShape[] {
            Block.box(1.0D, 12.0D, 5.0D, 15.0D, 16.0D, 11.0D),
            Block.box(5.0D, 12.0D, 1.0D, 11.0D, 16.0D, 15.0D)
    };
    public static final DirectionProperty FACING = DirectionProperty.create("facing", Direction.Plane.HORIZONTAL);

    public WeedLightBlock(Properties properties) {
        super(properties.lightLevel(s -> 15));
    }

    public VoxelShape getShape(BlockState state, IBlockReader block, BlockPos pos, ISelectionContext context) {
        if(state.getValue(FACING) == Direction.NORTH || state.getValue(FACING) == Direction.SOUTH)
            return SHAPE[0];
        return SHAPE[1];
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        BlockState state = super.getStateForPlacement(context);
        if(state != null) {
            return state.setValue(FACING, context.getHorizontalDirection());
        }
        return null;
    }

    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING);
        super.createBlockStateDefinition(builder);
    }
}
