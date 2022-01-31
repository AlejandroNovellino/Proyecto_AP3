/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.helpers;

import java.io.File;
import java.util.ArrayList;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import proyecto.dataModel.users.User;
import proyecto.helpers.dataModelLists.Users;

/**
 *
 * @author Alejandro
 */
public class XMLHelper {
    public static void writeUsersToFile(ArrayList<User> users) {
        try {
            // set the comunication with the XML API
            JAXBContext context = JAXBContext.newInstance(Users.class);
            Marshaller marshaller= context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            // create the file to save
            File file = new File("src/proyecto/files/users.xml");
            // create the auxiliary class
            Users listaProductos = new Users(users);
            // write to the file
            marshaller.marshal(listaProductos, file);
        } catch (Exception e) {
            System.out.println("No se pudo crear el XML");
            System.out.println(e);
        }
    }
    
    public static ArrayList<User> readProductosFromFile() {
        try {
            // set the comunication with the XML API
            JAXBContext context = JAXBContext.newInstance(Users.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            // create the file to save
            File file = new File("src/proyecto/files/users.xml");
            // get productos list
            Users listaProductos = (Users)unmarshaller.unmarshal(file);
            return listaProductos.getUsers();
        } catch (Exception e) {
            System.out.println("No se pudo recuperar la informacion");
            return null;
        }
    }
}
