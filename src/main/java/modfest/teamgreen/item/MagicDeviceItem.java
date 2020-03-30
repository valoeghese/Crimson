package modfest.teamgreen.item;

import modfest.teamgreen.logic.MagicDeviceItemstack;
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
		public MagicDeviceData(CompoundTag itemTag) {
			// TODO Auto-generated constructor stub
		}
	}
}
