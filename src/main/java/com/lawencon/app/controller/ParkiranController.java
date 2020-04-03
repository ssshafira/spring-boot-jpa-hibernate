package com.lawencon.app.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.app.model.CheckIn;
import com.lawencon.app.model.CheckOut;
import com.lawencon.app.model.JenisKendaraan;
import com.lawencon.app.service.CheckInService;
import com.lawencon.app.service.CheckOutService;
import com.lawencon.app.service.JenisKendaraanService;

@RestController
public class ParkiranController extends BaseController {
	
	/*
	 * Nama : Serenada Salma Shafira
	 */

	@Autowired
	private JenisKendaraanService jkService;

	@Autowired
	private CheckInService ciService;

	@Autowired
	private CheckOutService coService;

	@GetMapping("/jenis")
	public ResponseEntity<List<JenisKendaraan>> getAllJenis(@RequestHeader("Authorization") String uname) {
		List<JenisKendaraan> listData = new ArrayList<JenisKendaraan>();
		try {
			String[] auth = super.authUser(uname);
			listData = jkService.findAll(auth[0],auth[1]);
			return new ResponseEntity<>(listData, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(listData, HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/jenis/persist")
	public ResponseEntity<List<JenisKendaraan>> persistJenis(@RequestBody JenisKendaraan jk, @RequestHeader("Authorization") String uname) {
		try {
			String[] auth = super.authUser(uname);
			jkService.insert(jk,auth[0],auth[1]);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	/*** CRUD CHECK IN ***/

	@GetMapping("/checkin")
	public ResponseEntity<List<CheckIn>> getAllCheckIn(@RequestHeader("Authorization") String uname) {
		List<CheckIn> listData = new ArrayList<CheckIn>();
		try {
			String[] auth = super.authUser(uname);
			listData = ciService.findAll(auth[0],auth[1]);
		} catch (Exception e) {
			return new ResponseEntity<>(listData, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(listData, HttpStatus.OK);
	}

	@PostMapping("/checkin/persist")
	public ResponseEntity<?> persistCheckIn(@RequestBody CheckIn ci, @RequestHeader("Authorization") String uname) {
		try {
			String[] auth = super.authUser(uname);
			if (ciService.insert(ci,auth[0],auth[1])) {
				ciService.insert(ci,auth[0],auth[1]);
				return new ResponseEntity<>("berhasil cek in", HttpStatus.OK);
			} else {
				return new ResponseEntity<>(
						"plat salah atau sudah cek in di hari yang sama\npisahkan plat dgn spasi (contoh: B 123 ER)",
						HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("/checkin/merge")
	public ResponseEntity<List<CheckIn>> mergeCheckIn(@RequestBody CheckIn ci, @RequestHeader("Authorization") String uname) {
		try {
			String[] auth = super.authUser(uname);
			ciService.update(ci,auth[0],auth[1]);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@DeleteMapping("/checkin/delete")
	public ResponseEntity<List<CheckIn>> deleteCheckIn(@RequestBody CheckIn ci, @RequestHeader("Authorization") String uname) {
		try {
			String[] auth = super.authUser(uname);
			ciService.delete(ci,auth[0],auth[1]);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	/*** CRUD CHECK OUT ***/

	@GetMapping("/checkout")
	public ResponseEntity<List<CheckOut>> getAllCheckOut(@RequestHeader("Authorization") String uname) {
		List<CheckOut> listData = new ArrayList<CheckOut>();
		try {
			String[] auth = super.authUser(uname);
			listData = coService.findAll(auth[0],auth[1]);
		} catch (Exception e) {
			return new ResponseEntity<>(listData, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(listData, HttpStatus.OK);
	}

	@PostMapping("/checkout/persist")
	public ResponseEntity<?> persistCheckOut(@RequestBody CheckOut co, @RequestHeader("Authorization") String uname) {
		try {
			String[] auth = super.authUser(uname);
			if (coService.insert(co,auth[0],auth[1])) {
				coService.insert(co,auth[0],auth[1]);
				return new ResponseEntity<>("berhasil cek out", HttpStatus.OK);
			} else {
				return new ResponseEntity<>("tanggal cek out harus setelah cek in", HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("/checkout/merge")
	public ResponseEntity<List<CheckIn>> mergeCheckOut(@RequestBody CheckOut co, @RequestHeader("Authorization") String uname) {
		try {
			String[] auth = super.authUser(uname);
			coService.update(co,auth[0],auth[1]);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@DeleteMapping("/checkout/delete")
	public ResponseEntity<List<CheckOut>> deleteCheckOut(@RequestBody CheckOut co, @RequestHeader("Authorization") String uname) {
		try {
			String[] auth = super.authUser(uname);
			coService.delete(co,auth[0],auth[1]);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}	

	public static boolean isParsable(String input) {
		try {
			Integer.parseInt(input);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
