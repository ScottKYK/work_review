package com.example.work_demo1.pojo;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "goods")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Goods {
    @Id
    private String _id;
    private String name;
    private String cr_user;
    private LocalDateTime cr_datetime;
    private String up_user;
    private LocalDateTime up_datetime;
}
