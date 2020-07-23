package modfest.teamgreen.mixin;

import java.util.Map;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

import net.minecraft.block.Block;
import net.minecraft.item.AxeItem;

@Mixin(AxeItem.class)
public interface AccessorAxeItem {
	@Accessor(value = "STRIPPED_BLOCKS")
	public static Map<Block, Block> getStrippedBlocks() {
		return null;
	}
	
	@Mutable
	@Accessor(value = "STRIPPED_BLOCKS")
	public static void setStrippedBlocks(Map<Block, Block> strippedBlocks) {
		return;
	}
}

