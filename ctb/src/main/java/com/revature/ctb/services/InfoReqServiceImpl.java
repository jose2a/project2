package com.revature.ctb.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.revature.ctb.daos.InfoRequestDAO;
import com.revature.ctb.domains.InfoReq;
import com.revature.ctb.exceptions.NotFoundRecordException;

public class InfoReqServiceImpl implements InfoReqService {

	private InfoRequestDAO infoReqDao;

	@Autowired
	public void setInfoReqDao(InfoRequestDAO infoReqDao) {
		this.infoReqDao = infoReqDao;
	}

	@Override
	public boolean addInfoRequested(InfoReq infoReq) {
		infoReq.setProvided(false); // Set information requested as not provided by default
		
		return infoReqDao.addRequest(infoReq);
	}

	@Override
	public boolean updateInfoRequested(InfoReq infoReq) {
		return infoReqDao.updateRequest(infoReq);
	}

	@Override
	public InfoReq getInfoRequestedById(Integer infoReqId) {
		InfoReq infoReq = infoReqDao.getInfoReqById(infoReqId);

		if (infoReq == null) {
			throw new NotFoundRecordException("Information required not found.");
		}

		return infoReq;
	}

	@Override
	public List<InfoReq> getInfoRequestedByEmployeeId(Integer employeeId) {
		return infoReqDao.getReqByEmpId(employeeId);
	}

	@Override
	public List<InfoReq> getAllInfoRequested() {
		return infoReqDao.getInfoRequests();
	}

	@Override
	public boolean confirmInfoRequestedWasReceived(Integer infoReqId) {
		InfoReq infoReq = infoReqDao.getInfoReqById(infoReqId);

		if (infoReq == null) {
			throw new NotFoundRecordException("Information required not found.");
		}
		
		infoReq.setProvided(true);
		
		return infoReqDao.updateRequest(infoReq);
	}

}
