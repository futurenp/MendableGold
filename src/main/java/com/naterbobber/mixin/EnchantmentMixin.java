package com.naterbobber.mixin;


import com.naterbobber.data.MGTags;
import net.minecraft.component.ComponentMap;
import net.minecraft.component.EnchantmentEffectComponentTypes;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Enchantment.class)
public class EnchantmentMixin {

    @Shadow @Final private ComponentMap effects;

    @Inject(method = "isAcceptableItem", at = @At("HEAD"), cancellable = true)
    private void preventMendingOnGoldItems(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        if(this.effects.contains(EnchantmentEffectComponentTypes.REPAIR_WITH_XP)) {
            if(stack.isIn(MGTags.MENDABLE)){
                cir.setReturnValue(false);
            }
        }
    }
}
