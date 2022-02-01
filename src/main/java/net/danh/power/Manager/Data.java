package net.danh.power.Manager;

import net.danh.power.Power;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class Data {

    private static Data instance;
    private File configFile, langFile, dataFile;
    private FileConfiguration config, language, data;

    public static Data getInstance() {

        if (instance == null) {
            instance = new Data();
        }
        return instance;
    }

    public void createconfig() {
        configFile = new File(Power.getInstance().getDataFolder(), "config.yml");
        langFile = new File(Power.getInstance().getDataFolder(), "language.yml");
        dataFile = new File(Power.getInstance().getDataFolder(), "data.yml");

        if (!configFile.exists()) Power.getInstance().saveResource("config.yml", false);
        if (!langFile.exists()) Power.getInstance().saveResource("language.yml", false);
        if (!dataFile.exists()) Power.getInstance().saveResource("data.yml", false);
        config = new YamlConfiguration();
        language = new YamlConfiguration();
        data = new YamlConfiguration();

        try {
            config.load(configFile);
            language.load(langFile);
            data.load(dataFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }


    public FileConfiguration getconfig() {
        return config;
    }

    public FileConfiguration getlanguage() {
        return language;
    }

    public FileConfiguration getdata() {
        return data;
    }

    public void reloadconfig() {
        saveconfig();
        config = YamlConfiguration.loadConfiguration(configFile);
        savelanguage();
        language = YamlConfiguration.loadConfiguration(langFile);
    }

    public void saveconfig() {
        try {
            config.save(configFile);
        } catch (IOException ignored) {
        }
    }


    public void savelanguage() {
        try {
            language.save(langFile);
        } catch (IOException ignored) {
        }
    }


    public void savedata() {
        try {
            data.save(dataFile);
        } catch (IOException ignored) {
        }
    }

    public int getPower(Player p) {
        return getdata().getInt("players." + p.getName() + ".Power");
    }

    public void setPower(Player p, int number) {
        getdata().set("players." + p.getName() + ".Power", number);
        savedata();
    }

    public void addPower(Player p, int number) {
        getdata().set("players." + p.getName() + ".Power", getPower(p) + number);
        savedata();
    }

    public void removePower(Player p, int number) {
        if (getPower(p) > number) {
            getdata().set("players." + p.getName() + ".Power", getPower(p) - number);
            savedata();
        } else {
            getdata().set("players." + p.getName() + ".Power", 0);
            savedata();
        }
    }

    public int getMaxPower(Player p) {
        return getdata().getInt("players." + p.getName() + ".MaxPower");
    }

    public void setMaxPower(Player p, int number) {
        getdata().set("players." + p.getName() + ".MaxPower", number);
        savedata();
    }

    public void addMaxPower(Player p, int number) {
        getdata().set("players." + p.getName() + ".MaxPower", getMaxPower(p) + number);
        savedata();
    }

    public void removeMaxPower(Player p, int number) {
        getdata().set("players." + p.getName() + ".MaxPower", getMaxPower(p) - number);
        savedata();
    }

    public String convert(String s) {
        return s.replaceAll("&", "§");
    }
}
