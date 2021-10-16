package com.jasonjat.cardinaltest.networking;

import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;

import static com.jasonjat.cardinaltest.cca.Components.ABILITIES;

public class ModPacketsC2S {
    public static void register() {
        ServerPlayNetworking.registerGlobalReceiver(ModPackets.KEYBIND_PACKET, ModPacketsC2S::keybindMethod);
    }

    private static void keybindMethod(MinecraftServer minecraftServer, ServerPlayerEntity serverPlayerEntity, ServerPlayNetworkHandler serverPlayNetworkHandler, PacketByteBuf packetByteBuf, PacketSender packetSender) {
        int result = packetByteBuf.readByte();
        switch (result) {
            case 1:
                ABILITIES.get(serverPlayerEntity).clearList();
            case 2:
                ABILITIES.get(serverPlayerEntity).printList();
        }
    }
}
