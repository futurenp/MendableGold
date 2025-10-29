package com.naterbobber.init;

import com.mojang.serialization.Codec;
import net.minecraft.component.ComponentType;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.util.Unit;

public class MGComponents {
    public static final ComponentType<Unit> GOLD_MENDING = ComponentType.<Unit>builder()
            .codec(Codec.unit(Unit.INSTANCE))
            .packetCodec(PacketCodec.unit(Unit.INSTANCE))
            .build();
}
