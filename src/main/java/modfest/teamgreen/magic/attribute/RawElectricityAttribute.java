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
		if (world.getLevelProperties().isThundering()) {
			BlockPos[] positions = modifier.positions(pos);
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
		boolean flag = previous == 15;

		if (world.getLevelProperties().isThundering() || flag) {
			BlockPos[] positions = modifier.positions(pos);

			int i = flag ? 10 : 7;
			int i2 = 2 * i;

			for (BlockPos pos1 : positions) {
				summonLightning(world, pos1.add(new Vec3i(RAND.nextInt(i2) - i, 0, RAND.nextInt(i2) - i)), false);
			}
			return 15;
		} else {
			if (previous > 7) {
				BlockPos[] positions = modifier.positions(pos);

				for (BlockPos pos1 : positions) {
					summonLightning(world, pos1, true);
				}

				return previous - 1;
			}

			return 0;
		}
	}

	@Override
	public BlockPos[] positions(BlockPos base) {
		return new BlockPos[] {base.add(new Vec3i(RAND.nextInt(14) - 7, 0, RAND.nextInt(14) - 7))};
	}

	public static final Morpheme MORPHEME = new Morpheme("naira", "nai", "nei", false);
}
