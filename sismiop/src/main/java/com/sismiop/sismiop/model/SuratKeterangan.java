package com.sismiop.sismiop.model;

import javax.persistence.*;

@Entity
@Table(name = "surat_keterangan")
public class SuratKeterangan {
    @Id
    @Column(unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //    private String noSurat;
    private String NOP;
    private String namaPenduduk;

    private String alamatPenduduk;
    private String jenisPermohonan;
    private String isiSurat;
    private boolean status = false;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "penduduk_id")
    private Penduduk penduduk;

    public SuratKeterangan() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNOP() {
        return NOP;
    }

    public void setNOP(String NOP) {
        this.NOP = NOP;
    }

    public String getNamaPenduduk() {
        return namaPenduduk;
    }

    public void setNamaPenduduk(String namaPenduduk) {
        this.namaPenduduk = namaPenduduk;
    }

    public String getAlamatPenduduk() {
        return alamatPenduduk;
    }

    public void setAlamatPenduduk(String alamatPenduduk) {
        this.alamatPenduduk = alamatPenduduk;
    }

    public String getJenisPermohonan() {
        return jenisPermohonan;
    }

    public void setJenisPermohonan(String jenisPermohonan) {
        this.jenisPermohonan = jenisPermohonan;
    }

    public String getIsiSurat() {
        return isiSurat;
    }

    public void setIsiSurat(String isiSurat) {
        this.isiSurat = isiSurat;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Penduduk getPenduduk() {
        return penduduk;
    }

    public void setPenduduk(Penduduk penduduk) {
        this.penduduk = penduduk;
    }
}
