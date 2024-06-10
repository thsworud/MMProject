package com.shop.demo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

import lombok.Data;


@Data
@Entity
@Table(name="MMMember")
public class MMMember {
    @Id
    @Column
    private String id;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    @Column(length=40, nullable=false)
    private String password;

    @NotBlank(message = "이름을 입력하세요.")
    @Column(length=10, nullable=false)
    private String name;

    @Column(length=10, nullable=false)
    private String gender;

    @NotBlank(message = "전화번호를 입력하세요.")
    @Column(length=13, nullable=false)
    private String phone;
}
