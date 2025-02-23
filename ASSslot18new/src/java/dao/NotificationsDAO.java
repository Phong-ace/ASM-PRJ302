/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.Notifications;

/**
 *
 * @author Admin
 */
public class NotificationsDAO {

    public static ArrayList<Notifications> getNotificationsByUserID(int userId) {
        DBContext db = DBContext.getInstance();
        ArrayList<Notifications> notifications = new ArrayList<Notifications>();
        try {
            String sql = """
                         select * from Notifications
                         where UserID = ?
                         """;
            PreparedStatement statment = db.getConnection().prepareStatement(sql);
            statment.setInt(1, userId);
            ResultSet rs = statment.executeQuery();
            while (rs.next()) {
                Notifications no = new Notifications(
                        rs.getInt("NotificationID"),
                        rs.getInt("UserID"),
                        rs.getString("Message"),
                        rs.getDate("SentDate"),
                        rs.getBoolean("IsRead")
                );
                notifications.add(no);
            }
        } catch (Exception e) {
            return notifications;
        }

        return notifications;
    }

//    public static void main(String[] args) {
//        ArrayList<Notifications> no = NotificationsDAO.getNotificationsByUserID(1);
//        for (Notifications notifications : no) {
//            System.out.println(notifications.toString());
//        }
//    }
    
}
