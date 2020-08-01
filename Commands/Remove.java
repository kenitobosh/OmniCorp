package Commands;

import Events.Censorship;
import Main.Main;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.Objects;

public class Remove extends ListenerAdapter
{
    public void onGuildMessageReceived(GuildMessageReceivedEvent event)
    {
        String[] args = event.getMessage().getContentRaw().split("\\s+");

        if (args[0].equalsIgnoreCase(Main.prefix + "remove"))
        {
            String remove = args[1];
            Role role = event.getGuild().getRoleById("735696287701139507");

            if(Objects.requireNonNull(event.getMember()).getRoles().contains(role))
            {
                for(int i = 0; i < Censorship.blacklist.size(); i++)
                {
                    if(Censorship.blacklist.get(i).equalsIgnoreCase(remove))
                    {
                        //noinspection SuspiciousListRemoveInLoop
                        Censorship.blacklist.remove(i);
                    }
                }
            }
            else
            {
                event.getChannel().sendMessage("You Don't Have The Power!").queue();
            }

            event.getChannel().sendMessage("Censor Word Removed: " + remove).queue();
        }
    }

}
