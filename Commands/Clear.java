package Commands;

import Main.Main;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.List;

public class Clear extends ListenerAdapter
{
    public void onGuildMessageReceived(GuildMessageReceivedEvent event)
    {
        String[] args = event.getMessage().getContentRaw().split("\\s+");

        if (args[0].equalsIgnoreCase(Main.prefix + "clear"))
        {
            if (args.length < 2)
            {
                event.getChannel().sendMessage("~ + 'Message Amount'").queue();
            }
            else
            {
                try
                {
                    List<Message> messages = event.getChannel().getHistory().retrievePast(Integer.parseInt(args[1])).complete();
                    event.getChannel().deleteMessages(messages).queue();
                }
                catch (IllegalArgumentException e)
                {
                    if (e.toString().startsWith("java.lang.IllegalArgumentException: Message retrieval")) {
                        event.getChannel().sendMessage("Too Many Messages To Delete").queue();
                    } else {
                        event.getChannel().sendMessage("Message Too Old To Delete").queue();
                    }
                }
            }
        }
    }


}
