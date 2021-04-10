/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.preparacao.pos;


import com.github.javafaker.Faker;
import com.preparacao.pos.model.Usuario;
import com.preparacao.pos.services.UsuarioService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class InsereMuitosUsuarios {

    @Autowired
    UsuarioService usuarioService;

    @Test
    public void insereMuitos() {
        Faker faker = new Faker();
        int qnt = 1000;
        while (qnt > 0) {
            Usuario usuario1 = new Usuario();
            usuario1.setNome(faker.gameOfThrones().character());
            usuario1.setEmail(faker.internet().emailAddress());
            usuario1.setSenha(faker.internet().password());
            usuario1.setBiografia(faker.hobbit().quote());
            usuario1.setFoto(faker.internet().avatar());
            try {
                usuarioService.save(usuario1);
            } catch (Exception e) {
                continue;
            }
            qnt--;
        }
        Assertions.assertEquals(0, qnt);
    }

}
