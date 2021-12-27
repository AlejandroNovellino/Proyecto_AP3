/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.dataModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Alejandro
 */
public abstract class  Evaluation implements Serializable{
    protected String id;
    //protected tipo evaluacion VERIFICAR SI ES NECESARIO
    protected float weighing; //ponderacion
    protected Date initDate;
    protected Date closeDate;
    protected boolean active;
    protected int tries; //intentos
    protected ArrayList<EvaluationRegistry> results;
    protected ArrayList<Question> questions;
}
