package com.doraweb.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Orang {
    String nama;
    String batch;
    String alamat;

    // public Orang(String nama, String batch, String alamat) {
    // this.nama = nama;
    // this.alamat = alamat;
    // this.batch = batch;
    // }
}
