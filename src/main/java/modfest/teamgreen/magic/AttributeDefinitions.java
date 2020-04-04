package modfest.teamgreen.magic;

import java.util.HashMap;
import java.util.Map;

import modfest.teamgreen.CrimsonInit;
import modfest.teamgreen.item.ModItems;
import modfest.teamgreen.magic.attribute.Attribute;
import modfest.teamgreen.magic.attribute.ChanneledElectricityAttribute;
import modfest.teamgreen.magic.attribute.RawElectricityAttribute;
import modfest.teamgreen.magic.attribute.TestAttribute;
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
	public static final Attribute ELECTRICITY = new ChanneledElectricityAttribute(CrimsonInit.from("electricity"));

	public static Attribute ensureInit() {
		return TEST;
	}

	static {
		ATTRIBUTE_MAP.put(ModItems.LAZULITE.get(), BIRI_BIRI);
	}
}
