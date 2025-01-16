package com.bs.spring.demo.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//import java.util.Date;
import java.sql.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Demo {
    private String devName;
    private int devAge;
    private String devGender;
    private String[] devLang;
    private Address address;
    private Date birthday;
}

