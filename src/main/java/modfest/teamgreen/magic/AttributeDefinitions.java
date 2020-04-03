package modfest.teamgreen.magic;

import java.util.HashMap;
import java.util.Map;

import modfest.teamgreen.ModInit;
import modfest.teamgreen.magic.attribute.Attribute;
import modfest.teamgreen.magic.attribute.TestAttribute;
import net.minecraft.item.Item;
import net.minecraft.item.Items;

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

	public static final Attribute TEST = new TestAttribute(ModInit.from("test"));

	public static Attribute ensureInit() {
		return TEST;
	}

	static {
		ATTRIBUTE_MAP.put(Items.DIRT, TEST);
	}
}
