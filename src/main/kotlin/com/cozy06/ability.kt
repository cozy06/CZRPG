package com.cozy06

import org.bukkit.Material
import org.bukkit.entity.ArmorStand
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.event.player.PlayerToggleSneakEvent


class ability: Listener {

    @EventHandler
    fun onPlayerToggleSneak(event: PlayerToggleSneakEvent) {
        val player = event.player
        if (player.isSneaking) {
            armorstand(player, Material.DIAMOND_SWORD)
            player.sendMessage("sss")
        }
    }

    @EventHandler
    fun click(p: PlayerInteractEvent) {
        val player = p.player
        val action = p.action
        var reinforcelvl = (player.itemInHand.itemMeta?.lore().toString().length) - 452
//        if(action == Action.RIGHT_CLICK_AIR && player.isSneaking && 20 >= reinforcelvl && reinforcelvl >= 10) {
//
//        }
        if(player.isSneaking) {
            armorstand(player, Material.DIAMOND_SWORD)
            player.sendMessage("sss")
        }
    }

    fun armorstand(p: Player, item: Material) {
        val armorStand = p.world.spawnEntity(p.getLocation().add(0.0, -1.0, 0.0), EntityType.ARMOR_STAND) as ArmorStand
        armorStand.isInvisible = false
        armorStand.isInvulnerable = false
        //armorStand.isSmall = true
        armorStand.helmet.type = item
        armorStand.headPose
        armorStand.setVelocity(p.location.direction.multiply(5))
    }


}