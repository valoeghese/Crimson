package modfest.teamgreen.magic;

import javax.annotation.Nullable;

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

	public int activate(IWorld world, MagicUser user, @Nullable BlockPos pos) {
		return this.baseAttribute.activate(world, user, pos, this.modifyingAttribute);
	}

	public int process(IWorld world, int previous, MagicUser user, @Nullable BlockPos pos) {
		return this.baseAttribute.process(world, previous, user, pos, this.modifyingAttribute);
	}

	public int[] serialise() {
		if (this.modifyingAttribute instanceof Attribute) {
			return new int[] {this.baseAttribute.getSaveId(), ((Attribute) this.modifyingAttribute).getSaveId(), -1};
		} else if (this.modifyingAttribute instanceof ModifyingAttribute.Compound) {
			return ((ModifyingAttribute.Compound) this.modifyingAttribute).serialise(this.baseAttribute.getSaveId());
		} else {
			return new int[] {this.baseAttribute.getSaveId(), -1, -1};
		}
	}

	public static ConfiguredAttribute deserialise(int baseId, int modifierId0, int modifierId1) {
		Attribute baseAttribute = Attribute.getBySaveId(baseId);
		ModifyingAttribute modifier = null;

		if (modifierId0 != -1) {
			if (modifierId1 != -1) {
				modifier = new ModifyingAttribute.Compound(Attribute.getBySaveId(modifierId0), Attribute.getBySaveId(modifierId1));
			} else {
				modifier = Attribute.getBySaveId(modifierId0);
			}
		}

		return new ConfiguredAttribute(baseAttribute, modifier);
	}
}
