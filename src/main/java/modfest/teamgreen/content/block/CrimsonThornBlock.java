package modfest.teamgreen.content.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FlowerBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public final class CrimsonThornBlock extends FlowerBlock {
	public CrimsonThornBlock(Block.Settings settings) {
		super(StatusEffects.RESISTANCE, 10, settings);
	}

	@Override
	public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
		entity.damage(DamageSource.CACTUS, 1.0f);
		world.breakBlock(pos, false);
	}
}
