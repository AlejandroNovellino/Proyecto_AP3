/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.helpers.dataModelLists;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import proyecto.dataModel.subjectRelated.Subject;

/**
 *
 * @author Alejandro
 */
@XmlRootElement(name = "subjects")
@XmlAccessorType(XmlAccessType.FIELD)
public class Subjects {
    @XmlElementWrapper(name="subjects")
    @XmlElement(name="subject")
    private ArrayList<Subject> subjects;

    public Subjects() {
    }
    
    public Subjects(ArrayList<Subject> subjects) {
        this.subjects = subjects;
    }

    public ArrayList<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(ArrayList<Subject> subjects) {
        this.subjects = subjects;
    }
}
