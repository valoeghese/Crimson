package modfest.teamgreen.mixin;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import modfest.teamgreen.item.MagicDeviceItem;
import modfest.teamgreen.item.MagicDeviceItem.MagicDeviceData;
import modfest.teamgreen.logic.MagicDeviceItemstack;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;

@Mixin(ItemStack.class)
public class MixinItemStack implements MagicDeviceItemstack {
	@Shadow
	@Final
	private Item item;

	private MagicDeviceData crimson_magicData;

	@Shadow
	private CompoundTag getOrCreateTag() {
		throw new RuntimeException("Error failed in shadowing method ItemStack#getOrCreateTag");
	}

	@Inject(method = "fromTag", at = @At("RETURN"))
	private static void loadTagData(CompoundTag tag, CallbackInfoReturnable<ItemStack> info) {
		ItemStack result = info.getReturnValue();

		if (!result.isEmpty()) {
			Item item = result.getItem();

			if (item instanceof MagicDeviceItem) {
				if (tag.contains("tag", 10)) {
					CompoundTag itemTag = tag.getCompound("tag");
					((MagicDeviceItemstack) item).setData(new MagicDeviceData(itemTag));
				}
			}
		}
	}

	@Inject(method = "setTag", at = @At("RETURN"))
	private void setTag(CompoundTag tag, CallbackInfo info) {
		if (this.item instanceof MagicDeviceItem) {
			this.setData(new MagicDeviceData(tag));
		}
	}

	@Override
	public MagicDeviceData getData() {
		return this.crimson_magicData;
	}

	@Override
	public void setData(MagicDeviceData data) {
		this.crimson_magicData = data;
	}
}
