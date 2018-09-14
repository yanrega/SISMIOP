package com.sismiop.sismiop.controller;

import com.sismiop.sismiop.model.*;
import com.sismiop.sismiop.service.*;
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
@RequestMapping("/pelayanan")
public class PelayananController {
    @Autowired
    private PendudukService pendudukService;

    @Autowired
    private PermohonanOpService permohonanOpService;

    @Autowired
    private PermohonanService permohonanService;

    @Autowired
    private JatuhTempoService jatuhTempoService;

    @Autowired
    private RestitusiService restitusiService;

    @Autowired
    private KompensasiService kompensasiService;

    @Autowired
    private PenugasanService penugasanService;

    @GetMapping("/permohonan-op/daftar")
    public ModelAndView getAllDaftarPermohonanOp(@PageableDefault(size = 7)Pageable pageable, @Valid @ModelAttribute("authUser") Penduduk p) {
        ModelAndView m = new ModelAndView();
        m.addObject("subTitle", "Daftar Permohonan OP");
        Page<PermohonanOp> page =  permohonanOpService.getAllPermohonanByPenduduk(p, pageable);
        m.addObject("page", page);
        m.setViewName("pages/penduduk/daftar-permohonan-op");
        return  m;
    }

    @GetMapping("permohonan-op-penduduk/daftar")
    public ModelAndView getAllDaftarPermohonanOp(@PageableDefault(size = 15)Pageable pageable) {
        ModelAndView m = new ModelAndView();
        m.addObject("subTitle", "Daftar Permohonan OP Penduduk");
        Page<PermohonanOp> page =  permohonanOpService.getAllPermohonan(pageable);
        m.addObject("page", page);
        m.setViewName("pages/admin/daftar-semua-permohonan-op");
        return  m;
    }

    @PostMapping(value = {"/permohonan/penugasan"})
    public ModelAndView savePenugasan(@Valid @ModelAttribute("penugasan") Penugasan penugasan, @Valid @ModelAttribute("authUser") Penduduk penduduk, BindingResult bindingResult) {
        ModelAndView m  = new ModelAndView();
        Penugasan p = new Penugasan();

        m.addObject("subTitle", "Daftar Surat Pengantar");
        m.addObject("penugasan", p);
        penugasan.setPenduduk(penduduk);
        penugasan.setStatus(false);
        if(penugasanService.add(penugasan)) {
            m.addObject("success", true);
            m.setViewName("pages/pelayanan/pbb-penugasan");
        }
        else {
            m.addObject("error", false);
            m.setViewName("pages/pelayanan/pbb-penugasan");
        }
        return m;
    }

    @GetMapping("/permohonan/penugasan")
    public ModelAndView getAllPenugasan(@PageableDefault(size = 7) Pageable pageable, @Valid @ModelAttribute("authUser") Penduduk p) {
        ModelAndView m = new ModelAndView();
        Penugasan pe =  new Penugasan();
        m.addObject("subTitle", "Registrasi surat Pengantar");
        m.addObject("penugasan", pe);
        m.setViewName("pages/pelayanan/pbb-penugasan");
        return m;
    }



    @GetMapping("/penduduk/permohonan/daftar-permohonanStaff")
    public ModelAndView getDaftarPermohonanStaff(@PageableDefault(size = 7)Pageable pageable, @Valid @ModelAttribute("authUser") Penduduk p) {
        ModelAndView m = new ModelAndView();
        m.addObject("subTitle", "Daftar Permohonan");
        Page<Permohonan> page =  permohonanService.getAllPermohonanByPenduduk(p, pageable);
        m.addObject("permohonanStaff", page);
        m.setViewName("pages/penduduk/pbb-daftarPermohonanStaff");
        return  m;
    }

    @PostMapping(value = {"/permohonan/daftar-permohonan-staff"})
    public ModelAndView getDaftarPermohonanStaff(@Valid @ModelAttribute("permohonanStaff") Permohonan permohonan, @Valid @ModelAttribute("authUser") Penduduk penduduk, BindingResult bindingResult) {
        ModelAndView m  = new ModelAndView();
        Permohonan p = new Permohonan();
        m.addObject("subTitle", "Registrasi Permohonan");
        m.addObject("permohonanStaff", p);
        permohonan.setPenduduk(penduduk);
        permohonan.setStatus(false);
        if(permohonanService.add(permohonan)) {
            m.addObject("success", true);
            m.setViewName("pages/penduduk/pbb-penugasan");
        }
        else {
            m.addObject("error", false);
            m.setViewName("pbb-daftarPermohonanStaff");
        }
        return m;
    }

