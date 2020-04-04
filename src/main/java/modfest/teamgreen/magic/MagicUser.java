package modfest.teamgreen.magic;

import javax.annotation.Nullable;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;

public final class MagicUser {
	public MagicUser(PlayerEntity player) {
		this.player = player;
		this.pos = player.getBlockPos();
		this.type = Type.PLAYER;
	}

	public MagicUser(BlockPos pos) {
		this.pos = pos;
		this.type = Type.BLOCK;
	}

	private final Type type;
	@Nullable
	private PlayerEntity player;
	private BlockPos pos;

	public Type type() {
		return this.type;
	}

	/**
	 * @return the position of user
	 */
	public BlockPos blockPos() {
		return this.pos;
	}

	@Nullable
	/**
	 * @return the player caster, if a player
	 */
	public PlayerEntity player() {
		return this.player;
	}

	public static enum Type {
		BLOCK,
		PLAYER;
	}
}
