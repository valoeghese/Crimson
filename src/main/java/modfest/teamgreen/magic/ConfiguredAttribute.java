package modfest.teamgreen.magic;

import modfest.teamgreen.magic.attribute.Attribute;
import modfest.teamgreen.magic.attribute.ModifyingAttribute;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;

public final class ConfiguredAttribute {
	public ConfiguredAttribute(Attribute attribute, ModifyingAttribute modifier) {
		this.modifyingAttribute = modifier;
		this.baseAttribute = attribute;
	}

	private final ModifyingAttribute modifyingAttribute;
	private final Attribute baseAttribute;

	public int activate(IWorld world, MagicUser user, BlockPos pos) {
		return this.baseAttribute.activate(world, user, pos, this.modifyingAttribute);
	}

	public int process(IWorld world, int previous, MagicUser user, BlockPos pos) {
		return this.baseAttribute.process(world, previous, user, pos, this.modifyingAttribute);
	}
}
