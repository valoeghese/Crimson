package modfest.teamgreen.crafting.gui;

import io.github.cottonmc.cotton.gui.CottonCraftingController;
import io.github.cottonmc.cotton.gui.client.CottonInventoryScreen;
import io.github.cottonmc.cotton.gui.widget.WGridPanel;
import io.github.cottonmc.cotton.gui.widget.WItemSlot;
import modfest.teamgreen.CrimsonInit;
import modfest.teamgreen.crafting.MagicDeviceCrafting;
import net.fabricmc.api.EnvType;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.container.ArrayPropertyDelegate;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.RecipeType;
import net.minecraft.util.DefaultedList;
import net.minecraft.util.Identifier;

public class MagicDeviceCraftingController extends CottonCraftingController {
	public MagicDeviceCraftingController(int syncId, PlayerInventory playerInventory) {
		super(/*dummy*/RecipeType.SMELTING, syncId, playerInventory, new CraftingInventory(CrimsonInit.CONFIG.magicDeviceMaxSections * 3, DefaultedList.ofSize(CrimsonInit.CONFIG.magicDeviceMaxSections * 3 + 37, ItemStack.EMPTY)), new ArrayPropertyDelegate(0));

		WGridPanel root = new WGridPanel();
		this.setRootPanel(root);

		int sections = CrimsonInit.CONFIG.magicDeviceMaxSections;
		this.size = 3 * sections;

		boolean client = FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT;

		// input slots
		for (int x = 0; x < sections; ++x) {
			for (int y = 0; y < 3; ++y) {
				WItemSlot inputSlot = WItemSlot.of(this.blockInventory, 3 * x + y);

				if (client) {
					if (y != 1) {
						inputSlot.setBackgroundPainter(new ModifierBackgroundPainter(y == 0));
					}
				}

				root.add(inputSlot, x, y);
			}
		}

		// output slots
		WItemSlot outputSlot = WItemSlot.outputOf(this.blockInventory, this.size);
		root.add(outputSlot, sections + 1, 1);

		root.add(createPlayerInventoryPanel(), 0, 4);

		this.addListener(new MagicDeviceCrafting(playerInventory.player, sections));

		root.validate(this);
	}

	private final int size;

	@Override
	public void close(PlayerEntity player) {
		super.close(player);

		// drop non empty stacks
		for (int i = 0; i < this.size; ++i) {
			ItemStack stack = this.blockInventory.getInvStack(i);

			if (!stack.isEmpty()) {
				this.world.spawnEntity(player.dropItem(stack, false));
			}
		}
	}

	public static final Identifier ID = CrimsonInit.from("magic_device_crafter");

	public static class Screen extends CottonInventoryScreen<MagicDeviceCraftingController> {
		public Screen(MagicDeviceCraftingController container, PlayerEntity player) {
			super(container, player);
		}
	}

	public static class CraftingInventory implements Inventory {
		public CraftingInventory(int size, DefaultedList<ItemStack> defaults) {
			this.size = size;
			this.defaults = defaults;
		}

		private final int size;
		private final DefaultedList<ItemStack> defaults;

		/**
		 * Gets the item list of this inventory.
		 * Must return the same instance every time it's called.
		 */
		public DefaultedList<ItemStack> getItems() {
			return this.defaults;
		}
		// insertion check
		@Override
		public boolean isValidInvStack(int slot, ItemStack stack) {
			if (slot == this.size) {
				return false;
			}
			return Inventory.super.isValidInvStack(slot, stack);
		}
		// Inventory
		/**
		 * Returns the inventory size.
		 */
		@Override
		public int getInvSize() {
			return getItems().size();
		}
		/**
		 * @return true if this inventory has only empty stacks, false otherwise
		 */
		@Override
		public boolean isInvEmpty() {
			for (int i = 0; i < getInvSize(); i++) {
				ItemStack stack = getInvStack(i);
				if (!stack.isEmpty()) {
					return false;
				}
			}
			return true;
		}
		/**
		 * Gets the item in the slot.
		 */
		@Override
		public ItemStack getInvStack(int slot) {
			return getItems().get(slot);
		}
		/**
		 * Takes a stack of the size from the slot.
		 * <p>(default implementation) If there are less items in the slot than what are requested,
		 * takes all items in that slot.
		 */
		@Override
		public ItemStack takeInvStack(int slot, int count) {
			ItemStack result = Inventories.splitStack(getItems(), slot, count);
			if (!result.isEmpty()) {
				markDirty();
			}
			return result;
		}
		/**
		 * Removes the current stack in the {@code slot} and returns it.
		 */
		@Override
		public ItemStack removeInvStack(int slot) {
			return Inventories.removeStack(getItems(), slot);
		}
		/**
		 * Replaces the current stack in the {@code slot} with the provided stack.
		 * <p>If the stack is too big for this inventory ({@link Inventory#getInvMaxStackAmount()}),
		 * it gets resized to this inventory's maximum amount.
		 */
		@Override
		public void setInvStack(int slot, ItemStack stack) {
			getItems().set(slot, stack);
			if (stack.getCount() > getInvMaxStackAmount()) {
				stack.setCount(getInvMaxStackAmount());
			}
		}
		/**
		 * Clears {@linkplain #getItems() the item list}}.
		 */
		@Override
		public void clear() {
			getItems().clear();
		}
		@Override
		public void markDirty() {
			// Override if you want behaviour.
		}
		@Override
		public boolean canPlayerUseInv(PlayerEntity player) {
			return true;
		}
	}
}
