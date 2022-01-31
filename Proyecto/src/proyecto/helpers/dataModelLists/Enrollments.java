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
import proyecto.dataModel.manyToManyRelations.Enrollment;

/**
 *
 * @author Alejandro
 */
@XmlRootElement(name = "enrollments")
@XmlAccessorType(XmlAccessType.FIELD)
public class Enrollments {
    @XmlElementWrapper(name="enrollments")
    @XmlElement(name="enrollment")
    private ArrayList<Enrollment> enrollments;

    public Enrollments() {
    }
    
    public Enrollments(ArrayList<Enrollment> enrollments) {
        this.enrollments = enrollments;
    }

    public ArrayList<Enrollment> getEnrollments() {
        return enrollments;
    }

    public void setEnrollments(ArrayList<Enrollment> enrollments) {
        this.enrollments = enrollments;
    }
}
