package Main;

import javax.security.auth.login.LoginException;

import Events.*;
import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;

public class Main
{
    public static JDA jda;
    public static String prefix = "~";

    // Main method
    public static void main(String[] args) throws LoginException
    {

        jda = new JDABuilder(AccountType.BOT).setToken("").build();
        jda.getPresence().setStatus(OnlineStatus.ONLINE);

        // Commands Listener
        jda.addEventListener(new Commands.Censor());
        jda.addEventListener(new Commands.Check());
        jda.addEventListener(new Commands.Clear());
        jda.addEventListener(new Commands.Filter());
        jda.addEventListener(new Commands.Info());
        jda.addEventListener(new Commands.Invite());
        jda.addEventListener(new Commands.Remove());

        //EventListener
        jda.addEventListener(new Censorship());


    }
}
