package modfest.teamgreen.magic.attribute;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.util.Identifier;
import net.minecraft.world.IWorld;

public abstract class Attribute implements ModifyingAttribute {
	protected Attribute(Identifier id) {
		this.id = id;
		ID_TO_ATTRIBUTE.put(id, this);
	}

	private Identifier id;

	public Identifier getId() {
		return this.id;
	}

	/**
	 * @param world the world
	 * @return an int that can be passed on to following modifiers. Typically represents some kind of strength from 0-15.
	 */
	abstract public int activate(IWorld world, ModifyingAttribute modifier);
	/**
	 * @param world the world
	 * @param previous the int passed from the previous modifier
	 * @return an int that can be passed on to following modifiers. Typically represents some kind of strength from 0-15.
	 */
	abstract public int modify(IWorld world, int previous, ModifyingAttribute modifier);

	public static Attribute getById(Identifier id) {
		return ID_TO_ATTRIBUTE.get(id);
	}

	private static final Map<Identifier, Attribute> ID_TO_ATTRIBUTE = new HashMap<>();
}
