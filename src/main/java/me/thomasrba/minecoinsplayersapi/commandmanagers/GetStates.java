package me.thomasrba.minecoinsplayersapi.commandmanagers;

import me.thomasrba.minecoinsplayersapi.MineCoinsPlayersAPI;
import me.thomasrba.minecoinsplayersapi.playermanager.PlayerState;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class GetStates implements CommandExecutor {

    MineCoinsPlayersAPI mineCoinsPlayersAPI;
    public GetStates(MineCoinsPlayersAPI main){
        this.mineCoinsPlayersAPI = main;
    }
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, java.lang.String s, java.lang.String[] strings) {
        for (PlayerState playerState: this.mineCoinsPlayersAPI.playerManagers.playerStates.values()){
            commandSender.sendMessage(String.valueOf(playerState.getUuid()));
            commandSender.sendMessage(String.valueOf(playerState.getPsuedo()));
            commandSender.sendMessage(String.valueOf(playerState.getGradeId()));
            commandSender.sendMessage(String.valueOf(playerState.getMoney()));
            commandSender.sendMessage(String.valueOf(playerState.getBoutiquePts()));
        }
        return false;
    }
}