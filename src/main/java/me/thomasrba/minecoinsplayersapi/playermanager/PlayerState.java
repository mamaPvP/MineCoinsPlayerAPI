package me.thomasrba.minecoinsplayersapi.playermanager;

import me.thomasrba.minecoinsplayersapi.MineCoinsPlayersAPI;
import org.bukkit.entity.Player;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class PlayerState {
    private final MineCoinsPlayersAPI main;
    private final UUID uuid;
    private final String psuedo;
    private final Player player;
    private int money;
    private int gradeId;
    private int boutiquePts;

    public PlayerState(MineCoinsPlayersAPI main, UUID uuid, Player p) {
        this.main = main;
        this.uuid = uuid;
        this.psuedo = p.getName();
        this.player = p;

        this.getSQLInformation(uuid);

    }

    private void getSQLInformation(UUID uuid){
        try {
            Connection connection = this.main.getDataBaseManagers().getDataBaseConnection().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT money, grade_id, boutique_pts FROM Players WHERE UUID = ?");
            preparedStatement.setString(1, uuid.toString());
            ResultSet resultSet  = preparedStatement.executeQuery();
            if (resultSet.next()){
                this.money = resultSet.getInt(1);
                this.gradeId = resultSet.getInt(2);
                this.boutiquePts = resultSet.getInt(3);
            }else{
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void saveSQLInformation(UUID uuid){
        try {
            Connection connection = this.main.getDataBaseManagers().getDataBaseConnection().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Players SET money = ?, grade_id = ?, boutique_pts = ? WHERE uuid = ?");
            preparedStatement.setInt(1, this.money);
            preparedStatement.setInt(2, this.gradeId);
            preparedStatement.setInt(3, this.boutiquePts);
            preparedStatement.setString(4, uuid.toString());preparedStatement.executeUpdate();
            preparedStatement.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getPsuedo() {
        return psuedo;
    }

    public Player getPlayer() {
        return player;
    }

    public int getMoney() {
        return this.money;
    }

    public int getGradeId() {
        return this.gradeId;
    }

    public int getBoutiquePts() {
        return this.boutiquePts;
    }

    public void setGradeId(int gradeId) {
        this.gradeId = gradeId;
    }
}