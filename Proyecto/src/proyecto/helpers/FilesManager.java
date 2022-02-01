/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.helpers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import proyecto.dataModel.enums.filesNames;
import proyecto.dataModel.evaluationRelated.Evaluation;
import proyecto.dataModel.manyToManyRelations.Enrollment;
import proyecto.dataModel.manyToManyRelations.EvaluationRegistry;
import proyecto.dataModel.subjectRelated.Subject;
import proyecto.dataModel.users.Admin;
import proyecto.dataModel.users.Student;
import proyecto.dataModel.users.User;
import proyecto.helpers.dataModelLists.Enrollments;
import proyecto.helpers.dataModelLists.EvaluationRegistries;
import proyecto.helpers.dataModelLists.Evaluations;
import proyecto.helpers.dataModelLists.Subjects;
import proyecto.helpers.dataModelLists.Users;

/**
 *
 * @author Alejandro
 */
public class FilesManager {
    
    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    //XML
    
    public static void writeListToFile(ArrayList<?> list, filesNames fileName) {
        switch(fileName) {
            case users:
                writeUsersToFile((ArrayList<User>)list);
                break;
            case subjects:
                writeSubjectsToFile((ArrayList<Subject>)list);
                break;
            case evaluations:
                writeEvaluationsToFile((ArrayList<Evaluation>)list);
                break;
            case evaluationRegistries:
                writeEvaluationRegistriesToFile((ArrayList<EvaluationRegistry>)list);
                break;
            case enrollments:
                writeEnrollmentsToFile((ArrayList<Enrollment>)list);
                break;
            default:
                System.out.println("Opcion de archivo no valido");
        }
    }
    
    private static void writeUsersToFile(ArrayList<User> users) {
        try {
            // set the comunication with the XML API
            JAXBContext context = JAXBContext.newInstance(Users.class);
            Marshaller marshaller= context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            // create the file to save
            File file = new File("src/proyecto/files/users.xml");
            // create the auxiliary class
            Users userList = new Users(users);
            // write to the file
            marshaller.marshal(userList, file);
        } catch (Exception e) {
            System.out.println("No se pudo crear el XML");
            System.out.println(e);
        }
    }
    
    private static void writeSubjectsToFile(ArrayList<Subject> subjects) {
        try {
            // set the comunication with the XML API
            JAXBContext context = JAXBContext.newInstance(Subjects.class);
            Marshaller marshaller= context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            // create the file to save
            File file = new File("src/proyecto/files/subjects.xml");
            // create the auxiliary class
            Subjects subjectList = new Subjects(subjects);
            // write to the file
            marshaller.marshal(subjectList, file);
        } catch (Exception e) {
            System.out.println("No se pudo crear el XML");
            System.out.println(e);
        }
    }
    
    private static void writeEvaluationsToFile(ArrayList<Evaluation> evaluations) {
        try {
            // set the comunication with the XML API
            JAXBContext context = JAXBContext.newInstance(Evaluations.class);
            Marshaller marshaller= context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            // create the file to save
            File file = new File("src/proyecto/files/evaluations.xml");
            // create the auxiliary class
            Evaluations evaluationList = new Evaluations(evaluations);
            // write to the file
            marshaller.marshal(evaluationList, file);
        } catch (Exception e) {
            System.out.println("No se pudo crear el XML");
            System.out.println(e);
        }
    }
    
    private static void writeEvaluationRegistriesToFile(ArrayList<EvaluationRegistry> evaluationRegistries) {
        try {
            // set the comunication with the XML API
            JAXBContext context = JAXBContext.newInstance(EvaluationRegistries.class);
            Marshaller marshaller= context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            // create the file to save
            File file = new File("src/proyecto/files/evaluationRegistries.xml");
            // create the auxiliary class
            EvaluationRegistries evaluationList = new EvaluationRegistries(evaluationRegistries);
            // write to the file
            marshaller.marshal(evaluationList, file);
        } catch (Exception e) {
            System.out.println("No se pudo crear el XML");
            System.out.println(e);
        }
    }
    
