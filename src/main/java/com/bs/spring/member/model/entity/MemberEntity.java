package com.bs.spring.member.model.entity;

import com.bs.spring.member.model.dto.Member;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name="MEMBER")
public class MemberEntity {
    @Id
    private String userId;
    private String password;
    private String userName;
    private String gender;
    private Integer age;
    //@Builder.Default
    private String email;
    private String phone;
    private String address;
    private String hobby;
//    @Builder.Default
    private Date enrollDate=Date.valueOf(LocalDate.now());


    public Member toMember() {
        return Member.builder()
                .userId(userId)
                .name(userName)   //db랑 연결해야 하기 떄문에.
                .age(age)
                .email(email)
                .phone(phone)
                .address(address)
                .hobby(hobby!=null?hobby.split(","):null)
                .enrollDate(enrollDate)
                .build();
    }

    public static MemberEntity fromMember(Member m) {
        return MemberEntity.builder()
                .userId(m.getUserId())
                .userName(m.getName())   //db랑 연결해야 하기 떄문에.
                .age(m.getAge())
                .email(m.getEmail())
                .phone(m.getPhone())
                .address(m.getAddress()
                )
                .hobby(m.getHobby()!=null?String.join(",",m.getHobby()):null)
                .enrollDate(m.getEnrollDate())
                .build();
    }
}
