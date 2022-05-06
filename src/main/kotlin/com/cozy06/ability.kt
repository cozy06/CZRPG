package com.cozy06

import org.bukkit.Bukkit
import org.bukkit.ChatColor
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
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack
import org.bukkit.plugin.Plugin
import org.bukkit.scheduler.BukkitRunnable


class ability: Listener {

    var l: Main = Main()
    ///val plugin = l.

    @EventHandler
    fun onPlayerToggleSneak(event: PlayerToggleSneakEvent) {
        val player = event.player
        if (player.isSneaking) {
            armorstand(player, Material.TNT)
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
        //armorStand.isSmall = true
        armorStand.setHelmet(ItemStack(item, 1))
        val headPoseyaw = p.player!!.eyeLocation.yaw
        p.player!!.eyeLocation.pitch
        armorStand.eyeLocation.yaw = headPoseyaw
        //val headpose = EulerAngle(90.0, 45.0, 0.0)
        //armorStand.headPose = headpose
        armorStand.setVelocity(p.location.direction.multiply(1))

        if(armorStand.helmet.type == Material.TNT) {
//            l.delayschedule(5,
//                {
//                    armorStand.world.createExplosion(armorStand.location, 1f)
//                    armorStand.remove()
//                }
//            )

            val scheduler = Bukkit.getScheduler();
            l.plugin?.let {
                scheduler.scheduleSyncDelayedTask(it, {
                    armorStand.world.createExplosion(armorStand.location, 1f)
                    armorStand.remove()
                }, 5*20L)
            }
        }
    }



    @EventHandler
    fun onProjectileHit(e: ProjectileHitEvent) { //투사체 착탄시 실행
        if(e.entity.type == EntityType.ARROW) {
            val arrow = e.entity as Arrow //객체를 화살로 변환
            arrow.remove()
        }
//        val arrow = e.entity as Arrow //객체를 화살로 변환
//        arrow.world.createExplosion(arrow.location, 1f) //화살의 착탄 위치에 폭발 생성
//        arrow.remove() //화살 삭제
    }
}
