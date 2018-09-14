package com.sismiop.sismiop.service;

import com.sismiop.sismiop.model.Penduduk;
import com.sismiop.sismiop.model.Penugasan;
import com.sismiop.sismiop.repository.PenugasanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PenugasanService implements ModelService<Penugasan, Long> {
    @Autowired
    private PenugasanRepository penugasanRepository;

    @Override
    public boolean add(Penugasan penugasan) {
        try{
            penugasanRepository.save(penugasan);
            return true;
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean addAll(Iterable<Penugasan> penugasans) {
        try{
            penugasanRepository.saveAll(penugasans);
            return true;
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean update(Penugasan penugasan) {
        return add(penugasan);
    }

    @Override
    public boolean delete(Long aLong) {
        try{
            Penugasan p = null;
            p = penugasanRepository.findPenugasanById(aLong);
            if(p != null) {
                p.setPenduduk(null);
                penugasanRepository.delete(p);
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
    public Penugasan getById(Long aLong) {
        return penugasanRepository.findPenugasanById(aLong);
    }

    public Page<Penugasan> getAllPenugasan(Pageable pageable){
        return penugasanRepository.findAll(pageable);
    }

    public List<Penugasan> findAll() {

        List<Penugasan> penugasans = (List<Penugasan>) penugasanRepository.findAll();

        return penugasans;
    }
    public Page<Penugasan> getAllPenugasanByPenduduk(Penduduk p, Pageable pageable) {
        return penugasanRepository.findPenugasanByPenduduk(p, pageable);
    }
}
