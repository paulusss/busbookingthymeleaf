package com.doraweb.controller;

import java.util.List;

import com.doraweb.model.Booking;
import com.doraweb.model.Keberangkatan;
import com.doraweb.model.KeberangkatanDetail;
import com.doraweb.model.Penumpang;
import com.doraweb.repository.BookingRepository;
import com.doraweb.repository.KeberangkatanRepository;
import com.doraweb.repository.PenumpangRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
// @RequestMapping("/busbookingsystem")

public class BusController {

    @Autowired
    PenumpangRepository penumpangRepo;

    @Autowired
    KeberangkatanRepository keberangkatanRepo;

    @Autowired
    BookingRepository bookingRepo;

    @GetMapping("/loginpenumpang")
    public String login(Model model) {
        model.addAttribute("formData", new Penumpang());
        return "formlogin";
    }

    @PostMapping("/loginresult")
    public String loginresult(@ModelAttribute("data") Penumpang formData, Model model2) {
        String alamatHasil = "/";
        String nik = formData.getNik();
        List<Penumpang> penumpangBeneran = penumpangRepo.findByNik(nik);
        if (penumpangBeneran.size() == 0) {
            alamatHasil = "kenihilan";
        } else {
            model2.addAttribute("data", penumpangBeneran.get(0));
            alamatHasil = "detailPenumpang";
        }
        return alamatHasil;
    }

    @GetMapping("/daftar")
    public String daftar(Model model) {
        model.addAttribute("formData", new Penumpang());
        return "formpenumpangbaru";
    }

    @PostMapping("/savepenumpang")
    public String savepenumpang(@ModelAttribute("formData") Penumpang formData, Model model) {
        penumpangRepo.save(formData);
        model.addAttribute("data", formData);
        return "/detailpenumpang";
    }

    @GetMapping("/carikeberangkatan")
    public String cariKeberangkatan(Model model) {
        model.addAttribute("formData", new Keberangkatan());
        return "formcarikeberangkatan";
    }

    @PostMapping("/carikeberangkatanresult")
    public String cariKeberangkatanResult(@ModelAttribute("data") Keberangkatan formData, Model model2) {
        String alamatHasil = "/";
        String tanggal = formData.getTanggal();
        String terminalAwal = formData.getId_jurusan().getTerminal_awal();
        List<KeberangkatanDetail> keberangkatanBeneran = keberangkatanRepo.getDetail(terminalAwal, tanggal);
        if (keberangkatanBeneran.size() == 0) {
            alamatHasil = "kenihilankeberangkatan";
        } else {
            model2.addAttribute("data", keberangkatanBeneran);
            alamatHasil = "listdetailkeberangkatan";
        }
        return alamatHasil;
    }

    @GetMapping("/booking")
    public String booking(Model model) {
        model.addAttribute("formData", new Booking());
        return "formbooking";
    }

    @PostMapping("/bookingresult")
    public String bookingResult(@ModelAttribute("data") Booking formData, Model model2) {
        String nik = formData.getNik().getNik();
        String nama = penumpangRepo.findByNik(nik).get(0).getNama();
        formData.getNik().setNama(nama);
        // long id_keberangkatan = formData.getId_keberangkatan().getId();
        // int harga = keberangkatanRepo.findById(id_keberangkatan).get(0).getHarga();
        bookingRepo.save(formData);
        Penumpang penumpangSementara = new Penumpang();
        penumpangSementara.setNik(nik);
        List<Booking> hasilSimpan = bookingRepo.findByNik(penumpangSementara);
        model2.addAttribute("data", hasilSimpan.get(hasilSimpan.size() - 1));
        return "bookingdetail";
    }

    @GetMapping("/cancel")
    public String cancel(Model model) {
        model.addAttribute("formData", new Booking());
        return "formcancel";
    }

    @PostMapping("/cancelresult")
    public String cancelnResult(@ModelAttribute("data") Booking formData) {
        bookingRepo.deleteById(formData.getId());
        return "cancelmessage";
    }

}
