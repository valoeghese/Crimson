package modfest.teamgreen.content.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class ModOreBlock extends Block {
	public ModOreBlock(Settings settings, OreProperties properties) {
		super(settings);

		this.minExp = properties.minExperienceWhenMined;
		this.maxExp = properties.maxExperienceWhenMined;
	}

	private final int minExp, maxExp;

	private int getExperienceWhenMined(Random random) {
		return MathHelper.nextInt(random, this.minExp, this.maxExp);
	}

	public void onStacksDropped(BlockState state, World world, BlockPos pos, ItemStack stack) {
		super.onStacksDropped(state, world, pos, stack);

		if (EnchantmentHelper.getLevel(Enchantments.SILK_TOUCH, stack) == 0) {
			int i = this.getExperienceWhenMined(world.random);
			if (i > 0) {
				this.dropExperience(world, pos, i);
			}
		}
	}

	public static class OreProperties {
		private int minExperienceWhenMined, maxExperienceWhenMined;

		public OreProperties experience(int min, int max) {
			this.minExperienceWhenMined = min;
			this.maxExperienceWhenMined = max;
			return this;
		}
	}
}
