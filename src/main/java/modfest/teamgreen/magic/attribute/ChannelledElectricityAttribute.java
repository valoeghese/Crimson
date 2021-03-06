package modfest.teamgreen.magic.attribute;

import modfest.teamgreen.magic.MagicUser;
import modfest.teamgreen.magic.language.Morpheme;
import net.minecraft.entity.LightningEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

public class ChannelledElectricityAttribute extends Attribute {
	public ChannelledElectricityAttribute(Identifier id) {
		super(id, MORPHEME);
		// TODO Auto-generated constructor stub
	}

	private void summonLightning(IWorld world, BlockPos pos, boolean cosmetic) {
		if (world instanceof ServerWorld) {
			LightningEntity entity = new LightningEntity((World) world, pos.getX(), pos.getY(), pos.getZ(), cosmetic);
			((ServerWorld) world).addLightning(entity);
		}
	}

	@Override
	public int activate(IWorld world, MagicUser user, BlockPos pos, ModifyingAttribute modifier) {
		if (world.getLevelProperties().isThundering()) {
			BlockPos[] positions = modifier.positions(pos, 1);
			for (BlockPos pos1 : positions) {
				summonLightning(world, pos1.add(new Vec3i(RAND.nextInt(8) - 4, 0, RAND.nextInt(8) - 4)), false);
			}

			// charged by lightning
			return 15;
		} else {
			// still able to provide some magical power since channelled
			spawnParticles(world, ParticleTypes.SMOKE, 15, pos, 0.0, 0.1, 0.0, 0.02);
			return 3;
		}
	}

	@Override
	public int process(IWorld world, int previous, MagicUser user, BlockPos pos, ModifyingAttribute modifier) {
		boolean thundering = world.getLevelProperties().isThundering();

		if (thundering || previous == 15) {
			BlockPos[] positions = modifier.positions(pos, previous);

			for (BlockPos pos1 : positions) {
				summonLightning(world, pos1.add(new Vec3i(RAND.nextInt(8) - 4, 0, RAND.nextInt(8) - 4)), false);
			}

			if (thundering) { // charged by thunder
				return 15;
			} else {
				// consume more "power" to do lightning, but only -1 when channelled type
				return previous == 0 ? 0 : previous - 1;
			}
		} else {
			// cosmetic lightning at high numbers
			if (previous > 12) {
				BlockPos[] positions = modifier.positions(pos, previous);

				for (BlockPos pos1 : positions) {
					summonLightning(world, pos1, true);
				}
			} else {
				spawnParticles(world, ParticleTypes.SMOKE, 15, pos, 0.0, 0.1, 0.0, 0.02);
			}

			// channelled electricity type : more efficient. always only subtract 1, provide 3 min
			return previous < 4 ? 3 : previous - 1;
		}
	}

	@Override
	public BlockPos[] positions(BlockPos base, int strength) {
		return new BlockPos[] {base.add(new Vec3i(RAND.nextInt(14) - 7, 0, RAND.nextInt(14) - 7))};
	}

	@Override
	public double power(IWorld world, BlockPos pos) {
		if (world.getLevelProperties().isThundering()) {
			return 15.0;
		}

		return 3.0;
	}

	// "channelling power"
	public static final Morpheme MORPHEME = new Morpheme("eleri", "eli", "eli", true);
}
