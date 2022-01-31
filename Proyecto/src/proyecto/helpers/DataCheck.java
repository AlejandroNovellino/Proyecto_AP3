/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.helpers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Alejandro
 */
public class DataCheck {
    public static boolean validString(String word) {
        Pattern pattern = Pattern.compile("\\d", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(word);
        boolean matchFound = matcher.find();
        return !matchFound;
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
            return !FilesManager.getAllCiStudents().contains(ci);
        } catch (Exception e) {
            return true;
        }
    }
    
    public static boolean uniqueSubjectCode(int code) {
        try {
            return !FilesManager.getAllSubjectsCodes().contains(code);
        } catch (Exception e) {
            return true;
        }
    }
}
