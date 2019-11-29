package com.example.advt.controller;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/*
 *@autor Hennadiy Voroboiv
 *@email henadiyv@gmail.com
 *25.08.2019
 */
public class UtilsController {
    static Map<String,String> getErrors(BindingResult bindingResult){
        Collector<FieldError,?,Map<String,String>> collector=Collectors.toMap(
                fieldError->fieldError.getField()+"Error",
                FieldError::getDefaultMessage
        );
        return bindingResult.getFieldErrors().stream().collect(collector);
    }
}
