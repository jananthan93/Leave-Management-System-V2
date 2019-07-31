package com.invicta.lms.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.invicta.lms.dto.LeaveDtoRequest;
import com.invicta.lms.dto.LeaveDtoResponse;
import com.invicta.lms.dto.LeaveProcessDtoRequest;
import com.invicta.lms.dto.mapper.LeaveRequestMapper;
import com.invicta.lms.entity.LeaveRequest;
import com.invicta.lms.enums.LeaveRequestAction;
import com.invicta.lms.enums.LeaveRequestStatus;
import com.invicta.lms.repository.LeaveRequestRepository;
import com.invicta.lms.repository.UserRepository;
import com.invicta.lms.service.LeaveRequestProcessService;
import com.invicta.lms.service.LeaveRequestService;

@Service
public class LeaveRequestServiceImpl implements LeaveRequestService {
	@Autowired
	LeaveRequestRepository leaveRequestRepository;
	@Autowired
	LeaveRequestMapper leaveRequestMapper;
	
	@Autowired
	UserRepository userRepository;
	@Autowired
	LeaveRequestProcessService leaveRequestProcessService;
	
	
	private  LeaveRequest addLeaveRequest(LeaveDtoRequest leaveDtoRequest,Long requestUserId) {
		LeaveRequest leaveRequest = new LeaveRequest();
		leaveRequest =leaveRequestMapper.maptDtoToEnity(leaveDtoRequest, leaveRequest);
	
		if(leaveRequest != null) {
			leaveRequest.setLeaveRequestStatus(LeaveRequestStatus.PENDING);
			leaveRequest.setRequestedUser(userRepository.findUserById(requestUserId));
			return leaveRequestRepository.save(leaveRequest);
			
		}
		return null;
	}
	
	@Override
	public List<LeaveDtoResponse> findAllLeaveRequest() {
		return leaveRequestMapper.mapEntityListToDtoList(leaveRequestRepository.findAll());
	}

	@Override
	public Long deleteLeaveRequest(Long id) {
		if(leaveRequestRepository.getOne(id)!=null) {
			leaveRequestRepository.deleteById(id);
			 return id;
			}
			return null;
		}


	@Override
	public LeaveDtoResponse findLeaveRequestById(Long id) {
		if(leaveRequestRepository.getOne(id)!= null) {
			return leaveRequestMapper.mapEntityToDto(leaveRequestRepository.findLeaveRequestById(id));
		}
		return null;
	}

	@Override
	public List<LeaveDtoResponse> findLeaveRequestByUserId(Long userId) {
		if(userId!=null) {
			return leaveRequestMapper.mapEntityListToDtoList(leaveRequestRepository.findByrequestedUser(userRepository.findUserById(userId)));
		}
		return null;
	}

	@Override
	public Boolean createInitialLeaveRequestProcess(LeaveDtoRequest leaveDtoRequest,Long requestUserId) {
		LeaveRequest leaveRequest=this.addLeaveRequest(leaveDtoRequest, requestUserId);
		LeaveProcessDtoRequest leaveProcessDtoRequest=new LeaveProcessDtoRequest(LeaveRequestAction.APPLIED,"Leave Request applied");
		leaveRequestProcessService.processLeaveRequest(leaveRequest.getId(),requestUserId,leaveProcessDtoRequest);
		
		return false;
	}
}
