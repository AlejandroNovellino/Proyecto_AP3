/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;
import proyecto.controls.CreateStudentControl;
import proyecto.dataModel.enums.evaluationType;
import proyecto.dataModel.users.Admin;
import proyecto.dataModel.users.Student;
import proyecto.dataModel.users.User;
import proyecto.dataModel.enums.gender;
import proyecto.dataModel.enums.userType;
import proyecto.dataModel.evaluationRelated.Evaluation;
import proyecto.dataModel.evaluationRelated.Quiz;
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
                1111, 
                gender.Femenino, 
                true,
                UUID.randomUUID().toString(), 
                "student1", 
                "student1", 
                userType.STUDENT
        );
        
        Student student2 = new Student(
                "student2", 
                "student2",
                2222, 
                gender.Masculino, 
                false,
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
        //System.out.println("\n    testStudents:");
        ArrayList<Student> students = FilesManager.getStudents();
        //System.out.println(students);
    }
    
    public static void testAdmins() {
        //System.out.println("\n    testAdmins:");
        ArrayList<Admin> admins = FilesManager.getAdmins();
        //System.out.println(admins);
    }
    
    public static void createSubjects(ArrayList<Subject> subjects) {
        subjects.add(
            new Subject(
                    UUID.randomUUID().toString(),
                    1111,
                    "Materia1"
            )
        );
        subjects.add(
            new Subject(
                    UUID.randomUUID().toString(),
                    2222,
                    "Materia2"
            )
        );
        
        subjects.add(
            new Subject(
                    UUID.randomUUID().toString(),
                    3333,
                    "Materia3"
            )
        );
        
        ArrayList<Integer> codes4 = new ArrayList<>();
        codes4.add(3333);
        subjects.add(
            new Subject(
                UUID.randomUUID().toString(),
                4444,
                "Materia4"
            )
        );
    }
    
    public static void createEvaluations(ArrayList<Evaluation> evaluations) {
        
        evaluations.add(
            new Quiz(
                UUID.randomUUID().toString(), 
                evaluationType.Quiz, 
                15, 
                new Date(), 
                new Date(), 
                true, 
                2
            )
        );
        
        evaluations.add(
            new Quiz(
                UUID.randomUUID().toString(), 
                evaluationType.Quiz, 
                15, 
                new Date(), 
                new Date(), 
                true, 
                2
            )
        );
    }
    
    public static void testEvaluations()  {
        ArrayList<Evaluation> evaluations = new ArrayList<>();
        createEvaluations(evaluations);
        //System.out.println(evaluations);
        // save to file
        FilesManager.writeListToFile(evaluations, "evaluations");
    }
    
    public static void testSubjects() {
        //System.out.println("\n    testSubjects:");
        ArrayList<Subject> subjects = new ArrayList<>();
        createSubjects(subjects);
        //System.out.println("\n    Subjects created:\n"+subjects);
        //file test
        FilesManager.writeListToFile(subjects, "subjects");
        ArrayList<Subject> aux = (ArrayList<Subject>)FilesManager.readListFromFile("subjects");
        //System.out.println("\n    File content:\n"+aux);
    }
    
    public static void initFiles() {
        // Test users
        ArrayList<User> users = new ArrayList<>();
        createUsers(users);

        FilesManager.writeListToFile(users, "users");
        //System.out.println("\n    File content:\n"+FilesManager.getUsers());

        testStudents();
        testAdmins();

        // Test subjects
        testSubjects();
        
        // test evaluations
        testEvaluations();
    }
}
