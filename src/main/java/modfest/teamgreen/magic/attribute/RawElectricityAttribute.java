package modfest.teamgreen.magic.attribute;

import modfest.teamgreen.magic.MagicUser;
import modfest.teamgreen.magic.language.Morpheme;
import net.minecraft.entity.LightningEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

public class RawElectricityAttribute extends Attribute {
	public RawElectricityAttribute(Identifier id) {
		super(id, MORPHEME);
	}

	private void summonLightning(IWorld world, BlockPos pos, boolean cosmetic) {
		if (world instanceof ServerWorld) {
			LightningEntity entity = new LightningEntity((World) world, pos.getX(), pos.getY(), pos.getZ(), cosmetic);
			((ServerWorld) world).addLightning(entity);
		}
	}

	@Override
	public int activate(IWorld world, MagicUser user, BlockPos pos, ModifyingAttribute modifier) {
		pos = usagePosIfNull(user, pos);
		if (world.getLevelProperties().isThundering()) {
			BlockPos[] positions = modifier.positions(pos, 1);
			for (BlockPos pos1 : positions) {
				summonLightning(world, pos1.add(new Vec3i(RAND.nextInt(14) - 7, 0, RAND.nextInt(14) - 7)), false);
			}
			return 15;
		} else {
			return 0;
		}
	}

	@Override
	public int process(IWorld world, int previous, MagicUser user, BlockPos pos, ModifyingAttribute modifier) {
		pos = usagePosIfNull(user, pos);
		boolean flag = previous == 15;
		boolean thundering = world.getLevelProperties().isThundering();

		if (thundering || flag) {
			BlockPos[] positions = modifier.positions(pos, previous);

			int i = flag ? 10 : 7;
			int i2 = 2 * i;

			for (BlockPos pos1 : positions) {
				summonLightning(world, pos1.add(new Vec3i(RAND.nextInt(i2) - i, 0, RAND.nextInt(i2) - i)), false);
			}

			if (thundering) { // charged by thunder
				return 15;
			} else {
				// consume more "power" to do lightning
				return (previous - 2) <= 0 ? 0 : previous - 2;
			}
		} else {
			if (previous > 7) {
				BlockPos[] positions = modifier.positions(pos, previous);

				for (BlockPos pos1 : positions) {
					summonLightning(world, pos1, true);
				}

				// consume more "power" to do lightning
				return previous - 2;
			}

			return previous == 0 ? 0 : previous - 1;
		}
	}

	@Override
	public BlockPos[] positions(BlockPos base, int strength) {
		BlockPos result = base.add(new Vec3i(RAND.nextInt(20) - 10, RAND.nextInt(20) - 10, RAND.nextInt(20) - 10));

		if (result.getY() < 0) result = new BlockPos(result.getX(), 0, result.getZ());
		else if (result.getY() > 255) result = new BlockPos(result.getX(), 255, result.getZ());

		return new BlockPos[] {result};
	}

	// "lightning" / "thunderstorm"
	public static final Morpheme MORPHEME = new Morpheme("naira", "nai", "nei", false);
}
