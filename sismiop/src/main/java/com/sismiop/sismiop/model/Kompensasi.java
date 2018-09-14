package com.sismiop.sismiop.model;

import javax.persistence.*;

@Entity
@Table(name = "kompensasi")
public class Kompensasi {
    @Id
    @Column(unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nama;
    private String npwp;
    private String NOP;
    private String jenisPermohonan;
    private String alamat;
    private String besarKelebihan;
    private String alasan;
    private String fcKTP;
    private String fcSPPT;
    private String fcPerubahanKetetapan;
    private String fcSTTSAsli;
    private String fcPernyataanOP;

    private boolean status = false;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "penduduk_id")
    private Penduduk penduduk;

    @OneToOne(mappedBy = "permohonanOp")
    private Spop spop = null;

    public Kompensasi() {
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNpwp() {
        return npwp;
    }

    public void setNpwp(String npwp) {
        this.npwp = npwp;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getBesarKelebihan() {
        return besarKelebihan;
    }

    public void setBesarKelebihan(String besarKelebihan) {
        this.besarKelebihan = besarKelebihan;
    }

    public String getAlasan() {
        return alasan;
    }

    public void setAlasan(String alasan) {
        this.alasan = alasan;
    }

    public String getFcKTP() {
        return fcKTP;
    }

    public void setFcKTP(String fcKTP) {
        this.fcKTP = fcKTP;
    }

    public String getFcSPPT() {
        return fcSPPT;
    }

    public void setFcSPPT(String fcSPPT) {
        this.fcSPPT = fcSPPT;
    }

    public String getFcPerubahanKetetapan() {
        return fcPerubahanKetetapan;
    }

    public void setFcPerubahanKetetapan(String fcPerubahanKetetapan) {
        this.fcPerubahanKetetapan = fcPerubahanKetetapan;
    }

    public String getFcSTTSAsli() {
        return fcSTTSAsli;
    }

    public void setFcSTTSAsli(String fcSTTSAsli) {
        this.fcSTTSAsli = fcSTTSAsli;
    }

    public String getFcPernyataanOP() {
        return fcPernyataanOP;
    }

    public void setFcPernyataanOP(String fcPernyataanOP) {
        this.fcPernyataanOP = fcPernyataanOP;
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

    public Spop getSpop() {
        return spop;
    }

    public void setSpop(Spop spop) {
        this.spop = spop;
    }
}
