package com.rain.springbootdemo.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity                // ставя эту аннотацию мы говорим, что эта сущность связана с БД
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
}
