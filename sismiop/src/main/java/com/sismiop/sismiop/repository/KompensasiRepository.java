package com.sismiop.sismiop.repository;

import com.sismiop.sismiop.model.Kompensasi;
import com.sismiop.sismiop.model.Penduduk;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KompensasiRepository extends JpaRepository<Kompensasi, Long> {
    Kompensasi findKompensasiById(Long id);
    Page<Kompensasi> findKompensasiByPenduduk(Penduduk p, Pageable pageable);
}
