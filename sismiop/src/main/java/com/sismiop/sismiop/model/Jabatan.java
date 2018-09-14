package com.sismiop.sismiop.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "jabatans")
public class Jabatan {
    public Jabatan() {
    }



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(unique = true)
    private String role;
    private String keterangan;


    @ManyToMany(mappedBy = "jabatans")
    private Set<Penduduk> penduduks = new HashSet<>();

    public Jabatan(@NotNull String role, String keterangan) {
        this.role = role;
        this.keterangan = keterangan;
    }
    public Long getId() {
        return id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public Set<Penduduk> getPenduduks() {
        return penduduks;
    }

    public void setPenduduks(Set<Penduduk> penduduks) {
        this.penduduks = penduduks;
    }

    @Override
    public String toString() {
        return this.role;
    }
}
