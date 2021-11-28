package com.doraweb.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "jeniskelamin")

public class JenisKelamin {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idjk;
	private String jkname;

}
