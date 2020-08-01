package Commands;

import Events.Censorship;
import Main.Main;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.ArrayList;
import java.util.List;

public class Check extends ListenerAdapter
{
    public void onGuildMessageReceived(GuildMessageReceivedEvent event)
    {
        String[] args = event.getMessage().getContentRaw().split("\\s+");

        if (args[0].equalsIgnoreCase(Main.prefix + "check"))
        {
            List<String> banned = new ArrayList<>();

            for(int i = 0; i < Censorship.blacklist.size(); i++)
            {
                banned.add(Censorship.blacklist.get(i));
            }

            event.getChannel().sendMessage(banned.toString()).queue();
        }
    }

}
