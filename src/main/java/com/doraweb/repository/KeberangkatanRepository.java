package com.doraweb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.doraweb.model.Keberangkatan;
import com.doraweb.model.KeberangkatanDetail;

public interface KeberangkatanRepository extends JpaRepository<Keberangkatan, Long> {
	List<Keberangkatan> findById(long id);

	@Query(value = "SELECT keberangkatan.id, jurusan.deskripsi, keberangkatan.tanggal as waktu, "
			+ "bus.nama_perusahaan as perusahaan, keberangkatan.kelas, keberangkatan.harga" + " FROM keberangkatan"
			+ " INNER JOIN jurusan ON keberangkatan.id_jurusan = jurusan.id "
			+ " INNER JOIN bus ON keberangkatan.no_polisi = bus.nomor_polisi"
			+ " WHERE jurusan.terminal_awal = ?1 AND keberangkatan.tanggal LIKE ?2% "
			+ " AND bus.kapasitas > (SELECT COUNT(*) FROM booking WHERE booking.id_keberangkatan = keberangkatan.id)", nativeQuery = true)
	List<KeberangkatanDetail> getDetail(String terminal_awal, String tanggal);

	@Query(value = "SELECT keberangkatan.id, jurusan.deskripsi, keberangkatan.tanggal as waktu, "
			+ "bus.nama_perusahaan as perusahaan, keberangkatan.kelas, keberangkatan.harga" + " FROM keberangkatan"
			+ " INNER JOIN jurusan ON keberangkatan.id_jurusan = jurusan.id "
			+ " INNER JOIN bus ON keberangkatan.no_polisi = bus.nomor_polisi" + " WHERE keberangkatan.tanggal LIKE ?1% "
			+ " AND bus.kapasitas > (SELECT COUNT(*) FROM booking WHERE booking.id_keberangkatan = keberangkatan.id)", nativeQuery = true)
	// static
	List<KeberangkatanDetail> getDetail(String tanggal);

}
