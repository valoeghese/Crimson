package modfest.teamgreen.magic.language;

import java.util.List;

import modfest.teamgreen.magic.ConfiguredAttribute;
import modfest.teamgreen.magic.attribute.Attribute;
import modfest.teamgreen.magic.attribute.ModifyingAttribute;

public class Language {
	private Language() {
	}

	public static String createWord(Attribute main, ModifyingAttribute connective) {
		StringBuilder sb = new StringBuilder();
		Morpheme morpheme = main.getMorpheme();
		boolean closeFrontHarmony = morpheme.closeFrontVowelHarmony;

		if (closeFrontHarmony) {
			sb.append(Attribute.getConnectiveCloseFrontMorpheme(connective));
		} else {
			sb.append(Attribute.getConnectiveMorpheme(connective));
		}

		sb.append(morpheme.nominal);
		return sb.toString();
	}

	public static String magicName(List<ConfiguredAttribute> components) {
		StringBuilder sb = new StringBuilder();
		int i = components.size();

		for (ConfiguredAttribute ca : components) {
			sb.append(ca.getWord());

			--i;
			if (i != 0) {
				sb.append(ca.wordTriggersCloseFrontVowelHarmony() ? CAUSATIVE_POSTPOSITION_CLOSE_FRONT_HARMONY : CAUSATIVE_POSTPOSITION);
			}
		}

		return sb.toString();
	}

	// Close Front Vowels: i, e
	// Other Vowels, a, o, u
	// Dipthongs that cause Close Front Vowel Harmony: ei
	// Other Dipthongs: ai, ao, au, oa

	// Nasal Consonants: m, n
	// Plosive Consonants: p, t, k
	// Affriciate Consonants: pf, ts
	// Fricative Consonants: f, s, sh, kh, h, 
	// Approximant Consonants: w
	// Trill Consonants: r

	// Phonotactics: (C)V(N)

	// the postposition is treated like a suffix, and gets vowel harmony applied
	public static String CAUSATIVE_POSTPOSITION_CLOSE_FRONT_HARMONY = " e ";
	public static String CAUSATIVE_POSTPOSITION = " a ";
}
