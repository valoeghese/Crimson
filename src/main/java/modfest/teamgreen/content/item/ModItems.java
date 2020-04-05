package modfest.teamgreen.content.item;

import java.util.function.Function;

import modfest.teamgreen.CrimsonInit;
import modfest.teamgreen.magic.AttributeDefinitions;
import net.minecraft.item.Item;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;

@SuppressWarnings("unchecked")
public enum ModItems {
	MAGIC_DEVICE("magic_device", MagicDeviceItem::new, new Item.Settings().rarity(Rarity.EPIC).maxDamage(35)),
	LAZULITE("lazulite", Item::new, new Item.Settings()),
	REALGAR("realgar", Item::new, new Item.Settings()),
	VANADINITE("vanadinite", Item::new, new Item.Settings()),
	CELESTINE("celestine", Item::new, new Item.Settings()),
	COPPER_INGOT("copper_ingot", Item::new, new Item.Settings()),
	CUT_LAZULITE("cut_lazulite", Item::new, new Item.Settings()),
	CRIMSON_ESSENCE("crimson_essence", Item::new, new Item.Settings()),
	IMBUED_LAZULITE("imbued_lazulite", CrimsonIngredientItem::new, new CrimsonIngredientItem.Settings().attribute(AttributeDefinitions.BIRI_BIRI)),
	IMBUED_REALGAR("imbued_realgar", CrimsonIngredientItem::new, new CrimsonIngredientItem.Settings().attribute(AttributeDefinitions.FLAME)),
	IMBUED_CELESTINE("imbued_celestine", CrimsonIngredientItem::new, new CrimsonIngredientItem.Settings().attribute(AttributeDefinitions.TRANSPORTATION)),
	IMBUED_CUT_LAZULITE("imbued_cut_lazulite", CrimsonIngredientItem::new, new CrimsonIngredientItem.Settings().attribute(AttributeDefinitions.CHANNELLED_ELECTRICITY)),
	IMBUED_DIAMOND("imbued_diamond", CrimsonIngredientItem::new, new CrimsonIngredientItem.Settings().attribute(AttributeDefinitions.AERO));

	private <T extends Item.Settings> ModItems(String id, Function<T, Item> constructor, T settings) {
		this.item = Registry.register(Registry.ITEM, CrimsonInit.from(id), constructor.apply((T) settings.group(CrimsonInit.GROUP)));
	}

	private final Item item;

	public Item get() {
		return this.item;
	}

	public static ModItems ensureInit() {
		return MAGIC_DEVICE;
	}
}
