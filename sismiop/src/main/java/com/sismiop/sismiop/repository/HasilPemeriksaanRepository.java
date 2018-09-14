package com.sismiop.sismiop.repository;

import com.sismiop.sismiop.model.HasilPemeriksaan;
import com.sismiop.sismiop.model.Penduduk;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HasilPemeriksaanRepository extends JpaRepository<HasilPemeriksaan, Long> {
    HasilPemeriksaan findHasilPemeriksaanById(Long id);
    Page<HasilPemeriksaan> findHasilPemeriksaanByPenduduk(Penduduk p, Pageable pageable);
}
