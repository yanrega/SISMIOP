package com.sismiop.sismiop.controller;

import com.sismiop.sismiop.model.*;
import com.sismiop.sismiop.repository.PermohonanRepository;
import com.sismiop.sismiop.service.*;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.WebParam;
import javax.validation.Valid;

@Controller
@ControllerAdvice
public class PendudukController {

    @Autowired
    private PendudukService pendudukService;

    @Autowired
    private PermohonanOpService permohonanOpService;

    @Autowired
    private PermohonanService permohonanService;

    @Autowired
    private RestitusiService restitusiService;

    @Autowired
    private KompensasiService kompensasiService;

    @Autowired
    private JatuhTempoService jatuhTempoService;

    @Autowired
    private PenugasanService penugasanService;

    @Autowired
    private PermohonanRepository permohonanRepository;

    @ModelAttribute
    public void addAttributes(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Penduduk p = pendudukService.getPendudukByNoTelp(auth.getName());
        model.addAttribute("authUser", p);
    }

    @GetMapping(value = {"/penduduk/register-spop"})
    public ModelAndView entrySPOP(Exception e) {
        ModelAndView m  = new ModelAndView();
        m.addObject("subTitle", "Registrasi SPOP Baru");
        m.setViewName("pages/penduduk/register-spop");
        return m;
    }

    @PostMapping(value = {"/penduduk/permohonan-op"})
    public ModelAndView savePermohonan(@Valid @ModelAttribute("newPermohonan") PermohonanOp permohonanOp, @Valid @ModelAttribute("authUser") Penduduk penduduk, BindingResult bindingResult) {
        ModelAndView m  = new ModelAndView();
        PermohonanOp op = new PermohonanOp();
        m.addObject("subTitle", "Registrasi Permohonan OP Baru");
        m.addObject("newPermohonan", op);
        permohonanOp.setPenduduk(penduduk);
        permohonanOp.setStatus(false);
        if(permohonanOpService.add(permohonanOp)) {
            m.addObject("success", true);
            m.setViewName("pages/penduduk/permohonan");
        }
        else {
            m.addObject("error", false);
            m.setViewName("pages/penduduk/permohonan");
        }
        return m;
    }

    @GetMapping(value = {"/penduduk/permohonan-op"})
    public ModelAndView createPermohonan(Exception e) {
        ModelAndView m  = new ModelAndView();
        PermohonanOp pop =  new PermohonanOp();
        m.addObject("subTitle", "Registrasi Permohonan OP");
        m.addObject("newPermohonan", pop);
        m.setViewName("pages/penduduk/permohonan");
        return m;
    }

//    @GetMapping("/penduduk/permohonan-op/daftar")
//    public ModelAndView getAllDaftarPermohonanOp(@PageableDefault(size = 7)Pageable pageable, @Valid @ModelAttribute("authUser") Penduduk p) {
//        ModelAndView m = new ModelAndView();
//        m.addObject("subTitle", "Daftar Permohonan OP");
//        Page<PermohonanOp> page =  permohonanOpService.getAllPermohonanByPenduduk(p, pageable);
//        m.addObject("page", page);
//        m.setViewName("pages/penduduk/daftar-permohonan-op");
//        return  m;
//    }

    @GetMapping("/penduduk/permohonan-op/delete/{id}")
    public ModelAndView deletePermohonanOp(@PathVariable ("id") Long id, @Valid @ModelAttribute("authUser") Penduduk p) {
        ModelAndView m = new ModelAndView();
        m.addObject("subTitle", "Daftar Permohonan OP");
        PermohonanOp pDelete = permohonanOpService.getById(id);
        if(pDelete.getPenduduk().getId().equals(p.getId())) {
            if(permohonanOpService.delete(id))
                m.addObject("msg", "Permohonan berhasil di hapus");
        }else {
            m.addObject("msg", "Permohonan Gagal di hapus");

        }
        m.setViewName("redirect:/penduduk/permohonan-op/daftar");
        return  m;
    }

