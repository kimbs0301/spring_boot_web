package com.example.boot.web;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.boot.account.model.AjaxResponseBody;
import com.example.boot.account.model.SearchCriteria;
import com.example.boot.account.model.User;

@RestController
@RequestMapping("/ajaxs")
public class AjaxController {
	private List<User> users;
	
	@PostConstruct
	private void init() {
		users = new ArrayList<User>();
		User user1 = new User("kimbs", "123456", "kimbs0301@gamil.com", "123-1234-1234", "address 123");
		users.add(user1);
	}

	@RequestMapping(value = "/search/api/getSearchResult")
	public @ResponseBody AjaxResponseBody getSearchResultViaAjax(@RequestBody SearchCriteria search) {
		AjaxResponseBody result = new AjaxResponseBody();

		if (isValidSearchCriteria(search)) {
			List<User> users = findByUserNameOrEmail(search.getUsername(), search.getEmail());
			if (users.size() > 0) {
				result.setCode("200");
				result.setMsg("");
				result.setResult(users);
			} else {
				result.setCode("204");
				result.setMsg("No user!");
			}
		} else {
			result.setCode("400");
			result.setMsg("Search criteria is empty!");
		}
		return result;
	}

	private boolean isValidSearchCriteria(SearchCriteria search) {
		boolean valid = true;
		if (search == null) {
			valid = false;
		}
		if ((StringUtils.isEmpty(search.getUsername())) && (StringUtils.isEmpty(search.getEmail()))) {
			valid = false;
		}
		return valid;
	}

	private List<User> findByUserNameOrEmail(String username, String email) {
		List<User> result = new ArrayList<User>();

		for (User user : users) {
			if ((!StringUtils.isEmpty(username)) && (!StringUtils.isEmpty(email))) {
				if (username.equals(user.getUsername()) && email.equals(user.getEmail())) {
					result.add(user);
					continue;
				} else {
					continue;
				}
			}
			if (!StringUtils.isEmpty(username)) {
				if (username.equals(user.getUsername())) {
					result.add(user);
					continue;
				}
			}
			if (!StringUtils.isEmpty(email)) {
				if (email.equals(user.getEmail())) {
					result.add(user);
					continue;
				}
			}
		}
		return result;
	}
}