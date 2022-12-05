package use_cases.login_registration.user_register_usecase;

import entities.InstructorUser;
import entities.StudentUser;
import entities.User;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

// Use Case Layer

public class UserRegSaveRequest implements Serializable {
    /**
     * A transient data storage class that contains the same information as a User
     * The parent to InstructorSaveRequest and StudentSaveRequest
     */

    private final String name;

    private String password;

    private final LocalDateTime creationTime;

    /**
     * @param name the name of this User
     * @param password the password of this User
     * @param user the User object
     * @param creationTime the time at which this User was saved
     */
    public UserRegSaveRequest(String name, String password, User user, LocalDateTime creationTime) {
        this.name = name;
        this.password = password;
        this.creationTime = creationTime;
    }

    public String getName() { return name; }

    public String getPass() { return password; }

    public void setPass(String password) {
        this.password = password;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }


    /**
     * @return a User based on the information stored in this UserRegSaveRequest object
     * Default is StudentUser
     */
    public User initializeUser() {
        return new StudentUser(this.name, this.password);
    }

}
