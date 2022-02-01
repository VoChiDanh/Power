package net.danh.power.Manager;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import net.danh.power.Power;
import org.bukkit.entity.Player;

public class Placeholder extends PlaceholderExpansion {
    @Override
    public String getAuthor() {
        return Power.getInstance().getDescription().getAuthors().toString();
    }

    @Override
    public String getVersion() {
        return Power.getInstance().getDescription().getVersion();
    }


    @Override
    public String getIdentifier() {
        return "power";
    }

    @Override
    public boolean persist() {
        return true;
    }

    @Override
    public String onPlaceholderRequest(Player p, String identifier) {
        if (p == null) {
            return "Player not online";
        }

        if (identifier.equalsIgnoreCase("amount")) {
            return String.valueOf(Data.getInstance().getPower(p));
        }
        if (identifier.equalsIgnoreCase("max")) {
            return String.valueOf(Data.getInstance().getMaxPower(p));
        }
        return null;
    }
}
