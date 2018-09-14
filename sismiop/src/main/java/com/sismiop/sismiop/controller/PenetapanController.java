package com.sismiop.sismiop.controller;

import com.sismiop.sismiop.model.HasilPemeriksaan;
import com.sismiop.sismiop.model.Penduduk;
import com.sismiop.sismiop.model.SuratKeterangan;
import com.sismiop.sismiop.service.HasilPemeriksaanService;
import com.sismiop.sismiop.service.SuratKeteranganService;
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
@RequestMapping("/penetapan")
public class PenetapanController {

    @Autowired
    private HasilPemeriksaanService hasilPemeriksaanService;

    @Autowired
    private SuratKeteranganService suratKeteranganService;

    @GetMapping({"/surat-keterangan"})
    public ModelAndView detailPermohonanPengurangan() {
        ModelAndView m = new ModelAndView();
        SuratKeterangan p =new SuratKeterangan();
        m.addObject("subTitle", "Surat Keterangan Permohonan PBB");
        m.addObject("suratKeterangan", p);
        m.setViewName("pages/penetapan/pbb_surat_keterangan");

        return  m;
    }

    @PostMapping(value = {"/suratKeterangan"})
    public ModelAndView savePenugasan(@Valid @ModelAttribute("suratKeterangan") SuratKeterangan suratKeterangan, @Valid @ModelAttribute("authUser") Penduduk penduduk, BindingResult bindingResult) {
        ModelAndView m  = new ModelAndView();
        SuratKeterangan p = new SuratKeterangan();

        m.addObject("subTitle", "Daftar Surat Keterangan");
        m.addObject("suratKeterangan", p);
        suratKeterangan.setPenduduk(penduduk);
        suratKeterangan.setStatus(false);
        if(suratKeteranganService.add(suratKeterangan)) {
            m.addObject("success", true);
            m.setViewName("pages/penetapan/pbb_surat_keterangan");
        }
        else {
            m.addObject("error", false);
            m.setViewName("pages/penetapan/pbb_surat_keterangan");
        }
        return m;
    }


    @GetMapping("/hasil-pemeriksaan/daftar")
    public ModelAndView getAllDaftarHasilPemeriksaan(@PageableDefault(size = 15) Pageable pageable) {
        ModelAndView m = new ModelAndView();
        m.addObject("subTitle", "Daftar Hasil Pemeriksaan");
        Page<HasilPemeriksaan> page =  hasilPemeriksaanService.getAllPemeriksaans(pageable);
        m.addObject("daftarPemeriksaan", page);
        m.setViewName("pages/penetapan/pbb_daftar_hasil_pemeriksaan_lapangan");
        return  m;
    }

    @GetMapping("/hasil-pemeriksaan/setujui/{id}")
    public ModelAndView setujuiHasilPemeriksaan(@PathVariable("id") Long id) {
        ModelAndView m = new ModelAndView();
        if(updateStatusHasilPemeriksaan(id, true)) {
            m.addObject("msg", "Hasil Pemeriksaan berhasil disetujui");
        }else {
            m.addObject("msg", "Permohonan Gagal di disetujui");

        }
        m.setViewName("redirect:/penetapan/surat-keterangan");
        return  m;
    }

    private boolean updateStatusHasilPemeriksaan(Long id, boolean status) {
        HasilPemeriksaan p = hasilPemeriksaanService.getById(id);
        p.setStatus(status);
        return hasilPemeriksaanService.add(p);
    }

    @GetMapping("/surat-keterangan/daftar")
    public ModelAndView getAllDaftarSuratKeterangan(@PageableDefault(size = 15) Pageable pageable) {
        ModelAndView m = new ModelAndView();
        m.addObject("subTitle", "Daftar Surat Keterangan");
        Page<SuratKeterangan> page =  suratKeteranganService.getAllSuratKeterangan(pageable);
        m.addObject("daftarSK", page);
        m.setViewName("pages/penetapan/pbb-daftar-surat-keterangan");
        return  m;
    }

    @GetMapping("/surat-keterangan/setujui/{id}")
    public ModelAndView setujuiSuratKeterangan(@PathVariable("id") Long id) {
        ModelAndView m = new ModelAndView();
        if(updateStatusSuratKeterangan(id, true)) {
            m.addObject("msg", "Surat Keterangan Diambil");
        }else {
            m.addObject("msg", "Permohonan Gagal di disetujui");

        }
        m.setViewName("redirect:/penetapan/surat-keterangan/daftar");
        return  m;
    }

    private boolean updateStatusSuratKeterangan(Long id, boolean status) {
        SuratKeterangan p = suratKeteranganService.getById(id);
        p.setStatus(status);
        return suratKeteranganService.add(p);
    }

}
