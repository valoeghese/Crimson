package modfest.teamgreen.content.item;

import java.util.List;

import modfest.teamgreen.magic.AttributeDefinitions;
import modfest.teamgreen.magic.attribute.Attribute;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.util.Rarity;
import net.minecraft.world.World;

public class CrimsonIngredientItem extends Item {
	public CrimsonIngredientItem(Settings settings) {
		super(settings.rarity(Rarity.RARE).maxCount(16));

		Attribute a = ((Settings) settings).attribute;
		this.tooltip = "Crimson Attribute: " + a.getMorpheme().nominal;
		AttributeDefinitions.put(this, a);
	}

	private final String tooltip;

	@Override
	public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
		tooltip.add(new LiteralText(this.tooltip));
	}

	@Override
	public boolean hasEnchantmentGlint(ItemStack stack) {
		return true;
	}

	public static class Settings extends Item.Settings {
		private Attribute attribute = AttributeDefinitions.TEST;

		public Settings attribute(Attribute attribute) {
			this.attribute = attribute;
			return this;
		}
	}
}
