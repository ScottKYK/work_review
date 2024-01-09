package com.example.work_demo1.pojo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "systemuser")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SystemUser {
    @Id
    private String _id;
    private String account;
    private String password;
    private String name;
}
