package com.doraweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

import com.doraweb.model.Penumpang;

public interface PenumpangRepository extends JpaRepository<Penumpang, String> {
	List<Penumpang> findByNik(String nik);
}
