/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.dataModel.evaluationRelated;

import java.io.Serializable;
import java.util.ArrayList;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Alejandro
 */
@XmlRootElement(name = "question")
@XmlAccessorType(XmlAccessType.FIELD)
public class Question implements Serializable{
    private String id;
    //private tag
    private String statement; //enunciado
    private float score; //puntaje (peso)
    private boolean inUse;
    @XmlElementWrapper(name="options")
    @XmlElement(name="option")
    private ArrayList<Option> options;

    public Question() {
    }

    public Question(String id, String statement, float score, boolean inUse, ArrayList<Option> options) {
        this.id = id;
        this.statement = statement;
        this.score = score;
        this.inUse = inUse;
        this.options = options;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatement() {
        return statement;
    }

    public void setStatement(String statement) {
        this.statement = statement;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public boolean isInUse() {
        return inUse;
    }

    public void setInUse(boolean inUse) {
        this.inUse = inUse;
    }

    public ArrayList<Option> getOptions() {
        return options;
    }

    public void setOptions(ArrayList<Option> options) {
        this.options = options;
    }

    @Override
    public String toString() {
        return "Enunciado= " + statement + ", puntaje= " + score + ", Num. opciones= " +options.size();
    }
}
