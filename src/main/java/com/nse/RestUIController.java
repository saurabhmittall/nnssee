package com.nse;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import sm.nse.util.FileOperation;

@RestController
public class RestUIController {
	@Autowired
	Application app;

	@RequestMapping("/health")
	public String health(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "k", defaultValue = "") String key,
			@RequestParam(value = "s", defaultValue = "a") String sort) {
		String contentType = "text/html;charset=UTF-8";
		response.setContentType(contentType);
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		response.setCharacterEncoding("utf-8");

		return "fine";
	}

	@RequestMapping("/download")
	public String download(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "k", defaultValue = "") String key,
			@RequestParam(value = "day", defaultValue = "0") int day) {
		app.downloadFile( day);
		String contentType = "text/html;charset=UTF-8";
		response.setContentType(contentType);
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		response.setCharacterEncoding("utf-8");

		return "fine";
	}

	@RequestMapping("/downloadDateRange")
	public String download(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "day1", defaultValue = "0") int day1,
			@RequestParam(value = "day2", defaultValue = "0") int day2) {
	
		for (; day1 < day2; day1++)
			app.downloadFile( day1);
		String contentType = "text/html;charset=UTF-8";
		response.setContentType(contentType);
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
		
			e.printStackTrace();
		}

		response.setCharacterEncoding("utf-8");

		return "fine";
	}

	@RequestMapping("/unZip")
	public String dbinsert(HttpServletRequest request, HttpServletResponse response) {

		app.UnZip();
		return "done";
	}
}
