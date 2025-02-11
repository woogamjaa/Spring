package com.bs.spring.demo.model.entity;


import com.bs.spring.demo.model.dto.Demo;
import jakarta.persistence.*;
import lombok.Data;

@Data

@Entity
@Table(name="DEMO")
@SequenceGenerator(name="seqdemono", sequenceName = "SEQ_DEV_NO" , allocationSize = 1)
public class DemoEntity {
    @Id
    @GeneratedValue(generator = "seqdemono", strategy = GenerationType.SEQUENCE)
    private long devNo;

    private String devName;
    private Integer devAge;
    private String devEmail;
    private String devGender;
    private String devLang;

    public Demo toDemo() {
        return Demo.builder()
                .devName(this.devName)
                .devEmail(this.devEmail)
                .devGender(devGender)
                .devLang(devLang!=null?devLang.split(","):null)
                .build();
    }

    public static DemoEntity fromDemo(Demo demo) {
        DemoEntity demoEntity = new DemoEntity();
        demoEntity.setDevName(demo.getDevName());
        demoEntity.setDevEmail(demo.getDevEmail());
        demoEntity.setDevGender(demo.getDevGender());
        demoEntity.setDevAge(demo.getDevAge());
        demoEntity.setDevLang(demo.getDevLang()!=null?String.join(",",demo.getDevLang()):null);
        return demoEntity;
    }
}
