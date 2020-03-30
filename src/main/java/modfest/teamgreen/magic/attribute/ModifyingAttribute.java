package modfest.teamgreen.magic.attribute;

public interface ModifyingAttribute {
	static ModifyingAttribute NO_MODIFIER = new ModifyingAttribute() {
	};

	static class Compound implements ModifyingAttribute {
		public Compound(Attribute ma0, Attribute ma1) {
			this.ma0 = ma0;
			this.ma1 = ma1;
		}

		private final Attribute ma0, ma1;

		public int[] serialise(int baseSaveId) {
			return new int[] {baseSaveId, this.ma0.getSaveId(), this.ma1.getSaveId()};
		}
	}
}
