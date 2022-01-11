/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.dataModel.subjectRelated;

import proyecto.dataModel.manyToManyRelations.Enrollment;
import java.io.Serializable;
import java.util.ArrayList;
import proyecto.dataModel.evaluationRelated.Evaluation;

/**
 *
 * @author Alejandro
 */
public class Subject implements Serializable{
    private String id; 
    private int code;
    private String name;
    private ArrayList<Enrollment> enrrolments;
    private ArrayList<Evaluation> evaluations;

    public Subject(String id, int code, String name) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.enrrolments = new ArrayList<>();
        this.evaluations = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Enrollment> getEnrrolments() {
        return enrrolments;
    }

    public void setEnrrolments(ArrayList<Enrollment> enrrolments) {
        this.enrrolments = enrrolments;
    }

    public ArrayList<Evaluation> getEvaluations() {
        return evaluations;
    }

    public void setEvaluations(ArrayList<Evaluation> evaluations) {
        this.evaluations = evaluations;
    }

    @Override
    public String toString() {
        return "Nombre: " + name +", código: " + code;
    }
}
