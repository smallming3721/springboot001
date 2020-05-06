package com.hqyj.modules.test.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hqyj.modules.account.service.UserService;
import com.hqyj.modules.test.service.CityService;

@Controller
@RequestMapping("/test")
public class TestController {
	
	@Autowired
	private UserService userServiceImpl;
	@Autowired
	private CityService cityServiceImpl;
	
	@RequestMapping("/download")
	public ResponseEntity<Resource> downLoadFile(@RequestParam String fileName) {
		try {
			Resource resource = new UrlResource(Paths.get("E:\\" + fileName).toUri());
			return ResponseEntity.ok()
					.header(HttpHeaders.CONTENT_TYPE, "application/octet-stream")
					.header(HttpHeaders.CONTENT_DISPOSITION, 
							String.format("attachment; filename=\"%s\"", resource.getFilename()))
					.body(resource);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	

	
	@PostMapping("/uploads")
	public String uploadFiles(@RequestParam MultipartFile[] files,RedirectAttributes redirectAttributes){
		boolean isEmpty = true;
		try {
			for (MultipartFile file : files) {
				if(!file.isEmpty()){
					String fileName = file.getOriginalFilename();
					String destFilePath = "E:\\" + fileName;
//				File destFile = new File(destFilePath);
//				file.transferTo(destFile);
//				 使用工具类Files来上传文件
					byte[] bytes = file.getBytes();
					Path path = Paths.get(destFilePath);
					Files.write(path, bytes);
					isEmpty=false;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("message", "upload file failed.");
			return "redirect:/test/index";
		}
		if(isEmpty){
			redirectAttributes.addFlashAttribute("message", "Please select file.");
		}else{
			redirectAttributes.addFlashAttribute("message", "Upload file success.");
		}
		return "redirect:/test/index";
	}
	
	@PostMapping("/upload")
	public String uploadFile(@RequestParam MultipartFile file,RedirectAttributes redirectAttributes){
		if(file.isEmpty()){
			redirectAttributes.addFlashAttribute("message", "Please select file.");
			return "redirect:/test/index";
		}
		try {
			String fileName = file.getOriginalFilename();
			String destFilePath = "E:\\" + fileName;
//			File destFile = new File(destFilePath);
//			file.transferTo(destFile);
//			 使用工具类Files来上写·传文件
			byte[] bytes = file.getBytes();
			Files.write(Paths.get(destFilePath), bytes);

			redirectAttributes.addFlashAttribute("message", "Upload file success.");
		} catch (IOException e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("message", "Upload file failed.");
			return "redirect:/test/index";
		}
		return "redirect:/test/index";
	}
	
	
	/**
	 * http://127.0.0.1/test/index
	 */
	@RequestMapping("/index")
	public String index(ModelMap map){
		//map.addAttribute("template", "test/index");
		map.addAttribute("text1", "文本内容");
		map.addAttribute("thif", true);
		map.addAttribute("checkbox", "checkbox");
		map.addAttribute("bdUrl", "http://www.baidu.com");
		map.addAttribute("imgurl", "https://himg.bdimg.com/sys/portrait/hotitem/wildkid/2");
//		map.addAttribute("user",userServiceImpl );
		map.addAttribute("city",cityServiceImpl.selCitiesByCountryId(522).get(0) );
		map.addAttribute("updateCityUri","/api/city/test" );
		map.addAttribute("cities",cityServiceImpl.selCitiesByCountryId(522).
				stream().limit(10).collect(Collectors.toList()) );
		
		return "index";
	}
	
	/**
	 * 127.0.0.1/test/testFilter?key=fuck
	 * 测试过滤器过滤字符
	 */
	@RequestMapping("/testFilter")
	@ResponseBody
	public String testFilter(HttpServletRequest req,String key){
		String string = req.getParameter("key");
		return "testFilter---"+string+"---"+key;
	}

	@RequestMapping("/test")
	public String test(HttpServletRequest req){
		ArrayList<Object> list = new ArrayList<>();
		list.add("测试1");
		list.add("测试2");
		list.add("测试3");
		req.setAttribute("list", list );
		return "table.html";
	}
}
