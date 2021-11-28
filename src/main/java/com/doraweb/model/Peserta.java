package com.doraweb.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "peserta")

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Peserta {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String nama;
	private String notelp;
	private String alamat;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idjk", referencedColumnName = "idjk")
	private JenisKelamin idjk;

	public Peserta(PesertaSementara bahan) {
		this.id = bahan.getId();
		this.nama = bahan.getNama();
		this.alamat = bahan.getAlamat();
		this.notelp = bahan.getNotelp();
		if (bahan.getIdjk().equals("1")) {
			this.idjk = new JenisKelamin(1, "male");
		} else if (bahan.getIdjk().equals("2")) {
			this.idjk = new JenisKelamin(2, "female");
		} else {
			this.idjk = new JenisKelamin(3, "others");
		}
	}

	public Peserta(long id, String nama, String notelp, String alamat, String idjk) {
		this.id = id;
		this.nama = nama;
		this.notelp = notelp;
		this.alamat = alamat;

		if (idjk.equals("1")) {
			this.idjk = new JenisKelamin(1, "male");
		} else if (idjk.equals("2")) {
			this.idjk = new JenisKelamin(2, "female");
		} else {
			this.idjk = new JenisKelamin(3, "others");
		}
	}
}
