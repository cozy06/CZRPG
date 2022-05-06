package com.cozy06

import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.plugin.java.JavaPlugin

class Main: JavaPlugin() {
    var plugin: JavaPlugin? = null
    override fun onEnable() {
        logger.info("${ChatColor.GREEN}CZRPG plugin enabled")
        Bukkit.getPluginManager().registerEvents(join(), this)
        Bukkit.getPluginManager().registerEvents(reinforce(), this)
        Bukkit.getPluginManager().registerEvents(ability(), this)
        getCommand("party")?.setExecutor(party())
    }

    override fun onDisable() {
        logger.info("${ChatColor.RED}CZRPG plugin disabled")
    }
}


