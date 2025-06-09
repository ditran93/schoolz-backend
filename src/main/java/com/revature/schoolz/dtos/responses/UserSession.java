package com.revature.schoolz.dtos.responses;

import com.revature.schoolz.models.UserRole;
import com.revature.schoolz.models.User;

import lombok.Data;

@Data
public class UserSession {
    private Long id;
    private String username;
    private UserRole role;

    public UserSession(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.role = user.getRole();
    }
}
