package com.sismiop.sismiop.controller;

import com.sismiop.sismiop.model.HasilPemeriksaan;
import com.sismiop.sismiop.model.Penduduk;
import com.sismiop.sismiop.model.Penugasan;
import com.sismiop.sismiop.service.HasilPemeriksaanService;
import com.sismiop.sismiop.service.PenugasanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("/penilaian")
public class PenilaianController {

    @Autowired
    private PenugasanService penugasanService;

    @Autowired
    private HasilPemeriksaanService hasilPemeriksaanService;

    @GetMapping("/permohonan/daftar-penugasan")
    public ModelAndView getAllDaftarPenugasan(@PageableDefault(size = 7) Pageable pageable, @Valid @ModelAttribute("authUser") Penduduk p) {
        ModelAndView m = new ModelAndView();
        m.addObject("subTitle", "Daftar Surat Pengantar");
        Page<Penugasan> page =  penugasanService.getAllPenugasan(pageable);
        m.addObject("daftarPenugasan", page);
        m.setViewName("pages/penilaian/pbb-daftar-penugasan");
        return  m;
    }

    @GetMapping({"/permohonan/penugasan/detail/{id}"})
    public ModelAndView detailPenugasan(@PathVariable("id") Long id, @Valid @ModelAttribute("authUser") Penduduk p) {
        ModelAndView m = new ModelAndView();
        m.addObject("subTitle", "Detail Penugasan");
        Penugasan detailPenugasan= penugasanService.getById(id);
        if(p.getPenugasans().contains(detailPenugasan)) {
            m.addObject("detailPenugasan", detailPenugasan);
            m.setViewName("pages/penilaian/pbb-detail-penugasan");
        }else {
            m.setViewName("redirect:/403");
        }
        return  m;
    }

    private boolean updateStatusPenugasan(Long id, boolean status) {
        Penugasan p = penugasanService.getById(id);
        p.setStatus(status);
        return penugasanService.add(p);
    }

    @GetMapping("permohonan-penugasan/setujui/{id}")
    public ModelAndView setujuiPenugasan(@PathVariable("id") Long id) {
        ModelAndView m = new ModelAndView();
        if(updateStatusPenugasan(id, true)) {
            m.addObject("msg", "Penugasan berhasil disetujui");
        }else {
            m.addObject("msg", "Penugasan Gagal disetujui");

        }
        m.setViewName("redirect:/penilaian/permohonan/hasil-pemeriksaan");
        return  m;
    }

    @GetMapping("permohonan-penugasan/batal-menyetujui/{id}")
    public ModelAndView batalMenyetujuiPenugasan(@PathVariable("id") Long id) {
        ModelAndView m = new ModelAndView();
        if(updateStatusPenugasan(id, false)) {
            m.addObject("msg", "Penugasan berhasil disetujui");
        }else {
            m.addObject("msg", "Penugasan Gagal disetujui");

        }
        m.setViewName("redirect:/penilaian/permohonan/hasil-pemeriksaan");
        return  m;
    }

    @GetMapping("/permohonan/hasil-pemeriksaan")
    public ModelAndView getAllHasilPemeriksaan(@PageableDefault(size = 7) Pageable pageable, @Valid @ModelAttribute("authUser") Penduduk p) {
        ModelAndView m = new ModelAndView();
        HasilPemeriksaan pe =  new HasilPemeriksaan();
        m.addObject("subTitle", "Registrasi surat Hasil Pemeriksaan");
        m.addObject("pemeriksaan", pe);
        m.setViewName("pages/penilaian/pbb-membuat-hasil-pemeriksaan-lapangan");
        return m;
    }

    @PostMapping(value = {"/permohonan/hasil-pemeriksaan"})
    public ModelAndView saveHasilPemeriksaan(@Valid @ModelAttribute("pemeriksaan") HasilPemeriksaan hasilPemeriksaan, @Valid @ModelAttribute("authUser") Penduduk penduduk, BindingResult bindingResult) {
        ModelAndView m  = new ModelAndView();
        HasilPemeriksaan p = new HasilPemeriksaan();

        m.addObject("subTitle", "Registrasi surat Hasil Pemeriksaan");
        m.addObject("pemeriksaan", p);
        hasilPemeriksaan.setPenduduk(penduduk);
        hasilPemeriksaan.setStatus(false);
        if(hasilPemeriksaanService.add(hasilPemeriksaan)) {
            m.addObject("success", true);
            m.setViewName("pages/penilaian/pbb-membuat-hasil-pemeriksaan-lapangan");
        }
        else {
            m.addObject("error", false);
            m.setViewName("pages/penilaian/pbb-membuat-hasil-pemeriksaan-lapangan");
        }
        return m;
    }

}
