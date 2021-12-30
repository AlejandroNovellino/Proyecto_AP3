/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.helpers;

/**
 *
 * @author Alejandro
 */
public class DataCheck {
    public static boolean validString(String word) {
        if(word.matches("\\d+")) {
            return false;
        }
        return word.matches("\\w+");
    }
    
    public static boolean validInt(String word) {
        try {
            Integer.parseInt(word);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public static boolean validFloat(String word) {
        try {
            Float.parseFloat(word);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public static boolean uniqueCi(int ci) {
        try {
            return FilesManager.getAllCiStudents().contains(ci);
        } catch (Exception e) {
            return true;
        }
    }
}
