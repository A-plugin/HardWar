package org.apo.hardwar;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.apo.hardwar.GUI.Upgrade;
import org.apo.hardwar.Listener.Listener;
import org.apo.hardwar.System.Avatar;
import org.apo.hardwar.System.Discord;
import org.apo.hardwar.System.ItemD;
import org.apo.hardwar.System.Recipe;
import org.bukkit.configuration.Configuration;
import org.bukkit.plugin.java.JavaPlugin;

public final class HardWar extends JavaPlugin {

    public static HardWar Instance;
    public static JDA jda;
    public JDA dj() {
        return jda;
    }
    Configuration config= this.getConfig();

    public String token= config.getString("Dtoken");

    @Override
    public void onEnable() {
        if (Instance==null){
            HardWar.Instance = this;
            getServer().getPluginManager().registerEvents(new Avatar(), this);
            getServer().getPluginManager().registerEvents(new Listener(), this);
            getServer().getPluginManager().registerEvents(new Upgrade(), this);
            Recipe recipe = new Recipe();
            recipe.R();


            jda = JDABuilder.createDefault(token,
                            GatewayIntent.GUILD_MESSAGES,
                            GatewayIntent.MESSAGE_CONTENT,
                            GatewayIntent.GUILD_MEMBERS)
                    .addEventListeners(new Discord())
                    .setActivity(Activity.playing("하드코워"))
                    .setStatus(OnlineStatus.ONLINE)
                    .build();

            jda.upsertCommand("등록","자신의 디스코드와 마인크래프트 계정을 연동시킵니다.").queue();

            ItemD d=new ItemD();
            d.set();
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
