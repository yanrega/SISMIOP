package com.sismiop.sismiop.repository;

import com.sismiop.sismiop.model.Spop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpopRepository extends JpaRepository<Spop, Long> {
    Spop findSpopById(Long id);
    Spop findSpopByPermohonanOpId(Long id);

}
