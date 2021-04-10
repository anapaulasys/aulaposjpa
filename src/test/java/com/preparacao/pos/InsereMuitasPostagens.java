/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.preparacao.pos;

import com.github.javafaker.Faker;
import com.preparacao.pos.model.Feed;
import com.preparacao.pos.model.Job;
import com.preparacao.pos.model.Story;
import com.preparacao.pos.model.Usuario;
import com.preparacao.pos.services.PostagemService;
import com.preparacao.pos.services.UsuarioService;
import java.math.BigDecimal;
import java.util.Locale;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

@SpringBootTest
public class InsereMuitasPostagens {

    @Autowired
    UsuarioService usuarioService;
    @Autowired
    PostagemService postagemService;

    @Test
    public void insereMuitos() {
        Page<Usuario> page = usuarioService.findAll(PageRequest.of(0, 999));
        Assertions.assertNotEquals(page.getContent().size(), 0);

        Faker faker = new Faker(new Locale("pt-BR"));
        int postgens = 0;
        for (Usuario usuario : page.getContent()) {
            try {
                Feed post = new Feed();
                post.setConteudo(faker.shakespeare().hamletQuote());
                post.setUsuario(usuario);
                postagemService.save(post);

                Story story = new Story();
                story.setConteudo(faker.superhero().name());
                story.setFoto(faker.internet().image());
                story.setUsuario(usuario);
                postagemService.save(story);

                Job job = new Job();
                job.setConteudo(faker.job().position());
                job.setEmpresa(faker.company().name());
                job.setUsuario(usuario);
                job.setRemuneracao(new BigDecimal(faker.number().numberBetween(1000, 3000)));
                postagemService.save(job);
            } catch (Exception e) {
                continue;
            }
            postgens++;

        }
        Assertions.assertEquals(page.getContent().size(), postgens);

    }

}
