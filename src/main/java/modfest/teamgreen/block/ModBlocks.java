package modfest.teamgreen.block;

import java.util.function.Function;

import modfest.teamgreen.ModInit;
import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public enum ModBlocks {
	;

	private <T extends FabricBlockSettings> ModBlocks(String id, Function<T, Block> constructor, T settings) {
		this(id, constructor, settings, new Item.Settings().group(ModInit.GROUP));
	}

	private <T extends FabricBlockSettings> ModBlocks(String id, Function<T, Block> constructor, T settings, Item.Settings itemSettings) {
		Identifier identifier = ModInit.from(id);
		this.block = Registry.register(Registry.BLOCK, identifier, constructor.apply(settings));
		this.item = Registry.register(Registry.ITEM, identifier, new BlockItem(this.block, itemSettings));
	}

	private final Block block;
	private final Item item;

	public Block get() {
		return this.block;
	}

	public Item getItem() {
		return this.item;
	}

	public static ModBlocks ensureInit() {
		return null;
	}
}
