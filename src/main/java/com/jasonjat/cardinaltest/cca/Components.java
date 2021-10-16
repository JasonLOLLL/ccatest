package com.jasonjat.cardinaltest.cca;

import com.jasonjat.cardinaltest.CardinalTest;
import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistryV3;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentInitializer;
import dev.onyxstudios.cca.api.v3.entity.RespawnCopyStrategy;
import net.minecraft.util.Identifier;

public final class Components implements EntityComponentInitializer {
    public static final ComponentKey<AbilityComponent> ABILITIES = ComponentRegistryV3.INSTANCE.getOrCreate(new Identifier(CardinalTest.MODID, "abilitypls"), AbilityComponent.class);

    @Override
    public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry) {
        registry.registerForPlayers(ABILITIES, AbilityComponent::new, RespawnCopyStrategy.ALWAYS_COPY);
    }
}
