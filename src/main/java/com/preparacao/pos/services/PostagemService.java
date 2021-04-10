/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.preparacao.pos.services;

import com.preparacao.pos.model.Comentario;
import com.preparacao.pos.model.Curtida;
import com.preparacao.pos.model.Feed;
import com.preparacao.pos.model.Job;
import com.preparacao.pos.model.Postagem;
import com.preparacao.pos.model.Story;
import com.preparacao.pos.repository.ComentarioJpaRepository;
import com.preparacao.pos.repository.FeedJpaRepository;
import com.preparacao.pos.repository.JobJpaRepository;
import java.util.Optional;
import javax.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.preparacao.pos.repository.PostagemJpaRepository;
import com.preparacao.pos.repository.StoryJpaRepository;
import java.util.Date;

@Service
public class PostagemService {

    @Autowired
    private PostagemJpaRepository jpaRepository;
    @Autowired
    private ComentarioJpaRepository comentarioJpaRepository;
    @Autowired
    private FeedJpaRepository feedJpaRepository;
    @Autowired
    private StoryJpaRepository storyJpaRepository;
    @Autowired
    private JobJpaRepository jobJpaRepository;

    public Page<Feed> findAllFeed(Pageable page) {
        return feedJpaRepository.findByOrderByIdDesc(page);
    }

    public Page<Story> findAllStory(Pageable page) {
        return storyJpaRepository.findByOrderByIdDesc(page);
    }

    public Page<Job> findAllJob(Pageable page) {
        return jobJpaRepository.findByOrderByIdDesc(page);
    }

    public Page<Postagem> findByUsuario(String email, Pageable page) {
        return jpaRepository.findByUsuario(email, page);
    }

    public Postagem save(Postagem postagem) {
        if (postagem.getPostadoEm() == null) {
            postagem.setPostadoEm(new Date());
        }
        return jpaRepository.save(postagem);
    }

    public void remove(Long id) {
        Optional<Postagem> postagem = jpaRepository.findById(id);
        if (postagem.isPresent()) {
            jpaRepository.delete(postagem.get());
        } else {
            throw new ValidationException("Não existe existe um usuário com esse ID");
        }
    }

    public Postagem curtir(Curtida curtida) {
        Optional<Feed> optional = feedJpaRepository.findById(curtida.getFeed().getId());
        ///validar para não curtir duas vezes
        if (optional.isPresent()) {
            curtida.setCriadoEm(new Date());
            final Feed postagem = optional.get();
            postagem.getCurtidas().add(curtida);
            return save(postagem);
        } else {
            throw new ValidationException("Não existe postagem com esse ID");
        }
    }

    public Comentario comentar(Comentario comentario) {
        Optional<Feed> optional = feedJpaRepository.findById(comentario.getFeed().getId());
        if (optional.isPresent()) {
            comentario.setCriadoEm(new Date());
            comentario.setFeed(optional.get());
            return comentarioJpaRepository.save(comentario);
        } else {
            throw new ValidationException("Não existe postagem com esse ID");
        }
    }

    public Page<Comentario> getAllComentariosByPostagemId(Long postagemId, Pageable pageable) {
        return comentarioJpaRepository.findByFeedId(postagemId, pageable);
    }

}
