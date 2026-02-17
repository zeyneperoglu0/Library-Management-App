package com.tpe.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Response <T>{

    private final boolean success;
    private final String message;
    private T data;
}
