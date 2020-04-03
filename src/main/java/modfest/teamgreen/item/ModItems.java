package modfest.teamgreen.item;

import java.util.function.Function;

import modfest.teamgreen.CrimsonInit;
import net.minecraft.item.Item;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;

@SuppressWarnings("unchecked")
public enum ModItems {
	MAGIC_DEVICE("magic_device", MagicDeviceItem::new, new Item.Settings().rarity(Rarity.EPIC).maxCount(1)),
	LAZULITE("lazulite", Item::new, new Item.Settings());

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
