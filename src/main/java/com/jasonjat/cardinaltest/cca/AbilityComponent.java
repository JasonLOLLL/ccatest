package com.jasonjat.cardinaltest.cca;

import dev.onyxstudios.cca.api.v3.component.ComponentV3;
import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import net.fabricmc.fabric.api.util.NbtType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.nbt.NbtString;
import net.minecraft.server.network.ServerPlayerEntity;

import java.util.ArrayList;
import java.util.List;

public class AbilityComponent implements AutoSyncedComponent, ComponentV3 {
    private final PlayerEntity player;
    private final List<String> unlocked = new ArrayList<>();

    public AbilityComponent(PlayerEntity player) {
        this.player = player;
    }

//    public boolean unlock(EntityType<?> type) {
//        Identifier typeId = Registry.ENTITY_TYPE.getId(type);
//
//        if (!unlocked.contains((typeId))) {
//            unlocked.add(typeId);
//            return true;
//        }
//        return false;
//    }

    @Override
    public void readFromNbt(NbtCompound tag) {
        unlocked.clear();

        NbtList idList = tag.getList("unlocked", NbtType.STRING);

        idList.forEach(idTag -> unlocked.add(idTag.asString()));
    }

    @Override
    public void writeToNbt(NbtCompound tag) {
        NbtList idList = new NbtList();

        unlocked.forEach(idTag -> {
            idList.add(NbtString.of(idTag));
        });

        tag.put("unlocked", idList);
    }

    @Override
    public boolean shouldSyncWith(ServerPlayerEntity player) {
        return player == this.player;
    }
}
