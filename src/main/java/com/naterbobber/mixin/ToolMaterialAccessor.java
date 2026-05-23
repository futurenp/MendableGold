package com.naterbobber.mixin;

import net.minecraft.world.item.ToolMaterial;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(ToolMaterial.class)
public interface ToolMaterialAccessor {

    @Accessor("GOLD")
    @Mutable
    static void setGOLD(ToolMaterial material) {
        throw new AssertionError();
    }
}