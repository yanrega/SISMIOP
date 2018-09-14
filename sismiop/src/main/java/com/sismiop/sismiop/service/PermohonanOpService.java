package com.sismiop.sismiop.service;

import com.sismiop.sismiop.model.Penduduk;
import com.sismiop.sismiop.model.PermohonanOp;
import com.sismiop.sismiop.repository.PermohonanOpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.lang.reflect.Executable;

@Service
public class PermohonanOpService implements  ModelService<PermohonanOp, Long>{
    @Autowired
    private PermohonanOpRepository permohonanOpRepository;

    @Override
    public boolean add(PermohonanOp permohonanOp) {
        try{
            permohonanOpRepository.save(permohonanOp);
            return true;
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean addAll(Iterable<PermohonanOp> permohonanOps) {
        try{
            permohonanOpRepository.saveAll(permohonanOps);
            return true;
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean update(PermohonanOp permohonanOp) {
        return add(permohonanOp);
    }

    @Override
    public boolean delete(Long aLong) {
        try{
            PermohonanOp p = null;
            p = permohonanOpRepository.findPermohonanOpById(aLong);
            if(p != null) {
                p.setPenduduk(null);
                p.setSpop(null);
                permohonanOpRepository.delete(p);
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
    public PermohonanOp getById(Long aLong) {
        return permohonanOpRepository.findPermohonanOpById(aLong);
    }

    public Page<PermohonanOp> getAllPermohonan(Pageable pageable){
        return permohonanOpRepository.findAll(pageable);
    }


    public Page<PermohonanOp> getAllPermohonanByPenduduk(Penduduk p, Pageable pageable) {
        return permohonanOpRepository.findPermohonanOpByPenduduk(p, pageable);
    }
}
