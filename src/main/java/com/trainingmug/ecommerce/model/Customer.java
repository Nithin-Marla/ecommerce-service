package com.trainingmug.ecommerce.model;

import com.trainingmug.ecommerce.enums.Gender;
import com.trainingmug.ecommerce.enums.Status;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Customer {
    private int id;
    private String name;
    private String email;
    private String phone;
    private String password;
    private Gender gender;
    private Status status; //Backend operation
    private LocalDateTime createdAt;
    private LocalDateTime lastLoggedInAt;

}
