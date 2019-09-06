package com.example.advt.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/*
 *@autor Hennadiy Voroboiv
 *@email henadiyv@gmail.com
 *22.08.2019
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)

public class NotFoundException extends RuntimeException{

}
