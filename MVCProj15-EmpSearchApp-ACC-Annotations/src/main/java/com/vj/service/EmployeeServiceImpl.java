package com.vj.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vj.bo.EmployeeBO;
import com.vj.bo.EmployeeResultBO;
import com.vj.dao.EmployeeDAO;
import com.vj.dto.EmployeeDTO;
import com.vj.dto.EmployeeResultDTO;

@Service("empService")
public class EmployeeServiceImpl implements EmployeeService {
	@Autowired
	private EmployeeDAO dao;

	@Override
	public List<EmployeeResultDTO> search(EmployeeDTO dto) {
		List<EmployeeResultDTO> listRDTO=new ArrayList();
		List<EmployeeResultBO> listRBO=null;
		EmployeeBO bo=null;
		//convert dto to BO
		bo=new EmployeeBO();
		BeanUtils.copyProperties(dto, bo);
		//use DAO
		listRBO=dao.find(bo);
		//convert listRBO to listRDTO
		listRBO.forEach(rbo->{
			//copy each RBO obj to each RDTO object
			EmployeeResultDTO rdto=new EmployeeResultDTO();
			BeanUtils.copyProperties(rbo, rdto);
			listRDTO.add(rdto);
		});
		return listRDTO;
	}//method

}//class
