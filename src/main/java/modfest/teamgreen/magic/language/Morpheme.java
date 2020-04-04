package modfest.teamgreen.magic.language;

public class Morpheme {
	public Morpheme(String nominal, String connective, String connectiveCloseFront, boolean causesCloseFrontVowelHarmony) {
		this.nominal = nominal;
		this.connective = connective;
		this.connectiveCloseFront = connectiveCloseFront;
		this.closeFrontVowelHarmony = causesCloseFrontVowelHarmony;
	}

	public final String nominal;
	public final String connective;
	public final String connectiveCloseFront;
	public final boolean closeFrontVowelHarmony;
}
