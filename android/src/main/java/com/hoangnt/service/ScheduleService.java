package com.hoangnt.service;

import java.util.List;

import com.hoangnt.model.ScheduleDTO;

public interface ScheduleService {
	void addSchedule(ScheduleDTO scheduleDTO);

	void updateSchedule(ScheduleDTO scheduleDTO);

	void deleteSchedule(int id);

	List<ScheduleDTO> getScheduleByIdUser(int id);
}
