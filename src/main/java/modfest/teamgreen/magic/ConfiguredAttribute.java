package modfest.teamgreen.magic;

import modfest.teamgreen.magic.attribute.Attribute;
import modfest.teamgreen.magic.attribute.Attribute;
import modfest.teamgreen.magic.attribute.ModifyingAttribute;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.IWorld;

public class ConfiguredAttribute implements Attribute {
	public ConfiguredAttribute(Attribute attribute, Attribute[] modifiers) {
		this.modifyingAttribute = new Modifying(modifiers);
	}

	public static ModifyingAttribute modifyingAttribute;

	@Override
	public int activate(IWorld world, PlayerEntity caster, ModifyingAttribute modifier) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int process(IWorld world, int previous, PlayerEntity caster, ModifyingAttribute modifier) {
		// TODO Auto-generated method stub
		return 0;
	}

	public static class Modifying implements ModifyingAttribute {
		private Modifying(ModifyingAttribute[] sources) {
			this.sources = sources;
		}

		private ModifyingAttribute[] sources;
	}
}
