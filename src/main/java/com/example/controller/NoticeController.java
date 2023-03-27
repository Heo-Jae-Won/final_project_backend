package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.dto.NoticeDto;
import com.example.response.NoticeListResponse;
import com.example.service.NoticeService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@CrossOrigin(origins ="http://localhost:3000")
public class NoticeController {

	@Autowired
	private final NoticeService noticeService;

	@ResponseBody
	@RequestMapping("/api/notice")
	public NoticeListResponse list(int page, int num, String searchType, String keyword) throws Exception {
		NoticeListResponse noticeListResponse = new NoticeListResponse();
		noticeListResponse.setNoticeList(noticeService.getList(page, num, searchType, keyword));
		noticeListResponse.setNoticeListTotal(noticeService.getTotal(searchType, keyword));

		return noticeListResponse;
	};
	
	@ResponseBody
	@RequestMapping(value = "/api/notice", method = RequestMethod.POST)
	public void insert(@RequestBody NoticeDto vo) {
		noticeService.insert(vo);
	}
	
	@ResponseBody
	@RequestMapping(value = "/api/notice/{ncode}")
	public NoticeDto read(@PathVariable String ncode) {
		return noticeService.read(ncode);
	}
	
	@ResponseBody
	@RequestMapping(value = "/api/notice/{ncode}", method = RequestMethod.DELETE)
	public void delete(@PathVariable String ncode) {
		noticeService.delete(ncode);
	}
	
	@ResponseBody
	@RequestMapping(value = "/api/notice", method = RequestMethod.PATCH)
	public void update(@RequestBody NoticeDto vo) {
		noticeService.update(vo);
	}

}
