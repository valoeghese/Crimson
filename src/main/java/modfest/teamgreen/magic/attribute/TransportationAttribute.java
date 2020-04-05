package modfest.teamgreen.magic.attribute;

import modfest.teamgreen.magic.MagicUser;
import modfest.teamgreen.magic.language.Morpheme;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
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
		teleport(world, user, positions[RAND.nextInt(positions.length)], 1);
		return 0;
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
		teleport(world, user, positions[RAND.nextInt(positions.length)], previous);
		return MathHelper.floor(previous / 2.0);
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

	private static final Morpheme MORPHEME = new Morpheme("asaro", "asai", "esei", false);
}
