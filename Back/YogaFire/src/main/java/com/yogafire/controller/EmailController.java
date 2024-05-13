package com.yogafire.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/email")
public class EmailController {
	private final EmailService emailService;
	
	@GetMapping("/auth")
	public EmailAuthResponse sendAuthCode(@RequestParam String address) {
		return emailService.sendEmail(address);
	}
}
