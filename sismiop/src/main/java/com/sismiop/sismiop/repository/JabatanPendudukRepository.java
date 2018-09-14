package com.sismiop.sismiop.repository;

import com.sismiop.sismiop.model.JabatanPenduduk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface JabatanPendudukRepository extends JpaRepository<JabatanPenduduk, Long> {
    JabatanPenduduk findJabatanPendudukById(Long id);
    JabatanPenduduk findJabatanPendudukByPendudukIdAndJabatanId(Long pendudukId, Long jabatanId);
    JabatanPenduduk findJabatanPendudukByPendudukId(Long pendudukId);

    @Query(name="DELETE FROM jabatan_penduduk WHERE id=?")
    void deleteJabatanPendudukByPendudukId(Long id);


}
