/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.preparacao.pos.services;

import com.preparacao.pos.model.Usuario;
import com.preparacao.pos.repository.UsuarioJpaRepository;
import java.util.Optional;
import javax.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioJpaRepository jpaRepository;

    public Page<Usuario> findAll(Pageable page) {
        return jpaRepository.findAll(page);
    }
    
    public Usuario findOneByEmail(String email){
        return jpaRepository.findOneByEmail(email);
    }

    public Usuario save(Usuario usuario) {
        Usuario existente = jpaRepository.findOneByEmail(usuario.getEmail());
        if (existente != null) {
            throw new ValidationException("Já Existe um usuário com esse email");
        }
        return jpaRepository.save(usuario);
    }

    public void remove(Long id) {
        Optional<Usuario> usuario = jpaRepository.findById(id);
        if (usuario.isPresent()) {
            jpaRepository.delete(usuario.get());
        }else{
            throw new ValidationException("Não existe existe um usuário com esse ID");
        }
    }
    
}
