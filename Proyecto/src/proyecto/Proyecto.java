/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto;

import java.util.UUID;
import proyecto.dataModel.Admin;
import proyecto.dataModel.Student;
import proyecto.dataModel.gender;
import proyecto.dataModel.status;
import proyecto.dataModel.userType;

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
                status.Activo,
                UUID.randomUUID().toString(), 
                "student1", 
                "student1", 
                userType.STUDENT
        );
        System.out.println(student1);
    }
    
}
