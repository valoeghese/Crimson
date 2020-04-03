package modfest.teamgreen.magic.attribute;

import modfest.teamgreen.magic.MagicUser;
import net.minecraft.block.Blocks;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;

public class TestAttribute extends Attribute {
	public TestAttribute(Identifier id) {
		super(id);
	}

	@Override
	public int activate(IWorld world, MagicUser user, BlockPos pos, ModifyingAttribute modifier) {
		if (pos != null) {
			world.setBlockState(pos, Blocks.GOLD_ORE.getDefaultState(), 0b11);
		}

		return 0;
	}

	@Override
	public int process(IWorld world, int previous, MagicUser user, BlockPos pos, ModifyingAttribute modifier) {
		if (pos != null) {
			world.setBlockState(pos, Blocks.GOLD_ORE.getDefaultState(), 0b11);
		}

		return previous;
	}
}
