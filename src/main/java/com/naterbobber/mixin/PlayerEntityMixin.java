package com.naterbobber.mixin;

import com.naterbobber.MGComponents;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin {

    @Inject(method = "addExperience", at = @At("HEAD"), cancellable = true)
    private void implementCustomMending(int experience, CallbackInfo ci) {
        PlayerEntity player = (PlayerEntity) (Object) this;

        List<ItemStack> eligibleItems = new ArrayList<>();
        EquipmentSlot[] equipmentSlotList = EquipmentSlot.values();

        for(EquipmentSlot equipment : equipmentSlotList) {
            ItemStack item = player.getEquippedStack(equipment);
            if (item == null) continue;
            if (item.isDamaged() && item.contains(MGComponents.GOLD_MENDING)) {
                eligibleItems.add(item);
            }
        }

        if (eligibleItems.isEmpty()) {
            return;
        }

        Collections.shuffle(eligibleItems);
        ItemStack itemToRepair = eligibleItems.getFirst();

        int currentDamage = itemToRepair.getDamage();
        int repairedDamage = Math.min(experience * 2, currentDamage);

        itemToRepair.setDamage(currentDamage - repairedDamage);

        ci.cancel();
    }
}