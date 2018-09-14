package com.sismiop.sismiop.service;

import com.sismiop.sismiop.model.Kompensasi;
import com.sismiop.sismiop.model.Penduduk;
import com.sismiop.sismiop.repository.KompensasiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class KompensasiService implements ModelService<Kompensasi, Long> {
    @Autowired
    private KompensasiRepository kompensasiRepository;

    @Override
    public boolean add(Kompensasi kompensasi) {
        try{
            kompensasiRepository.save(kompensasi);
            return true;
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean addAll(Iterable<Kompensasi> kompensasis) {
        try{
            kompensasiRepository.saveAll(kompensasis);
            return true;
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean update(Kompensasi kompensasi) {
        return add(kompensasi);
    }

    @Override
    public boolean delete(Long aLong) {
        try{
            Kompensasi p = null;
            p = kompensasiRepository.findKompensasiById(aLong);
            if(p != null) {
                p.setPenduduk(null);
                kompensasiRepository.delete(p);
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
    public Kompensasi getById(Long aLong) {
        return kompensasiRepository.findKompensasiById(aLong);
    }

    public Page<Kompensasi> getAllKompensasi(Pageable pageable){
        return kompensasiRepository.findAll(pageable);
    }


    public Page<Kompensasi> getAllKompensasiByPenduduk(Penduduk p, Pageable pageable) {
        return kompensasiRepository.findKompensasiByPenduduk(p, pageable);
    }
}
