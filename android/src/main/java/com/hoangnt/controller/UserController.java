package com.hoangnt.controller;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hoangnt.entity.User;
import com.hoangnt.model.UserDTO;
import com.hoangnt.service.UserService;
import com.hoangnt.service.impl.UserServiceImpl;

@RestController
public class UserController {
	@Autowired
	JavaMailSender javaMailSender;

	@Autowired
	UserService userService;

	@GetMapping("/test")
	public ResponseEntity<?> test() {

		return new ResponseEntity<String>("Hello rest api", HttpStatus.OK);
	}

	@PostMapping("/login")
	public ResponseEntity<UserDTO> login(@RequestBody UserDTO userDTO) {
		UserDTO dto = userService.getUserByEmail(userDTO.getEmail());
		if (dto != null && BCrypt.checkpw(userDTO.getPassword(), dto.getPassword())) {
			return new ResponseEntity<UserDTO>(dto, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	@PostMapping("/register")
	public ResponseEntity<UserDTO> register(@RequestBody UserDTO userDTO) throws MessagingException {
		if (userService.getUserByEmail(userDTO.getEmail()) == null) {
			User user = userService.addUser(userDTO);

			MimeMessage message = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
			String html = "<div>Username : " + user.getEmail() + "</div></br><div>Password : "
					+ UserServiceImpl.chuanHoaDate(userDTO.getDate_of_birth())
					+ "</div></br><div>Please don't provide this information to anyone in any form !!!</div>";
			message.setContent(html, "text/html");
			helper.setTo(userDTO.getEmail());
			helper.setSubject("Login information for weather service");
			this.javaMailSender.send(message);

			return new ResponseEntity<UserDTO>(userDTO, HttpStatus.CREATED);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

}
