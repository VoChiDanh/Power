package net.danh.power;

import net.danh.power.Commands.Commands;
import net.danh.power.Events.Break;
import net.danh.power.Events.Eat;
import net.danh.power.Events.Fish;
import net.danh.power.Events.Join;
import net.danh.power.Manager.Data;
import net.danh.power.Manager.Placeholder;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Iterator;
import java.util.logging.Level;

public final class Power extends JavaPlugin implements Listener {


    private static Power instance;

    public static Power getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;
        getServer().getPluginManager().registerEvents(new Join(), this);
        getServer().getPluginManager().registerEvents(new Break(), this);
        getServer().getPluginManager().registerEvents(new Eat(), this);
        getServer().getPluginManager().registerEvents(new Fish(), this);
        getCommand("power").setExecutor(new Commands());
        getCommand("mpower").setExecutor(new Commands());
        if (getServer().getPluginManager().isPluginEnabled("PlaceholderAPI")) {
            getLogger().log(Level.INFO, "Hooked with PlaceholderAPI");
            new Placeholder().register();
        } else {
            getLogger().log(Level.WARNING, "Cannot found PlaceholderAPI");
        }
        Data.getInstance().createconfig();
        if (Data.getInstance().getconfig().getDouble("CONFIG_VERSION") != 0.1) {
            getLogger().log(Level.WARNING, "You need remove old config!");
        }
        if (Data.getInstance().getlanguage().getDouble("LANGUAGE_VERSION") != 0.1) {
            getLogger().log(Level.WARNING, "You need remove old language!");
        }
        (new BukkitRunnable() {
            public void run() {
                Iterator var2 = Bukkit.getOnlinePlayers().iterator();
                while (var2.hasNext()) {
                    Player p = (Player) var2.next();
                    if (Data.getInstance().getMaxPower(p) < Data.getInstance().getPower(p)) {
                        Data.getInstance().setPower(p, Data.getInstance().getMaxPower(p));
                    }
                    if (Data.getInstance().getPower(p) < Data.getInstance().getconfig().getInt("MINIMUM_POWER")) {
                        p.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, Integer.MAX_VALUE, Integer.MIN_VALUE));
                    } else {
                        p.removePotionEffect(PotionEffectType.CONFUSION);
                    }
                }
            }
        }).runTaskTimer(this, 20L, 20L);
    }

    @Override
    public void onDisable() {
        Data.getInstance().savedata();
        Data.getInstance().savelanguage();
        Data.getInstance().saveconfig();
    }
}
