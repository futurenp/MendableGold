package com.naterbobber.mixin;

import com.naterbobber.MGComponents;
import net.minecraft.component.ComponentType;
import net.minecraft.item.Item;
import net.minecraft.item.equipment.ArmorMaterial;
import net.minecraft.item.equipment.ArmorMaterials;
import net.minecraft.item.equipment.EquipmentType;
import net.minecraft.util.Unit;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


@Mixin(Item.Settings.class)
public abstract class ItemMixin {

    @Shadow public abstract <T> Item.Settings component(ComponentType<T> type, T value);

    @Inject(method = "armor", at = @At("RETURN"), cancellable = true)
    public void addComponentToGoldArmor(ArmorMaterial material, EquipmentType type, CallbackInfoReturnable<Item.Settings> cir) {
        if(material == ArmorMaterials.GOLD) {
            Item.Settings settings = cir.getReturnValue();
            settings.component(MGComponents.GOLD_MENDING, Unit.INSTANCE);
            cir.setReturnValue(settings);
        }
    }

}
