package modfest.teamgreen.magic;

import modfest.teamgreen.magic.attribute.Attribute;
import modfest.teamgreen.magic.attribute.ModifyingAttribute;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.IWorld;

public final class ConfiguredAttribute {
	public ConfiguredAttribute(Attribute attribute, ModifyingAttribute modifier) {
		this.modifyingAttribute = modifier;
		this.baseAttribute = attribute;
	}

	private final ModifyingAttribute modifyingAttribute;
	private final Attribute baseAttribute;

	public int activate(IWorld world, PlayerEntity caster) {
		return this.baseAttribute.activate(world, caster, this.modifyingAttribute);
	}

	public int process(IWorld world, int previous, PlayerEntity caster) {
		return this.baseAttribute.process(world, previous, caster, this.modifyingAttribute);
	}
}
