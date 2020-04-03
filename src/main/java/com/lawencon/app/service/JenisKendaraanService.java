package com.lawencon.app.service;

import java.util.List;

import com.lawencon.app.model.JenisKendaraan;

public interface JenisKendaraanService {

	abstract List<JenisKendaraan> findAll(String au0, String au1) throws Exception;
	abstract void insert(JenisKendaraan jk, String au0, String au1) throws Exception;
}
