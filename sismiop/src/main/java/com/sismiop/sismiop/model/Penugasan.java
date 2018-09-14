package com.sismiop.sismiop.model;

import javax.persistence.*;

@Entity
@Table(name = "penugasan")
public class Penugasan {
    @Id
    @Column(unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    private String noSurat;
    private String NOP;
    private String jenisPermohonan;
    private String isiSurat;

    private boolean status = false;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "penduduk_id")
    private Penduduk penduduk;

    public Penugasan() {
    }

    public Penugasan(String NOP, String isiSurat) {
//        this.noSurat = noSurat;
        this.NOP = NOP;
        this.isiSurat = isiSurat;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
//
//    public String getNoSurat() {
//        return noSurat;
//    }
//
//    public void setNoSurat(String noSurat) {
//        this.noSurat = noSurat;
//    }

    public String getNOP() {
        return NOP;
    }

    public void setNOP(String NOP) {
        this.NOP = NOP;
    }

    public String getIsiSurat() {
        return isiSurat;
    }

    public void setIsiSurat(String isiSurat) {
        this.isiSurat = isiSurat;
    }
}
