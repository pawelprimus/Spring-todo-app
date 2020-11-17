package com.example.todoapp.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(annotations = IllegalExceptionProcessing.class)
// pozwala nam zapiac sie na obsluge bledow z kontrolerow
    /*Jednym z najpraktyczniejszych zastosowań adnotacji @ControllerAdvice jest zgromadzenie
    wszystkich metod obsługi wyjątków w pojedynczej klasie, aby wyjątki wystę-
pujące we wszystkich kontrolerach obsługiwane były w spójny sposób w jednym miejscu*/
class IllegalExceptionControllerAdvice {

    @ExceptionHandler(IllegalArgumentException.class)
    ResponseEntity<String> handlerIllegalArgument(IllegalArgumentException e) {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(IllegalStateException.class)
    ResponseEntity<String> handlerIllegalState(IllegalStateException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
