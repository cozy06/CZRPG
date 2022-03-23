package com.cozy06

import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack
import java.util.*
import kotlin.math.round


class reinforce: Listener {
    var inv: Inventory? = null

    @EventHandler
    fun reinforce(p: PlayerInteractEvent) {
        val player = p.player
        val action = p.action
        if(action == Action.RIGHT_CLICK_BLOCK) {
            if(p.clickedBlock!!.type == Material.ENCHANTING_TABLE) {
                p.isCancelled = true
                inv = Bukkit.createInventory(null, 9, "강화")
                val GLASS_PANE = ItemStack(Material.GRAY_STAINED_GLASS_PANE, 1) //다이아몬드 검 생성
                val GLASS_PANEMeta = GLASS_PANE.itemMeta //검의 메타데이터
                GLASS_PANEMeta.setDisplayName(" ") //검의 이름 설정
                GLASS_PANE.itemMeta = GLASS_PANEMeta //메타데이터 저장
                inv!!.setItem(0, GLASS_PANE)
                inv!!.setItem(1, GLASS_PANE)
                inv!!.setItem(2, GLASS_PANE)
                inv!!.setItem(4, GLASS_PANE)
                inv!!.setItem(6, GLASS_PANE)
                inv!!.setItem(7, GLASS_PANE)

                val item = ItemStack(Material.ANVIL, 1) //다이아몬드 검 생성
                val itemMeta = item.itemMeta //검의 메타데이터
                itemMeta.setDisplayName("강화!") //검의 이름 설정
                item.itemMeta = itemMeta //메타데이터 저장
                inv!!.setItem(8, item)
                player.openInventory(inv!!)
            }
        }
        else {return}
    }

    @EventHandler
    fun onInventoryClick(e: InventoryClickEvent) {   //인벤토리 클릭 시
        if (e.inventory !== inv) return  //이 인벤토리를 클릭한게 아니라면 취소
        else {
            if (inv!!.getItem(3) != null) {
                val item = ItemStack(Material.ANVIL, 1) //다이아몬드 검 생성
                val itemMeta = item.itemMeta //검의 메타데이터
                itemMeta.setDisplayName("강화!") //검의 이름 설정
                var reinforce = inv!!.getItem(3)?.itemMeta?.lore().toString().length
                var reinforcenum = (inv!!.getItem(3)?.itemMeta?.lore().toString().length) - 452
                var reinforceper = 20/reinforcenum
                var distroyper = -20/((reinforcenum/2)-11)-0.4
                e.whoClicked.sendMessage("" + reinforceper + "%")
                val l: MutableList<String> = ArrayList()
                if(reinforce == 4) {
                    l.add("강화 확률:" + 100 + "%")
                }
                else {
                    l.add("강화 확률:" + reinforceper + "%")
                }
                l.add("파괴 확률:" + distroyper + "%")
                itemMeta.lore = l
                item.itemMeta = itemMeta //메타데이터 저장
                inv!!.setItem(8, item)
            }
            val clickedItem = e.currentItem //클릭된 아이템
            //만약 클릭된 아이템이 없다면 취소
            if (clickedItem == null || clickedItem.type == Material.AIR) return
            val player = e.whoClicked as Player //클릭한 사람에게
            if (clickedItem.type == Material.GRAY_STAINED_GLASS_PANE){e.isCancelled = true}
            else if(clickedItem.type == Material.ANVIL) {
                e.isCancelled = true
                if(inv!!.getItem(5)?.type == Material.RED_DYE && inv!!.getItem(3)?.type != null) {

                    val reinforcestone = inv!!.getItem(5)?.type?.let { ItemStack(it, (inv!!.getItem(5)?.amount!!)-1) }
                    val reinforcestoneMeta = reinforcestone?.itemMeta
                    if (reinforcestone != null) {
                        reinforcestone.itemMeta = reinforcestoneMeta
                    } //메타데이터 저장
                    inv!!.setItem(5, reinforcestone)

                    var reinforcenum1 = inv!!.getItem(3)?.itemMeta?.lore().toString().length
                    var reinforcenum2 = (inv!!.getItem(3)?.itemMeta?.lore().toString().length) - 452
                    //player.sendMessage(" "+inv!!.getItem(3)?.itemMeta?.lore().toString())
                    when(if(reinforcenum1 == 4) {0} else {randomfunc(20/reinforcenum2)}){
                        0 -> {
                            player.sendMessage("성공")
                            val item = inv!!.getItem(3)
                            //?.type?.let { ItemStack(it, 1) }
                            val itemMeta = item?.itemMeta

                            if(reinforcenum1 == 4) {
                                if (itemMeta != null) {
                                    itemMeta.lore = listOf("★")
                                }
                            }
                            else {
                                var s = ""
                                for (i in 0..reinforcenum2) {
                                    s = (s + "★")
                                }
                                if (itemMeta != null) {
                                    itemMeta.lore = listOf(s)
                                }
                            }
                            if (item != null) {
                                item.itemMeta = itemMeta //메타데이터 저장
                                inv!!.setItem(3, item)
                            }
                        }//성공
                        1 -> {
                            player.sendMessage("실패"+ (-20/((reinforcenum2/2)-11)-0.4))
                            //val distory = (-20/((reinforcenum/2)-11)-0.4).toInt()
                            when(randomfunc((-20/((reinforcenum2/2)-11)-0.4).toInt())) {
                                1 -> {player.sendMessage("파괴X")}
                                0 -> {
                                    player.sendMessage("파괴O")
                                    val air = ItemStack(Material.AIR, 1) //다이아몬드 검 생성
                                    val airMeta = air.itemMeta
                                    inv!!.setItem(3, air)
                                }
                            }
                        }//실패
                    }
                }
            }
        }
    }

    fun randomfunc(percentage: Int): Int {
        val room = LongArray(100)
        for (count in 0..99) {
            if(count <= round(percentage.toDouble())) {
                room[count] = 0
            }
            else {room[count] = 1}
        }
        val roomnum = Random().nextInt(100)
        return room[roomnum].toInt()
    }
}