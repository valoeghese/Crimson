package modfest.teamgreen.magic.attribute;

import modfest.teamgreen.magic.MagicUser;
import modfest.teamgreen.magic.language.Morpheme;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.IWorld;

public class StrengthAttribute extends Attribute {
	public StrengthAttribute(Identifier id) {
		super(id, MORPHEME);
	}

	@Override
	public BlockPos[] positions(BlockPos base, int strength) {
		BlockPos result = base.up(RAND.nextInt(16) + 16);

		if (result.getY() > 255) {
			result = new BlockPos(result.getX(), 255, result.getZ());
		}

		return new BlockPos[] {result};
	}

	@Override
	public int activate(IWorld world, MagicUser user, BlockPos pos, ModifyingAttribute modifier) {
		pos = usagePosIfNull(user, pos);
		world.getEntities(LivingEntity.class, new Box(pos).expand(1.0), le -> true).forEach(le -> le.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 20)));
		return 15;
	}

	@Override
	public int process(IWorld world, int previous, MagicUser user, BlockPos pos, ModifyingAttribute modifier) {
		pos = usagePosIfNull(user, pos);
		double m = modifier.power(world, pos);
		world.getEntities(LivingEntity.class, new Box(pos).expand(1.0), le -> true).forEach(le -> le.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, (int) (m < 2 ? 20 : 10 * m))));
		return 15;
	}

	@Override
	public double power(IWorld world, BlockPos pos) {
		return 15.0;
	}

	public static final Morpheme MORPHEME = new Morpheme("nakha", "nao", "ne", true);
}
