package com.cozy06

import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.inventory.ItemStack
import java.util.*

class join: Listener {
    @EventHandler
    fun onPlayerJoin(p: PlayerJoinEvent) {
        val item = ItemStack(Material.RED_DYE, 1) //다이아몬드 검 생성
        val itemMeta = item.itemMeta //검의 메타데이터
        itemMeta.setDisplayName("§e주문서") //검의 이름 설정
        itemMeta.setCustomModelData(1)
        itemMeta.lore = listOf("우클릭하여 사용") //검의 설명 설정
        item.itemMeta = itemMeta //메타데이터 저장

        if (p.player.inventory.contains(item)) return else {
            p.player.inventory.addItem(item)
        }
    }

}