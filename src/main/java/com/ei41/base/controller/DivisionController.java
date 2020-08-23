package com.ei41.base.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ei41.base.Service.DivisionService;
import com.ei41.base.model.Division;

@Controller
@RequestMapping("division")
public class DivisionController {
	@Autowired
	DivisionService divisionService;

	

	@RequestMapping(value = "/list", method = { RequestMethod.GET })
	@ResponseBody
	public List<Division> listDivision() {
		return divisionService.listDivision();
	}

}