package net.danh.power.Events;

import net.danh.power.Manager.Data;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;

public class Fish implements Listener {

    @EventHandler
    public void onFish(PlayerFishEvent e) {
        Player p = e.getPlayer();
        String caughttype = e.getCaught().getType().toString();
        int power = Data.getInstance().getconfig().getInt("FISHING.DEFAULT.POWER");
        int rpower = Data.getInstance().getconfig().getInt("FISHING.DEFAULT.RPOWER");
        if (power == 0) {
            if (rpower == 0) {
                return;
            }
        }
        for (String getCaughttype : Data.getInstance().getconfig().getConfigurationSection("FISHING.").getKeys(false)) {
            if (caughttype.equalsIgnoreCase(getCaughttype)) {
                power = Data.getInstance().getconfig().getInt("FISHING." + caughttype + ".POWER");
                rpower = Data.getInstance().getconfig().getInt("FISHING." + caughttype + ".RPOWER");
                break;
            }
        }
        if (Data.getInstance().getPower(p) < rpower) {
            e.setCancelled(true);
            p.sendMessage(Data.getInstance().convert(Data.getInstance().getlanguage().getString("PLAYER_NOT_ENOUGH_POWER")));
        } else {
            Data.getInstance().removePower(p, power);
        }
    }
}
