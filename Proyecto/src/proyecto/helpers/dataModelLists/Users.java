/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.helpers.dataModelLists;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import proyecto.dataModel.enums.userType;
import proyecto.dataModel.users.Admin;
import proyecto.dataModel.users.Student;
import proyecto.dataModel.users.User;

/**
 *
 * @author Alejandro
 */
@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.FIELD)
public class Users {
    // students
    @XmlElementWrapper(name="students")
    @XmlElement(name="student")
    private ArrayList<Student> students;
    // admins
    @XmlElementWrapper(name="admins")
    @XmlElement(name="admin")
    private ArrayList<Admin> admins;

    public Users() {
    }

    public Users(ArrayList<User> users) {
        students = new ArrayList<>();
        admins = new ArrayList<>();
        
        // get the students and the admins
        users.forEach(element -> {
            if(element.getType().equals(userType.STUDENT)) {
                // add the student
                students.add((Student)element);
            } else {
                // add the admin
                admins.add((Admin)element);
            }
        });
    }

    public ArrayList<User> getUsers() {
        ArrayList<User> users = new ArrayList<>();
        // add all the students
        students.forEach(element -> {
            users.add(element);
        });
        // add all the admins
        admins.forEach(element -> {
            users.add(element);
        });
        
        return users;
    }
}
