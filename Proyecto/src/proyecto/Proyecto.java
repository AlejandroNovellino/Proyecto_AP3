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
import proyecto.dataModel.subjectRelated.Subject;
import proyecto.helpers.FilesManager;

/**
 *
 * @author Alejandro
 */
public class Proyecto {
    
    public static void createUsers(ArrayList<User> list) {
        Admin admin1 = new Admin(UUID.randomUUID().toString(), "admin1", "admin1", userType.ADMIN);
        
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
        
        list.add(admin1);
        list.add(student1);
        list.add(student2);
    }
    
    public static void  testStudents() {
        System.out.println("\n    testStudents:");
        ArrayList<Student> students = FilesManager.getStudents();
        System.out.println(students);
    }
    
    public static void testAdmins() {
        System.out.println("\n    testAdmins:");
        ArrayList<Admin> admins = FilesManager.getAdmins();
        System.out.println(admins);
    }
    
    public static void createSubjects(ArrayList<Subject> subjects) {
        subjects.add(
            new Subject(
                    UUID.randomUUID().toString(),
                    1111,
                    "Materia1",
                    5,
                    null
            )
        );
        subjects.add(
            new Subject(
                    UUID.randomUUID().toString(),
                    2222,
                    "Materia2",
                    4,
                    null
            )
        );
    }
    
    public static void testSubjects() {
        System.out.println("\n    testSubjects:");
        ArrayList<Subject> subjects = new ArrayList<>();
        createSubjects(subjects);
        System.out.println("\n    Subjects created:\n"+subjects);
        //file test
        FilesManager.writeListToFile(subjects, "subjects");
        ArrayList<Subject> aux = (ArrayList<Subject>)FilesManager.readListFromFile("subjects");
        System.out.println("\n    File content:\n"+aux);
    }
    
//    public static void main(String[] args) {
//        // TODO code application logic here
//
//        // Test users
//        ArrayList<User> users = new ArrayList<>();
//        createUsers(users);
//
//        FilesManager.writeListToFile(users, "users");
//        System.out.println("\n    File content:\n"+FilesManager.getUsers());
//
//        testStudents();
//        testAdmins();
//
//        // Test subjects
//        testSubjects();
//    }
}
