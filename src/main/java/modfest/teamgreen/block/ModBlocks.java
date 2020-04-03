package modfest.teamgreen.block;

import java.util.function.BiFunction;
import java.util.function.Function;

import modfest.teamgreen.ModInit;
import modfest.teamgreen.block.ModOreBlock.OreProperties;
import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tools.FabricToolTags;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public enum ModBlocks {
	LAZULITE_ORE("lazulite_ore", ModOreBlock::new, FabricBlockSettings.copy(Blocks.IRON_ORE).breakByTool(FabricToolTags.PICKAXES, 1), new OreProperties().experience(2, 4)),
	MAGIC_DEVICE_CRAFTER("magic_device_crafter", MagicDeviceCraftingBlock::new, FabricBlockSettings.copy(Blocks.COBBLESTONE));

	private ModBlocks(String id, Function<Block.Settings, Block> constructor, FabricBlockSettings settings) {
		this(id, constructor, settings, new Item.Settings().group(ModInit.GROUP));
	}

	private <T extends Object> ModBlocks(String id, BiFunction<Block.Settings, T, Block> constructor, FabricBlockSettings settings, T moddedBlockProperties) {
		this(id, constructor, settings, moddedBlockProperties, new Item.Settings().group(ModInit.GROUP));
	}

	private ModBlocks(String id, Function<Block.Settings, Block> constructor, FabricBlockSettings settings, Item.Settings itemSettings) {
		Identifier identifier = ModInit.from(id);
		this.block = Registry.register(Registry.BLOCK, identifier, constructor.apply(settings.build()));
		this.item = Registry.register(Registry.ITEM, identifier, new BlockItem(this.block, itemSettings));
	}

	private <T extends Object> ModBlocks(String id, BiFunction<Block.Settings, T, Block> constructor, FabricBlockSettings settings, T moddedBlockProperties, Item.Settings itemSettings) {
		Identifier identifier = ModInit.from(id);
		this.block = Registry.register(Registry.BLOCK, identifier, constructor.apply(settings.build(), moddedBlockProperties));
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
