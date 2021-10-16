package com.jasonjat.cardinaltest.cca;

import com.jasonjat.cardinaltest.CardinalTest;
import dev.onyxstudios.cca.api.v3.component.ComponentV3;
import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import net.fabricmc.fabric.api.util.NbtType;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.nbt.NbtString;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.ArrayList;
import java.util.List;

public class AbilityComponent implements AutoSyncedComponent, ComponentV3 {
    private final PlayerEntity player;
    private final List<Identifier> unlocked = new ArrayList<>();

    public AbilityComponent(PlayerEntity player) {
        this.player = player;
    }

    public boolean unlock(EntityType<?> type) {
        Identifier typeId = Registry.ENTITY_TYPE.getId(type);

        if (!unlocked.contains(typeId)) {
            unlocked.add(typeId);
            return true;
        }
        return false;
    }

    public List<Identifier> getUnlocked() {
        return unlocked;
    }

    public void clearList() {
        unlocked.clear();
        System.out.println("The list has been cleared!");
    }

    public void printList() {
        System.out.println("Printing lists...");
        unlocked.forEach(System.out::println);
    }

    @Override
    public void readFromNbt(NbtCompound tag) {
        unlocked.clear();

        NbtList idList = tag.getList("unlocked", NbtType.STRING);

        idList.forEach(idTag -> unlocked.add(new Identifier(idTag.asString())));
    }

    @Override
    public void writeToNbt(NbtCompound tag) {
        NbtList idList = new NbtList();

        unlocked.forEach(idTag -> {
            idList.add(NbtString.of(idTag.toString()));
        });

        tag.put("unlocked", idList);
    }

    @Override
    public boolean shouldSyncWith(ServerPlayerEntity player) {
        return player == this.player;
    }
}
