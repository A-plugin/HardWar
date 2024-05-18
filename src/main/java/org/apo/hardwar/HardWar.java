package org.apo.hardwar;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.apo.hardwar.Commands.Commands;
import org.apo.hardwar.GUI.Upgrade;
import org.apo.hardwar.Listener.Listener;
import org.apo.hardwar.System.Avatar;
import org.apo.hardwar.Discord.Discord;
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
    public Configuration config= this.getConfig();


    @Override
    public void onEnable() {
        if (Instance==null){
            HardWar.Instance = this;
            getServer().getPluginManager().registerEvents(new Avatar(), this);
            getServer().getPluginManager().registerEvents(new Listener(), this);
            getServer().getPluginManager().registerEvents(new Upgrade(), this);
            Recipe recipe = new Recipe();
            recipe.R();


            jda = JDABuilder.createDefault("MTIyMzU2NTk2NTM2MzY0MjM3OA.Gfqkwd.mDmfTMlkP-FVC-iiQrJFVRoBoBzlu-hBllcKUk",
                            GatewayIntent.GUILD_MESSAGES,
                            GatewayIntent.MESSAGE_CONTENT,
                            GatewayIntent.GUILD_MEMBERS)
                    .addEventListeners(new Discord())
                    .setActivity(Activity.playing("하드코워"))
                    .setStatus(OnlineStatus.ONLINE)
                    .build();

            new Commands(this);

        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
