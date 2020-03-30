package modfest.teamgreen.item;

import modfest.teamgreen.logic.MagicDeviceItemstack;
import modfest.teamgreen.magic.MagicInteraction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.ActionResult;

public class MagicDeviceItem extends Item {
	public MagicDeviceItem(Settings settings) {
		super(settings);
	}

	@Override
	public ActionResult useOnBlock(ItemUsageContext context) {
		Object stack = context.getStack();

		if (stack instanceof MagicDeviceItemstack) {
			MagicDeviceData data = ((MagicDeviceItemstack) stack).getData();
			MagicInteraction interaction = data.getInteraction();

			if (data != null) {
				// do stuff
			}
		}

		return ActionResult.PASS;
	}

	@Override
	public boolean hasEnchantmentGlint(ItemStack stack) {
		return true;
	}

	public static class MagicDeviceData {
		public void load(CompoundTag itemTag) {
			// load data from tag if it exists
		}

		private MagicInteraction interaction;

		public MagicInteraction getInteraction() {
			return this.interaction;
		}

		public void setInteraction(MagicInteraction interaction) {
			this.interaction = interaction;
		}
	}
}
