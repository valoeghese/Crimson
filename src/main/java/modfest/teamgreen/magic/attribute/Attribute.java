package modfest.teamgreen.magic.attribute;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nullable;

import it.unimi.dsi.fastutil.ints.Int2ObjectArrayMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import modfest.teamgreen.magic.MagicUser;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;

public abstract class Attribute implements ModifyingAttribute {
	protected Attribute(Identifier id) {
		this.id = id;
		this.saveId = currentSaveId++;
		ID_TO_ATTRIBUTE.put(id, this);
		SAVE_ID_MAP.put(this.saveId, this);
	}

	private final Identifier id;
	private final int saveId;

	public Identifier getId() {
		return this.id;
	}

	public int getSaveId() {
		return this.saveId;
	}

	/**
	 * @param world the world
	 * @param user the user of the magic. Can be a block or player.
	 * @param pos If this was activated on something, represents the blockpos thereof. Otherwise null.
	 * @return an int that can be passed on to following modifiers. Typically represents some kind of strength from 0-15.
	 */
	public abstract int activate(IWorld world, @Nullable MagicUser user, @Nullable BlockPos pos, ModifyingAttribute modifier);
	/**
	 * @param world the world
	 * @param previous the int passed from the previous modifier
	 * @param the user of the magic. Can be a block or player.
	 * @param pos If this was activated on something, represents the blockpos thereof. Otherwise null.
	 * @return an int that can be passed on to following modifiers. Typically represents some kind of strength from 0-15.
	 */
	public abstract int process(IWorld world, int previous, @Nullable MagicUser user, @Nullable BlockPos pos, ModifyingAttribute modifier);

	public static Attribute getById(Identifier id) {
		return ID_TO_ATTRIBUTE.get(id);
	}

	public static Attribute getBySaveId(int id) {
		return SAVE_ID_MAP.get(id);
	}

	private static int currentSaveId = 0;
	private static final Map<Identifier, Attribute> ID_TO_ATTRIBUTE = new HashMap<>();
	private static final Int2ObjectMap<Attribute> SAVE_ID_MAP = new Int2ObjectArrayMap<>();
}
