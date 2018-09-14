package com.sismiop.sismiop;

import com.sismiop.sismiop.model.Jabatan;
import com.sismiop.sismiop.model.Penduduk;
import com.sismiop.sismiop.repository.JabatanRepository;
import com.sismiop.sismiop.repository.PendudukRepository;
import com.sismiop.sismiop.service.JabatanService;
import com.sismiop.sismiop.service.ModelService;
import com.sismiop.sismiop.service.PendudukService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.AfterDomainEventPublication;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SismiopApplicationTests {
    @Autowired
    private JabatanService jabatanService;

    @Autowired
    private PendudukService pendudukService;


    @Test
    public void addJabatan() {
        Jabatan j = new Jabatan("ADMNISTRATOR", "admin sismiop");
        jabatanService.add(j);
        j = new Jabatan("PENDUDUK", "PENDUDUK SISMIOP");
        jabatanService.add(j);
        j = new Jabatan("PELAYANAN", "PELAYANAN SISMIOP");
        jabatanService.add(j);
        j = new Jabatan("PENETAPAN", "PENETAPAN SISMIOP");
        jabatanService.add(j);
        j = new Jabatan("PENILAIAN", "PENILAIAN SISMIOP");
        jabatanService.add(j);
    }

    @Test
    public void addPenduduk() {
        Jabatan j = jabatanService.getById(new Long(2));
        Penduduk p = new Penduduk();
        p.setNama("Benyamin Panjaitan");
        p.setAgama("Kristen Protestan");
        p.setAlamat("Pematangsiantar");
        p.setGolDarah("0");
        p.setJk("Laki-laki");
        p.setNoTelp("081231234512");
        p.setNik("1217052101960001");
        p.setNpwp("1217052101960001");
        p.setPekerjaan("Mahasiswa");
        p.setStatusPerkawinan("Belum Kawin");
        p.setPassword("08123");
        p.getJabatans().add(j);
        try {
            p.setTglLahir(new SimpleDateFormat("dd/MM/yyyy").parse("21/01/1996"));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        if(pendudukService.add(p) && jabatanService.addAll(p.getJabatans()))
            System.out.println("Penduduk was added");
        else
            System.out.println("Error adding Penduduk");

    }



    @Test
    public void addPenduduks() {
        Jabatan j = jabatanService.getById(new Long(1));
        Penduduk p = new Penduduk();
        p.setNama("Hottua Gultom");
        p.setAgama("Kristen Protestan");
        p.setAlamat("Laguboti");
        p.setGolDarah("B");
        p.setJk("Laki-laki");
        p.setNoTelp("082364609502");
        p.setPassword("123456");
        p.setNik("1217052609950002");
        p.setPekerjaan("Mahasiswa");
        p.setStatusPerkawinan("Belum Kawin");
        try {
            p.setTglLahir(new SimpleDateFormat("dd/MM/yyyy").parse("26/09/1995"));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        p.getJabatans().add(j);

        if(pendudukService.add(p) && jabatanService.addAll(p.getJabatans()))
            System.out.println("Penduduk was added");
        else
            System.out.println("Error while adding Penduduk");
    }

    @Test
    public void addpenduduk() {
        Jabatan j = jabatanService.getById(new Long(3));
        Penduduk p = new Penduduk();
        p.setNama("Erwin Sihotang");
        p.setAgama("Kristen Protestan");
        p.setAlamat("Laguboti");
        p.setGolDarah("B");
        p.setJk("Laki-laki");
        p.setNoTelp("082364609502");
        p.setPassword("123456");
        p.setNik("1217052609950002");
        p.setNpwp("1217052101960002");
        p.setPekerjaan("Mahasiswa");
        p.setStatusPerkawinan("Belum Kawin");
        try {
            p.setTglLahir(new SimpleDateFormat("dd/MM/yyyy").parse("26/09/1995"));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        p.getJabatans().add(j);

        if(pendudukService.add(p) && jabatanService.addAll(p.getJabatans()))
            System.out.println("Penduduk was added");
        else
            System.out.println("Error while adding Penduduk");
    }

    @Test
    public void addpenduduks() {
        Jabatan j = jabatanService.getById(new Long(4));
        Penduduk p = new Penduduk();
        p.setNama("Yan Rega Panjaitan");
        p.setAgama("Kristen Protestan");
        p.setAlamat("Silaen");
        p.setGolDarah("O");
        p.setJk("Laki-laki");
        p.setNoTelp("081122334455");
        p.setPassword("11111");
        p.setNik("1217052609950002");
        p.setNpwp("1217052101960002");
        p.setPekerjaan("Mahasiswa");
        p.setStatusPerkawinan("Belum Kawin");
        try {
            p.setTglLahir(new SimpleDateFormat("dd/MM/yyyy").parse("26/09/1995"));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        p.getJabatans().add(j);

        if(pendudukService.add(p) && jabatanService.addAll(p.getJabatans()))
            System.out.println("Penduduk was added");
        else
            System.out.println("Error while adding Penduduk");
    }


    @Test
    public void testToString(){
        Penduduk p = pendudukService.getPendudukById(new Long(6));
        System.out.println(p.getJabatans().toString().getClass());
        System.out.println(p.getJabatans().toString());
    }

}
