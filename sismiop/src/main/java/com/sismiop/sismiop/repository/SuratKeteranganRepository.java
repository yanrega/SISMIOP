package com.sismiop.sismiop.repository;

import com.sismiop.sismiop.model.Penduduk;
import com.sismiop.sismiop.model.SuratKeterangan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SuratKeteranganRepository extends JpaRepository<SuratKeterangan, Long> {
    SuratKeterangan findSuratKeteranganById(Long id);
    Page<SuratKeterangan> findSuratKeteranganByPenduduk(Penduduk p, Pageable pageable);
}
