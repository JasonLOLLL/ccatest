package com.jasonjat.cardinaltest.client;

import com.jasonjat.cardinaltest.networking.ModPackets;
import io.netty.buffer.Unpooled;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.network.PacketByteBuf;
import org.lwjgl.glfw.GLFW;

@Environment(EnvType.CLIENT)
public class Keybinds {
    public static KeyBinding keybinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "key.cardinaltest.bind1",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_R,
            "key.cardinaltest.custombindings"
    ));

    public static KeyBinding keyBinding2 = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "key.cardinaltest.bind2",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_V,
            "key.cardinaltest.custombindings"
    ));

    public static void register() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (keybinding.wasPressed()) {
                ClientPlayNetworking.send(ModPackets.KEYBIND_PACKET, new PacketByteBuf(Unpooled.buffer().writeByte(1)));
            }
            while (keyBinding2.wasPressed()) {
                ClientPlayNetworking.send(ModPackets.KEYBIND_PACKET, new PacketByteBuf(Unpooled.buffer().writeByte(2)));
            }
        });
    }
}
