/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.preparacao.pos.resources;

import com.preparacao.pos.model.Comentario;
import com.preparacao.pos.model.Curtida;
import com.preparacao.pos.model.Feed;
import com.preparacao.pos.model.Job;
import com.preparacao.pos.model.Postagem;
import com.preparacao.pos.model.Story;
import com.preparacao.pos.services.PostagemService;
import com.preparacao.pos.util.HeaderUtil;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PostagemResource {

    @Autowired
    private PostagemService service;

    @GetMapping("/feed")
    public ResponseEntity<List<Feed>> getAllFeed(@RequestParam("page") Integer size, @RequestParam("limit") Integer limit) {
        try {
            Page<Feed> page = service.findAllFeed(PageRequest.of(size, limit));
            return ResponseEntity.ok().body(page.getContent());
        } catch (Exception e) {
            return HeaderUtil.getResposeError(e.getMessage());
        }
    }

    @GetMapping("/story")
    public ResponseEntity<List<Story>> getAllStory(@RequestParam("page") Integer size, @RequestParam("limit") Integer limit) {
        try {
            Page<Story> page = service.findAllStory(PageRequest.of(size, limit));
            return ResponseEntity.ok().body(page.getContent());
        } catch (Exception e) {
            return HeaderUtil.getResposeError(e.getMessage());
        }
    }

    @GetMapping("/job")
    public ResponseEntity<List<Job>> getAllJob(@RequestParam("page") Integer size, @RequestParam("limit") Integer limit) {
        try {
            Page<Job> page = service.findAllJob(PageRequest.of(size, limit));
            return ResponseEntity.ok().body(page.getContent());
        } catch (Exception e) {
            return HeaderUtil.getResposeError(e.getMessage());
        }
    }

    @GetMapping("/postagem-by-usuario")
    public ResponseEntity<List<Postagem>> getAllByUsuario(@RequestParam("email") String email, @RequestParam("page") Integer size, @RequestParam("limit") Integer limit) {
        try {
            Page<Postagem> page = service.findByUsuario(email, PageRequest.of(size, limit));
            return ResponseEntity.ok().body(page.getContent());
        } catch (Exception e) {
            return HeaderUtil.getResposeError(e.getMessage());
        }
    }

    @PostMapping("/feed")
    public ResponseEntity<Feed> create(@RequestBody @Valid Feed postagem) {
        try {
            return ResponseEntity.ok().body((Feed) service.save(postagem));
        } catch (Exception e) {
            return HeaderUtil.getResposeError(e.getMessage());
        }
    }

    @PostMapping("/story")
    public ResponseEntity<Story> create(@RequestBody @Valid Story postagem) {
        try {
            return ResponseEntity.ok().body((Story) service.save(postagem));
        } catch (Exception e) {
            return HeaderUtil.getResposeError(e.getMessage());
        }
    }

    @PostMapping("/job")
    public ResponseEntity<Job> create(@RequestBody @Valid Job postagem) {
        try {
            return ResponseEntity.ok().body((Job) service.save(postagem));
        } catch (Exception e) {
            return HeaderUtil.getResposeError(e.getMessage());
        }
    }

    @PostMapping("/curtir")
    public ResponseEntity<Postagem> curtir(@RequestBody @Valid Curtida curtida) {
        try {
            return ResponseEntity.ok().body(service.curtir(curtida));
        } catch (Exception e) {
            return HeaderUtil.getResposeError(e.getMessage());
        }
    }

    @PostMapping("/comentario")
    public ResponseEntity<Comentario> curtir(@RequestBody @Valid Comentario comentario) {
        try {
            return ResponseEntity.ok().body(service.comentar(comentario));
        } catch (Exception e) {
            return HeaderUtil.getResposeError(e.getMessage());
        }
    }

    @GetMapping("/comentario")
    public ResponseEntity<List<Comentario>> getAllComentarioByPostagem(@RequestParam("page") Integer size,
            @RequestParam("limit") Integer limit, @RequestParam("postagemId") Long postagemId) {
        try {
            Page<Comentario> page = service.getAllComentariosByPostagemId(postagemId, PageRequest.of(size, limit));
            return ResponseEntity.ok().body(page.getContent());
        } catch (Exception e) {
            return HeaderUtil.getResposeError(e.getMessage());
        }
    }

    @DeleteMapping("/postagem/}")
    public ResponseEntity remove(@PathVariable Long id) {
        try {
            service.remove(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return HeaderUtil.getResposeError(e.getMessage());
        }
    }
}
