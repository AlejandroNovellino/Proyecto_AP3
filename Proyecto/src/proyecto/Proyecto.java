/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;
import proyecto.controls.CreateEvaluationControl;
import proyecto.controls.CreateStudentControl;
import proyecto.dataModel.enums.evaluationType;
import proyecto.dataModel.users.Admin;
import proyecto.dataModel.users.Student;
import proyecto.dataModel.users.User;
import proyecto.dataModel.enums.gender;
import proyecto.dataModel.enums.userType;
import proyecto.dataModel.evaluationRelated.Evaluation;
import proyecto.dataModel.evaluationRelated.Option;
import proyecto.dataModel.evaluationRelated.Question;
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
        
        FilesManager.writeListToFile(list, "users");
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
        
        subjects.add(
            new Subject(
                UUID.randomUUID().toString(),
                4444,
                "Materia4"
            )
        );
    }
    
    public static void createEvaluations(ArrayList<Evaluation> evaluations) {
        FilesManager.writeListToFile(new ArrayList<>(), "evaluations");
        
        CreateEvaluationControl control = new CreateEvaluationControl();
        control.setSubject(4444);
        // aux date
        Date closeDate = new Date();
        closeDate.setMonth(4);
        
        // create the options
        ArrayList<Option> options = new ArrayList<>();
        options.add(
                new Option(
                        UUID.randomUUID().toString(), 
                        "Opcion 1", 
                        true
                )
        );
        options.add(
                new Option(
                        UUID.randomUUID().toString(), 
                        "Opcion 2", 
                        false
                )
        );
        
        // create the questions
        ArrayList<Question> questions = new ArrayList<>();
        questions.add(new Question(
                UUID.randomUUID().toString(),
                "Pregunta 1",
                20, 
                false, 
                new ArrayList(options)
        ));
        // create the evaluation
        control.setQuestions(questions);
        control.createEvaluation(evaluationType.Quiz, 15, new Date(), closeDate, true, 2);
        
        // set the second evaluation
        questions.clear();
        // set question 1
        questions.add(new Question(
                UUID.randomUUID().toString(),
                "Pregunta 1",
                10, 
                false, 
                new ArrayList(options)
        ));
        options.add(
                new Option(
                        UUID.randomUUID().toString(), 
                        "Opcion 3", 
                        false
                )
        );
        questions.add(new Question(
                UUID.randomUUID().toString(),
                "Pregunta 2",
                10, 
                false, 
                new ArrayList(options)
        ));
        // create the evaluation
        control.setQuestions(questions);
        control.createEvaluation(evaluationType.Quiz, 20, new Date(), closeDate, true, 2);
        // create the evaluation
        control.setQuestions(questions);
        control.createEvaluation(evaluationType.Prueba, 15, new Date(), new Date(), true, 10000);
//        control.createEvaluation(evaluationType.Quiz, 15, new Date(), new Date(), true, 2);
    }
    
    public static void testUsers() {
        ArrayList<User> users = new ArrayList<>();
        createUsers(users);
    }
    
    public static void testEvaluations()  {
        ArrayList<Evaluation> evaluations = new ArrayList<>();
        createEvaluations(evaluations);
    }
    
    public static void testSubjects() {
        ArrayList<Subject> subjects = new ArrayList<>();
        createSubjects(subjects);
        //file test
        FilesManager.writeListToFile(subjects, "subjects");
    }
    
    public static void testUpdateUser() {
        // get the students
        ArrayList<Student> allStudents = FilesManager.getStudents();
        // create the control
        CreateStudentControl control = new CreateStudentControl(allStudents.get(0));
        // get the subject to enrolled
        ArrayList<Subject> subjects = new ArrayList<>();
        subjects.add(FilesManager.getSubjectByCode(4444));
        control.setEnrolledSubjects(subjects);
        // make the changes
        control.updateStudent(
                allStudents.get(0).getNames(), 
                allStudents.get(0).getLastNames(), 
                allStudents.get(0).getCi(), 
                allStudents.get(0).getGender(), 
                true
        );
    }
    
    public static void initFiles() {
        // test users
        testUsers();

        // test subjects
        testSubjects();
        
        // test evaluations
        testEvaluations();
        
        // test updating a student
        testUpdateUser();
    }
}
