package com.hoangnt.service.impl;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.transaction.Transactional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hoangnt.entity.User;
import com.hoangnt.model.ScheduleDTO;
import com.hoangnt.model.UserDTO;
import com.hoangnt.repository.UserRepository;
import com.hoangnt.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	@Autowired
	UserRepository userRepository;

	@Override
	public UserDTO getUser() {

		return null;
	}

	@Override
	public UserDTO getUserByEmail(String email) {
		User user = userRepository.findByEmail(email);
		if (user != null) {

			UserDTO userDTO = new UserDTO();
			userDTO.setId(user.getId());
			userDTO.setEmail(user.getEmail());
			userDTO.setAddress(user.getAddress());
			userDTO.setPassword(user.getPassword());
			userDTO.setDate_of_birth(user.getDate_of_birth());
			userDTO.setName(user.getName());

			List<ScheduleDTO> scheduleDTOs = new ArrayList<ScheduleDTO>();
			user.getSchedules().forEach(schedule -> {
				ScheduleDTO scheduleDTO = new ScheduleDTO();

				scheduleDTO.setId(schedule.getId());
				scheduleDTO.setAddress(schedule.getAddress());
				scheduleDTO.setDate(schedule.getDate());
				scheduleDTO.setSuggest(schedule.getSuggest());
				scheduleDTOs.add(scheduleDTO);
			});
			userDTO.setScheduleDTOs(scheduleDTOs);
			return userDTO;
		}
		return null;
	}

	@Override
	public User addUser(UserDTO userDTO) {
		User user = new User();
		user.setEmail(userDTO.getEmail());
		user.setName(userDTO.getName());
		user.setAddress(userDTO.getAddress());
		user.setDate_of_birth(userDTO.getDate_of_birth());
		user.setPassword(BCrypt.hashpw(chuanHoaDate(userDTO.getDate_of_birth()), BCrypt.gensalt(12)));

		userRepository.save(user);
		return user;
	}

	public static String removeAccent(String s) {
		String s2;
		String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
		Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
		String s1[] = pattern.matcher(temp).replaceAll("").trim().toLowerCase().split("\\s");
		s2 = s1[s1.length - 1];
		for (int i = 0; i < s1.length - 1; i++) {
			s2 += s1[i].substring(0, 1);
		}
		return s2;
	}

	public static String chuanHoaDate(String s) {
		String s2;
		String s1[] = s.split("-");
		s2 = s1[2] + s1[1] + s1[0].substring(2, 4);
		return s2;
	}

}