    private static void writeEnrollmentsToFile(ArrayList<Enrollment> enrollments) {
        try {
            // set the comunication with the XML API
            JAXBContext context = JAXBContext.newInstance(Enrollments.class);
            Marshaller marshaller= context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            // create the file to save
            File file = new File("src/proyecto/files/enrollments.xml");
            // create the auxiliary class
            Enrollments evaluationList = new Enrollments(enrollments);
            // write to the file
            marshaller.marshal(evaluationList, file);
        } catch (Exception e) {
            System.out.println("No se pudo crear el XML");
            System.out.println(e);
        }
    }
    
    ////////////////////////////////////////////////////////////////////////////
    /////// Getters of the xml files
    
    public static ArrayList<User> getUsers() {
        try {
            // set the comunication with the XML API
            JAXBContext context = JAXBContext.newInstance(Users.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            // create the file to save
            File file = new File("src/proyecto/files/users.xml");
            // get productos list
            Users list = (Users)unmarshaller.unmarshal(file);
            return list.getUsers();
        } catch (Exception e) {
            System.out.println("No se pudo recuperar la informacion ----users");
            return null;
        }
    }
    
    public static ArrayList<Subject> getSubjects() {
        try {
            // set the comunication with the XML API
            JAXBContext context = JAXBContext.newInstance(Subjects.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            // create the file to save
            File file = new File("src/proyecto/files/subjects.xml");
            // get productos list
            Subjects list = (Subjects)unmarshaller.unmarshal(file);
            return list.getSubjects();
        } catch (Exception e) {
            System.out.println("No se pudo recuperar la informacion ----subjects");
            return null;
        }
    }
    
    public static ArrayList<Evaluation> getEvaluations() {
        try {
            // set the comunication with the XML API
            JAXBContext context = JAXBContext.newInstance(Evaluations.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            // create the file to save
            File file = new File("src/proyecto/files/evaluations.xml");
            // get productos list
            Evaluations list = (Evaluations)unmarshaller.unmarshal(file);
            return list.getEvaluations();
        } catch (Exception e) {
            System.out.println("No se pudo recuperar la informacion ----evaluations");
            return new ArrayList<>();
        }
    }
    
    public static ArrayList<EvaluationRegistry> getEvaluationRegistries() {
        try {
            // set the comunication with the XML API
            JAXBContext context = JAXBContext.newInstance(EvaluationRegistries.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            // create the file to save
            File file = new File("src/proyecto/files/evaluationRegistries.xml");
            // get productos list
            EvaluationRegistries list = (EvaluationRegistries)unmarshaller.unmarshal(file);
            return list.getEvaluationRegistries();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
    
    public static ArrayList<Enrollment> getEnrollments() {
        try {
            // set the comunication with the XML API
            JAXBContext context = JAXBContext.newInstance(Enrollments.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            // create the file to save
            File file = new File("src/proyecto/files/enrollments.xml");
            // get productos list
            Enrollments list = (Enrollments)unmarshaller.unmarshal(file);
            return list.getEnrollments();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
    
    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    // test function
    public static void writeListToFile(ArrayList<?> list, String fileName) {
        try {
            FileOutputStream file = new FileOutputStream(new File("src/proyecto/files/"+fileName+".txt"));
            ObjectOutputStream stream = new ObjectOutputStream(file);
            
            stream.writeObject(list);
            
            stream.close();
            file.close();

        } catch (FileNotFoundException e) {
                System.out.println("File not found");
        }catch (IOException e) {
                System.out.println("Error initializing stream");
        }
    }

    public static ArrayList<Admin> getAdmins() {
        ArrayList<User> users = new ArrayList<>(getUsers());
        // remove all the students
        users.removeIf(user -> (user.getType().toString().equals("STUDENT")));
        // convert to admin type
        ArrayList<Admin> admins = new ArrayList<>();
        users.forEach(user -> admins.add((Admin)user));      
        // return 
        return admins;
    }
    
    public static ArrayList<Student> getStudents() {
        ArrayList<User> users = new ArrayList<>(getUsers());
        // remove the admins
        users.removeIf(user -> (user.getType().toString().equals("ADMIN")));
        // convert to student type
        ArrayList<Student> students = new ArrayList<>();
        users.forEach(user -> students.add((Student)user));      
        // return
        return students;
    }
    
    public static Student getStudentByCi(int ci) {
        ArrayList<Student> aux = getStudents();
        aux.removeIf(element -> element.getCi()!=ci);
        return aux.get(0);
    }
    
    public static ArrayList<Integer> getAllCiStudents() {
        ArrayList<Integer> aux = new ArrayList<>();
        getStudents().forEach(student -> aux.add(student.getCi()));
        return aux;
    }
    
    public static ArrayList<Integer> getAllSubjectsCodes() {
        ArrayList<Integer> codes = new ArrayList<>();
        getSubjects().forEach(subject -> codes.add(subject.getCode()));
        return codes;
    }
    
    public static ArrayList<Subject> getViewedSubjectsForStudent(Student student) {
        if(student == null) {
            return new ArrayList<>();
        }
        
        try {
            ArrayList<Subject> subjects = getSubjects();
            ArrayList<Subject> subjectsToReturn = new ArrayList<>();
            ArrayList<String> subjectsViewedIds = new ArrayList<>();
            // get all the enrolled subjects ids
            student.getEnrollments().forEach(enrollment -> {
                if(enrollment.getPassed()) {
                    subjectsViewedIds.add(enrollment.getSubjectId());
                }
            });
            // add the enrolled subjects to the return list
            subjects.forEach(element -> {
                if(subjectsViewedIds.contains(element.getId())) {
                    subjectsToReturn.add(element);
                }
            });
            // return the list
            return subjectsToReturn;
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
    
    public static ArrayList<Subject> getNotViewedSubjectsForStudent(Student student) {
        if(student == null) {
            return getSubjects();
        }
        
        try {
            ArrayList<Enrollment> enrollments = getEnrollments();
            ArrayList<Subject> subjects = getSubjects();
            enrollments.removeIf(enrollment -> !enrollment.getStudentId().equals(student.getId()));
            enrollments.forEach(enrollment -> {
                subjects.removeIf(subject -> subject.getId().equals(enrollment.getSubjectId()));
            });
            return subjects;
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
    
    public static ArrayList<Subject> getCurrentEnrollSubjectsForStudent(Student student) {
        if(student == null) {
            return new ArrayList<>();
        }
        
        try {
            ArrayList<Subject> subjects = getSubjects();
            ArrayList<Subject> subjectsToReturn = new ArrayList<>();
            ArrayList<String> subjectsViewedIds = new ArrayList<>();
            // get all the enrolled subjects ids
            student.getEnrollments().forEach(enrollment -> {
                if(!enrollment.getPassed()) {
                    subjectsViewedIds.add(enrollment.getSubjectId());
                }
            });
            // add the enrolled subjects to the return list
            subjects.forEach(element -> {
                if(subjectsViewedIds.contains(element.getId())) {
                    subjectsToReturn.add(element);
                }
            });
            // return the list
            return subjectsToReturn;
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
    
    public static Subject getSubjectByCode(int code) {
        ArrayList<Subject> allSubjects = getSubjects();
        
        allSubjects.removeIf(element -> !(element.getCode() == code));
        
        return allSubjects.get(0);
    }
    
    public static Subject getSubjectById(String id) {
        ArrayList<Subject> allSubjects = getSubjects();
        
        allSubjects.removeIf(element -> !(element.getId().equals(id)));
        
        return allSubjects.get(0);
    }
    
    public static Evaluation getEvaluationById(String id) {
        ArrayList<Evaluation> allEvaluations = getEvaluations();
        
        allEvaluations.removeIf(element -> !(element.getId().equals(id)));
        
        return allEvaluations.get(0);
    }
}
