package modfest.teamgreen.crafting;

import java.util.ArrayList;
import java.util.List;

import modfest.teamgreen.content.item.ModItems;
import modfest.teamgreen.logic.MagicDeviceItemstack;
import modfest.teamgreen.magic.AttributeDefinitions;
import modfest.teamgreen.magic.MagicInteraction;
import modfest.teamgreen.magic.attribute.Attribute;
import net.fabricmc.fabric.api.network.ServerSidePacketRegistry;
import net.minecraft.container.Container;
import net.minecraft.container.ContainerListener;
import net.minecraft.container.Slot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.s2c.play.ContainerSlotUpdateS2CPacket;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.DefaultedList;

public class MagicDeviceCrafting implements ContainerListener {
	public MagicDeviceCrafting(PlayerEntity player) {
		this.player = player;
	}

	private final PlayerEntity player;

	private Item[] recipe = new Item[12];
	private boolean hasValidOutput = false;

	@Override
	public void onContainerRegistered(Container container, DefaultedList<ItemStack> defaultedList) {

	}

	@Override
	public void onContainerSlotUpdate(Container container, int slotId, ItemStack itemStack) {
		// 36 - 47 input slots
		slotId -= 36;
		// 0 - 11
		// column first

		if (slotId == 12) { // output
			if (this.hasValidOutput) {
				if (itemStack.isEmpty()) {
					// successfully craft
					for (int i = 36; i < 48; ++i) {
						Slot s = container.getSlot(i);
						ItemStack stack = s.getStack();

						if (AttributeDefinitions.isValid(stack.getItem())) {
							stack.decrement(1);
						}
					}
				}
			}
		} else if (slotId >= 0 && slotId < 12) { // input
			this.recipe[slotId] = itemStack.isEmpty() ? null : itemStack.getItem();

			List<Attribute> attributes = new ArrayList<>();

			for (int column = 0; column < 4; ++column) {
				int start = column * 3;
				Item main = this.recipe[start + 1];

				if (main == null) {
					continue;
				} else if (AttributeDefinitions.isValid(main)) {
					attributes.add(AttributeDefinitions.get(this.recipe[start]));
					attributes.add(AttributeDefinitions.get(main));
					attributes.add(AttributeDefinitions.get(this.recipe[start + 2]));
				} else {
					updateSlot(container, 48, ItemStack.EMPTY);
					this.hasValidOutput = false;
					return;
				}
			}

			if (!attributes.isEmpty()) {
				this.hasValidOutput = true;

				ItemStack result = new ItemStack(ModItems.MAGIC_DEVICE.get(), 1);
				Object mdi = (Object) result;

				if (mdi instanceof MagicDeviceItemstack) {
					// make array, set interaction
					((MagicDeviceItemstack) mdi).setInteraction(new MagicInteraction(attributes.toArray(new Attribute[0])));
				}

				updateSlot(container, 48, result);
			} else {
				updateSlot(container, 48, ItemStack.EMPTY);
				this.hasValidOutput = false;
			}
		}
	}

	public void updateSlot(Container container, int slot, ItemStack stack) {
		container.getSlot(slot).setStack(stack);
		if (this.player instanceof ServerPlayerEntity) {
			ContainerSlotUpdateS2CPacket packet = new ContainerSlotUpdateS2CPacket(container.syncId, slot, stack);
			ServerSidePacketRegistry.INSTANCE.sendToPlayer(this.player, packet);
		}
	}

	public void onContainerPropertyUpdate(Container container, int propertyId, int i) {

	}
}
