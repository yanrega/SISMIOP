package com.sismiop.sismiop.service;

import com.sismiop.sismiop.model.Penduduk;
import com.sismiop.sismiop.model.Restitusi;
import com.sismiop.sismiop.repository.RestitusiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class RestitusiService implements ModelService<Restitusi, Long> {

    @Autowired
    private RestitusiRepository restitusiRepository;

    @Override
    public boolean add(Restitusi restitusi) {
        try{
            restitusiRepository.save(restitusi);
            return true;
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean addAll(Iterable<Restitusi> restitusis) {
        try{
            restitusiRepository.saveAll(restitusis);
            return true;
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean update(Restitusi restitusi) {
        return add(restitusi);
    }

    @Override
    public boolean delete(Long aLong) {
        try{
            Restitusi p = null;
            p = restitusiRepository.findRestitusiById(aLong);
            if(p != null) {
                p.setPenduduk(null);
                restitusiRepository.delete(p);
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
    public Restitusi getById(Long aLong) {
        return restitusiRepository.findRestitusiById(aLong);
    }
    public Page<Restitusi> getAllRestitusi(Pageable pageable){
        return restitusiRepository.findAll(pageable);
    }


    public Page<Restitusi> getAllRestitusiByPenduduk(Penduduk p, Pageable pageable) {
        return restitusiRepository.findRestitusiByPenduduk(p, pageable);
    }
}
