/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.dataModel;

/**
 *
 * @author Alejandro
 */
public class Admin extends User {

    public Admin() {
    }

    public Admin(String id, String userName, String passWord, userType type) {
        super(id, userName, passWord, type);
    }
}
