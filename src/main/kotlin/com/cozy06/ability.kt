package com.cozy06

import org.bukkit.Material
import org.bukkit.entity.ArmorStand
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.event.player.PlayerToggleSneakEvent
import org.bukkit.inventory.ItemStack
import org.bukkit.util.EulerAngle


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
        val armorStand = p.world.spawnEntity(p.getLocation().add(0.0, 0.5, 0.0), EntityType.ARMOR_STAND) as ArmorStand
        armorStand.isInvisible = true
        armorStand.isInvulnerable = false
        armorStand.isSmall = true
        armorStand.setHelmet(ItemStack(item, 1))
        val headPoseyaw = p.player!!.eyeLocation.yaw
        p.player!!.eyeLocation.pitch
        armorStand.eyeLocation.yaw = headPoseyaw
        val headpose = EulerAngle(90.0, 45.0, 0.0)
        armorStand.headPose = headpose
        armorStand.setVelocity(p.location.direction.multiply(3))
        //땅에 닿으면 멈추기
    }

}