package modfest.teamgreen.content.block;

import java.util.function.BiFunction;
import java.util.function.Function;

import modfest.teamgreen.CrimsonInit;
import modfest.teamgreen.content.block.ModOreBlock.OreProperties;
import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tools.FabricToolTags;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.LogBlock;
import net.minecraft.block.MaterialColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public enum ModBlocks {
	LAZULITE_ORE("lazulite_ore", ModOreBlock::new, FabricBlockSettings.copy(Blocks.IRON_ORE).breakByTool(FabricToolTags.PICKAXES, 2), new OreProperties().experience(2, 5)),
	REALGAR_ORE("realgar_ore", ModOreBlock::new, FabricBlockSettings.copy(Blocks.COAL_ORE).breakByTool(FabricToolTags.PICKAXES, 1), new OreProperties().experience(1, 3)),
	BORNITE("bornite", ModOreBlock::new, FabricBlockSettings.copy(Blocks.COAL_ORE).breakByTool(FabricToolTags.PICKAXES, 0), new OreProperties().experience(1, 2)),
	VANADINITE_ORE("vanadinite_ore", ModOreBlock::new, FabricBlockSettings.copy(Blocks.COAL_ORE).breakByTool(FabricToolTags.PICKAXES, 1), new OreProperties().experience(2, 3)),
	CELESTINE_ORE("celestine_ore", ModOreBlock::new, FabricBlockSettings.copy(Blocks.DIAMOND_ORE).breakByTool(FabricToolTags.PICKAXES, 3), new OreProperties().experience(2, 4)),
	MAGIC_DEVICE_CRAFTER("magic_device_crafter", MagicDeviceCraftingBlock::new, FabricBlockSettings.copy(Blocks.COBBLESTONE)),
	CRIMSON_LEAVES("crimson_leaves", LeavesBlock::new, FabricBlockSettings.copy(Blocks.ACACIA_LEAVES)),
	CRIMSON_SAPLING("crimson_sapling", CrimsonSaplingBlock::new, FabricBlockSettings.copy(Blocks.ACACIA_SAPLING)),
	CRIMSON_LOG("crimson_log", a -> new LogBlock(MaterialColor.RED, a), FabricBlockSettings.copy(Blocks.ACACIA_LOG)),
	CRIMSON_THORN("crimson_thorn", CrimsonThornBlock::new, FabricBlockSettings.copy(Blocks.FERN)),
	CRIMSON_TENDRILS("crimson_tendrils", CrimsonTendrilsBlock::new, FabricBlockSettings.copy(Blocks.FERN));

	private ModBlocks(String id, Function<Block.Settings, Block> constructor, FabricBlockSettings settings) {
		this(id, constructor, settings, new Item.Settings().group(CrimsonInit.GROUP));
	}

	private <T extends Object> ModBlocks(String id, BiFunction<Block.Settings, T, Block> constructor, FabricBlockSettings settings, T moddedBlockProperties) {
		this(id, constructor, settings, moddedBlockProperties, new Item.Settings().group(CrimsonInit.GROUP));
	}

	private ModBlocks(String id, Function<Block.Settings, Block> constructor, FabricBlockSettings settings, Item.Settings itemSettings) {
		Identifier identifier = CrimsonInit.from(id);
		this.block = Registry.register(Registry.BLOCK, identifier, constructor.apply(settings.build()));
		this.item = Registry.register(Registry.ITEM, identifier, new BlockItem(this.block, itemSettings));
	}

	private <T extends Object> ModBlocks(String id, BiFunction<Block.Settings, T, Block> constructor, FabricBlockSettings settings, T moddedBlockProperties, Item.Settings itemSettings) {
		Identifier identifier = CrimsonInit.from(id);
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
