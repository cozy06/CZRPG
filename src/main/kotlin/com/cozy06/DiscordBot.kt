package com.cozy06

import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.OnlineStatus
import net.dv8tion.jda.api.entities.ChannelType
import net.dv8tion.jda.api.events.GenericEvent
import net.dv8tion.jda.api.events.ReadyEvent
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import java.util.EventListener
import javax.security.auth.login.LoginException


class DiscordBot: EventListener {
    var token: String? = null
    //    @JvmStatic
    @Throws(LoginException::class)
    fun main(args: Array<String>) {
        token = "OTcyMjE5NTI3Mzg1NDY0ODcy.YnV3_g.-9dl_Waec5BGBPO64q7w7MLlbgY"
        val jda: JDA = JDABuilder.createDefault(token).addEventListeners(DiscordBot()).build()
        jda.presence.setStatus(OnlineStatus.ONLINE)
        //You can also add event listeners to the already built JDA instance
        // Note that some events may not be received if the listener is added after calling build()
        // This includes events such as the ReadyEvent
    }

    fun onEvent(event: GenericEvent) {
        if(event is ReadyEvent) {
            System.out.println("ready")
        }
    }

//    fun onMessageReceived(event: MessageReceivedEvent) {
//        if (event.isFromType(ChannelType.PRIVATE)) {
//            System.out.printf(
//                "[PM] %s: %s\n", event.getAuthor().getName(),
//                event.getMessage().getContentDisplay()
//            )
//        } else {
//            System.out.printf(
//                "[%s][%s] %s: %s\n", event.getGuild().getName(),
//                event.getTextChannel().getName(), event.getMember().getEffectiveName(),
//                event.getMessage().getContentDisplay()
//            )
//        }
//    }
}