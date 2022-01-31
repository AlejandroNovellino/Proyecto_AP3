/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.dataModel.manyToManyRelations;

import java.io.Serializable;
import java.util.ArrayList;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import proyecto.dataModel.evaluationRelated.Option;

/**
 *
 * @author Alejandro
 */
@XmlRootElement(name = "evaluationRegistry")
@XmlAccessorType(XmlAccessType.FIELD)
public class EvaluationRegistry implements Serializable{
    private String id;
    private String evaluationId;
    private String studentId;
    private Float note; // note in the evaluation
    private int numTries;
    private boolean presented; //presentada
    private boolean beingPresented; //siendoPresentada
    @XmlElementWrapper(name="options")
    @XmlElement(name="option")
    private ArrayList<Option> answers;

    public EvaluationRegistry() {
    }
    
    public EvaluationRegistry(String id, String evaluationId, String studentId, Float note, int numTries, boolean presented, boolean beingPresented) {
        this.id = id;
        this.evaluationId = evaluationId;
        this.studentId = studentId;
        this.note = note;
        this.numTries = numTries;
        this.presented = presented;
        this.beingPresented = beingPresented;
        this.answers = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEvaluationId() {
        return evaluationId;
    }

    public void setEvaluationId(String evaluationId) {
        this.evaluationId = evaluationId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public Float getNote() {
        return note;
    }

    public void setNote(Float note) {
        this.note = note;
    }

    public int getNumTries() {
        return numTries;
    }

    public void setNumTries(int numTries) {
        this.numTries = numTries;
    }

    public boolean getPresented() {
        return presented;
    }

    public void setPresented(boolean presented) {
        this.presented = presented;
    }

    public boolean isBeingPresented() {
        return beingPresented;
    }

    public void setBeingPresented(boolean beingPresented) {
        this.beingPresented = beingPresented;
    }

    public ArrayList<Option> getAnswers() {
        return answers;
    }

    public void setAnswers(ArrayList<Option> answers) {
        this.answers = answers;
    }

    @Override
    public String toString() {
        return "EvaluationRegistry{" + "id=" + id + ", evaluationId=" + evaluationId + ", studentId=" + studentId + ", note=" + note + ", presented=" + presented + ", beingPresented=" + beingPresented + ", answers=" + answers + '}';
    } 
}
