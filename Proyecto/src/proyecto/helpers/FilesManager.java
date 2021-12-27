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
import proyecto.dataModel.users.Student;
import proyecto.dataModel.users.User;

/**
 *
 * @author Alejandro
 */
public class FilesManager {
    public static void createUsersFile(ArrayList<User> users) {
        try {
            FileOutputStream file = new FileOutputStream(new File("src/proyecto/files/users.txt"));
            ObjectOutputStream stream = new ObjectOutputStream(file);
            
            stream.writeObject(users);

            stream.close();
            file.close();

        } catch (FileNotFoundException e) {
                System.out.println("File not found");
        }catch (IOException e) {
                System.out.println("Error initializing stream");
        }
    }
    
    public static ArrayList<User> readUsersFile() {
        ArrayList<User> users = new ArrayList<>(); 
        try {
            FileInputStream file = new FileInputStream(new File("src/proyecto/files/users.txt"));
            ObjectInputStream stream = new ObjectInputStream (file);
            
            users = (ArrayList<User>) stream.readObject();

            stream.close();
            file.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }catch (IOException e) {
            System.out.println("Error initializing stream");
        } catch (ClassNotFoundException e) {
            System.out.println("Error class not found");
        }
        
        return users;
    }
}
