/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.controls;

import java.util.ArrayList;
import proyecto.dataModel.users.User;
import proyecto.helpers.FilesManager;

/**
 *
 * @author Alejandro
 */
public class LoginControl {
    public static User getUserByUsername(String username) {
        try {
            ArrayList<User> users = FilesManager.getUsers();
            users.removeIf(user -> !user.getUserName().equals(username));
            User user = users.get(0);

            return user;
        } catch (Exception e) {
            return null;
        }
    }
}
