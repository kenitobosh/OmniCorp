package Commands;

import Events.Censorship;
import Main.Main;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.Objects;

public class Filter extends ListenerAdapter
{
    public void onGuildMessageReceived(GuildMessageReceivedEvent event)
    {
        String[] args = event.getMessage().getContentRaw().split("\\s+");

        if (args[0].equalsIgnoreCase(Main.prefix + "filter"))
        {
            Role role = event.getGuild().getRoleById("735696287701139507");
            if(Objects.requireNonNull(event.getMember()).getRoles().contains(role))
            {
                if(Censorship.button)
                {
                    Censorship.button = false;
                    event.getChannel().sendMessage("Censorship Disabled").queue();
                }
                else
                {
                    Censorship.button = true;
                    event.getChannel().sendMessage("Censorship Enabled").queue();
                }
            }
            else
            {
                event.getChannel().sendMessage("You Don't Have The Power!").queue();
            }

        }
    }


}
