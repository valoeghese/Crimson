package modfest.teamgreen.magic.attribute;

import modfest.teamgreen.magic.MagicUser;
import modfest.teamgreen.magic.language.Morpheme;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;

public class ChanneledElectricityAttribute extends Attribute implements ModifyingAttribute.Default {
	public ChanneledElectricityAttribute(Identifier id) {
		super(id, new Morpheme("", "", "", false));
		// TODO Auto-generated constructor stub
	}

	@Override
	public int activate(IWorld world, MagicUser user, BlockPos pos, ModifyingAttribute modifier) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int process(IWorld world, int previous, MagicUser user, BlockPos pos, ModifyingAttribute modifier) {
		// TODO Auto-generated method stub
		return 0;
	}

}
