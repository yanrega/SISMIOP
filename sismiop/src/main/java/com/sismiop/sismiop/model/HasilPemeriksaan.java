package com.sismiop.sismiop.model;

import javax.persistence.*;

@Entity
@Table(name = "hasil_pemeriksaan")
public class HasilPemeriksaan {

    @Id
    @Column(unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //    private String noSurat;
    private String NOP;
    private String jenisPermohonan;

    private String fileHasil;
    private boolean status = false;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "penduduk_id")
    private Penduduk penduduk;

    public HasilPemeriksaan() {
    }

    public String getFileHasil() {
        return fileHasil;
    }

    public void setFileHasil(String fileHasil) {
        this.fileHasil = fileHasil;
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

    public String getJenisPermohonan() {
        return jenisPermohonan;
    }

    public void setJenisPermohonan(String jenisPermohonan) {
        this.jenisPermohonan = jenisPermohonan;
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
