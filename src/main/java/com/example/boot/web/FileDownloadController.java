package com.example.boot.web;

import java.io.File;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

/**
 * @author gimbyeongsu
 * 
 */
@Controller
@RequestMapping("/filedownload")
public class FileDownloadController {
	private static final Logger LOGGER = LoggerFactory.getLogger(FileDownloadController.class);

	private String fileUploadPath;

	@Autowired
	private Environment environment;

	@Autowired
	private View downloadView;

	@PostConstruct
	public void init() {
		fileUploadPath = environment.getRequiredProperty("file.upload.path");
	}

	@RequestMapping(value = "/sample/{file}/ok", method = RequestMethod.GET)
	public ModelAndView sample(@PathVariable("file") String file) {
		LOGGER.debug("");
		// ModelAndView mav = new ModelAndView("downloadView");
		ModelAndView mav = new ModelAndView(downloadView);
		File downloadFile = new File(fileUploadPath + "/" + file);
		mav.addObject("downloadFile", downloadFile);
		return mav;
	}
}
