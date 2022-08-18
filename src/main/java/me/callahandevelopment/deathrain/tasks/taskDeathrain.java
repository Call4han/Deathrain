package me.callahandevelopment.deathrain.tasks;

import me.callahandevelopment.deathrain.DeathRain;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class taskDeathrain extends BukkitRunnable{
    DeathRain plugin;

    public taskDeathrain(DeathRain plugin) {
        this.plugin = plugin;
    }
    public static String getSeconds(int input) {
        int day = (int) TimeUnit.SECONDS.toDays(input);
        long hours = TimeUnit.SECONDS.toHours(input) - (day * 24);
        long minute = TimeUnit.SECONDS.toMinutes(input) - (TimeUnit.SECONDS.toHours(input) * 60);
        long second = TimeUnit.SECONDS.toSeconds(input) - (TimeUnit.SECONDS.toMinutes(input) * 60);
        //String xd = String.format("%02d:%02d:%02d",hours,minute,second);


        return String.format("%02d:%02d:%02d",hours,minute,second);
    }



    public void run() {
            List<String> a  = plugin.getConfig().getStringList("worlds");
            for (String worlds : a){






            if (Bukkit.getWorld(worlds)!=null){
                if (Bukkit.getWorld(worlds).isThundering()){
                    int thunderduration = Bukkit.getWorld(worlds).getThunderDuration()/20;
                    for (Player p : Bukkit.getOnlinePlayers()){

                        String mostrar = getSeconds(thunderduration);
                        String edit = DeathRain.getPlugin().getConfig().getString("message-of-bar");
                        edit = edit.replace("%timer%",mostrar);
                        p.spigot().sendMessage(ChatMessageType.ACTION_BAR,new TextComponent(ChatColor.translateAlternateColorCodes('&',edit)));
                        //p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.GRAY +"Quedan "  +mostrar +  " de tormenta"));
                    }
                }



            }else {

            }
            }




    }




}
