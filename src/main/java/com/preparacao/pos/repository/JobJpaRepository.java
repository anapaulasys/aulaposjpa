/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.preparacao.pos.repository;

import com.preparacao.pos.model.Job;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface JobJpaRepository extends JpaRepository<Job, Long> {

    @Query("select p from Job p where lower(p.usuario.email) = lower(:email)")
    public Page<Job> findByUsuario(@Param("email") String email, Pageable pageable);

    public Page<Job> findByOrderByIdDesc(Pageable pageable);

}
