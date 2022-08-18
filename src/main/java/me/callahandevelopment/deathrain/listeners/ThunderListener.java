package me.callahandevelopment.deathrain.listeners;

import me.callahandevelopment.deathrain.DeathRain;
import me.callahandevelopment.deathrain.tasks.taskDeathrain;
import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scheduler.BukkitTask;

import java.util.List;


public class ThunderListener implements Listener {
    public DeathRain plugin;
    public ThunderListener(DeathRain plugin){
        this.plugin = plugin;
    }
    private BukkitTask task1;
    private BukkitTask task2;
    private static int TaskId;
    private static int TaskId2;
    @EventHandler
    public void onDeathplayer(PlayerDeathEvent e){
        Player p = e.getEntity();


                List<String> a  = plugin.getConfig().getStringList("worlds");

                for (String worlds : a){




                    World world1 = Bukkit.getWorld(worlds);
                    if (p.getWorld().isThundering()){
                        if (world1==p.getWorld()){
                            if (p.getWorld().hasStorm()) {
                                p.getWorld().setThunderDuration(p.getWorld().getThunderDuration() + plugin.getConfig().getInt("time-more-death-player"));
                                BukkitTask task1 = new taskDeathrain(DeathRain.getPlugin()).runTaskTimer(DeathRain.getPlugin(), 0L, 20L);
                                TaskId = task1.getTaskId();
                                for (Player pa : Bukkit.getOnlinePlayers()) {
                                    String message = plugin.getConfig().getString("storm-message-start");
                                    message = message.replace("%player%", pa.getDisplayName());

                                    if (plugin.getConfig().getBoolean("storm-message")) {

                                        pa.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
                                    }

                                }

                            }
                        }


                    }else {//don´t
                        if (Bukkit.getWorld(worlds)!=null){
                            String worldpl = p.getWorld().getName();
                            if (world1==p.getWorld()){
                                p.sendMessage(worldpl + worlds);


                                p.getWorld().setStorm(true);
                                p.getWorld().setThundering(true);
                                p.getWorld().setThunderDuration(plugin.getConfig().getInt("storm-base-initial"));
                            }

                        }

                    }







                }


            }











    public static void stopScheduler(int example){
        Bukkit.getScheduler().cancelTask(example);
    }
    @EventHandler
    public void onChangeWeather(WeatherChangeEvent e){
        long time = e.getWorld().getTime();
        if (time<12300||time>23850){
            stopScheduler(TaskId);

        }else {


        }


    }
    @EventHandler
    public void xd (PlayerRespawnEvent e){
        Player p = e.getPlayer();
        String name = p.getDisplayName();




                List<String> a  = plugin.getConfig().getStringList("worlds");
                for (String worlds : a){
                    World world1 = Bukkit.getWorld(worlds);
                    if (p.getWorld().isThundering()) {
                        if (world1 == p.getWorld()) {
                            if (p.getWorld().hasStorm()) {
                                if (plugin.getConfig().getBoolean("title-on-death")){
                                    p.sendTitle(ChatColor.translateAlternateColorCodes('&',plugin.getConfig().getString("title-on-death-message")),null,1,20,1);
                                }
                                if (plugin.getConfig().getBoolean("ban-on-death")){
                                    BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
                                    scheduler.scheduleSyncDelayedTask(DeathRain.getPlugin(), new Runnable() {
                                        @Override
                                        public void run() {
                                            Bukkit.getBanList(BanList.Type.NAME).addBan(p.getDisplayName(),ChatColor.translateAlternateColorCodes('&',plugin.getConfig().getString("message-ban")),null,"");
                                            p.kickPlayer(ChatColor.translateAlternateColorCodes('&',plugin.getConfig().getString("message-ban")));
                                        }
                                    },100L);







                                }



                            }
                        }
                    }







                }






            }













}
