package com.example.boot.web;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.boot.account.model.Account;
import com.example.boot.account.model.AccountXml;

@RestController
@RequestMapping("/members")
public class AccountController {

	@RequestMapping(value = "/member/{memberId}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public Account getAccount(@PathVariable Long memberId) {
		Account account = new Account();
		return account;
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ResponseEntity<Void> create(UriComponentsBuilder ucBuilder) {
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/account", method = RequestMethod.GET)
	@ResponseBody
	public AccountXml getAccountXml() {
		System.out.println("xml");
		AccountXml account = new AccountXml();
		account.setName("aaaa");
		account.setText("ok");

		return account;
	}
}
