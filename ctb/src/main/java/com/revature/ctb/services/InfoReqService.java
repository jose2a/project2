package com.revature.ctb.services;

import java.util.List;

import com.revature.ctb.domains.InfoReq;

public interface InfoReqService {

	public boolean addInfoRequested(InfoReq infoReq);

	public boolean updateInfoRequested(InfoReq infoReq);

	public InfoReq getInfoRequestedById(Integer infoReqId);
	
	public boolean confirmInfoRequestedWasReceived(Integer infoReqId);

	public List<InfoReq> getInfoRequestedByEmployeeId(Integer employeeId);

	public List<InfoReq> getAllInfoRequested();
}
