/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.dataModel.users;

import java.util.ArrayList;
import proyecto.dataModel.manyToManyRelations.Enrollment;
import proyecto.dataModel.enums.gender;
import proyecto.dataModel.enums.status;
import proyecto.dataModel.enums.userType;
import proyecto.dataModel.manyToManyRelations.EvaluationRegistry;

/**
 *
 * @author Alejandro
 */
public class Student extends User {
    private String names;
    private String lastNames;
    private int ci;
    private gender gender;
    private boolean status;
    private ArrayList<Enrollment> enrollments;
    private ArrayList<EvaluationRegistry> evaluationsRegistrys;

    public Student() {
    }

    public Student(String names, String lastNames, int ci, gender gender, boolean status, String id, String userName, String passWord, userType type) {
        super(id, userName, passWord, type);
        this.names = names;
        this.lastNames = lastNames;
        this.ci = ci;
        this.gender = gender;
        this.status = status;
        this.enrollments = new ArrayList<>();
        this.evaluationsRegistrys = new ArrayList<>();
    }    

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getLastNames() {
        return lastNames;
    }

    public void setLastNames(String lastNames) {
        this.lastNames = lastNames;
    }

    public int getCi() {
        return ci;
    }

    public void setCi(int ci) {
        this.ci = ci;
    }

    public gender getGender() {
        return gender;
    }

    public void setGender(gender gender) {
        this.gender = gender;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public ArrayList<Enrollment> getEnrollments() {
        return enrollments;
    }

    public void setEnrollments(ArrayList<Enrollment> enrollments) {
        this.enrollments = enrollments;
    }

    public ArrayList<EvaluationRegistry> getEvaluationsRegistrys() {
        return evaluationsRegistrys;
    }

    public void setEvaluationsRegistrys(ArrayList<EvaluationRegistry> evaluationsRegistrys) {
        this.evaluationsRegistrys = evaluationsRegistrys;
    }
    
    public void addEnrollment(Enrollment enrollment) {
        this.enrollments.add(enrollment);
    }
    
    public void addEvaluationRegistry(EvaluationRegistry evaluationRegistry) {
        this.evaluationsRegistrys.add(evaluationRegistry);
    }
    
    @Override
    public String toString() {
        return super.toString()
                + ", names: "+this.names
                + ", lastNames: "+this.lastNames
                + ", ci: "+this.ci
                + ", gender: "+this.gender
                + ", status: "+this.status
                + "}";
    }
}
