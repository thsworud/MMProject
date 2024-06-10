package com.shop.demo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="MyList")
public class MyList {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column
private int list;

@Column
private String id;
@Column(length=30, nullable=false)
private String day1;
@Column(length=20,nullable=false)
private String event1;
@Column(length=10,nullable=false)
private String weight1;
@Column(length=20,nullable=false)
private String number1;
@Column(length=20,nullable=false)
private String set1;
@Column(length=100,nullable=false)
private String feedback1;
}
