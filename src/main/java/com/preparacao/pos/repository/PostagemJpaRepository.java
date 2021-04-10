/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.preparacao.pos.repository;

import com.preparacao.pos.model.Postagem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PostagemJpaRepository extends JpaRepository<Postagem, Long> {

    @Query("select p from Postagem p where lower(p.usuario.email) = lower(:email)")
    public Page<Postagem> findByUsuario(@Param("email") String email, Pageable pageable);
    
    
    public Page<Postagem> findByOrderByIdDesc(Pageable pageable);
    
    
   
}
