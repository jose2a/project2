package com.revature.ctb.restcontrollers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.revature.ctb.domains.InfoReq;
import com.revature.ctb.services.InfoReqService;

public class InfoReqRestController {

	
	private InfoReqService infoReqServ;

	public void setInfoReqServ(InfoReqService infoReqServ) {
		this.infoReqServ = infoReqServ;
	}
	
	@PostMapping(value = "infoRequest", consumes = "application/json")
	@ResponseStatus(code = HttpStatus.CREATED)
	public boolean addInfoRequested(@RequestBody InfoReq infoReq) {
		 return infoReqServ.addInfoRequested(infoReq);

	}
	
	@PutMapping(value = "infoRequest", consumes = "application/json")
	@ResponseStatus(code = HttpStatus.OK)
	public void updateInfoRequested(@RequestBody InfoReq infoReq) {
		infoReqServ.updateInfoRequested(infoReq);
	}
	
	@GetMapping(value = "infoRequest/{infoReqId}")
	@ResponseStatus(code = HttpStatus.OK)
	public InfoReq getInfoRequestedById(@PathVariable Integer infoReqId) {
		
		return infoReqServ.getInfoRequestedById(infoReqId);
		
	}
	
	@PutMapping(value = "infoRequest/confirmed", consumes = "aaplication/json")
	@ResponseStatus(code = HttpStatus.OK)
	public boolean confirmInfoRequestedWasReceived(@RequestBody Integer infoReqId) {
		return infoReqServ.confirmInfoRequestedWasReceived(infoReqId);
	}

	
	@GetMapping(value ="infoRequest/{employeeId}")
	@ResponseStatus(code = HttpStatus.OK)
	public List<InfoReq> getInfoRequestedByEmployeeId(@PathVariable Integer employeeId){
		return infoReqServ.getInfoRequestedByEmployeeId(employeeId);
	}
	
	@GetMapping(value = "infoRequest")
	@ResponseStatus(code = HttpStatus.OK)
	public List<InfoReq> getAllInfoRequested(){
		return infoReqServ.getAllInfoRequested();
	}

}
