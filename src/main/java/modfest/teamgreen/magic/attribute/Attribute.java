package modfest.teamgreen.magic.attribute;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.annotation.Nullable;

import it.unimi.dsi.fastutil.ints.Int2ObjectArrayMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import modfest.teamgreen.magic.MagicUser;
import modfest.teamgreen.magic.language.Morpheme;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;

public abstract class Attribute implements ModifyingAttribute {
	public Attribute(Identifier id, Morpheme morpheme) {
		this.morpheme = morpheme;
		this.id = id;
		this.saveId = currentSaveId++;
		ID_TO_ATTRIBUTE.put(id, this);
		SAVE_ID_MAP.put(this.saveId, this);
	}

	protected static final Random RAND = new Random();

	private final Identifier id;
	private final int saveId;
	private final Morpheme morpheme;

	public Identifier getId() {
		return this.id;
	}

	public int getSaveId() {
		return this.saveId;
	}

	public Morpheme getMorpheme() {
		return this.morpheme;
	}

	/**
	 * @param world the world
	 * @param user the user of the magic. Can be a block or player.
	 * @param pos If this was activated on something, represents the blockpos thereof. Otherwise null.
	 * @param modifier the modifying attribute, used for calculations of the resulting actions of this attribute.
	 * @return an int that can be passed on to following modifiers. Typically represents some kind of strength from 0-15.
	 */
	public abstract int activate(IWorld world, MagicUser user, @Nullable BlockPos pos, ModifyingAttribute modifier);
	/**
	 * @param world the world
	 * @param previous the int passed from the previous modifier. Typically represents some king of strength from 0-15.
	 * @param the user of the magic. Can be a block or player.
	 * @param pos If this was activated on something, represents the blockpos thereof. Otherwise null.
	 * @param modifier the modifying attribute, used for calculations of the resulting actions of this attribute.
	 * @return an int that can be passed on to following modifiers. Typically represents some kind of strength from 0-15.
	 */
	public abstract int process(IWorld world, int previous, MagicUser user, @Nullable BlockPos pos, ModifyingAttribute modifier);

	public static String getConnectiveMorpheme(ModifyingAttribute ma) {
		if (ma instanceof Attribute) {
			return ((Attribute) ma).morpheme.connective;
		} else if (ma instanceof ModifyingAttribute.Compound) {
			return ModifyingAttribute.Compound.getConnectiveMorpheme((ModifyingAttribute.Compound) ma);
		} else {
			return "";
		}
	}

	public static String getConnectiveCloseFrontMorpheme(ModifyingAttribute ma) {
		if (ma instanceof Attribute) {
			return ((Attribute) ma).morpheme.connectiveCloseFront;
		} else if (ma instanceof ModifyingAttribute.Compound) {
			return ModifyingAttribute.Compound.getConnectiveCloseFrontMorpheme((ModifyingAttribute.Compound) ma);
		} else {
			return "";
		}
	}

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
