package com.example.demo.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
public class UserModel {

    int idUser;
    String username;
    String password;
    String email;
    String fullname;
    String status;
    String role;
    Date lastLogin;
    String alamat;


}
