/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.dataModel;

import java.util.ArrayList;

/**
 *
 * @author Alejandro
 */
public class EvaluationRegistry {
    private String id;
    private String evaluationId;
    private String studentId;
    private float note; 
    private boolean presented; //presentada
    private boolean beingPresented; //siendoPresentada
    private ArrayList<Option> answers;
}
