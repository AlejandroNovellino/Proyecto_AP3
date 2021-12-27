/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.dataModel.subjectRelated;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Alejandro
 */
public class Prelation implements Serializable{
    private String id;
    private ArrayList<Integer> subjectsCodes;
    private int uCredits;

    public Prelation() {
    }

    public Prelation(String id, ArrayList<Integer> materias, int uCredits) {
        this.id = id;
        this.subjectsCodes = materias;
        this.uCredits = uCredits;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<Integer> getMaterias() {
        return subjectsCodes;
    }

    public void setMaterias(ArrayList<Integer> materias) {
        this.subjectsCodes = materias;
    }

    public int getuCredits() {
        return uCredits;
    }

    public void setuCredits(int uCredits) {
        this.uCredits = uCredits;
    }
    
    
}
