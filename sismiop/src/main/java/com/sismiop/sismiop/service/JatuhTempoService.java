package com.sismiop.sismiop.service;

import com.sismiop.sismiop.model.JatuhTempo;
import com.sismiop.sismiop.model.Penduduk;
import com.sismiop.sismiop.repository.JatuhTempoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class JatuhTempoService implements ModelService<JatuhTempo, Long> {
    @Autowired
    private JatuhTempoRepository jatuhTempoRepository;

    @Override
    public boolean add(JatuhTempo jatuhTempo) {
        try{
            jatuhTempoRepository.save(jatuhTempo);
            return true;
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean addAll(Iterable<JatuhTempo> jatuhTempos) {
        try{
            jatuhTempoRepository.saveAll(jatuhTempos);
            return true;
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean update(JatuhTempo jatuhTempo) {
        return add(jatuhTempo);
    }

    @Override
    public boolean delete(Long aLong) {
        try{
            JatuhTempo p = null;
            p = jatuhTempoRepository.findJatuhTempoById(aLong);
            if(p != null) {
                p.setPenduduk(null);
                jatuhTempoRepository.delete(p);
                System.out.println("Berhasil menghapus permohonan");
                return true;
            }else {
                System.out.println("Gagal menghapus permohonan. Permohonan tidak ditemukan");
            }
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public JatuhTempo getById(Long aLong) {
        return jatuhTempoRepository.findJatuhTempoById(aLong);
    }

    public Page<JatuhTempo> getAllJatuhTempo(Pageable pageable){
        return jatuhTempoRepository.findAll(pageable);
    }


    public Page<JatuhTempo> getAllJatuhTempoByPenduduk(Penduduk p, Pageable pageable) {
        return jatuhTempoRepository.findJatuhTempoByPenduduk(p, pageable);
    }
}
