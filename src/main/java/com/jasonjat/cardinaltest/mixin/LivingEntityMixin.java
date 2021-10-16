package com.jasonjat.cardinaltest.mixin;

import com.jasonjat.cardinaltest.cca.Components;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static com.jasonjat.cardinaltest.cca.Components.ABILITIES;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {
    public LivingEntityMixin(EntityType<?> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method="onDeath", at = @At("RETURN"))
    public void onDeath(DamageSource source, CallbackInfo ci) {
        Entity attacker = source.getAttacker();
        EntityType<?> entityType = this.getType();

        if (attacker instanceof PlayerEntity) {
           boolean unlockedTry = ABILITIES.get(attacker).unlock(entityType);
            ABILITIES.sync(attacker);
            String result = unlockedTry ? "Success adding a new entity called " + entityType.toString() : "This entity already exists within";
            System.out.println(result);
        }
    }
}
