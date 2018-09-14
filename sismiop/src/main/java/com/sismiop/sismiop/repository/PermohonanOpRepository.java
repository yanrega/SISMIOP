package com.sismiop.sismiop.repository;

import com.sismiop.sismiop.model.Penduduk;
import com.sismiop.sismiop.model.PermohonanOp;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermohonanOpRepository extends JpaRepository<PermohonanOp, Long> {
    PermohonanOp findPermohonanOpById(Long id);
    Page<PermohonanOp> findPermohonanOpByPenduduk(Penduduk p, Pageable pageable);


}
