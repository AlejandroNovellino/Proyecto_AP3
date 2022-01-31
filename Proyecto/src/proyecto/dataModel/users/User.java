/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.dataModel.users;

import java.io.Serializable;
import java.util.Objects;
import javax.xml.bind.annotation.XmlTransient;
import proyecto.dataModel.enums.userType;

/**
 *
 * @author Alejandro
 */
@XmlTransient
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
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.userName);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        if (!Objects.equals(this.userName, other.userName)) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "{"
                + "id: "+this.id
                + ", userName: "+this.userName
                + ", password: "+this.passWord
                + ", userType: "+this.type;
    }
}
