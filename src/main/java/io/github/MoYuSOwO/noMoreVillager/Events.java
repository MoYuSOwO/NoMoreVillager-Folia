package io.github.MoYuSOwO.noMoreVillager;

import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityBreedEvent;
import org.bukkit.event.inventory.TradeSelectEvent;
import org.bukkit.inventory.Merchant;

import java.util.ArrayList;
import java.util.Objects;

public class Events implements Listener {

    @EventHandler
    public void onBreed(EntityBreedEvent event) {
        if (!(event.getEntity() instanceof Villager)) {
            return;
        }
        Villager baby = (Villager) event.getEntity();
        Location location = baby.getLocation();
        Chunk chunk = location.getChunk();
        Entity[] entities = chunk.getEntities();
        ArrayList<Villager> villagers = new ArrayList<>();
        for (Entity entity : entities) {
            if (entity instanceof Villager) {
                Villager villager = (Villager) entity;
                if (villager.getProfession() == Villager.Profession.NONE || !villager.isAdult()) {
                    villagers.add((Villager) entity);
                }
            }
        }
        if (villagers.size() >= NoMoreVillager.configManager.getForceDelete()) {
            int kill_number = villagers.size() - NoMoreVillager.configManager.getForceDelete();
            for (int i = 0; i < kill_number; i++) {
                Villager villager = villagers.get(i);
                villager.setHealth(0.0);
            }
        }
    }

    @EventHandler
    public void onPlayerTrade(TradeSelectEvent event) {
        Merchant merchant = event.getMerchant();
        Location location = Objects.requireNonNull(merchant.getTrader()).getLocation();
        Chunk chunk = location.getChunk();
        ArrayList<Villager> villagers = new ArrayList<>();
        Entity[] entities = chunk.getEntities();
        for (Entity entity : entities) {
            if (entity instanceof Villager) {
                Villager villager = (Villager) entity;
                if (villager.getProfession() == Villager.Profession.NONE || !villager.isAdult()) {
                    villagers.add((Villager) entity);
                }
            }
        }
        if (villagers.size() >= NoMoreVillager.configManager.getForceDelete()) {
            int kill_number = villagers.size() - NoMoreVillager.configManager.getForceDelete();
            for (int i = 0; i < kill_number; i++) {
                Villager villager = villagers.get(i);
                villager.setHealth(0.0);
            }
        }
    }
}
