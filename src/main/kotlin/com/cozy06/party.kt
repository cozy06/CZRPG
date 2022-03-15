package com.cozy06

import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class party: CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, strings: Array<out String>): Boolean {
        if (sender !is Player) return false
        val player = sender
        if (command != null) {
            when (strings[0]) {
                "create" -> {
                    val partyname = strings[1]
                    val partysc = player.scoreboard
                    val party = partysc.getTeam(partyname)
                    if(party == null){
                        partysc.registerNewTeam(partyname)
                        if (party != null) {
                            party.players.add(player)
                        }
                        sender.sendMessage("파티 생성 완료")
                    }
                    else {
                        if (party.players.isEmpty()){
                            if (party != null) {
                                party.players.add(player)
                            }
                            sender.sendMessage("파티 생성 완료")
                        }
                        else {
                            sender.sendMessage("같은 이름의 파티가 있습니다")
                        }
                    }
                }
                "delete" -> {
                    val partyname = strings[1]
                    val partysc = player.scoreboard
                    val party = partysc.getTeam(partyname)
                    if (party != null) {
                        if (party.players?.contains(player) == true){

                        }
                    }
                }
            }
        }
        return true
    }
}