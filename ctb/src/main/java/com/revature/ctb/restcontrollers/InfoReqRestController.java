package com.revature.ctb.restcontrollers;

import java.util.ArrayList;
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
import com.revature.ctb.dtos.InfoReqAnswerDto;
import com.revature.ctb.dtos.InfoReqDto;
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
	public void addInfoRequested(@Valid @RequestBody InfoReq infoReq) {
		infoReqServ.addInfoRequested(infoReq);

	}

	@PutMapping(value = "information/{infoReqId}", consumes = "application/json")
	@ResponseStatus(code = HttpStatus.OK)
	public void updateInfoRequested(@PathVariable Integer infoReqId,
			@Valid @RequestBody InfoReqAnswerDto infoReqAnswerDto) {
		infoReqServ.answerQuestion(infoReqAnswerDto.getInforeqId(), infoReqAnswerDto.getAnswer());
	}

	@GetMapping(value = "information/{infoReqId}")
	@ResponseStatus(code = HttpStatus.OK)
	public InfoReqDto getInfoRequestedById(@PathVariable Integer infoReqId) {

		InfoReq infoReq = infoReqServ.getInfoRequestedById(infoReqId);

		return mapper.map(infoReq, InfoReqDto.class);
	}

	@PutMapping(value = "information/{infoReqId}/confirmed", consumes = "application/json")
	@ResponseStatus(code = HttpStatus.OK)
	public void confirmInfoRequestedWasReceived(@PathVariable Integer infoReqId) {
		infoReqServ.confirmInfoRequestedWasReceived(infoReqId);
	}

	@GetMapping(value = "information/employee/{employeeId}")
	@ResponseStatus(code = HttpStatus.OK)
	public List<InfoReqDto> getInfoRequestedByEmployeeId(@PathVariable Integer employeeId) {
		List<InfoReqDto> infoReqDtoList = new ArrayList<>();

		infoReqServ.getInfoRequestedByEmployeeId(employeeId)
				.forEach(ir -> infoReqDtoList.add(mapper.map(ir, InfoReqDto.class)));

		return infoReqDtoList;
	}

	@GetMapping(value = "information")
	@ResponseStatus(code = HttpStatus.OK)
	public List<InfoReqDto> getAllInfoRequested() {
		List<InfoReqDto> infoReqDtoList = new ArrayList<>();

		infoReqServ.getAllInfoRequested().forEach(ir -> infoReqDtoList.add(mapper.map(ir, InfoReqDto.class)));

		return infoReqDtoList;
	}

}
