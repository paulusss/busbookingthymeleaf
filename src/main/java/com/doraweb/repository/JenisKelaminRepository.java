package com.doraweb.repository;

import com.doraweb.model.JenisKelamin;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JenisKelaminRepository extends JpaRepository<JenisKelamin, Long> {
    JenisKelamin findJenisKelaminByIdjk(long idjk);
}
