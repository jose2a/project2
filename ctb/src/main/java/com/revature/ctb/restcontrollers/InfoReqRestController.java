package com.revature.ctb.restcontrollers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.revature.ctb.domains.InfoReq;
import com.revature.ctb.services.InfoReqService;

@RestController
public class InfoReqRestController extends BasedRestController {

	private InfoReqService infoReqServ;

	@Autowired
	public void setInfoReqServ(InfoReqService infoReqServ) {
		this.infoReqServ = infoReqServ;
	}

	@PostMapping(value = "information", consumes = "application/json")
	@ResponseStatus(code = HttpStatus.CREATED)
	public boolean addInfoRequested(@Valid @RequestBody InfoReq infoReq) {
		return infoReqServ.addInfoRequested(infoReq);

	}

	@PutMapping(value = "information/{infoReqId}", consumes = "application/json")
	@ResponseStatus(code = HttpStatus.OK)
	public void updateInfoRequested(@PathVariable Integer infoReqId, @RequestBody InfoReq infoReq) {
		infoReqServ.updateInfoRequested(infoReq);
	}

	@GetMapping(value = "information/{infoReqId}")
	@ResponseStatus(code = HttpStatus.OK)
	public InfoReq getInfoRequestedById(@PathVariable Integer infoReqId) {

		return infoReqServ.getInfoRequestedById(infoReqId);

	}

	@PutMapping(value = "information/{infoReqId}/confirmed", consumes = "application/json")
	@ResponseStatus(code = HttpStatus.OK)
	public boolean confirmInfoRequestedWasReceived(@PathVariable Integer infoReqId) {
		return infoReqServ.confirmInfoRequestedWasReceived(infoReqId);
	}

	@GetMapping(value = "information/employee/{employeeId}")
	@ResponseStatus(code = HttpStatus.OK)
	public List<InfoReq> getInfoRequestedByEmployeeId(@PathVariable Integer employeeId) {
		return infoReqServ.getInfoRequestedByEmployeeId(employeeId);
	}

	@GetMapping(value = "information")
	@ResponseStatus(code = HttpStatus.OK)
	public List<InfoReq> getAllInfoRequested() {
		return infoReqServ.getAllInfoRequested();
	}

}
