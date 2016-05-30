package com.example.boot.web;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * @author gimbyeongsu
 * 
 */
@Controller
@RequestMapping("/file")
public class FileUploadController {
	private static final Logger LOGGER = LoggerFactory.getLogger(FileUploadController.class);

	@Value("#{configProperties['file.upload.path']}")
	private String fileUploadPath;

	@RequestMapping(method = RequestMethod.GET, value = "/")
	public String provideUploadInfo(Model model) {
		
//		LOGGER.debug(testValue);
//		LOGGER.debug(testValue);
//		LOGGER.debug(testValue);
		
		File rootFolder = new File(fileUploadPath);
		List<String> fileNames = Arrays.stream(rootFolder.listFiles()).map(f -> f.getName())
				.collect(Collectors.toList());

		for (String fileName : fileNames) {
			LOGGER.debug("{}", fileName);
		}

		model.addAttribute(
				"files",
				Arrays.stream(rootFolder.listFiles()).sorted(Comparator.comparingLong(f -> -1 * f.lastModified()))
						.map(f -> f.getName()).collect(Collectors.toList()));

		return "uploadForm";
	}

	@RequestMapping(method = RequestMethod.POST, value = "/upload")
	public String handleFileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {

		LOGGER.debug("");

		String name = file.getOriginalFilename();

		if (name.contains("/")) {
			redirectAttributes.addFlashAttribute("message", "Folder separators not allowed");
			return "redirect:/";
		}
		if (name.contains("/")) {
			redirectAttributes.addFlashAttribute("message", "Relative pathnames not allowed");
			return "redirect:/";
		}

		if (!file.isEmpty()) {
			try {
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(fileUploadPath
						+ "/" + name)));
				FileCopyUtils.copy(file.getInputStream(), stream);
				stream.close();
				redirectAttributes.addFlashAttribute("message", "You successfully uploaded " + name + "!");
			} catch (Exception e) {
				redirectAttributes.addFlashAttribute("message",
						"You failed to upload " + name + " => " + e.getMessage());
			}
		} else {
			redirectAttributes.addFlashAttribute("message", "You failed to upload " + name
					+ " because the file was empty");
		}

		LOGGER.debug("");

		return "redirect:/";
	}

}