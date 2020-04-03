package modfest.teamgreen.block;

import net.minecraft.block.SaplingBlock;
import net.minecraft.block.sapling.SaplingGenerator;

public class CrimsonSaplingBlock extends SaplingBlock {
    protected CrimsonSaplingBlock(Settings settings) {
        super(new CrimsonSaplingGenerator(), settings);
    }
}
