/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.dataModel;

/**
 *
 * @author Alejandro
 */
public class Student extends User {
    private String names;
    private String lastNames;
    private int ci;
    private gender gender;
    private status status;
    //private enrollments
    //private answers

    public Student() {
    }

    public Student(String names, String lastNames, int ci, gender gender, status status, String id, String userName, String passWord, userType type) {
        super(id, userName, passWord, type);
        this.names = names;
        this.lastNames = lastNames;
        this.ci = ci;
        this.gender = gender;
        this.status = status;
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

    public status getStatus() {
        return status;
    }

    public void setStatus(status status) {
        this.status = status;
    }
    
    @Override
    public String toString() {
        return super.toString()
                + ", names: "+this.names
                + ", lastNames: "+this.lastNames
                + ", ci: "+this.ci
                + ", gender: "+this.gender
                + ", status: "+this.status;
    }
    
}