    @GetMapping({"/penduduk/permohonan-op/edit/{id}"})
    public ModelAndView editPermohonanOp(@PathVariable ("id") Long id, @Valid @ModelAttribute("authUser") Penduduk p) {
        ModelAndView m = new ModelAndView();
        m.addObject("subTitle", "Edit Permohonan OP");
        PermohonanOp editPermohonan = permohonanOpService.getById(id);
        if(p.getPermohonanOps().contains(editPermohonan)) {
            m.addObject("editPermohonan", editPermohonan);
            m.setViewName("pages/penduduk/edit-permohonan");
        }else {
            m.setViewName("redirect:/403");
        }
        return  m;
    }

    @PostMapping({"/penduduk/permohonan-op/edit"})
    public ModelAndView saveEditPermohonan(PermohonanOp editPermohonan) {
        ModelAndView m  = new ModelAndView();
        if(permohonanOpService.update(editPermohonan)) {
            m.addObject("success", true);
            m.setViewName("redirect:/penduduk/permohonan-op/daftar");
        }
        else {
            m.addObject("error",true);
            m.setViewName("redirect:/penduduk/permohonan-op/daftar");
        }
        return m;
    }

    @PostMapping(value = {"/penduduk/permohonan"})
    public ModelAndView savePermohonanWP(@Valid @ModelAttribute("permohonan") Permohonan permohonan, @Valid @ModelAttribute("authUser") Penduduk penduduk, BindingResult bindingResult) {
        ModelAndView m  = new ModelAndView();
        Permohonan p = new Permohonan();
        m.addObject("subTitle", "Registrasi Permohonan");
        m.addObject("permohonan", p);
        permohonan.setPenduduk(penduduk);
        permohonan.setStatus(false);
        if(permohonanService.add(permohonan)) {
            m.addObject("success", true);
            m.setViewName("pages/penduduk/pbb-wajib-pajak");
        }
        else {
            m.addObject("error", false);
            m.setViewName("pages/penduduk/pbb-wajib-pajak");
        }
        return m;
    }

    @GetMapping(value = {"/penduduk/permohonan"})
    public ModelAndView createPermohonanWP(Exception e) {
        ModelAndView m  = new ModelAndView();
        Permohonan po =  new Permohonan();
        m.addObject("subTitle", "Registrasi Permohonan Pengurangan");
        m.addObject("permohonan", po);
        m.setViewName("pages/penduduk/pbb-wajib-pajak");
        return m;
    }

    @GetMapping({"/penduduk/permohonan/pengurangan/edit/{id}"})
    public ModelAndView editPermohonanPengurangan(@PathVariable ("id") Long id, @Valid @ModelAttribute("authUser") Penduduk p) {
        ModelAndView m = new ModelAndView();
        m.addObject("subTitle", "Edit Permohonan Pengurangan");
        Permohonan editPengurangan= permohonanService.getById(id);
        if(p.getPermohonans().contains(editPengurangan)) {
            m.addObject("editPengurangan", editPengurangan);
            m.setViewName("pages/penduduk/pbb-edit-permohonan-pengurangan");
        }else {
            m.setViewName("redirect:/403");
        }
        return  m;
    }

    @PostMapping({"/penduduk/permohonan/pengurangan/edit"})
    public ModelAndView saveEditPermohonanPengurangan(Permohonan editPengurangan) {
        System.out.println("masuuuuuuuuuuuuuuuuuuuuk");
        ModelAndView m  = new ModelAndView();
        if(permohonanService.update(editPengurangan)) {
            System.out.println("masuuuuuuuuuuuuuuuuuuuuk1111");
            m.addObject("success", true);
            m.addObject("editPengurangan", editPengurangan);
            m.addObject("subTitle", "Edit Permohonan Pengurangan");
            m.setViewName("pages/penduduk/pbb-edit-permohonan-pengurangan");
        }
        else {
            System.out.println("masuuuuuuuuuuuuuuuuuuuuk222");
            m.addObject("error",true);
            m.setViewName("pages/penduduk/pbb-edit-permohonan-pengurangan");
        }
        return m;
    }

