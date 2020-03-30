package modfest.teamgreen.magic;

import modfest.teamgreen.magic.attribute.BasicAttribute;
import modfest.teamgreen.magic.attribute.ModifyingAttribute;

public class ConfiguredAttribute {
	public ConfiguredAttribute(BasicAttribute attribute, BasicAttribute[] modifiers) {
		this.modifyingAttribute = new Modifying(modifiers);
	}

	public static ModifyingAttribute modifyingAttribute;

	public static class Modifying implements ModifyingAttribute {
		private Modifying(ModifyingAttribute[] sources) {
			this.sources = sources;
		}

		private ModifyingAttribute[] sources;
	}
}
