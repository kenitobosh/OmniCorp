package Commands;

import Main.Main;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.concurrent.TimeUnit;

public class Reminder extends ListenerAdapter
{
    public void onGuildMessageReceived(GuildMessageReceivedEvent event)
    {
        String[] args = event.getMessage().getContentRaw().split("\\s+");

        if (args[0].equalsIgnoreCase(Main.prefix + "reminder"))
        {
            if(args.length < 3)
            {
                event.getChannel().sendMessage("~reminder + TIME HOURS + MESSAGE").queue();
            }

            int time = Integer.parseInt(args[1]);
            String message = args[2];

            event.getChannel().sendMessage("Reminder Set!)").queue();
            

        }
    }

}
