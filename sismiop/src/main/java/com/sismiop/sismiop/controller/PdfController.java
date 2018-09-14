package com.sismiop.sismiop.controller;

import com.sismiop.sismiop.GeneratePdfReport;
import com.sismiop.sismiop.model.Penugasan;
import com.sismiop.sismiop.service.PenugasanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class PdfController {

    @Autowired
    PenugasanService penugasanService;

    @GetMapping(value = "/pdfreport/{id}", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> penugasansReport(@PathVariable("id") Long id) throws IOException {
        List<Penugasan> p = new ArrayList<>();

        p.add(penugasanService.getById(id));

        List<Penugasan> penugasans = p ;

        ByteArrayInputStream bis = GeneratePdfReport.penugasanReport(penugasans);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=penugasansreport.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }
}
