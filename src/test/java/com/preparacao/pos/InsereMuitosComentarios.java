/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.preparacao.pos;

import com.github.javafaker.Faker;
import com.preparacao.pos.model.Comentario;
import com.preparacao.pos.model.Feed;
import com.preparacao.pos.model.Usuario;
import com.preparacao.pos.services.PostagemService;
import com.preparacao.pos.services.UsuarioService;
import java.util.Random;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

@SpringBootTest
public class InsereMuitosComentarios {

    @Autowired
    UsuarioService usuarioService;
    @Autowired
    PostagemService postagemService;

    @Test
    public void insereMuitos() {
        Page<Usuario> pageUsuario = usuarioService.findAll(PageRequest.of(0, 999));
        Page<Feed> pagePostagem = postagemService.findAllFeed(PageRequest.of(0, 99));
        Faker faker = new Faker();
        for (Feed postagem : pagePostagem.getContent()) {
            int quantidade = new Random().nextInt(3);
            for (int i = 0; i < quantidade; i++) {
                int indexDoUsuario = new Random().nextInt(pageUsuario.getNumberOfElements());
                Comentario comentario = new Comentario();
                comentario.setFeed(postagem);
                comentario.setUsuario(pageUsuario.getContent().get(indexDoUsuario));
                comentario.setConteudo(faker.lorem().paragraph());
                postagemService.comentar(comentario);
            }
        }

    }

}
