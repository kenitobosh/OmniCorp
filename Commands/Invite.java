package Commands;

import Main.Main;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Invite extends ListenerAdapter
{
    public void onGuildMessageReceived(GuildMessageReceivedEvent event)
    {
        int timeString = 3600;
        String[] args = event.getMessage().getContentRaw().split("\\s+");

        if (args[0].equalsIgnoreCase(Main.prefix + "invite"))
        {
            event.getChannel().sendMessage("Link: " + event.getChannel().createInvite().setMaxAge(timeString).complete().getUrl()).queue();
        }
    }


}
