package com.sismiop.sismiop.repository;

import com.sismiop.sismiop.model.Penduduk;
import com.sismiop.sismiop.model.Permohonan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermohonanRepository extends JpaRepository<Permohonan, Long> {
    Permohonan findPermohonanById(Long id);
    Page<Permohonan> findPermohonanByPenduduk(Penduduk p, Pageable pageable);

}
