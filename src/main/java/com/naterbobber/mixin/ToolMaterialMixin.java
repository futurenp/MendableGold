package com.naterbobber.mixin;

import com.naterbobber.MendableGold;
import net.minecraft.item.ToolMaterial;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.ItemTags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ToolMaterial.class)
public class ToolMaterialMixin {

    @Inject(method = "<clinit>", at = @At("TAIL"))
    private static void modifyGoldMaterial(CallbackInfo ci) {
        if(MendableGold.CONFIG.ironLevelMinable()) {
            ToolMaterial newGoldMaterial = new ToolMaterial(
                    BlockTags.INCORRECT_FOR_IRON_TOOL,
                    32,
                    12.0F,
                    0.0F,
                    22,
                    ItemTags.GOLD_TOOL_MATERIALS
            );

            ToolMaterialAccessor.setGOLD(newGoldMaterial);
        }
    }
}