package com.ei41.base.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ei41.base.mapper.DivisionMapper;
import com.ei41.base.model.Division;

@Service
public class DivisionService {

	@Autowired
	DivisionMapper divisionMapper;

	public List<Division> listDivision() {
		return divisionMapper.listDivision();
	}

}
