package Main;

import javax.security.auth.login.LoginException;

import Commands.Commands;
import Events.GuildMessageReceived;
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
        // Have fun with my token ;D
        jda = new JDABuilder(AccountType.BOT).setToken("NzM1MTU3NzY4Mzk0MjQ0MTA2.XxcXfQ.dEF4XGkc5m0KiFsDqCPiUChKiPY").build();
        jda.getPresence().setStatus(OnlineStatus.ONLINE);

        jda.addEventListener(new Commands());
        jda.addEventListener(new GuildMessageReceived());

    }
}
