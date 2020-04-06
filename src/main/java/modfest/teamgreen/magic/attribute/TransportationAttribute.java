package modfest.teamgreen.magic.attribute;

import modfest.teamgreen.magic.MagicUser;
import modfest.teamgreen.magic.language.Morpheme;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.Heightmap;
import net.minecraft.world.IWorld;

public class TransportationAttribute extends Attribute {
	public TransportationAttribute(Identifier id) {
		super(id, MORPHEME);
	}

	@Override
	public int activate(IWorld world, MagicUser user, BlockPos pos, ModifyingAttribute modifier) {
		pos = usagePosIfNull(user, pos);
		BlockPos[] positions = modifier.positions(pos, 1);
		BlockPos dest = positions[RAND.nextInt(positions.length)];
		teleport(world, user, dest, 1);
		int result = telePower(user.blockPos(), dest);
		return result > 15 ? 15 : result;
	}

	@Override
	public int process(IWorld world, int previous, MagicUser user, BlockPos pos, ModifyingAttribute modifier) {
		pos = usagePosIfNull(user, pos);

		if (previous > 1) {
			if (user.type() == MagicUser.Type.PLAYER) {
				BlockPos uPos = user.blockPos();

				if (pos != uPos) {
					int x0 = uPos.getX();
					int z0 = uPos.getZ();
					int x1 = pos.getX();
					int z1 = pos.getZ();

					if (x0 != x1 || z0 != z1) {
						int dx = x1 - x0;
						int dz = z1 - z0;

						pos = pos.add(dx * 0.7 * previous, 0, dz * 0.7 * previous);
					}
				}
			}
		}

		BlockPos[] positions = modifier.positions(pos, previous);
		BlockPos dest = positions[RAND.nextInt(positions.length)];
		teleport(world, user, dest, previous);

		int result = telePower(user.blockPos(), dest);
		return result > 15 ? 15 : result;
	}

	@Override
	public BlockPos[] positions(BlockPos base, int strength) {
		++strength;

		Direction direction = null;

		switch (RAND.nextInt(4)) {
		case 0:
			direction = Direction.NORTH;
			break;
		case 1:
			direction = Direction.EAST;
			break;
		case 2:
			direction = Direction.SOUTH;
			break;
		case 3:
			direction = Direction.WEST;
			break;
		}

		return new BlockPos[] {base.add(direction.getOffsetX() * 12 * strength, 0, direction.getOffsetZ() * 12 * strength)};
	}

	@Override
	public double power(IWorld world, BlockPos pos) {
		double result = pos.getSquaredDistance(Vec3i.ZERO) / 1048576.0;
		return result > 1.0 ? 15.0 : 15.0 * result;
	}

	private static void teleport(IWorld world, MagicUser user, BlockPos pos, int strength) {
		if (user.type() == MagicUser.Type.PLAYER) {
			world.getChunk(pos).getBlockState(pos);
			pos = world.getTopPosition(Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, pos);
			user.player().setPos(pos.getX(), pos.getY() + 0.5, pos.getZ());
		} else {
			PlayerEntity pe = world.getClosestPlayer(user.blockPos().getX(), user.blockPos().getZ(), 2.0 * strength);
			world.getChunk(pos);
			pos = world.getTopPosition(Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, pos);
			pe.setPos(pos.getX(), pos.getY() + 0.5, pos.getZ());
		}
	}

	private static int telePower(BlockPos original, BlockPos result) {
		return (int) (15.0 * (double) original.getSquaredDistance(result) / 1048576.0);
	}

	private static final Morpheme MORPHEME = new Morpheme("asaro", "asai", "esei", false);
}
