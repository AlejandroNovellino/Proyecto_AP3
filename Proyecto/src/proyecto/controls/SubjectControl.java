/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.controls;

import java.util.ArrayList;
import java.util.UUID;
import proyecto.dataModel.enums.filesNames;
import proyecto.dataModel.subjectRelated.Subject;
import proyecto.helpers.FilesManager;

/**
 *
 * @author Alejandro
 */
public class SubjectControl {
    public static void createSubject(String name, int code) {
        // create the new subject
        Subject newSubject = new Subject(UUID.randomUUID().toString(), code, name);
        ArrayList<Subject> allSubjects = FilesManager.getSubjects();
        allSubjects.add(newSubject);
        // save it to the file
        FilesManager.writeListToFile(allSubjects, filesNames.subjects);
    }
}
