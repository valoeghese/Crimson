package modfest.teamgreen.item;

import modfest.teamgreen.logic.MagicDeviceItemstack;
import modfest.teamgreen.magic.MagicInteraction;
import modfest.teamgreen.magic.MagicUser;
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

			if (interaction != null) {
				interaction.apply(context.getWorld(), new MagicUser(context.getPlayer()), context.getBlockPos());
			}
		}

		return ActionResult.PASS;
	}

	@Override
	public boolean hasEnchantmentGlint(ItemStack stack) {
		return true;
	}

	public static class MagicDeviceData {
		private MagicInteraction interaction;

		public MagicInteraction getInteraction() {
			return this.interaction;
		}

		/**
		 * Use MagicDeviceItemstack#setInteraction
		 */
		public void setInteraction(MagicInteraction interaction) {
			this.interaction = interaction;
		}

		public void writeInteraction(CompoundTag itemTag) {
			CompoundTag magicDevice = new CompoundTag();

			if (this.interaction != null) {
				magicDevice.putIntArray("interaction", this.interaction.serialise());
			}
			itemTag.put("crimsonMagicDevice", itemTag);
		}

		public void load(CompoundTag itemTag) {
			if (itemTag.contains("crimsonMagicDevice")) {
				CompoundTag magicDevice = itemTag.getCompound("crimsonMagicDevice");
				
				if (magicDevice.contains("interaction", 11)) {
					int[] serialisedData = magicDevice.getIntArray("interaction");
					this.setInteraction(MagicInteraction.deserialise(serialisedData));
				}
			}
		}
	}
}
