package com.sismiop.sismiop.model;

import javax.persistence.*;

@Entity
@Table(name = "permohonan")
public class Permohonan {
    @Id
    @Column(unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tahun;
    private String namaWP;
    private String NPWP;
    private String alamatWP;
    private String noTelpWP;
    private String besarPengurangan;
    private String NOP;
    private String jenisPermohonan;
    private String alamatOP;
    private String keterangan;
    private String fcSPPT;
    private String fcBuktiPelunasan;
    private String fcSuratKuasa;
    private String fcTandaVeteran;
    private String fcDaftarGaji;
    private String fcBesarPenghasilan;
    private String fcRekeningListrik;
    private String fcKeteranganBencana;
    private String fcSPTPPh;
    private String fcLaporanKeuanganPerusahaan;

    private boolean status = false;
    private boolean notif = false;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "penduduk_id")
    private Penduduk penduduk;


    public Permohonan() {
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

    public String getTahun() {
        return tahun;
    }

    public void setTahun(String tahun) {
        this.tahun = tahun;
    }

    public String getNamaWP() {
        return namaWP;
    }

    public void setNamaWP(String namaWP) {
        this.namaWP = namaWP;
    }

    public String getNPWP() {
        return NPWP;
    }

    public void setNPWP(String NPWP) {
        this.NPWP = NPWP;
    }

    public String getAlamatWP() {
        return alamatWP;
    }

    public void setAlamatWP(String alamatWP) {
        this.alamatWP = alamatWP;
    }

    public String getNoTelpWP() {
        return noTelpWP;
    }

    public void setNoTelpWP(String noTelpWP) {
        this.noTelpWP = noTelpWP;
    }

    public String getBesarPengurangan() {
        return besarPengurangan;
    }

    public void setBesarPengurangan(String besarPengurangan) {
        this.besarPengurangan = besarPengurangan;
    }

    public String getNOP() {
        return NOP;
    }

    public void setNOP(String NOP) {
        this.NOP = NOP;
    }

    public String getAlamatOP() {
        return alamatOP;
    }

    public void setAlamatOP(String alamatOP) {
        this.alamatOP = alamatOP;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getFcSPPT() {
        return fcSPPT;
    }

    public void setFcSPPT(String fcSPPT) {
        this.fcSPPT = fcSPPT;
    }

    public String getFcBuktiPelunasan() {
        return fcBuktiPelunasan;
    }

    public void setFcBuktiPelunasan(String fcBuktiPelunasan) {
        this.fcBuktiPelunasan = fcBuktiPelunasan;
    }

    public String getFcSuratKuasa() {
        return fcSuratKuasa;
    }

    public void setFcSuratKuasa(String fcSuratKuasa) {
        this.fcSuratKuasa = fcSuratKuasa;
    }

    public String getFcTandaVeteran() {
        return fcTandaVeteran;
    }

    public void setFcTandaVeteran(String fcTandaVeteran) {
        this.fcTandaVeteran = fcTandaVeteran;
    }

    public String getFcDaftarGaji() {
        return fcDaftarGaji;
    }

    public void setFcDaftarGaji(String fcDaftarGaji) {
        this.fcDaftarGaji = fcDaftarGaji;
    }

    public String getFcBesarPenghasilan() {
        return fcBesarPenghasilan;
    }

    public void setFcBesarPenghasilan(String fcBesarPenghasilan) {
        this.fcBesarPenghasilan = fcBesarPenghasilan;
    }

    public String getFcRekeningListrik() {
        return fcRekeningListrik;
    }

    public void setFcRekeningListrik(String fcRekeningListrik) {
        this.fcRekeningListrik = fcRekeningListrik;
    }

    public String getFcKeteranganBencana() {
        return fcKeteranganBencana;
    }

    public void setFcKeteranganBencana(String fcKeteranganBencana) {
        this.fcKeteranganBencana = fcKeteranganBencana;
    }

    public String getFcSPTPPh() {
        return fcSPTPPh;
    }

    public void setFcSPTPPh(String fcSPTPPh) {
        this.fcSPTPPh = fcSPTPPh;
    }

    public String getFcLaporanKeuanganPerusahaan() {
        return fcLaporanKeuanganPerusahaan;
    }

    public void setFcLaporanKeuanganPerusahaan(String fcLaporanKeuanganPerusahaan) {
        this.fcLaporanKeuanganPerusahaan = fcLaporanKeuanganPerusahaan;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean isNotif() {
        return notif;
    }

    public void setNotif(boolean notif) {
        this.notif = notif;
    }

    public Penduduk getPenduduk() {
        return penduduk;
    }

    public void setPenduduk(Penduduk penduduk) {
        this.penduduk = penduduk;
    }
}
