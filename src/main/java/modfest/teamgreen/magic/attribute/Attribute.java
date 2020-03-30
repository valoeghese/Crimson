package modfest.teamgreen.magic.attribute;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.IWorld;

public interface Attribute {
	/**
	 * @param world the world
	 * @param caster the caster. Null if the caster is a block.
	 * @return an int that can be passed on to following modifiers. Typically represents some kind of strength from 0-15.
	 */
	int activate(IWorld world, PlayerEntity caster, ModifyingAttribute modifier);
	/**
	 * @param world the world
	 * @param previous the int passed from the previous modifier
	 * @param caster the caster. Null if the caster is a block.
	 * @return an int that can be passed on to following modifiers. Typically represents some kind of strength from 0-15.
	 */
	int process(IWorld world, int previous, PlayerEntity caster, ModifyingAttribute modifier);
}
