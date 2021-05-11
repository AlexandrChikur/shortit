package ru.mirea.shortit.services;


import javax.servlet.http.HttpServletRequest;

public interface RequestServiceInterface {

    String getClientIp(HttpServletRequest request);
    String getClientDevice(HttpServletRequest request);

}