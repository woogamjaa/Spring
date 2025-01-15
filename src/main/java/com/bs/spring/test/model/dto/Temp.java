package com.bs.spring.test.model.dto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
//어노테이션 방식으로 클래스를 spring bean 으로 등록
@Component("t")
public class Temp {
    private Person p;
    private Animal a;


    //a매개변수있는 생성자 선언
    //생성자러 의존성 주입을 받기!
    //@Autowired 를 생략해도 되고 각 매개변수에다가도 넣을 수 있음.
    public Temp(@Autowired @Qualifier ("person1") Person p,
                @Autowired @Qualifier ("animal") Animal a){
        this.p = p;
        this.a = a;
    }



    public void printDate(){
        System.out.println("Person"+p);
        System.out.println("Animal"+a);
    }

}
