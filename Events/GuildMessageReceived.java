package Events;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.*;
import java.util.List;

public class GuildMessageReceived extends ListenerAdapter
{
    //Filter Event
    public static ArrayList<String> blacklist = new ArrayList<String>();
    public static boolean filter = true;

    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        // Delete Keywords
        String message = event.getMessage().getContentRaw();
        boolean censor = censorship(message);

        if(filter)
        {
            if(censor)
            {
                event.getMessage().delete().queue();
            }
        }

    }
    public static boolean censorship(String message) {
        for (String s : blacklist)
        {
            if (message.toLowerCase().contains(s))
            {
                return true;
            }
        }
        return false;
    }


}
