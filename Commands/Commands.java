package Commands;

import Events.GuildMessageReceived;
import Main.Main;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import okhttp3.internal.http2.Http2Connection;

import java.util.List;

public class Commands extends ListenerAdapter
{

    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        int timeString = 3600;
        String[] args = event.getMessage().getContentRaw().split("\\s+");

        // Test Command
        if (args[0].equalsIgnoreCase(Main.prefix + "info")) {
            event.getChannel().sendMessage("I'M ALIVE").queue();
        }
        // Clear Command
        if (args[0].equalsIgnoreCase(Main.prefix + "clear")) {
            if (args.length < 2) {
                event.getChannel().sendMessage("~ + 'Message Amount'").queue();
            } else {
                try {
                    List<Message> messages = event.getChannel().getHistory().retrievePast(Integer.parseInt(args[1])).complete();
                    event.getChannel().deleteMessages(messages).queue();
                } catch (IllegalArgumentException e) {
                    if (e.toString().startsWith("java.lang.IllegalArgumentException: Message retrieval")) {
                        event.getChannel().sendMessage("Too Many Messages To Delete").queue();
                    } else {
                        event.getChannel().sendMessage("Message Too Old To Delete").queue();
                    }
                }
            }
        }
        // Add Censorship
        if (args[0].equalsIgnoreCase(Main.prefix + "censor")) {
            GuildMessageReceived.blacklist.add(args[1]);
            event.getChannel().sendMessage("Censor Word Added: " + args[1]).queue();
        }
        // Remove Censorship
        if (args[0].equalsIgnoreCase(Main.prefix + "remove")) {
            String remove = args[1];

            for(int i = 0; i < GuildMessageReceived.blacklist.size(); i++)
            {
                if(GuildMessageReceived.blacklist.get(i).equalsIgnoreCase(remove))
                {
                    //noinspection SuspiciousListRemoveInLoop
                    GuildMessageReceived.blacklist.remove(i);
                }
            }

            event.getChannel().sendMessage("Censor Word Removed: " + remove).queue();
        }
        // Filter Turn On/Off
        if (args[0].equalsIgnoreCase(Main.prefix + "filter")) {
            if(GuildMessageReceived.filter)
            {
                GuildMessageReceived.filter = false;
                event.getChannel().sendMessage("Censorship Disabled").queue();
            }
            else
            {
                GuildMessageReceived.filter = true;
                event.getChannel().sendMessage("Censorship Enabled").queue();
            }
        }
        // Invite Command
        if (args[0].equalsIgnoreCase(Main.prefix + "invite")) {
            event.getChannel().sendMessage("Link: " + event.getChannel().createInvite().setMaxAge(timeString).complete().getUrl()).queue();
        }
        // Purge Commnad
        if (args[0].equalsIgnoreCase(Main.prefix + "purge")) {
            try {
                List<Message> messages = event.getChannel().getHistory().retrievePast(100).complete();
                event.getChannel().deleteMessages(messages).queue();
            } catch (IllegalArgumentException e) {
                if (e.toString().startsWith("java.lang.IllegalArgumentException: Message retrieval")) {
                    event.getChannel().sendMessage("Too Many Messages To Delete").queue();
                } else {
                    event.getChannel().sendMessage("Message Too Old To Delete").queue();
                }
            }
        }
    }
}
