package Events;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.ArrayList;

public class Censorship extends ListenerAdapter
{
    public static ArrayList<String> blacklist = new ArrayList<String>();
    public static boolean button = true;

    public void onGuildMessageReceived(GuildMessageReceivedEvent event)
    {

        String message = event.getMessage().getContentRaw();

        // Delete Keywords
        boolean censor = censorship(message);
        if(button)
        {
            if(censor)
            {
                event.getMessage().delete().queue();
            }
        }

    }

    public static boolean censorship(String message)
    {
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
