package com.sismiop.sismiop.service;

import com.sismiop.sismiop.model.Penduduk;
import com.sismiop.sismiop.model.SuratKeterangan;
import com.sismiop.sismiop.repository.SuratKeteranganRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SuratKeteranganService implements ModelService<SuratKeterangan, Long> {
    @Autowired
    private SuratKeteranganRepository suratKeteranganRepository;

    @Override
    public boolean add(SuratKeterangan suratKeterangan) {
        try{
            suratKeteranganRepository.save(suratKeterangan);
            return true;
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean addAll(Iterable<SuratKeterangan> suratKeterangans) {
        try{
            suratKeteranganRepository.saveAll(suratKeterangans);
            return true;
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean update(SuratKeterangan suratKeterangan) {
        return add(suratKeterangan);
    }

    @Override
    public boolean delete(Long aLong) {
        try{
            SuratKeterangan p = null;
            p = suratKeteranganRepository.findSuratKeteranganById(aLong);
            if(p != null) {
                p.setPenduduk(null);
                suratKeteranganRepository.delete(p);
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
    public SuratKeterangan getById(Long aLong) {
        return suratKeteranganRepository.findSuratKeteranganById(aLong);
    }


    public Page<SuratKeterangan> getAllSuratKeterangan(Pageable pageable){
        return suratKeteranganRepository.findAll(pageable);
    }

    public List<SuratKeterangan> findAll() {

        List<SuratKeterangan> suratKeterangans= (List<SuratKeterangan>) suratKeteranganRepository.findAll();

        return suratKeterangans;
    }
    public Page<SuratKeterangan> getAllSuratKeteranganByPenduduk(Penduduk p, Pageable pageable) {
        return suratKeteranganRepository.findSuratKeteranganByPenduduk(p, pageable);
    }
}
