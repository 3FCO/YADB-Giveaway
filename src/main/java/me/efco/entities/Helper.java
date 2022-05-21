package me.efco.entities;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.entities.Message;

import java.awt.*;
import java.util.HashMap;

public class Helper {
    private static final Helper INSTANCE = new Helper();

    private Helper() {}

    public Message quickEmbedMessageBuilder(String text) {
        return new MessageBuilder().setEmbeds(
                    new EmbedBuilder().setTitle(text).setColor(Color.PINK).build())
                .allowMentions(Message.MentionType.USER, Message.MentionType.CHANNEL, Message.MentionType.ROLE)
                .build();
    }

    public static Helper getInstance() {
        return INSTANCE;
    }
}
