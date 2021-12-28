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
import proyecto.dataModel.subjectRelated.Subject;
import proyecto.dataModel.users.Admin;
import proyecto.dataModel.users.Student;
import proyecto.dataModel.users.User;

/**
 *
 * @author Alejandro
 */
public class FilesManager {
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
    
    public static ArrayList<?> readListFromFile(String fileName) {
        ArrayList<?> list = new ArrayList<>(); 
        try {
            FileInputStream file = new FileInputStream(new File("src/proyecto/files/"+fileName+".txt"));
            ObjectInputStream stream = new ObjectInputStream (file);
            
            list = (ArrayList<?>) stream.readObject();

            stream.close();
            file.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }catch (IOException e) {
            System.out.println("Error initializing stream");
        } catch (ClassNotFoundException e) {
            System.out.println("Error class not found");
        }
        
        return list;
    }
    
    public static ArrayList<User> getUsers() {
        return (ArrayList<User>)readListFromFile("users");
    }
    
     public static ArrayList<Admin> getAdmins() {
        ArrayList<User> users = (ArrayList<User>)readListFromFile("users");
        
        users.removeIf(user -> (user.getType().toString().equals("STUDENT")));
        ArrayList<Admin> admins = new ArrayList<>();
        users.forEach(user -> admins.add((Admin)user));      
        
        return admins;
    }
    
    public static ArrayList<Student> getStudents() {
        ArrayList<User> users = (ArrayList<User>)readListFromFile("users");
        
        users.removeIf(user -> (user.getType().toString().equals("ADMIN")));
        ArrayList<Student> students = new ArrayList<>();
        users.forEach(user -> students.add((Student)user));      
        
        return students;
    }
    
    public static ArrayList<Subject> getSubjects() {
        return (ArrayList<Subject>)readListFromFile("subjects");
    }
}
