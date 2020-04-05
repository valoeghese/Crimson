package modfest.teamgreen.content.block;

import modfest.teamgreen.crafting.gui.MagicDeviceCraftingController;
import net.fabricmc.fabric.api.container.ContainerProviderRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class MagicDeviceCraftingBlock extends Block {
	public MagicDeviceCraftingBlock(Settings settings) {
		super(settings);
	}

	@Override
	public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
		if (world.isClient) {
			return ActionResult.SUCCESS;
		}

		ContainerProviderRegistry.INSTANCE.openContainer(MagicDeviceCraftingController.ID, player, e -> {});
		return ActionResult.SUCCESS;
	}
}
