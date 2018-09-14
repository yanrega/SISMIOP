package com.sismiop.sismiop.service;

import com.sismiop.sismiop.model.HasilPemeriksaan;
import com.sismiop.sismiop.model.Penduduk;
import com.sismiop.sismiop.repository.HasilPemeriksaanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HasilPemeriksaanService implements ModelService<HasilPemeriksaan, Long> {

    @Autowired
    private HasilPemeriksaanRepository hasilPemeriksaanRepository;

    @Override
    public boolean add(HasilPemeriksaan hasilPemeriksaan) {
        try{
            hasilPemeriksaanRepository.save(hasilPemeriksaan);
            return true;
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean addAll(Iterable<HasilPemeriksaan> hasilPemeriksaans) {
        try{
            hasilPemeriksaanRepository.saveAll(hasilPemeriksaans);
            return true;
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean update(HasilPemeriksaan hasilPemeriksaan) {
        return add(hasilPemeriksaan);
    }

    @Override
    public boolean delete(Long aLong) {
        try{
            HasilPemeriksaan p = null;
            p = hasilPemeriksaanRepository.findHasilPemeriksaanById(aLong);
            if(p != null) {
                p.setPenduduk(null);
                hasilPemeriksaanRepository.delete(p);
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
    public HasilPemeriksaan getById(Long aLong) {
        return hasilPemeriksaanRepository.findHasilPemeriksaanById(aLong);
    }

    public Page<HasilPemeriksaan> getAllPemeriksaans(Pageable pageable){
        return hasilPemeriksaanRepository.findAll(pageable);
    }

    public List<HasilPemeriksaan> findAll() {

        List<HasilPemeriksaan> hasilPemeriksaans = (List<HasilPemeriksaan>) hasilPemeriksaanRepository.findAll();

        return hasilPemeriksaans;
    }
    public Page<HasilPemeriksaan> getAllHasilPemeriksaanByPenduduk(Penduduk p, Pageable pageable) {
        return hasilPemeriksaanRepository.findHasilPemeriksaanByPenduduk(p, pageable);
    }
}
