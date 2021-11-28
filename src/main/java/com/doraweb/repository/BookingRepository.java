package com.doraweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

import com.doraweb.model.Booking;
import com.doraweb.model.Penumpang;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByNik(Penumpang nik);
}
