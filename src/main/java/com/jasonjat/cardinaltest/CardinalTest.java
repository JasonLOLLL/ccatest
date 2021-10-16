package com.jasonjat.cardinaltest;

import com.jasonjat.cardinaltest.networking.ModPacketsC2S;
import net.fabricmc.api.ModInitializer;

public class CardinalTest implements ModInitializer {

    public static final String MODID = "ct";

    @Override
    public void onInitialize() {
        ModPacketsC2S.register();
    }
}
