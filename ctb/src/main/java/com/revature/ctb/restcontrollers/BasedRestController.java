package com.revature.ctb.restcontrollers;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import com.revature.ctb.domains.InfoReq;
import com.revature.ctb.domains.Ride;
import com.revature.ctb.dtos.CarDto;
import com.revature.ctb.dtos.EmployeeDto;
import com.revature.ctb.dtos.InfoReqDto;
import com.revature.ctb.dtos.RideDto;

@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class BasedRestController {

	protected DozerBeanMapper mapper;

	@Autowired
	public void setMapper(DozerBeanMapper mapper) {
		this.mapper = mapper;
	}

	protected RideDto mapRideToRideDto(Ride ride) {
		EmployeeDto employeeDto = mapper.map(ride.getEmployee(), EmployeeDto.class);
		CarDto carDto = mapper.map(ride.getCar(), CarDto.class);
		RideDto rideDto = mapper.map(ride, RideDto.class);
		rideDto.setEmployee(employeeDto);
		rideDto.setCar(carDto);
		return rideDto;
	}

	protected InfoReqDto mapInfoReqToInforReqDto(InfoReq infoReq) {
		EmployeeDto employeeDto = mapper.map(infoReq.getEmployee(), EmployeeDto.class);
		InfoReqDto infoReqDto = mapper.map(infoReq, InfoReqDto.class);
		infoReqDto.setEmployee(employeeDto);
		return infoReqDto;
	}
}
