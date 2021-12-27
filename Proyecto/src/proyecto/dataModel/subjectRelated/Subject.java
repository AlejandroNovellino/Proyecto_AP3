/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.dataModel.subjectRelated;

import proyecto.dataModel.manyToManyRelations.Enrollment;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Alejandro
 */
public class Subject implements Serializable{
    private String id; 
    private int code;
    private String name;
    Prelation prelation;
    private ArrayList<Enrollment> enrrolments;
    //Evaluaciones
}
