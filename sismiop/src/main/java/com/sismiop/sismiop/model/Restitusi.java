package com.sismiop.sismiop.model;

import javax.persistence.*;

@Entity
@Table(name = "restitusi")
public class Restitusi {
    @Id
    @Column(unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nama;
    private String NOP;
    private String jenisPermohonan;
    private String alamat;
    private String besarPengembalian;
    private String alasan;
    private String fcBuktiPembayaranNTPN;
    private String fcBuktiPemotongan;

    private boolean status = false;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "penduduk_id")
    private Penduduk penduduk;

    @OneToOne(mappedBy = "permohonanOp")
    private Spop spop = null;

    public Restitusi() {
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

    public String getBesarPengembalian() {
        return besarPengembalian;
    }

    public void setBesarPengembalian(String besarPengembalian) {
        this.besarPengembalian = besarPengembalian;
    }

    public String getAlasan() {
        return alasan;
    }

    public void setAlasan(String alasan) {
        this.alasan = alasan;
    }

    public String getFcBuktiPembayaranNTPN() {
        return fcBuktiPembayaranNTPN;
    }

    public void setFcBuktiPembayaranNTPN(String fcBuktiPembayaranNTPN) {
        this.fcBuktiPembayaranNTPN = fcBuktiPembayaranNTPN;
    }

    public String getFcBuktiPemotongan() {
        return fcBuktiPemotongan;
    }

    public void setFcBuktiPemotongan(String fcBuktiPemotongan) {
        this.fcBuktiPemotongan = fcBuktiPemotongan;
    }
}
