package com.sismiop.sismiop.service;

import com.lowagie.text.Document;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfWriter;
import com.sismiop.sismiop.model.Penugasan;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public class ReportPDF extends AbstractPdfView {
    @Override
    protected void buildPdfDocument(Map<String, Object> map, Document document, PdfWriter pdfWriter, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        httpServletResponse.setHeader("Content-Disposition","attachment: filename=\"penugasan_list.pdf\"");

        @SuppressWarnings("unused")
        List <Penugasan> list= (List<Penugasan>) map.get("penugasanList");

        Table table=new Table(3);
        table.addCell("No Surat");
        table.addCell("NOP");
        table.addCell("Isi Surat");

        for (Penugasan penugasan:list){
            table.addCell(String.valueOf(penugasan.getId()));
            table.addCell(penugasan.getNOP());
            table.addCell(penugasan.getIsiSurat());
        }
        document.add(table);
    }
}