    @GetMapping("permohonan-pengurangan-penduduk/daftar")
    public ModelAndView getAllDaftarPermohonanPengurangan(@PageableDefault(size = 15)Pageable pageable) {
        ModelAndView m = new ModelAndView();
        m.addObject("subTitle", "Daftar Permohonan Pengurangan PBB");
        Page<Permohonan> page =  permohonanService.getAllPermohonan(pageable);
        m.addObject("permohonanPengurangan", page);
        m.setViewName("pages/admin/daftar-semua-permohonan-pengurangan");
        return  m;
    }
    @GetMapping("permohonan-restitusi-penduduk/daftar")
    public ModelAndView getAllDaftarPermohonanRestitusi(@PageableDefault(size = 15)Pageable pageable) {
        ModelAndView m = new ModelAndView();
        m.addObject("subTitle", "Daftar Permohonan Restitusi PBB");
        Page<Restitusi> page =  restitusiService.getAllRestitusi(pageable);
        m.addObject("permohonanRestitusi", page);
        m.setViewName("pages/admin/daftar-semua-permohonan-restitusi");
        return  m;
    }

    @GetMapping("permohonan-kompensasi-penduduk/daftar")
    public ModelAndView getAllDaftarPermohonanKompensasi(@PageableDefault(size = 15)Pageable pageable) {
        ModelAndView m = new ModelAndView();
        m.addObject("subTitle", "Daftar Permohonan Kompensasi PBB");
        Page<Kompensasi> page =  kompensasiService.getAllKompensasi(pageable);
        m.addObject("permohonanKompensasi", page);
        m.setViewName("pages/admin/daftar-semua-permohonan-kompensasi");
        return  m;
    }

    @GetMapping("permohonan-jatuh-tempo-penduduk/daftar")
    public ModelAndView getAllDaftarPermohonanJatuhTempo(@PageableDefault(size = 15)Pageable pageable) {
        ModelAndView m = new ModelAndView();
        m.addObject("subTitle", "Daftar Permohonan Penentuan Jatuh Tempo PBB");
        Page<JatuhTempo> page =  jatuhTempoService.getAllJatuhTempo(pageable);
        m.addObject("permohonanJatuhTempo", page);
        m.setViewName("pages/admin/daftar-semua-permohonan-jatuh-tempo");
        return  m;
    }



    @GetMapping("permohonan-pengurangan-penduduk/setujui/{id}")
    public ModelAndView setujuiPermohonanPengurangan(@PathVariable("id") Long id) {
        ModelAndView m = new ModelAndView();
        if(updateStatusPermohonanPengurangan(id, true)) {
            m.addObject("msg", "Permohonan berhasil disetujui");
        }else {
            m.addObject("msg", "Permohonan Gagal di disetujui");

        }
        m.setViewName("redirect:/pelayanan/permohonan/penugasan");
        return  m;
    }

    @GetMapping("permohonan-pengurangan-penduduk/batal-menyetujui/{id}")
    public ModelAndView batalMenyetujuiPermohonanPengurangan(@PathVariable("id") Long id) {
        ModelAndView m = new ModelAndView();
        if(updateStatusPermohonanPengurangan(id, false)) {
            m.addObject("msg", "Permohonan berhasil disetujui");
        }else {
            m.addObject("msg", "Permohonan Gagal disetujui");

        }
        m.setViewName("redirect:/pelayanan/permohonan-pengurangan-penduduk/daftar");
        return  m;
    }
    private boolean updateStatusPermohonanPengurangan(Long id, boolean status) {
        Permohonan p = permohonanService.getById(id);
        p.setStatus(status);
        return permohonanService.add(p);
    }

    @GetMapping("permohonan-restitusi-penduduk/setujui/{id}")
    public ModelAndView setujuiPermohonanRestitusi(@PathVariable("id") Long id) {
        ModelAndView m = new ModelAndView();
        if(updateStatusPermohonanRestitusi(id, true)) {
            m.addObject("msg", "Permohonan berhasil disetujui");
        }else {
            m.addObject("msg", "Permohonan Gagal di disetujui");

        }
        m.setViewName("redirect:/pelayanan/permohonan/penugasan");
        return  m;
    }

    @GetMapping("permohonan-restitusi-penduduk/batal-menyetujui/{id}")
    public ModelAndView batalMenyetujuiPermohonanRestitusi(@PathVariable("id") Long id) {
        ModelAndView m = new ModelAndView();
        if(updateStatusPermohonanRestitusi(id, false)) {
            m.addObject("msg", "Permohonan berhasil disetujui");
        }else {
            m.addObject("msg", "Permohonan Gagal disetujui");

        }
        m.setViewName("redirect:/pelayanan/permohonan-pengurangan-penduduk/daftar");
        return  m;
    }

    private boolean updateStatusPermohonanRestitusi(Long id, boolean status) {
        Restitusi p = restitusiService.getById(id);
        p.setStatus(status);
        return restitusiService.add(p);
    }

