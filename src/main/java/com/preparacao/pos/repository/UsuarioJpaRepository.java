/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.preparacao.pos.repository;

import com.preparacao.pos.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UsuarioJpaRepository extends JpaRepository<Usuario, Long> {
    
    
    public Usuario findOneByEmail(String email);

}
