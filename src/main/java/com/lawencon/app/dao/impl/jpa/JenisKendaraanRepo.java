package com.lawencon.app.dao.impl.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lawencon.app.model.JenisKendaraan;

@Repository
public interface JenisKendaraanRepo extends JpaRepository<JenisKendaraan, Integer> {

}
