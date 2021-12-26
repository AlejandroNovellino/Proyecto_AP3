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
public abstract class User {
    protected String id;
    protected String userName;
    protected String passWord;
    protected userType type;
    
    public User() {
    }

    public User(String id, String userName, String passWord, userType type) {
        this.id = id;
        this.userName = userName;
        this.passWord = passWord;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public userType getType() {
        return type;
    }

    public void setType(userType type) {
        this.type = type;
    }
    
    @Override
    public String toString() {
        return ""
                + "id: "+this.id
                + ", userName: "+this.userName
                + ", password: "+this.passWord
                + ", userType: "+this.type;
    }
}
