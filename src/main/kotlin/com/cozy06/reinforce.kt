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
                player.sendMessage("1")
                p.isCancelled = true
                player.sendMessage("2")
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
                val l: MutableList<String> = ArrayList()
                if(reinforce == null) {
                    l.add("강화 확률:" + 100 + "%")
                }
                else {
                    l.add("강화 확률:" + (1/(reinforce*5))*100 + "%")
                }
                l.add("파괴 확률:" + (-20/((reinforce/2)-11)-0.4) + "%")
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
                    inv!!.setItem(3, reinforcestone)

                    var reinforcenum = inv!!.getItem(3)?.itemMeta?.lore().toString().length
                    player.sendMessage(" "+reinforcenum)
                    when(if(reinforcenum == 0) {0} else {randomfunc((1/(reinforcenum*5))*100)}){
                        0 -> {
                            val item = inv!!.getItem(3)?.type?.let { ItemStack(it, 1) }
                            val itemMeta = item?.itemMeta

                            if(reinforcenum == 0) {
                                if (itemMeta != null) {
                                    itemMeta.lore = listOf("${ChatColor.YELLOW}★")
                                }
                                else {
                                    var s = "★"
                                    for (i in 0..reinforcenum) {
                                        s = (s + "★")
                                    }
                                    if (itemMeta != null) {
                                        itemMeta.lore = listOf("${ChatColor.YELLOW}" + s)
                                    }
                                }
                            }
                            if (item != null) {
                                item.itemMeta = itemMeta //메타데이터 저장
                                inv!!.setItem(3, item)
                            }
                        }//성공
                        1 -> {
                            //val distory = (-20/((reinforcenum/2)-11)-0.4).toInt()
                            when(randomfunc((-20/((reinforcenum/2)-11)-0.4).toInt())) {
                                0 -> {return}
                                1 -> {
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