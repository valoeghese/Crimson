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
import modfest.teamgreen.magic.MagicInteraction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;

@Mixin(ItemStack.class)
public class MixinItemStack implements MagicDeviceItemstack {
	@Shadow
	@Final
	private Item item;
	@Shadow
	private CompoundTag tag;

	private MagicDeviceData crimson_magicData = new MagicDeviceData();

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
					((MagicDeviceItemstack) (Object) result).getData().load(itemTag);
				}
			}
		}
	}

	@Inject(method = "setTag", at = @At("RETURN"))
	private void setTag(CompoundTag tag, CallbackInfo info) {
		if (this.item instanceof MagicDeviceItem) {
			this.getData().load(tag);
		}
	}

	@Override
	public MagicDeviceData getData() {
		return this.crimson_magicData;
	}

	@Override
	public void setInteraction(MagicInteraction interaction) {
		this.crimson_magicData.setInteraction(interaction);

		if (this.tag == null) {
			this.tag = new CompoundTag();
		}

		this.crimson_magicData.writeInteraction(this.tag);
	}

	@Inject(method = "copy", at = @At("RETURN"))
	private void copy(CallbackInfoReturnable<ItemStack> cir) {
		ItemStack result = cir.getReturnValue();
		result.setTag(this.tag);
	}
}
