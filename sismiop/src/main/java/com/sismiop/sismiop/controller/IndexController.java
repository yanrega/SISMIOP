package com.sismiop.sismiop.controller;

import com.sismiop.sismiop.model.Jabatan;
import com.sismiop.sismiop.model.Penduduk;
import com.sismiop.sismiop.service.JabatanService;
import com.sismiop.sismiop.service.PendudukService;
import org.springframework.beans.factory.annotation.Autowired;
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
public class IndexController {

    @Autowired
    PendudukService pendudukService;

    @Autowired
    JabatanService jabatanService;

    @GetMapping({"/dashboard", "/"})
    public ModelAndView home() {

        ModelAndView m  = new ModelAndView();
        m.addObject("subTitle", "Dashboard");
        m.setViewName("pages/dashboard");

        return m;
    }

    @RequestMapping("/login")
    public ModelAndView loginUser()
    {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("subTitle", "Login");
        modelAndView.setViewName("login");
        return modelAndView;
    }

    // for 403 access denied page
    @GetMapping({"/403"})
    public ModelAndView accesssDenied() {
        ModelAndView model = new ModelAndView();
        model.addObject("subTitle", "Akses ditolak");
        model.setViewName("403");
        return model;

    }

    @GetMapping({"/404", "/error"})
    public ModelAndView pageNotFound() {

        ModelAndView model = new ModelAndView();
        model.addObject("subTitle", "Halaman tidak ditemukan");
        model.setViewName("404");
        return model;

    }

    @GetMapping("/register")
    public  ModelAndView registerPenduduk() {
        ModelAndView m = new ModelAndView();
        Penduduk p = new Penduduk();
        m.addObject("penduduk", p);
        m.setViewName("pages/penduduk/register-penduduk");
        return m;
    }

    @PostMapping("/register")
    public  ModelAndView savePendudukToDatabase(@Valid Penduduk penduduk, BindingResult bindingResult) {
        ModelAndView m  = new ModelAndView();
        Penduduk pendudukExists = pendudukService.getPendudukByNoTelp(penduduk.getNoTelp());
        if(pendudukExists != null) {
            bindingResult
                    .rejectValue("no_telp", "error.penduduk",
                            "Nomor telepon telah terdaftar");
        }
        if (bindingResult.hasErrors()) {
            m.setViewName("pages/penduduk/register-penduduk");
        } else {
            Jabatan j = jabatanService.getById(new Long(2));
            penduduk.getJabatans().add(j);
            pendudukService.add(penduduk);
            jabatanService.addAll(penduduk.getJabatans());
            m.addObject("successMessage", "Penduduk telah berhasil disimpan. Silahkan login");
            m.addObject("penduduk", new Penduduk());
            m.setViewName("redirect:/login");

        }
        return  m;
    }

//    @PostMapping({"/edit/profil"})
//    public ModelAndView saveEditPermohonanPengurangan(Penduduk editPenduduk) {
//        ModelAndView m  = new ModelAndView();
//        if(pendudukService.update(editPenduduk)) {
//            m.addObject("success", true);
//            m.addObject("editPenduduk", editPenduduk);
//            m.addObject("subTitle", "Edit Penduduk");
//            m.setViewName("pages/profil");
//        }
//        else {
//            m.addObject("error",true);
//            m.setViewName("pages/profil");
//        }
//        return m;
//    }

    @GetMapping({"/edit/profil/{id}"})
    public ModelAndView editProfil(@PathVariable("id") Long id, @Valid @ModelAttribute("authUser") Penduduk p) {
        System.out.println("masuuuuuuuuuuuuuuuuuuuuk");
        ModelAndView m = new ModelAndView();
        m.addObject("subTitle", "Edit Penduduk");
        Penduduk editPenduduk= pendudukService.getById(id);
//        if(p.getPermohonans().contains(editPenduduk)) {
        m.addObject("editPenduduk", editPenduduk);
        //m.setViewName("root");
        m.setViewName("pages/profil");
//        }else {
//            m.setViewName("redirect:/403");
//        }
        return  m;
    }

    @PostMapping({"/edit/profil"})
    public ModelAndView saveEditPermohonanPengurangan(Penduduk editPenduduk) {
        ModelAndView m  = new ModelAndView();
        if(pendudukService.update(editPenduduk)) {
            m.addObject("success", true);
            m.addObject("editPenduduk", editPenduduk);
            m.addObject("subTitle", "Edit Penduduk");
            m.setViewName("pages/profil");
        }
        else {
            m.addObject("error",true);
            m.setViewName("pages/profil");
        }
        return m;
    }
}
