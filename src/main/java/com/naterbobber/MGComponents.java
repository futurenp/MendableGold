package com.naterbobber;

import com.mojang.serialization.Codec;
import com.naterbobber.MendableGold;
import net.minecraft.component.ComponentType;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Unit;

public class MGComponents {
    protected static void initialize(){

    }
    public static final ComponentType<Unit> GOLD_MENDING = Registry.register(
            Registries.DATA_COMPONENT_TYPE,
            Identifier.of(MendableGold.MOD_ID, "gold_mending"),
            ComponentType.<Unit>builder().codec(Codec.unit(Unit.INSTANCE)).build()
    );
}
