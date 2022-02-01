package net.danh.power.Events;

import net.danh.power.Manager.Data;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class Break implements Listener {

    @EventHandler
    public void onBreak(BlockBreakEvent e) {
        Player p = e.getPlayer();
        String blocktype = e.getBlock().getType().toString();
        int power = Data.getInstance().getconfig().getInt("MINING.DEFAULT.POWER");
        int rpower = Data.getInstance().getconfig().getInt("MINING.DEFAULT.RPOWER");
        if (power == 0) {
            if (rpower == 0) {
                return;
            }
        }
        for (String getBlocktype : Data.getInstance().getconfig().getConfigurationSection("MINING.").getKeys(false)) {
            if (blocktype.equalsIgnoreCase(getBlocktype)) {
                power = Data.getInstance().getconfig().getInt("MINING." + blocktype + ".POWER");
                rpower = Data.getInstance().getconfig().getInt("MINING." + blocktype + ".RPOWER");
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
