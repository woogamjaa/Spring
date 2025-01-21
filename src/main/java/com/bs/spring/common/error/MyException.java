package com.bs.spring.common.error;

import lombok.Getter;

import java.time.LocalDate;


@Getter
public class MyException extends RuntimeException {
    private LocalDate date;

    public MyException(final String message) {
        super(message);
        date=LocalDate.now();
    }
}
