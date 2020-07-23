package modfest.teamgreen.content.block;

import com.google.common.collect.ImmutableMap;

import modfest.teamgreen.mixin.AccessorAxeItem;
import net.minecraft.block.Block;

public class CrimsonStrippedBlocks {
	public static void init() {
		ImmutableMap.Builder<Block, Block> builder = ImmutableMap.builder();
		builder.putAll(AccessorAxeItem.getStrippedBlocks());

		builder.put(CrimsonBlocks.CRIMSON_LOG.get(), CrimsonBlocks.STRIPPED_CRIMSON_LOG.get());
		builder.put(CrimsonBlocks.CRIMSON_WOOD.get(), CrimsonBlocks.STRIPPED_CRIMSON_WOOD.get());

		AccessorAxeItem.setStrippedBlocks(builder.build());
	}
}