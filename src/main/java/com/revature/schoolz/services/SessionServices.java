package com.revature.schoolz.services;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.revature.schoolz.dtos.responses.UserSession;
import com.revature.schoolz.models.User;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class SessionServices {
    private static final String SESSION_ID = "sessionId";

    public UserSession createSession(User user) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                .getRequest();
        HttpSession session = request.getSession(true);

        UserSession userSession = new UserSession(user);
        session.setAttribute(SESSION_ID, userSession);

        return userSession;
    }

    public void invalidateSession() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                .getRequest();
        HttpSession session = request.getSession(false);

        if (session != null) {
            session.invalidate();
        }
    }

    public UserSession getSession() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                .getRequest();
        HttpSession session = request.getSession(false);

        if (session != null) {
            return (UserSession) session.getAttribute(SESSION_ID);
        }
        return null;
    }
}
