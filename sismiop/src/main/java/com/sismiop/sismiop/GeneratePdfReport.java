package com.sismiop.sismiop;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import com.sismiop.sismiop.model.Penugasan;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GeneratePdfReport {

    public static ByteArrayInputStream penugasanReport(List<Penugasan> penugasanList) {

        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Date d = new Date();

        try {

            PdfPTable table = new PdfPTable(4);
            table.setWidthPercentage(60);
            table.setWidths(new int[]{1, 3, 5, 6});

            Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);

//            PdfPCell hcell;
//            hcell = new PdfPCell(new Phrase("Id", headFont));
//            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
//            table.addCell(hcell);
//
//            hcell = new PdfPCell(new Phrase("NOP", headFont));
//            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
//            table.addCell(hcell);
//
//            hcell = new PdfPCell(new Phrase("Jenis Permohonan", headFont));
//            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
//            table.addCell(hcell);
//
//            hcell = new PdfPCell(new Phrase("Isi Surat", headFont));
//            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
//            table.addCell(hcell);

//            for (Penugasan penugasan : penugasanList) {
//
//                PdfPCell cell;
//
//                cell = new PdfPCell(new Phrase(penugasan.getId().toString()));
//                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//                table.addCell(cell);
//
//                cell = new PdfPCell(new Phrase(penugasan.getNOP()));
//                cell.setPaddingLeft(5);
//                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
//                table.addCell(cell);
//
//                cell = new PdfPCell(new Phrase(penugasan.getJenisPermohonan()));
//                cell.setPaddingLeft(5);
//                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
//                table.addCell(cell);
//
//                cell = new PdfPCell(new Phrase(String.valueOf(penugasan.getIsiSurat())));
//                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//                cell.setPaddingRight(5);
//                table.addCell(cell);
//            }
            Paragraph p = new Paragraph();
            for (Penugasan pen : penugasanList){
                p.add(new Paragraph("Surat Pengantar ", headFont));
                p.add(("Nomor Surat : 2018/"));
                p.add(new Paragraph(pen.getId().toString()));
                p.add(("Alamat OP : "));
                p.add(new Paragraph(pen.getPenduduk().getAlamat()));
                p.add(("NOP : "));
                p.add(new Paragraph(pen.getNOP()));
                p.add(("Jenis Permohonan : "));
                p.add(new Paragraph(pen.getJenisPermohonan()));
                p.add(new Paragraph(""));
                p.add(("             "));
                p.add(new Paragraph(pen.getIsiSurat()));
                p.setAlignment(Element.ALIGN_LEFT);
            }
            p.add(new Paragraph(""));
            p.add(new Paragraph(""));
            p.add(new Paragraph(""));
            p.add(new Paragraph(""));
            p.add(new Paragraph(""));
            p.add(new Paragraph(""));
            p.add(new Paragraph(""));
            p.add(new Paragraph(""));
            p.add(new Paragraph(""));
            p.add(new Paragraph(""));
            p.add(new Paragraph(""));
            p.add(new Paragraph(""));
            p.add(new Paragraph(""));
            p.add(new Paragraph("Ttd"));
            p.add(new Paragraph(""));
            p.add(new Paragraph(""));
            p.add(new Paragraph(""));
            p.add(new Paragraph("(                              )"));

            PdfWriter.getInstance(document, out);
            document.open();
            document.add(p);
//            document.add(table);

            document.close();

        } catch (DocumentException ex) {

            Logger.getLogger(GeneratePdfReport.class.getName()).log(Level.SEVERE, null, ex);
        }

        return new ByteArrayInputStream(out.toByteArray());
    }
}
