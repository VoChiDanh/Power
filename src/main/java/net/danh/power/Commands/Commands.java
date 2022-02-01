package net.danh.power.Commands;

import net.danh.power.Manager.Data;
import net.danh.power.Power;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Commands implements CommandExecutor {


    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (label.equalsIgnoreCase("power")) {
            if (sender.hasPermission("power.admin")) {
                if (args.length == 0) {
                    sender.sendMessage(Data.getInstance().convert("&a" + Power.getInstance().getDescription().getName() + " v" + Power.getInstance().getDescription().getVersion()));
                    sender.sendMessage(Data.getInstance().convert("&a/Power set <player> <number> - Set amount of power"));
                    sender.sendMessage(Data.getInstance().convert("&a/Power add <player> <number> - Add power to player"));
                    sender.sendMessage(Data.getInstance().convert("&a/Power remove <player> <number> - Remove power to player"));
                    sender.sendMessage(Data.getInstance().convert("&a/MPower set <player> <number> - Set amount of max power"));
                    sender.sendMessage(Data.getInstance().convert("&a/MPower add <player> <number> - Add max power to player"));
                    sender.sendMessage(Data.getInstance().convert("&a/MPower remove <player> <number> - Remove max power of player"));
                    sender.sendMessage(Data.getInstance().convert("&a/Power check <player> - Check amount power of player"));
                    sender.sendMessage(Data.getInstance().convert("&a/Power reload - Reload config and language files"));
                    sender.sendMessage(Data.getInstance().convert("&a" + Power.getInstance().getDescription().getName() + " by " + Power.getInstance().getDescription().getAuthors().toString()));
                }
                if (args.length == 3) {
                    if (args[0].equalsIgnoreCase("set")) {
                        if (Bukkit.getPlayer(args[1]) != null) {
                            Data.getInstance().setPower(Bukkit.getPlayer(args[1]), Integer.parseInt(args[2]));
                            sender.sendMessage(Data.getInstance().convert(Data.getInstance().getlanguage().getString("POWER_SET")
                                    .replaceAll("%player%", Bukkit.getPlayer(args[1]).getName())
                                    .replaceAll("%power%", String.valueOf(args[2]))));
                        } else {
                            sender.sendMessage(Data.getInstance().convert(Data.getInstance().getlanguage().getString("PLAYER_NOT_FOUND")
                                    .replaceAll("%player%", Bukkit.getPlayer(args[1]).getName())));
                        }
                    }

                    if (args[0].equalsIgnoreCase("add")) {
                        if (Bukkit.getPlayer(args[1]) != null) {
                            Data.getInstance().addPower(Bukkit.getPlayer(args[1]), Integer.parseInt(args[2]));
                            sender.sendMessage(Data.getInstance().convert(Data.getInstance().getlanguage().getString("POWER_ADD")
                                    .replaceAll("%player%", Bukkit.getPlayer(args[1]).getName())
                                    .replaceAll("%power%", String.valueOf(args[2]))));
                        } else {
                            sender.sendMessage(Data.getInstance().convert(Data.getInstance().getlanguage().getString("PLAYER_NOT_FOUND")
                                    .replaceAll("%player%", Bukkit.getPlayer(args[1]).getName())));
                        }
                    }


                    if (args[0].equalsIgnoreCase("remove")) {
                        if (Bukkit.getPlayer(args[1]) != null) {
                            Data.getInstance().removePower(Bukkit.getPlayer(args[1]), Integer.parseInt(args[2]));
                            sender.sendMessage(Data.getInstance().convert(Data.getInstance().getlanguage().getString("POWER_REMOVE")
                                    .replaceAll("%player%", Bukkit.getPlayer(args[1]).getName())
                                    .replaceAll("%power%", String.valueOf(args[2]))));
                        } else {
                            sender.sendMessage(Data.getInstance().convert(Data.getInstance().getlanguage().getString("PLAYER_NOT_FOUND")
                                    .replaceAll("%player%", Bukkit.getPlayer(args[1]).getName())));
                        }
                    }
                }

                if (args.length == 2) {
                    if (args[0].equalsIgnoreCase("check")) {
                        if (Bukkit.getPlayer(args[1]) != null) {
                            sender.sendMessage(Data.getInstance().convert(Data.getInstance().getlanguage().getString("POWER_CHECK")
                                    .replaceAll("%player%", Bukkit.getPlayer(args[1]).getName())
                                    .replaceAll("%power%", String.valueOf(Data.getInstance().getPower(Bukkit.getPlayer(args[1]))))));
                        } else {
                            sender.sendMessage(Data.getInstance().convert(Data.getInstance().getlanguage().getString("PLAYER_NOT_FOUND")
                                    .replaceAll("%player%", Bukkit.getPlayer(args[1]).getName())));
                        }
                    }
                }
                if (args.length == 1) {
                    if (args[0].equalsIgnoreCase("reload")) {
                        Data.getInstance().reloadconfig();
                        sender.sendMessage(Data.getInstance().convert("&aReloaded"));
                    }
                }
            } else {
                sender.sendMessage(Data.getInstance().convert(Data.getInstance().getlanguage().getString("PLAYER_NOT_ENOUGH_PERM")));
            }
        }
        if (label.equalsIgnoreCase("mpower")) {
            if (sender.hasPermission("power.admin")) {
                if (args.length == 0) {
                    sender.sendMessage(Data.getInstance().convert("&a" + Power.getInstance().getDescription().getName() + " v" + Power.getInstance().getDescription().getVersion()));
                    sender.sendMessage(Data.getInstance().convert("&a/Power set <player> <number> - Set amount of power"));
                    sender.sendMessage(Data.getInstance().convert("&a/Power add <player> <number> - Add power to player"));
                    sender.sendMessage(Data.getInstance().convert("&a/Power remove <player> <number> - Remove power to player"));
                    sender.sendMessage(Data.getInstance().convert("&a/MPower set <player> <number> - Set amount of max power"));
                    sender.sendMessage(Data.getInstance().convert("&a/MPower add <player> <number> - Add max power to player"));
                    sender.sendMessage(Data.getInstance().convert("&a/MPower remove <player> <number> - Remove max power of player"));
                    sender.sendMessage(Data.getInstance().convert("&a/Power check <player> - Check amount power of player"));
                    sender.sendMessage(Data.getInstance().convert("&a/Power reload - Reload config and language files"));
                    sender.sendMessage(Data.getInstance().convert("&a" + Power.getInstance().getDescription().getName() + " by " + Power.getInstance().getDescription().getAuthors().toString()));
                }
                if (args.length == 3) {
                    if (args[0].equalsIgnoreCase("set")) {
                        if (Bukkit.getPlayer(args[1]) != null) {
                            Data.getInstance().setMaxPower(Bukkit.getPlayer(args[1]), Integer.parseInt(args[2]));
                            sender.sendMessage(Data.getInstance().convert(Data.getInstance().getlanguage().getString("MAX_POWER_SET")
                                    .replaceAll("%player%", Bukkit.getPlayer(args[1]).getName())
                                    .replaceAll("%power%", String.valueOf(args[2]))));
                        } else {
                            sender.sendMessage(Data.getInstance().convert(Data.getInstance().getlanguage().getString("PLAYER_NOT_FOUND")
                                    .replaceAll("%player%", Bukkit.getPlayer(args[1]).getName())));
                        }
                    }

                    if (args[0].equalsIgnoreCase("add")) {
                        if (Bukkit.getPlayer(args[1]) != null) {
                            Data.getInstance().addMaxPower(Bukkit.getPlayer(args[1]), Integer.parseInt(args[2]));
                            sender.sendMessage(Data.getInstance().convert(Data.getInstance().getlanguage().getString("MAX_POWER_ADD")
                                    .replaceAll("%player%", Bukkit.getPlayer(args[1]).getName())
                                    .replaceAll("%power%", String.valueOf(args[2]))));
                        } else {
                            sender.sendMessage(Data.getInstance().convert(Data.getInstance().getlanguage().getString("PLAYER_NOT_FOUND")
                                    .replaceAll("%player%", Bukkit.getPlayer(args[1]).getName())));
                        }
                    }


                    if (args[0].equalsIgnoreCase("remove")) {
                        if (Bukkit.getPlayer(args[1]) != null) {
                            Data.getInstance().removeMaxPower(Bukkit.getPlayer(args[1]), Integer.parseInt(args[2]));
                            sender.sendMessage(Data.getInstance().convert(Data.getInstance().getlanguage().getString("MAX_POWER_REMOVE")
                                    .replaceAll("%player%", Bukkit.getPlayer(args[1]).getName())
                                    .replaceAll("%power%", String.valueOf(args[2]))));
                        } else {
                            sender.sendMessage(Data.getInstance().convert(Data.getInstance().getlanguage().getString("PLAYER_NOT_FOUND")
                                    .replaceAll("%player%", Bukkit.getPlayer(args[1]).getName())));
                        }
                    }
                }
            } else {
                sender.sendMessage(Data.getInstance().convert(Data.getInstance().getlanguage().getString("PLAYER_NOT_ENOUGH_PERM")));
            }
        }
        return true;
    }
}
