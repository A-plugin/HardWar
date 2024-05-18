package org.apo.hardwar.Discord;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.apo.hardwar.HardWar;
import org.apo.hardwar.Listener.Listener;

import java.awt.*;

public class Discord extends ListenerAdapter {

    HardWar hardWar=HardWar.Instance;

    @Override
    public void onMessageReceived(MessageReceivedEvent e) {
        if (e.getAuthor().isBot()) return;

        String content = e.getMessage().getContentRaw();
        String[] args= e.getMessage().getContentRaw().split(" ");

        if (args[0].equals("!등록")) {
            if (args.length == 1) {
                EmbedBuilder em = new EmbedBuilder()
                        .setTitle("!등록 [<플레이어 닉네임>]")
                        .setColor(Color.RED);
                e.getMessage().replyEmbeds(em.build()).queue();
            } if (args.length>=2) {
                if (hardWar.getConfig().getString(args[1])==null) {
                    EmbedBuilder em = new EmbedBuilder()
                            .setTitle("등록 완료!")
                            .setColor(Color.GREEN)
                            .setDescription(args[1])
                            .setThumbnail("https://cravatar.eu/helmavatar/" + args[1] + "/64")
                            .setFooter("등록됨", "https://png.pngtree.com/png-vector/20191113/ourmid/pngtree-green-check-mark-icon-flat-style-png-image_1986021.jpg");

                    hardWar.getConfig().set(args[1], e.getMember().getId());
                    hardWar.saveConfig();
                    e.getMessage().replyEmbeds(em.build()).queue();
                } else {
                    EmbedBuilder em = new EmbedBuilder()
                            .setTitle("이미 등록이 완료된 유저입니다!")
                            .setColor(Color.RED);
                    e.getMessage().replyEmbeds(em.build()).queue();
                }
            }
        }
        if (args[0].equals("!상태")) {
            if (args.length == 1) {
                EmbedBuilder em = new EmbedBuilder()
                        .setTitle("!상태 [<플레이어 닉네임>]")
                        .setColor(Color.RED);
                e.getMessage().replyEmbeds(em.build()).queue();
            } if (args.length>=2){
                Listener listener=new Listener();
                String a = args[1];
                String s = (String) hardWar.getConfig().get(listener.name(a));
                if (s!=null){

                    if (s.contains("D")) {
                        EmbedBuilder em = new EmbedBuilder()
                                .setTitle("상태")
                                .setColor(Color.BLACK)
                                .setDescription(listener.name(a) + "의 상태 | 사망")
                                .setThumbnail("https://cravatar.eu/helmavatar/" + listener.name(a) + "/64");
                        e.getMessage().replyEmbeds(em.build()).queue();
                    } else if (s.contains("A")) {
                        EmbedBuilder em = new EmbedBuilder()
                                .setTitle("상태")
                                .setColor(Color.WHITE)
                                .setDescription(listener.name(a) + "의 상태 | 정상")
                                .setThumbnail("https://cravatar.eu/helmavatar/" + listener.name(a) + "/64");
                        e.getMessage().replyEmbeds(em.build()).queue();
                    } else {
                        EmbedBuilder em = new EmbedBuilder()
                                .setTitle("상태")
                                .setColor(Color.GRAY)
                                .setDescription(listener.name(a) + "의 상태 | 알 수 없음")
                                .setThumbnail("https://cravatar.eu/helmavatar/" + listener.name(a) + "/64")
                                .setFooter("플레이어의 닉네임이 잘못됬거나 등록하진 플레이어일 수 있습니다.");
                        e.getMessage().replyEmbeds(em.build()).queue();
                    }
                } else {
                    EmbedBuilder em = new EmbedBuilder()
                            .setTitle("상태")
                            .setColor(Color.GRAY)
                            .setDescription(listener.name(a) + "의 상태 | 알 수 없음")
                            .setThumbnail("https://cravatar.eu/helmavatar/" + listener.name(a) + "/64")
                            .setFooter("플레이어의 닉네임이 잘못됬거나 등록하진 플레이어일 수 있습니다.");
                    e.getMessage().replyEmbeds(em.build()).queue();
                }
            }
        }
    }
}