    @GetMapping("/penduduk/permohonan/daftar-permohonan-wajib-pajak")
    public ModelAndView getAllDaftarPermohonanWajibPajak(@PageableDefault(size = 7)Pageable pageable, @Valid @ModelAttribute("authUser") Penduduk p) {
        ModelAndView m = new ModelAndView();
        m.addObject("subTitle", "Daftar Permohonan");
        Page<Permohonan> page =  permohonanService.getAllPermohonanByPenduduk(p, pageable);
        m.addObject("daftarPermohonan", page);
        m.setViewName("pages/penduduk/pbb-daftar-permohonanWajibPajak");
        return  m;
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

    @PostMapping(value = {"/penduduk/permohonan/daftar-permohonan-staff"})
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

    @PostMapping(value = {"/penduduk/permohonan/restitusi"})
    public ModelAndView savePermohonanRestitusiWP(@Valid @ModelAttribute("restitusi") Restitusi restitusi, @Valid @ModelAttribute("authUser") Penduduk penduduk, BindingResult bindingResult) {
        ModelAndView m  = new ModelAndView();
        Restitusi p = new Restitusi();
        m.addObject("subTitle", "Registrasi Permohonan Restitusi");
        m.addObject("restitusi", p);
        restitusi.setPenduduk(penduduk);
        restitusi.setStatus(false);
        if(restitusiService.add(restitusi)) {
            m.addObject("success", true);
            m.setViewName("pages/penduduk/pbb-permohonan-restitusi");
        }
        else {
            m.addObject("error", false);
            m.setViewName("pages/penduduk/pbb-permohonan-restitusi");
        }
        return m;
    }



    @GetMapping(value = {"/penduduk/permohonan/restitusi"})
    public ModelAndView createPermohonanRestitusiWP(Exception e) {
        ModelAndView m  = new ModelAndView();
        Restitusi po =  new Restitusi();
        m.addObject("subTitle", "Registrasi Permohonan Restitusi");
        m.addObject("restitusi", po);
        m.setViewName("pages/penduduk/pbb-permohonan-restitusi");
        return m;
    }

    @GetMapping({"/penduduk/permohonan/restitusi/edit/{id}"})
    public ModelAndView editPermohonanRestitusi(@PathVariable ("id") Long id, @Valid @ModelAttribute("authUser") Penduduk p) {
        ModelAndView m = new ModelAndView();
        m.addObject("subTitle", "Edit Permohonan Restitusi");
        Restitusi editRestitusi= restitusiService.getById(id);
        if(p.getRestitusis().contains(editRestitusi)) {
            m.addObject("editRestitusi", editRestitusi);
            m.setViewName("pages/penduduk/pbb-edit-permohonan-restitusi");
        }else {
            m.setViewName("redirect:/403");
        }
        return  m;
    }

    @PostMapping({"/penduduk/permohonan/restitusi/edit"})
    public ModelAndView saveEditPermohonanRestitusi(Restitusi editRestitusi) {
        System.out.println("masuuuuuuuuuuuuuuuuuuuuk");
        ModelAndView m  = new ModelAndView();
        if(restitusiService.update(editRestitusi)) {
            System.out.println("masuuuuuuuuuuuuuuuuuuuuk1111");
            m.addObject("success", true);
            m.addObject("editRestitusi", editRestitusi);
            m.addObject("subTitle", "Edit Permohonan Restitusi");
            m.setViewName("pages/penduduk/pbb-edit-permohonan-restitusi");
        }
        else {
            System.out.println("masuuuuuuuuuuuuuuuuuuuuk222");
            m.addObject("error",true);
            m.setViewName("pages/penduduk/pbb-edit-permohonan-restitusi");
        }
        return m;
    }

    @GetMapping("/penduduk/permohonan/daftar-permohonan-wajib-pajak/restitusi")
    public ModelAndView getAllDaftarPermohonanRestitusiWajibPajak(@PageableDefault(size = 7)Pageable pageable, @Valid @ModelAttribute("authUser") Penduduk p) {
        ModelAndView m = new ModelAndView();
        m.addObject("subTitle", "Daftar Permohonan Restitusi");
        Page<Restitusi> page =  restitusiService.getAllRestitusiByPenduduk(p, pageable);
        m.addObject("daftarRestitusi", page);
        m.setViewName("pages/penduduk/pbb-daftar-permohonan-restitusi");
        return  m;
    }

    @PostMapping(value = {"/penduduk/permohonan/kompensasi"})
    public ModelAndView savePermohonanKompensasiWP(@Valid @ModelAttribute("kompensasi") Kompensasi kompensasi, @Valid @ModelAttribute("authUser") Penduduk penduduk, BindingResult bindingResult) {
        ModelAndView m  = new ModelAndView();
        Kompensasi p = new Kompensasi();
        m.addObject("subTitle", "Registrasi Permohonan");
        m.addObject("kompensasi", p);
        kompensasi.setPenduduk(penduduk);
        kompensasi.setStatus(false);
        if(kompensasiService.add(kompensasi)) {
            m.addObject("success", true);
            m.setViewName("pages/penduduk/pbb-permohonan-kompensasi");
        }
        else {
            m.addObject("error", false);
            m.setViewName("pages/penduduk/pbb-permohonan-kompensasi");
        }
        return m;
    }

    @GetMapping(value = {"/penduduk/permohonan/kompensasi"})
    public ModelAndView createPermohonanKompenasiWP(Exception e) {
        ModelAndView m  = new ModelAndView();
        Kompensasi po =  new Kompensasi();
        m.addObject("subTitle", "Registrasi Permohonan Kompensasi");
        m.addObject("kompensasi", po);
        m.setViewName("pages/penduduk/pbb-permohonan-kompensasi");
        return m;
    }

    @GetMapping({"/penduduk/permohonan/kompensasi/edit/{id}"})
    public ModelAndView editPermohonanKompensasi(@PathVariable ("id") Long id, @Valid @ModelAttribute("authUser") Penduduk p) {
        ModelAndView m = new ModelAndView();
        m.addObject("subTitle", "Edit Permohonan Kompensasi");
        Kompensasi editKompensasi= kompensasiService.getById(id);
        if(p.getKompensasis().contains(editKompensasi)) {
            m.addObject("editKompensasi", editKompensasi);
            m.setViewName("pages/penduduk/pbb-edit-permohonan-kompensasi");
        }else {
            m.setViewName("redirect:/403");
        }
        return  m;
    }

    @PostMapping({"/penduduk/permohonan/kompensasi/edit"})
    public ModelAndView saveEditPermohonanKompensasi(Kompensasi editKompensasi) {
        System.out.println("masuuuuuuuuuuuuuuuuuuuuk");
        ModelAndView m  = new ModelAndView();
        if(kompensasiService.update(editKompensasi)) {
            System.out.println("masuuuuuuuuuuuuuuuuuuuuk1111");
            m.addObject("success", true);
            m.addObject("editKompensasi", editKompensasi);
            m.addObject("subTitle", "Edit Permohonan Kompensasi");
            m.setViewName("pages/penduduk/pbb-edit-permohonan-kompensasi");
        }
        else {
            System.out.println("masuuuuuuuuuuuuuuuuuuuuk222");
            m.addObject("error",true);
            m.setViewName("pages/penduduk/pbb-edit-permohonan-kompensasi");
        }
        return m;
    }

    @GetMapping("/penduduk/permohonan/daftar-permohonan-wajib-pajak/kompensasi")
    public ModelAndView getAllDaftarPermohonanKompensasiWajibPajak(@PageableDefault(size = 7)Pageable pageable, @Valid @ModelAttribute("authUser") Penduduk p) {
        ModelAndView m = new ModelAndView();
        m.addObject("subTitle", "Daftar Permohonan Kompensasi");
        Page<Kompensasi> page =  kompensasiService.getAllKompensasiByPenduduk(p, pageable);
        m.addObject("daftarKompensasi", page);
        m.setViewName("pages/penduduk/pbb-daftar-permohonan-kompensasi");
        return  m;
    }


    @PostMapping(value = {"/penduduk/permohonan/jatuh-tempo"})
    public ModelAndView savePermohonanJatuhTempoWP(@Valid @ModelAttribute("kompensasi") JatuhTempo jatuhTempo, @Valid @ModelAttribute("authUser") Penduduk penduduk, BindingResult bindingResult) {
        ModelAndView m  = new ModelAndView();
        JatuhTempo p = new JatuhTempo();
        m.addObject("subTitle", "Registrasi Permohonan Penentuan Kembali Jatuh Tempo");
        m.addObject("jatuhTempo", p);
        jatuhTempo.setPenduduk(penduduk);
        jatuhTempo.setStatus(false);
        if(jatuhTempoService.add(jatuhTempo)) {
            m.addObject("success", true);
            m.setViewName("pages/penduduk/pbb-permohonan-penentuan-kembali-jatuh-tempo");
        }
        else {
            m.addObject("error", false);
            m.setViewName("pages/penduduk/pbb-permohonan-penentuan-kembali-jatuh-tempo");
        }
        return m;
    }

    @GetMapping(value = {"/penduduk/permohonan/jatuh-tempo"})
    public ModelAndView createPermohonanJatuhTempoWP(Exception e) {
        ModelAndView m  = new ModelAndView();
        JatuhTempo po =  new JatuhTempo();
        m.addObject("subTitle", "Registrasi Permohonan Penentuan Kembali Jatuh Tempo");
        m.addObject("jatuhTempo", po);
        m.setViewName("pages/penduduk/pbb-permohonan-penentuan-kembali-jatuh-tempo");
        return m;
    }

    @GetMapping({"/penduduk/permohonan/jatuh-tempo/edit/{id}"})
    public ModelAndView editPermohonanJatuhTempo(@PathVariable ("id") Long id, @Valid @ModelAttribute("authUser") Penduduk p) {
        ModelAndView m = new ModelAndView();
        m.addObject("subTitle", "Edit Permohonan Jatuh Tempo");
        JatuhTempo editJatuhTempo= jatuhTempoService.getById(id);
        if(p.getJatuhTempos().contains(editJatuhTempo)) {
            m.addObject("editJatuhTempo", editJatuhTempo);
            m.setViewName("pages/penduduk/pbb-edit-permohonan-jatuh-tempo");
        }else {
            m.setViewName("redirect:/403");
        }
        return  m;
    }

    @PostMapping({"/penduduk/permohonan/jatuh-tempo/edit"})
    public ModelAndView saveEditPermohonanJatuhTempo(JatuhTempo editJatuhTempo) {
        System.out.println("masuuuuuuuuuuuuuuuuuuuuk");
        ModelAndView m  = new ModelAndView();
        if(jatuhTempoService.update(editJatuhTempo)) {
            System.out.println("masuuuuuuuuuuuuuuuuuuuuk1111");
            m.addObject("success", true);
            m.addObject("editJatuhTempo", editJatuhTempo);
            m.addObject("subTitle", "Edit Permohonan Jatuh Tempo");
            m.setViewName("pages/penduduk/pbb-edit-permohonan-jatuh-tempo");
        }
        else {
            System.out.println("masuuuuuuuuuuuuuuuuuuuuk222");
            m.addObject("error",true);
            m.setViewName("pages/penduduk/pbb-edit-permohonan-jatuh-tempo");
        }
        return m;
    }

    @GetMapping("/penduduk/permohonan/daftar-permohonan-wajib-pajak/jatuh-tempo")
    public ModelAndView getAllDaftarPermohonanJatuhTempoWajibPajak(@PageableDefault(size = 7)Pageable pageable, @Valid @ModelAttribute("authUser") Penduduk p) {
        ModelAndView m = new ModelAndView();
        m.addObject("subTitle", "Daftar Permohonan Jatuh Tempo");
        Page<JatuhTempo> page =  jatuhTempoService.getAllJatuhTempoByPenduduk(p, pageable);
        m.addObject("daftarJatuhTempo", page);
        m.setViewName("pages/penduduk/pbb-daftar-permohonan-jatuh-tempo");
        return  m;
    }

    @GetMapping("/penduduk/permohonan/delete/{id}")
    public ModelAndView deletePermohonan(@PathVariable ("id") Long id, @Valid @ModelAttribute("authUser") Penduduk p,@PageableDefault(size = 7)Pageable pageable) {
        ModelAndView m = new ModelAndView();
        m.addObject("subTitle", "Daftar Permohonan");
        Page<Permohonan> page =  permohonanService.getAllPermohonanByPenduduk(p, pageable);
        m.addObject("daftarPermohonan", page);
        permohonanService.delete(id);
//        if(pDelete.getPenduduk().getId().equals(p.getId())) {
        if(permohonanService.delete(id))
            m.addObject("msg", "Permohonan berhasil di hapus");
        else {
            m.addObject("msg", "Permohonan Gagal di hapus");
        }
//        }

        m.setViewName("redirect:/penduduk/permohonan/daftar-permohonan-wajib-pajak");
        return  m;
    }
    @GetMapping("/penduduk/permohonan/restitusi/delete/{id}")
    public ModelAndView deleteRestitusi(@PathVariable ("id") Long id, @Valid @ModelAttribute("authUser") Penduduk p,@PageableDefault(size = 7)Pageable pageable) {
        ModelAndView m = new ModelAndView();
        m.addObject("subTitle", "Daftar Permohonan");
        Page<Restitusi> page =  restitusiService.getAllRestitusiByPenduduk(p, pageable);
        m.addObject("daftarResitusi", page);
        restitusiService.delete(id);
        if(restitusiService.delete(id))
            m.addObject("msg", "Permohonan berhasil di hapus");
        else {
            m.addObject("msg", "Permohonan Gagal di hapus");
        }
        m.setViewName("redirect:/penduduk/permohonan/daftar-permohonan-wajib-pajak/restitusi");
        return  m;
    }

    @GetMapping("/penduduk/permohonan/kompensasi/delete/{id}")
    public ModelAndView deleteKompensasi(@PathVariable ("id") Long id, @Valid @ModelAttribute("authUser") Penduduk p,@PageableDefault(size = 7)Pageable pageable) {
        ModelAndView m = new ModelAndView();
        m.addObject("subTitle", "Daftar Permohonan");
        Page<Kompensasi> page =  kompensasiService.getAllKompensasiByPenduduk(p, pageable);
        m.addObject("daftarKompensasi", page);
        kompensasiService.delete(id);
        if(kompensasiService.delete(id))
            m.addObject("msg", "Permohonan berhasil di hapus");
        else {
            m.addObject("msg", "Permohonan Gagal di hapus");
        }
        m.setViewName("redirect:/penduduk/permohonan/daftar-permohonan-wajib-pajak/kompensasi");
        return  m;
    }

    @GetMapping("/penduduk/permohonan/jatuh-tempo/delete/{id}")
    public ModelAndView deleteJatuhTempo(@PathVariable ("id") Long id, @Valid @ModelAttribute("authUser") Penduduk p,@PageableDefault(size = 7)Pageable pageable) {
        ModelAndView m = new ModelAndView();
        m.addObject("subTitle", "Daftar Permohonan");
        Page<JatuhTempo> page =  jatuhTempoService.getAllJatuhTempoByPenduduk(p, pageable);
        m.addObject("daftarJatuhTempo", page);
        jatuhTempoService.delete(id);
        if(jatuhTempoService.delete(id))
            m.addObject("msg", "Permohonan berhasil di hapus");
        else {
            m.addObject("msg", "Permohonan Gagal di hapus");
        }
        m.setViewName("redirect:/penduduk/permohonan/daftar-permohonan-wajib-pajak/jatuh-tempo");
        return  m;
    }

}
