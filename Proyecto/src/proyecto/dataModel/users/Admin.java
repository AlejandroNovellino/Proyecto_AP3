/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.dataModel.users;

import proyecto.dataModel.enums.userType;

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
    
    @Override
    public String toString() {
        return super.toString()
                + "}";
    }
}
