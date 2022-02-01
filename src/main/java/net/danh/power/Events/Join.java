package net.danh.power.Events;

import net.danh.power.Manager.Data;
import net.danh.power.Manager.Updater;
import net.danh.power.Power;
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
        if (p.isOp() || p.hasPermission("power.admin")) {
            new Updater(Power.getInstance(), 99682).getVersion(version -> {
                if (!Power.getInstance().getDescription().getVersion().equals(version)) {
                    p.sendMessage(Data.getInstance().convert("&cThere's a new update! The new version is v" + version + ", you are using v" + Power.getInstance().getDescription().getVersion()));
                    p.sendMessage(Data.getInstance().convert("&cDownload: https://www.spigotmc.org/resources/99682/"));
                }
            });
        }
    }
}

