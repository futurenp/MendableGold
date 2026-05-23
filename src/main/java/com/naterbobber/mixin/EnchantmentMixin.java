package com.naterbobber.mixin;


import com.naterbobber.data.MGTags;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentEffectComponents;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Enchantment.class)
public class EnchantmentMixin {

    @Shadow @Final private DataComponentMap effects;

    @Inject(method = "isSupportedItem", at = @At("HEAD"), cancellable = true)
    private void preventMendingOnGoldItemsSupport(ItemStack item, CallbackInfoReturnable<Boolean> cir) {
        if(this.effects.has(EnchantmentEffectComponents.REPAIR_WITH_XP)) {
            if(item.is(MGTags.MENDABLE)){
                cir.setReturnValue(false);
            }
        }
    }

    @Inject(method = "canEnchant", at = @At("HEAD"), cancellable = true)
    private void preventMendingOnGoldItemsEnchant(ItemStack item, CallbackInfoReturnable<Boolean> cir) {
        if(this.effects.has(EnchantmentEffectComponents.REPAIR_WITH_XP)) {
            if(item.is(MGTags.MENDABLE)){
                cir.setReturnValue(false);
            }
        }
    }
}
