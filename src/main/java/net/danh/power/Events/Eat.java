package net.danh.power.Events;

import net.danh.power.Manager.Data;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;

public class Eat implements Listener {

    @EventHandler
    public void onEat(PlayerItemConsumeEvent e) {
        Player p = e.getPlayer();
        if (e.getItem().getType() == Material.POTION) {
            Data.getInstance().addPower(p, Data.getInstance().getconfig().getInt("RESTORE_POWER"));
        }
    }
}
