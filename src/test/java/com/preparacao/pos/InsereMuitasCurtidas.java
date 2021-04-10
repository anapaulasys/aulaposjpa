/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.preparacao.pos;

import com.preparacao.pos.model.Curtida;
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
public class InsereMuitasCurtidas {

    @Autowired
    UsuarioService usuarioService;
    @Autowired
    PostagemService postagemService;

    @Test
    public void insereMuitos() {
        Page<Usuario> pageUsuario = usuarioService.findAll(PageRequest.of(0, 999));
        Page<Feed> pagePostagem = postagemService.findAllFeed(PageRequest.of(0, 99));

        for (Feed postagem : pagePostagem.getContent()) {
            int quantidade = new Random().nextInt(10);
            for (int i = 0; i < quantidade; i++) {
                int indexDoUsuario = new Random().nextInt(pageUsuario.getNumberOfElements());
                Curtida curtida = new Curtida();
                curtida.setFeed(postagem);
                curtida.setUsuario(pageUsuario.getContent().get(indexDoUsuario));
                postagemService.curtir(curtida);
            }
        }

    }

}
