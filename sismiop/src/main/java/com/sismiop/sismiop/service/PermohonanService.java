package com.sismiop.sismiop.service;

import com.sismiop.sismiop.model.Penduduk;
import com.sismiop.sismiop.model.Permohonan;
import com.sismiop.sismiop.repository.PermohonanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PermohonanService implements ModelService<Permohonan, Long> {

    @Autowired
    private PermohonanRepository permohonanRepository;

    @Override
    public boolean add(Permohonan permohonan) {
        try{
            permohonanRepository.save(permohonan);
            return true;
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean addAll(Iterable<Permohonan> permohonans) {
        try{
            permohonanRepository.saveAll(permohonans);
            return true;
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean update(Permohonan permohonan) {
        return add(permohonan);
    }

    @Override
    public boolean delete(Long aLong) {
        try{
            Permohonan p = null;
            p = permohonanRepository.findPermohonanById(aLong);
            if(p != null) {
                p.setPenduduk(null);
                permohonanRepository.delete(p);
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
    public Permohonan getById(Long aLong) {
        return permohonanRepository.findPermohonanById(aLong);
    }

    public Page<Permohonan> getAllPermohonan(Pageable pageable){
        return permohonanRepository.findAll(pageable);
    }


    public Page<Permohonan> getAllPermohonanByPenduduk(Penduduk p, Pageable pageable) {
        return permohonanRepository.findPermohonanByPenduduk(p, pageable);
    }
}

