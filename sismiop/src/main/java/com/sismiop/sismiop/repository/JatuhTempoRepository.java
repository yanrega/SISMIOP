package com.sismiop.sismiop.repository;

import com.sismiop.sismiop.model.JatuhTempo;
import com.sismiop.sismiop.model.Penduduk;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JatuhTempoRepository extends JpaRepository<JatuhTempo, Long> {
    JatuhTempo findJatuhTempoById(Long id);
    Page<JatuhTempo> findJatuhTempoByPenduduk(Penduduk p, Pageable pageable);
}
