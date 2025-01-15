package com.bs.spring.test.controller;

import com.bs.spring.test.model.dto.Animal;
import com.bs.spring.test.model.dto.Person;
import com.bs.spring.test.model.dto.Temp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;
import java.util.Scanner;

@Controller
public class TestController {
    //의존성 등록하기
    //spring bean으로 등록된 객체를 가져와 사용
    //@Autowired 어노테이션을 이용해서 선언.
    //필드값, 매개변수 만 설정이 가능 < 지역변수는 불가능

    @Autowired
    private Animal animal; //bean에 등록된 애들중에 알아서 넣어준다.

//  @Autowired
    //1. 변수명을 보고 일치하는값이 있는지 찾는다.
    //A 라는 이름의 ID가 없기에 의존성주입이 불가능함.
    //그래서 TYPE 찾아가려고 하는데 Animal 타입의 등록된 bean 3개인데 아무거나 집어넣을수 있는가? 아니다. 에러 보내줌.
//  private Animal A; < 못찾음.

    @Autowired
    @Qualifier("animal2") //@Qualifier 지정하여 주입한다.
    private Animal a;


    @Autowired
    @Qualifier("person")
    private Person p;

    //spring bean으로 등록되지 않은 클래스를
    //@Autowired 선언하면 에러가 발생 !

    //@Autowired 어노테이션 옵션설정 하는법  필수값 -> 무조건 넣어 ! 없으면 에러
    @Autowired(required = true) // 있으면 넣고 없으면 넣지마. // 예외처리를 옵션으로 설정
    private Scanner sc; // 필수값이 아니기 때문에 null로 처리


    @Autowired
    private Temp t;

    //string이 생성한 객체 가져오기
    @Autowired
    private WebApplicationContext wc;{
        System.out.println("Welcome to Spring Boot Test");
    }

    @Autowired
    List<Animal> animals;

    @RequestMapping("/test/beantest")
    public void testbean() {
        System.out.println("testbean실행"); // testbean실행
     // System.out.println(animal); // null 뜸. // @Autowired 를 추가하고 실행한 결과들 임. Animal(name=null, age=0, gender=null, weight=0.0)
        System.out.println(a); //@Autowired 넣을시 Animal(name=null, age=0, gender=null, weight=0.0) 이렇게 뜸.
        System.out.println(p); // Person(name=유병승, age=19, address=경기도 시흥시, animal=Animal(name=도토리사망, age=0, gender=null, weight=0.0))

        t.printDate(); //PersonPerson(name=null, age=0, address=null, animal=Animal(name=설기, age=1, gender=남, weight=3.12))
                        //AnimalAnimal(name=null, age=0, gender=null, weight=0.0)

        //WebApplicationContext이용
        System.out.println("===설정된 bean rkwudhrl");
        for (String name : wc.getBeanDefinitionNames()){
            System.out.println(name);
        }
        System.out.println("List로 객체 가져오기");
        animals.forEach(System.out::println);

    }


}
