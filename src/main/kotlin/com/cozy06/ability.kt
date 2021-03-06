package com.cozy06

import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.ArmorStand
import org.bukkit.entity.Arrow
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.ProjectileHitEvent
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.event.player.PlayerToggleSneakEvent
import org.bukkit.inventory.ItemStack
import org.bukkit.plugin.java.JavaPlugin.getPlugin


class ability: Listener {

    var main: Main = Main.getPlugin<Main>(Main::class.java)

    @EventHandler
    fun onPlayerToggleSneak(event: PlayerToggleSneakEvent) {
        val player = event.player
        if (player.isSneaking) {
//            armorstand(player, Material.TNT)
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
//            armorstand(player, Material.DIAMOND_SWORD)
            player.sendMessage("sss")
        }
    }

    fun armorstand(p: Player, item: Material) {
        val armorStand = p.world.spawnEntity(p.getLocation().add(0.0, 0.5, 0.0), EntityType.ARMOR_STAND) as ArmorStand
        armorStand.isInvisible = true
        armorStand.isInvulnerable = false
        //armorStand.isSmall = true
        armorStand.setHelmet(ItemStack(item, 1))
        val headPoseyaw = p.player!!.eyeLocation.yaw
        p.player!!.eyeLocation.pitch
        armorStand.eyeLocation.yaw = headPoseyaw
        //val headpose = EulerAngle(90.0, 45.0, 0.0)
        //armorStand.headPose = headpose
        armorStand.setVelocity(p.location.direction.multiply(1))

        if(armorStand.helmet.type == Material.TNT) {
            val scheduler = Bukkit.getScheduler();
            main.let {
                scheduler.scheduleSyncDelayedTask(it, {
                    armorStand.world.createExplosion(armorStand.location, 1f)
                    armorStand.remove()
                }, 5*20L)
            }
        }
    }

    @EventHandler
    fun onProjectileHit(e: ProjectileHitEvent) { //????????? ????????? ??????
        if(e.entity.type == EntityType.ARROW) {
            val arrow = e.entity as Arrow //????????? ????????? ??????
            arrow.remove()
        }
//        val arrow = e.entity as Arrow //????????? ????????? ??????
//        arrow.world.createExplosion(arrow.location, 1f) //????????? ?????? ????????? ?????? ??????
//        arrow.remove() //?????? ??????
    }
}
