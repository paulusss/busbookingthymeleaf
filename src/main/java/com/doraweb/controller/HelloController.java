package com.doraweb.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.doraweb.model.JenisKelamin;
import com.doraweb.model.Orang;
import com.doraweb.model.Peserta;
import com.doraweb.model.PesertaSementara;
import com.doraweb.repository.JenisKelaminRepository;
import com.doraweb.repository.PesertaRepository;

import org.springframework.ui.Model;

@Controller
public class HelloController {

	@Autowired
	PesertaRepository pesertaRepo;

	@Autowired
	JenisKelaminRepository kelaminRepo;

	@GetMapping("/")
	public String getIndex() {
		return "dora";
	}

	@GetMapping("/test")
	public String getIndexTest(Model model) {
		List<Orang> anu = new ArrayList<Orang>();
		anu.add(new Orang("Tessy Wahyuni Riwayati Hartati", "18", "Jalan Rusak no 68"));
		anu.add(new Orang("Mahfud.md", "18", "Madura"));
		// List<String> anu = new ArrayList<String>();
		// anu.add("Tessy Wahyuni Riwayati Hartati");
		// anu.add("Mahfud.Md");
		anu.add(new Orang("Seto Mulyadi", "1", "Jalan Rusak"));
		anu.add(new Orang("Dedi Gumelar", "18", "Jalan Longsor"));
		// anu.add("Rieke Diah Pitaloka");
		// anu.add("Bolot");
		model.addAttribute("data", anu);
		return "index";
	}

	@GetMapping("/peserta")
	public String getListPeserta(Model model) {
		List<Peserta> anu = pesertaRepo.findAll();
		model.addAttribute("data", anu);
		return "indexPeserta";
	}

	@GetMapping("/indexLama")
	public String getIndexLama(Model model) {
		String anu = "Tessy WRH";
		model.addAttribute("data", anu);
		return "indexLama";
	}

	@GetMapping("/form")
	public String getForm() {
		return "InputForm";
	}

	@GetMapping("/inputpeserta")
	public String getFormPeserta(Model model) {
		List<Peserta> anu = pesertaRepo.findAll();
		model.addAttribute("data", anu);

		model.addAttribute("formData", new Peserta());
		return "formpeserta";
	}

	@PostMapping("/createpeserta")
	public String createPeserta(@ModelAttribute("formData") Peserta formData) {
		// if (formData.getIdjk().getIdjk() == 1) {
		// formData.getIdjk().setJkname("male");
		// } else if (formData.getIdjk().getIdjk() == 2) {
		// formData.getIdjk().setJkname("female");
		// } else {
		// formData.getIdjk().setJkname("others");
		// }
		long idjkbeneran = formData.getIdjk().getIdjk();
		JenisKelamin kelaminBeneran = kelaminRepo.findJenisKelaminByIdjk(idjkbeneran);
		formData.setIdjk(kelaminBeneran);
		pesertaRepo.save(formData);
		return "redirect:/inputpeserta";
	}

	@GetMapping("/catalog")
	public String catalogPage(Model model) {
		List<Peserta> anu = pesertaRepo.findAll();
		model.addAttribute("data", anu);
		return "catalog_page";
	}

	// @GetMapping("/inputpeserta")
	// public String getFormPeserta(Model model) {
	// model.addAttribute("formData", new PesertaSementara());
	// return "formpeserta";
	// }

	// @PostMapping("/createpeserta")
	// public String createPeserta(@ModelAttribute("formData") PesertaSementara
	// formData) {
	// Peserta pesertaBeneran = new Peserta(formData);
	// pesertaRepo.save(pesertaBeneran);
	// return "redirect:/peserta";
	// }
}
