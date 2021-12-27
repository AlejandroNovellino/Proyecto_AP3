/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto;

import java.util.ArrayList;
import java.util.UUID;
import proyecto.dataModel.users.Admin;
import proyecto.dataModel.users.Student;
import proyecto.dataModel.users.User;
import proyecto.dataModel.enums.gender;
import proyecto.dataModel.enums.status;
import proyecto.dataModel.enums.userType;
import proyecto.helpers.FilesManager;

/**
 *
 * @author Alejandro
 */
public class Proyecto {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Admin admin1 = new Admin(UUID.randomUUID().toString(), "admin1", "admin1", userType.ADMIN);
        System.out.println(admin1);
        
        Student student1 = new Student(
                "student1", 
                "student1",
                0, 
                gender.Mujer, 
                true,
                UUID.randomUUID().toString(), 
                "student1", 
                "student1", 
                userType.STUDENT
        );
        System.out.println(student1);
        
        Student student2 = new Student(
                "student12", 
                "student2",
                0, 
                gender.Mujer, 
                true,
                UUID.randomUUID().toString(), 
                "student2", 
                "student2", 
                userType.STUDENT
        );
        System.out.println(student2);
        
        ArrayList<User> aux = new ArrayList<>();
        aux.add(admin1);
        aux.add(student1);
        aux.add(student2);
        
        FilesManager.writeListToFile(aux, "users");
        System.out.println("\n  File content:\n"+FilesManager.getUsers());
    }
    
}