    @GetMapping("permohonan-kompensasi-penduduk/setujui/{id}")
    public ModelAndView setujuiPermohonanKompensasi(@PathVariable("id") Long id) {
        ModelAndView m = new ModelAndView();
        if(updateStatusPermohonanKompensasi(id, true)) {
            m.addObject("msg", "Permohonan berhasil disetujui");
        }else {
            m.addObject("msg", "Permohonan Gagal di disetujui");

        }
        m.setViewName("redirect:/pelayanan/permohonan/penugasan");
        return  m;
    }

    @GetMapping("permohonan-kompensasi-penduduk/batal-menyetujui/{id}")
    public ModelAndView batalMenyetujuiPermohonanKompensasi(@PathVariable("id") Long id) {
        ModelAndView m = new ModelAndView();
        if(updateStatusPermohonanKompensasi(id, false)) {
            m.addObject("msg", "Permohonan berhasil disetujui");
        }else {
            m.addObject("msg", "Permohonan Gagal disetujui");

        }
        m.setViewName("redirect:/pelayanan/permohonan-kompensasi-penduduk/daftar");
        return  m;
    }

    private boolean updateStatusPermohonanKompensasi(Long id, boolean status) {
        Kompensasi p = kompensasiService.getById(id);
        p.setStatus(status);
        return kompensasiService.add(p);
    }

    @GetMapping("permohonan-jatuh-tempo-penduduk/setujui/{id}")
    public ModelAndView setujuiPermohonan(@PathVariable("id") Long id) {
        ModelAndView m = new ModelAndView();
        if(updateStatusPermohonanJatuhTempo(id, true)) {
            m.addObject("msg", "Permohonan berhasil disetujui");
        }else {
            m.addObject("msg", "Permohonan Gagal di disetujui");

        }
        m.setViewName("redirect:/pelayanan/permohonan/penugasan");
        return  m;
    }

    @GetMapping("permohonan-jatuh-tempo-penduduk/batal-menyetujui/{id}")
    public ModelAndView batalMenyetujuiPermohonanJatuhTempo(@PathVariable("id") Long id) {
        ModelAndView m = new ModelAndView();
        if(updateStatusPermohonanJatuhTempo(id, false)) {
            m.addObject("msg", "Permohonan berhasil disetujui");
        }else {
            m.addObject("msg", "Permohonan Gagal disetujui");

        }
        m.setViewName("redirect:/pelayanan/permohonan-pengurangan-penduduk/daftar");
        return  m;
    }

    private boolean updateStatusPermohonanJatuhTempo(Long id, boolean status) {
        JatuhTempo p = jatuhTempoService.getById(id);
        p.setStatus(status);
        return jatuhTempoService.add(p);
    }

    @GetMapping({"/permohonan/pengurangan/detail/{id}"})
    public ModelAndView detailPermohonanPengurangan(@PathVariable ("id") Long id, @Valid @ModelAttribute("authUser") Penduduk p) {
        ModelAndView m = new ModelAndView();
        m.addObject("subTitle", "Detail Permohonan Pengurangan");
        Permohonan detailPengurangan= permohonanService.getById(id);
            m.addObject("detailPengurangan", detailPengurangan);
            m.setViewName("pages/pelayanan/pbb-detail-permohonan-pengurangan");

        return  m;
    }

    @GetMapping({"/permohonan/jatuh-tempo/detail/{id}"})
    public ModelAndView detailPermohonanJatuhTempo(@PathVariable ("id") Long id, @Valid @ModelAttribute("authUser") Penduduk p) {
        ModelAndView m = new ModelAndView();
        m.addObject("subTitle", "Detail Permohonan Jatuh Tempo");
        JatuhTempo detailJatuhTempo= jatuhTempoService.getById(id);
        m.addObject("detailJatuhTempo", detailJatuhTempo);
        m.setViewName("pages/pelayanan/pbb-detail-permohonan-jatuh-tempo");

        return  m;
    }

    @GetMapping({"/permohonan/kompensasi/detail/{id}"})
    public ModelAndView detailPermohonanKompensasi(@PathVariable ("id") Long id, @Valid @ModelAttribute("authUser") Penduduk p) {
        ModelAndView m = new ModelAndView();
        m.addObject("subTitle", "Detail Permohonan Kompensasi");
        Kompensasi detailKompensasi= kompensasiService.getById(id);
        m.addObject("detailKompensasi", detailKompensasi);
        m.setViewName("pages/pelayanan/pbb-detail-permohonan-kompensasi");

        return  m;
    }

    @GetMapping({"/permohonan/restitusi/detail/{id}"})
    public ModelAndView detailPermohonanRestitusi(@PathVariable ("id") Long id, @Valid @ModelAttribute("authUser") Penduduk p) {
        ModelAndView m = new ModelAndView();
        m.addObject("subTitle", "Detail Permohonan Restitusi");
        Restitusi detailRestitusi= restitusiService.getById(id);
        m.addObject("detailRestitusi", detailRestitusi);
        m.setViewName("pages/pelayanan/pbb-detail-permohonan-restitusi");

        return  m;
    }
}
