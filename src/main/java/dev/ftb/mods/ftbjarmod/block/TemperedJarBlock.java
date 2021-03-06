package dev.ftb.mods.ftbjarmod.block;


import dev.ftb.mods.ftbjarmod.block.entity.TemperedJarBlockEntity;
import dev.ftb.mods.ftbjarmod.heat.Temperature;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Random;

/**
 * @author LatvianModder
 */
public class TemperedJarBlock extends JarBlock {
	public static final EnumProperty<Temperature> TEMPERATURE = EnumProperty.create("temperature", Temperature.class);

	public TemperedJarBlock() {
		registerDefaultState(getStateDefinition().any().setValue(TEMPERATURE, Temperature.NONE));
	}

	@Override
	public BlockEntity createTileEntity(BlockState state, BlockGetter world) {
		return new TemperedJarBlockEntity();
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(TEMPERATURE);
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		return defaultBlockState().setValue(TEMPERATURE, Temperature.fromWorld(context.getLevel(), context.getClickedPos().below()));
	}

	@Override
	@Deprecated
	public BlockState updateShape(BlockState stateIn, Direction facing, BlockState facingState, LevelAccessor worldIn, BlockPos currentPos, BlockPos facingPos) {
		return facing == Direction.DOWN && worldIn instanceof Level ? stateIn.setValue(TEMPERATURE, Temperature.fromWorld((Level) worldIn, facingPos)) : stateIn;
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void animateTick(BlockState stateIn, Level worldIn, BlockPos pos, Random rand) {
		BlockEntity entity = worldIn.getBlockEntity(pos);
		Temperature temperature = stateIn.getValue(TEMPERATURE);

		if (entity instanceof TemperedJarBlockEntity) {
			TemperedJarBlockEntity t = (TemperedJarBlockEntity) entity;

			if (t.particles) {
				for (int i = 0; i < 5; i++) {
					worldIn.addParticle(temperature == Temperature.HIGH ? ParticleTypes.SOUL_FIRE_FLAME : temperature == Temperature.LOW ? ParticleTypes.FLAME : ParticleTypes.SNEEZE, pos.getX() + rand.nextFloat(), pos.getY() + rand.nextFloat() / 3F, pos.getZ() + rand.nextFloat(), 0D, 0D, 0D);
				}
			}
		}
	}
}
