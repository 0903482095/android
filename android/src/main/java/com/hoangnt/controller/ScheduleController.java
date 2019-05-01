package com.hoangnt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hoangnt.model.ScheduleDTO;
import com.hoangnt.service.ScheduleService;

@RestController
public class ScheduleController {
	@Autowired
	ScheduleService scheduleService;

	@PostMapping("/addSchedule")
	public ResponseEntity<?> addSchedule(@RequestBody ScheduleDTO scheduleDTO) {
		scheduleService.addSchedule(scheduleDTO);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

	@PutMapping("/updateSchedule")
	public ResponseEntity<?> updateSchedule(@RequestBody ScheduleDTO scheduleDTO) {
		scheduleService.updateSchedule(scheduleDTO);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteSchedule(@PathVariable int id) {
		scheduleService.deleteSchedule(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

	@GetMapping("/getSchedule/{id}")
	public ResponseEntity<List<ScheduleDTO>> getScheduleByIdUser(@PathVariable int id) {
		List<ScheduleDTO> scheduleDTOs = scheduleService.getScheduleByIdUser(id);
		return new ResponseEntity<List<ScheduleDTO>>(scheduleDTOs, HttpStatus.OK);
	}
}
