package modfest.teamgreen.content.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FlowerBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public final class CrimsonTendrilsBlock extends FlowerBlock {
	public CrimsonTendrilsBlock(Block.Settings settings) {
		super(StatusEffects.BLINDNESS, 10, settings);
	}

	@Override
	public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
		if (entity instanceof LivingEntity && entity.getType() != EntityType.SPIDER && !world.isClient()) {
			LivingEntity livingEntity = (LivingEntity) entity;
			livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, 80));
			livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 60));
			livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOW_FALLING, 25));
			world.breakBlock(pos, false);
		}
	}
}
