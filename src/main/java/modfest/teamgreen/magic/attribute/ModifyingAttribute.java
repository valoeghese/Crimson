package modfest.teamgreen.magic.attribute;

public interface ModifyingAttribute {
	
	static ModifyingAttribute NO_MODIFIER = new ModifyingAttribute() {
	};

	static class Compound implements ModifyingAttribute {
		public Compound(ModifyingAttribute ma0, ModifyingAttribute ma1) {
			this.ma0 = ma0;
			this.ma1 = ma1;
		}

		private final ModifyingAttribute ma0, ma1;
	}
}
