/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.preparacao.pos.util;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author scarpini
 */
public class HeaderUtil {

    public static ResponseEntity getResposeError(String... messageError) {
        return new ResponseEntity<>(null, HeaderUtil.setMessageError(messageError), HttpStatus.BAD_REQUEST);
    }

    public static HttpHeaders setMessageError(String... messageError) {
        HttpHeaders header = new HttpHeaders();
        for (String s : messageError) {
            header.add("message-error", s);
        }
        return header;
    }
}
