/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.preparacao.pos.resources;

import java.util.List;
import com.preparacao.pos.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.preparacao.pos.services.UsuarioService;
import com.preparacao.pos.util.HeaderUtil;
import javax.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api")
public class UsuarioResource {

    @Autowired
    private UsuarioService service;

    @GetMapping("/usuario")
    public ResponseEntity<List<Usuario>> getAll(@RequestParam("page") Integer size, @RequestParam("limit") Integer limit) {
        try {
            Page<Usuario> page = service.findAll(PageRequest.of(size, limit));
            return ResponseEntity.ok().body(page.getContent());
        } catch (Exception e) {
            return HeaderUtil.getResposeError(e.getMessage());
        }
    }

    @PostMapping("/usuario")
    public ResponseEntity<Usuario> create(@RequestBody @Valid Usuario usuario) {
        try {
            return ResponseEntity.ok().body(service.save(usuario));
        } catch (Exception e) {
            return HeaderUtil.getResposeError(e.getMessage());
        }
    }

    @DeleteMapping("/usuario/{id}")
    public ResponseEntity remove(@PathVariable Long id) {
        try {
            service.remove(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return HeaderUtil.getResposeError(e.getMessage());
        }
    }
}
