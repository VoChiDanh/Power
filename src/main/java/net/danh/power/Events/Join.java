package net.danh.power.Events;

import net.danh.power.Manager.Data;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class Join implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        if (Data.getInstance().getPower(p) == 0 && Data.getInstance().getMaxPower(p) == 0) {
            Data.getInstance().setPower(p, Data.getInstance().getconfig().getInt("DEFAULT_POWER"));
            Data.getInstance().setMaxPower(p, Data.getInstance().getconfig().getInt("DEFAULT_MAX_POWER"));
            Data.getInstance().savedata();
        }
    }
}

