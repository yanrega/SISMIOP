package com.sismiop.sismiop.model;

import javax.persistence.*;

@Entity
@Table(name = "jatuh_tempo")
public class JatuhTempo {
    @Id
    @Column(unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nama;
    private String alamat;
    private String tahun;
    private String NOP;
    private String jenisPermohonan;
    private String letakOP;
    private String SPPTditerima;
    private String fcKTP;
    private String fcSPPT;
    private String fcBuktiBayar;
    private String fcNPWPD;
    private String fcSuratKuasa;

    private boolean status = false;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "penduduk_id")
    private Penduduk penduduk;

    @OneToOne(mappedBy = "permohonanOp")
    private Spop spop = null;

    public JatuhTempo() {
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

    public Spop getSpop() {
        return spop;
    }

    public void setSpop(Spop spop) {
        this.spop = spop;
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

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getTahun() {
        return tahun;
    }

    public void setTahun(String tahun) {
        this.tahun = tahun;
    }

    public String getNOP() {
        return NOP;
    }

    public void setNOP(String NOP) {
        this.NOP = NOP;
    }

    public String getLetakOP() {
        return letakOP;
    }

    public void setLetakOP(String letakOP) {
        this.letakOP = letakOP;
    }

    public String getSPPTditerima() {
        return SPPTditerima;
    }

    public void setSPPTditerima(String SPPTditerima) {
        this.SPPTditerima = SPPTditerima;
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

    public String getFcBuktiBayar() {
        return fcBuktiBayar;
    }

    public void setFcBuktiBayar(String fcBuktiBayar) {
        this.fcBuktiBayar = fcBuktiBayar;
    }

    public String getFcNPWPD() {
        return fcNPWPD;
    }

    public void setFcNPWPD(String fcNPWPD) {
        this.fcNPWPD = fcNPWPD;
    }

    public String getFcSuratKuasa() {
        return fcSuratKuasa;
    }

    public void setFcSuratKuasa(String fcSuratKuasa) {
        this.fcSuratKuasa = fcSuratKuasa;
    }
}
