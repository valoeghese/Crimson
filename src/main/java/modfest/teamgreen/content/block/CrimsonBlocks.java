package modfest.teamgreen.content.block;

import java.util.function.BiFunction;
import java.util.function.Function;

import modfest.teamgreen.CrimsonInit;
import modfest.teamgreen.content.block.CrimsonOreBlock.OreProperties;
import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tools.FabricToolTags;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.FlowerPotBlock;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.LogBlock;
import net.minecraft.block.MaterialColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public enum CrimsonBlocks {
	LAZULITE_ORE("lazulite_ore", CrimsonOreBlock::new, FabricBlockSettings.copy(Blocks.IRON_ORE).breakByTool(FabricToolTags.PICKAXES, 2), new OreProperties().experience(2, 5)),
	REALGAR_ORE("realgar_ore", CrimsonOreBlock::new, FabricBlockSettings.copy(Blocks.COAL_ORE).breakByTool(FabricToolTags.PICKAXES, 1), new OreProperties().experience(1, 3)),
	BORNITE("bornite", Block::new, FabricBlockSettings.copy(Blocks.COAL_ORE).breakByTool(FabricToolTags.PICKAXES, 0)),
	VANADINITE_ORE("vanadinite_ore", CrimsonOreBlock::new, FabricBlockSettings.copy(Blocks.COAL_ORE).breakByTool(FabricToolTags.PICKAXES, 1), new OreProperties().experience(2, 3)),
	CELESTINE_ORE("celestine_ore", CrimsonOreBlock::new, FabricBlockSettings.copy(Blocks.DIAMOND_ORE).breakByTool(FabricToolTags.PICKAXES, 3), new OreProperties().experience(2, 4)),
	MAGIC_DEVICE_CRAFTER("magic_device_crafter", MagicDeviceCraftingBlock::new, FabricBlockSettings.copy(Blocks.COBBLESTONE)),
	CRIMSON_LEAVES("crimson_leaves", LeavesBlock::new, FabricBlockSettings.copy(Blocks.ACACIA_LEAVES)),
	CRIMSON_SAPLING("crimson_sapling", CrimsonSaplingBlock::new, FabricBlockSettings.copy(Blocks.ACACIA_SAPLING)),
	POTTED_CRIMSON_SAPLING("potted_crimson_sapling", a -> new FlowerPotBlock(CRIMSON_SAPLING.get(), a), FabricBlockSettings.copy(Blocks.POTTED_ACACIA_SAPLING), null),
	CRIMSON_LOG("crimson_log", a -> new LogBlock(MaterialColor.RED, a), FabricBlockSettings.copy(Blocks.ACACIA_LOG)),
	CRIMSON_THORN("crimson_thorn", CrimsonThornBlock::new, FabricBlockSettings.copy(Blocks.FERN)),
	POTTED_CRIMSON_THORN("potted_crimson_thorn", a -> new FlowerPotBlock(CRIMSON_THORN.get(), a), FabricBlockSettings.copy(Blocks.POTTED_FERN), null),
	CRIMSON_TENDRILS("crimson_tendrils", CrimsonTendrilsBlock::new, FabricBlockSettings.copy(Blocks.FERN)),
	POTTED_CRIMSON_TENDRILS("potted_crimson_tendrils", a -> new FlowerPotBlock(CRIMSON_TENDRILS.get(), a), FabricBlockSettings.copy(Blocks.POTTED_FERN), null);

	private CrimsonBlocks(String id, Function<Block.Settings, Block> constructor, FabricBlockSettings settings) {
		this(id, constructor, settings, new Item.Settings().group(CrimsonInit.GROUP));
	}

	private <T extends Object> CrimsonBlocks(String id, BiFunction<Block.Settings, T, Block> constructor, FabricBlockSettings settings, T moddedBlockProperties) {
		this(id, constructor, settings, moddedBlockProperties, new Item.Settings().group(CrimsonInit.GROUP));
	}

	private CrimsonBlocks(String id, Function<Block.Settings, Block> constructor, FabricBlockSettings settings, Item.Settings itemSettings) {
		Identifier identifier = CrimsonInit.from(id);
		this.block = Registry.register(Registry.BLOCK, identifier, constructor.apply(settings.build()));

		if (itemSettings == null) {
			this.item = null;
		} else {
			this.item = Registry.register(Registry.ITEM, identifier, new BlockItem(this.block, itemSettings));
		}
	}

	private <T extends Object> CrimsonBlocks(String id, BiFunction<Block.Settings, T, Block> constructor, FabricBlockSettings settings, T moddedBlockProperties, Item.Settings itemSettings) {
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

	public static CrimsonBlocks ensureInit() {
		return null;
	}
}
