package com.jasonjat.cardinaltest.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class CardinalTestClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        Keybinds.register();
    }
}
