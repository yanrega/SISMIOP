package com.sismiop.sismiop.repository;

import com.sismiop.sismiop.model.Penduduk;
import com.sismiop.sismiop.model.Restitusi;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestitusiRepository extends JpaRepository<Restitusi, Long> {
    Restitusi findRestitusiById(Long id);
    Page<Restitusi> findRestitusiByPenduduk(Penduduk p, Pageable pageable);
}
