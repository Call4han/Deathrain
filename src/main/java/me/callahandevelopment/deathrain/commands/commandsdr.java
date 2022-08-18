package me.callahandevelopment.deathrain.commands;

import me.callahandevelopment.deathrain.DeathRain;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;

import java.util.List;

public class commandsdr implements TabExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        PluginDescriptionFile ord = DeathRain.getPlugin().getDescription();
        String vers = ord.getVersion();
        if (sender instanceof Player){
            Player p = (Player) sender;
            if (args.length==0){
                p.sendMessage(ChatColor.GOLD+""+ ord.getDescription()   +    ord.getVersion()    +  ChatColor.DARK_BLUE+ " By Callahan ");
                p.sendMessage(ChatColor.GOLD + "Use /Deathrain reload for reload the config");

            }else if (args.length==1){
                if (p.hasPermission("deathrain.*")){
                if (args[0].equalsIgnoreCase("reload")){
                    DeathRain.getPlugin().reloadConfig();
                    p.sendMessage(ChatColor.GREEN + "Plugin reloaded successfully");
                }
            }else {
                    p.sendMessage(ChatColor.RED + "You are don´t permissions");
                }
            }else if(args.length>1){
                p.sendMessage(ChatColor.GOLD + ord.getDescription());

            }
            else if (args.length>2){
                p.sendMessage(ChatColor.GOLD + ord.getDescription());
            }

        }


        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        return null;
    }
}
