/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.dataModel.evaluationRelated;

import java.io.Serializable;

/**
 *
 * @author Alejandro
 */
public class Option implements Serializable{
    private String id;
    private String value; //valor - lo que indica la opcion
    private boolean isAnswer;
}
