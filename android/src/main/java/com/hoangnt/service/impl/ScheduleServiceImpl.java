package com.hoangnt.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hoangnt.entity.Schedule;
import com.hoangnt.entity.User;
import com.hoangnt.model.ScheduleDTO;
import com.hoangnt.repository.ScheduleRepository;
import com.hoangnt.service.ScheduleService;

@Service
@Transactional
public class ScheduleServiceImpl implements ScheduleService {

	@Autowired
	ScheduleRepository scheduleRepository;

	@Override
	public void addSchedule(ScheduleDTO scheduleDTO) {
		Schedule schedule = new Schedule();
		schedule.setAddress(scheduleDTO.getAddress());
		schedule.setDate(scheduleDTO.getDate());
		schedule.setSuggest(scheduleDTO.getSuggest());
		schedule.setUser(new User(scheduleDTO.getId_user()));
		scheduleRepository.save(schedule);
	}

	@Override
	public void updateSchedule(ScheduleDTO scheduleDTO) {
		Schedule schedule = scheduleRepository.getOne(scheduleDTO.getId());

		schedule.setAddress(scheduleDTO.getAddress());
		schedule.setDate(scheduleDTO.getDate());
		schedule.setUser(new User(scheduleDTO.getId_user()));
		scheduleRepository.save(schedule);
	}

	@Override
	public void deleteSchedule(int id) {
		scheduleRepository.deleteById(id);
	}

	@Override
	public List<ScheduleDTO> getScheduleByIdUser(int id) {
		List<Schedule> schedules = scheduleRepository.findByIdUser(id);
		List<ScheduleDTO> scheduleDTOs=new ArrayList<>();
		schedules.forEach(schedule->{
			ScheduleDTO scheduleDTO = new ScheduleDTO();
			scheduleDTO.setId(schedule.getId());
			scheduleDTO.setAddress(schedule.getAddress());
			scheduleDTO.setDate(schedule.getDate());
			scheduleDTO.setSuggest(schedule.getSuggest());
			scheduleDTO.setId_user(schedule.getUser().getId());
			
			scheduleDTOs.add(scheduleDTO);
		});
		
		return scheduleDTOs;
	}

}
