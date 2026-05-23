package com.naterbobber.mixin;

import com.naterbobber.data.MGTags;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin {

    @ModifyVariable(method = "addExperience", at = @At("HEAD"), argsOnly = true)
    private int mendableGold$modifyExperience(int experience) {
        PlayerEntity player = (PlayerEntity) (Object) this;

        List<ItemStack> eligibleItems = new ArrayList<>();
        EquipmentSlot[] equipmentSlotList = EquipmentSlot.values();

        for(EquipmentSlot equipment : equipmentSlotList) {
            ItemStack itemStack = player.getEquippedStack(equipment);
            if (itemStack == null) continue;
            Item item = itemStack.getItem();

            if (itemStack.isIn(MGTags.MENDABLE) && itemStack.isDamaged()){
                eligibleItems.add(itemStack);
            }
        }

        if (eligibleItems.isEmpty()) {
            return experience;
        }

        Collections.shuffle(eligibleItems);
        ItemStack itemToRepair = eligibleItems.getFirst();

        float repairMultiplier = 2F;
        int currentDamage = itemToRepair.getDamage();
        int repairAmount = (int) (experience * repairMultiplier);
        int repairedDamage = Math.min(repairAmount, currentDamage);

        if(repairAmount > currentDamage) {
            experience = (int) ((repairAmount - currentDamage) / repairMultiplier);
        } else {
            experience = 0;
        }

        itemToRepair.setDamage(currentDamage - repairedDamage);

        return experience;
    }
}