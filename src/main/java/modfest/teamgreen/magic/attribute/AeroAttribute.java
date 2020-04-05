package modfest.teamgreen.magic.attribute;

import modfest.teamgreen.magic.MagicUser;
import modfest.teamgreen.magic.language.Morpheme;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;

public class AeroAttribute extends Attribute {
	public AeroAttribute(Identifier id) {
		super(id, MORPHEME);
	}
	
	public static final Morpheme MORPHEME = new Morpheme("hine", "hi", "hi", true);

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
		// TODO!
		return 0;
	}

	@Override
	public int process(IWorld world, int previous, MagicUser user, BlockPos pos, ModifyingAttribute modifier) {
		// TODO!
		return 0;
	}
}
