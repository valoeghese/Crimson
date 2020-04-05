package modfest.teamgreen.magic;

import java.util.HashMap;
import java.util.Map;

import modfest.teamgreen.CrimsonInit;
import modfest.teamgreen.content.item.ModItems;
import modfest.teamgreen.magic.attribute.Attribute;
import modfest.teamgreen.magic.attribute.ChannelledElectricityAttribute;
import modfest.teamgreen.magic.attribute.FlameAttribute;
import modfest.teamgreen.magic.attribute.RawElectricityAttribute;
import modfest.teamgreen.magic.attribute.TestAttribute;
import modfest.teamgreen.magic.attribute.TransportationAttribute;
import net.minecraft.item.Item;

public class AttributeDefinitions {
	private static final Map<Item, Attribute> ATTRIBUTE_MAP = new HashMap<>();

	public static void put(Item item, Attribute attribute) {
		ATTRIBUTE_MAP.put(item, attribute);
	}

	public static Attribute get(Item item) {
		return ATTRIBUTE_MAP.get(item);
	}

	public static boolean isValid(Item item) {
		return item != null && ATTRIBUTE_MAP.containsKey(item);
	}

	public static final Attribute TEST = new TestAttribute(CrimsonInit.from("test"));
	public static final Attribute BIRI_BIRI = new RawElectricityAttribute(CrimsonInit.from("biri_biri"));
	public static final Attribute CHANNELLED_ELECTRICITY = new ChannelledElectricityAttribute(CrimsonInit.from("channelled_electricity"));
	public static final Attribute TRANSPORTATION = new TransportationAttribute(CrimsonInit.from("transportation"));
	public static final Attribute FLAME = new FlameAttribute(CrimsonInit.from("flame"));

	public static Attribute ensureInit() {
		return TEST;
	}

	static {
		ATTRIBUTE_MAP.put(ModItems.LAZULITE.get(), BIRI_BIRI);
		ATTRIBUTE_MAP.put(ModItems.CUT_LAZULITE.get(), CHANNELLED_ELECTRICITY);
		ATTRIBUTE_MAP.put(ModItems.CELESTINE.get(), TRANSPORTATION);
		ATTRIBUTE_MAP.put(ModItems.REALGAR.get(), FLAME);
	}
}
