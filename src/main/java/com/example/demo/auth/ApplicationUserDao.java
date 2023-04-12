package com.example.demo.auth;

public interface ApplicationUserDao {


    ApplicationUserDetails getUserByUsername(String username);
}
