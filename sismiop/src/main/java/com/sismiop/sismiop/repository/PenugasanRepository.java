package com.sismiop.sismiop.repository;

import com.sismiop.sismiop.model.Penduduk;
import com.sismiop.sismiop.model.Penugasan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PenugasanRepository extends JpaRepository<Penugasan, Long> {
    Penugasan findPenugasanById(Long id);
    Page<Penugasan> findPenugasanByPenduduk(Penduduk p, Pageable pageable);

}
