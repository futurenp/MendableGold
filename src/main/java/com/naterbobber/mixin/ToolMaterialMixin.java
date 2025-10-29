package com.naterbobber.mixin;

import com.naterbobber.init.MGComponents;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterial;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Unit;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ToolMaterial.class)
public class ToolMaterialMixin {

    @Inject(method = "applySwordSettings", at = @At("RETURN"), cancellable = true)
    private void addComponentToGoldSwords(Item.Settings settings, float attackDamage, float attackSpeed, CallbackInfoReturnable<Item.Settings> cir) {

        ToolMaterial self = (ToolMaterial) (Object) this;
        if (self == ToolMaterial.GOLD) {
            Item.Settings modifiedSettings = cir.getReturnValue();
            modifiedSettings.component(MGComponents.GOLD_MENDING, Unit.INSTANCE);
            cir.setReturnValue(modifiedSettings);
        }
    }

    @Inject(method = "applyToolSettings", at = @At("RETURN"), cancellable = true)
    private void addComponentToGoldTools(Item.Settings settings, TagKey<Block> effectiveBlocks, float attackDamage, float attackSpeed, float disableBlockingForSeconds, CallbackInfoReturnable<Item.Settings> cir) {

        ToolMaterial self = (ToolMaterial) (Object) this;
        if (self == ToolMaterial.GOLD) {
            Item.Settings modifiedSettings = cir.getReturnValue();
            modifiedSettings.component(MGComponents.GOLD_MENDING, Unit.INSTANCE);
            cir.setReturnValue(modifiedSettings);
        }
    }

    @Inject(method = "<clinit>", at = @At("TAIL"))
    private static void modifyGoldMaterial(CallbackInfo ci) {
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