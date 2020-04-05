package modfest.teamgreen.magic.attribute;

import java.util.ArrayList;
import java.util.List;

import modfest.teamgreen.magic.MagicUser;
import modfest.teamgreen.magic.language.Morpheme;
import modfest.teamgreen.util.OneEighthDirection;
import net.minecraft.block.Blocks;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IWorld;
import net.minecraft.world.dimension.DimensionType;

public class FlameAttribute extends Attribute {
	public FlameAttribute(Identifier id) {
		super(id, MORPHEME);
	}

	@Override
	public int activate(IWorld world, MagicUser user, BlockPos pos, ModifyingAttribute modifier) {
		pos = usagePosIfNull(user, pos);
		BlockPos[] positions = modifier.positions(pos, 1);

		for (BlockPos position : positions) {
			BlockPos at = position.up();

			if (at.getY() < 256) {
				if (world.getBlockState(at).isAir()) {
					world.setBlockState(at, Blocks.FIRE.getDefaultState(), 0b11);
				}
			}
		}

		return world.getDimension().getType() == DimensionType.THE_NETHER ? 7 : 0;
	}

	@Override
	public int process(IWorld world, int previous, MagicUser user, BlockPos pos, ModifyingAttribute modifier) {
		pos = usagePosIfNull(user, pos);
		BlockPos[] positions = modifier.positions(pos, 1);

		for (BlockPos position : positions) {
			BlockPos at = position.up();

			if (at.getY() < 256) {
				if (world.getBlockState(at).isAir()) {
					world.setBlockState(at, Blocks.FIRE.getDefaultState(), 0b11);
				}
			}
		}

		int result = MathHelper.floor(previous / 2.0);

		if (result > 7) {
			return result;
		} else {
			return world.getDimension().getType() == DimensionType.THE_NETHER ? 7 : result;
		}
	}

	@Override
	public BlockPos[] positions(BlockPos base, int strength) {
		List<BlockPos> result = new ArrayList<>();

		// treat strength as radius
		OneEighthDirection.stream().forEach(
				vec -> result.add(base.add(vec.getX() * strength, 0, vec.getZ() * strength)));

		return result.toArray(new BlockPos[result.size()]);
	}

	private static final Morpheme MORPHEME = new Morpheme("shakha", "sha", "she", false);

	@Override
	public double power(IWorld world, BlockPos pos) {
		return world.getLightLevel(pos);
	}
}
