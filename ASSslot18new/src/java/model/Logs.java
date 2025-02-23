/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;

/**
 *
 * @author Admin
 */
public class Logs {

    private int LogID;
    private int UserID;
    private String Action;
    private Date Timestamp;

    public Logs() {
    }

    public Logs(int LogID, int UserID, String Action, Date Timestamp) {
        this.LogID = LogID;
        this.UserID = UserID;
        this.Action = Action;
        this.Timestamp = Timestamp;
    }

    public int getLogID() {
        return LogID;
    }

    public void setLogID(int LogID) {
        this.LogID = LogID;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int UserID) {
        this.UserID = UserID;
    }

    public String getAction() {
        return Action;
    }

    public void setAction(String Action) {
        this.Action = Action;
    }

    public Date getTimestamp() {
        return Timestamp;
    }

    public void setTimestamp(Date Timestamp) {
        this.Timestamp = Timestamp;
    }

    @Override
    public String toString() {
        return "Logs{" + "LogID=" + LogID + ", UserID=" + UserID + ", Action=" + Action + ", Timestamp=" + Timestamp + '}';
    }
}
