package com.sismiop.sismiop.model;

import javax.persistence.*;

@Entity
@Table(name = "permohonan_op")
public class PermohonanOp {
    public PermohonanOp() {
    }

    @Id
    @Column(unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String bertindakSebagai;
    private String luasBangunan;
    private String luasTanah;
    private String letakObjek;
    private String kecamatan;
    private String kelurahan;
    private boolean status = false;
    private boolean notif = false;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "penduduk_id")
    private Penduduk penduduk;

    @OneToOne(mappedBy = "permohonanOp")
    private Spop spop = null;




    public Long getId() {
        return id;
    }

    public String getBertindakSebagai() {
        return bertindakSebagai;
    }

    public void setBertindakSebagai(String bertindakSebagai) {
        this.bertindakSebagai = bertindakSebagai;
    }

    public String getLuasBangunan() {
        return luasBangunan;
    }

    public void setLuasBangunan(String luasBangunan) {
        this.luasBangunan = luasBangunan;
    }

    public String getLuasTanah() {
        return luasTanah;
    }

    public void setLuasTanah(String luasTanah) {
        this.luasTanah = luasTanah;
    }

    public String getLetakObjek() {
        return letakObjek;
    }

    public void setLetakObjek(String letakObjek) {
        this.letakObjek = letakObjek;
    }

    public String getKecamatan() {
        return kecamatan;
    }

    public void setKecamatan(String kecamatan) {
        this.kecamatan = kecamatan;
    }

    public String getKelurahan() {
        return kelurahan;
    }

    public void setKelurahan(String kelurahan) {
        this.kelurahan = kelurahan;
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

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isNotif() {
        return notif;
    }

    public void setNotif(boolean notif) {
        this.notif = notif;
    }

    public Spop getSpop() {
        return spop;
    }

    public void setSpop(Spop spop) {
        this.spop = spop;
    }
}
