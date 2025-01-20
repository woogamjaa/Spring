package com.bs.spring.member.model.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Member {
    @NotEmpty(message = "헤헤")

    @Pattern(regexp = ".{4,}", message = "4 글자이상 한글만 가능합니다.")
    private String userId;

    @Pattern(regexp = "^(?=.*[0-9])(?=.*[!@#$%^&*(),.?\":{}|<>])(?=.*[a-zA-Z]).{8,}$", message = "비밀번호는 8자 이상이어야 하며, 숫자와 특수기호를 포함해야 합니다.")
    private String password;

    private String userName;

    private String gender;

    @Min(value=14 , message = "14새 이상 가능.")
    private int age;
    private String email;
    private String phone;
    private String address;

    @NotEmpty(message = "취미 반드시 작성하세요 ! ")
    private String[] hobby;
    private Date enrollDate;
}

