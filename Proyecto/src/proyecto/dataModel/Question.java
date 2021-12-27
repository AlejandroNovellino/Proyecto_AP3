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
public class Question {
    private String id;
    //private tag
    private String statement; //enunciado
    private float score; //puntaje (peso)
    private boolean inUse; 
    private ArrayList<Option> options;
}
