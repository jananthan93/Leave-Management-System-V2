package com.invicta.lms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.invicta.lms.dto.LeaveProcessDtoRequest;
import com.invicta.lms.service.LeaveRequestProcessService;

@RestController
@RequestMapping("leaveRequestProcess")
public class LeaveRequestProcessController {
	@Autowired
	LeaveRequestProcessService leaveRequestProcessService;

	@PostMapping("/leaverequest/{leaverequestid}/processuser/{processuserid}")
	public ResponseEntity<?> acceptLeaveRequest(@RequestBody LeaveProcessDtoRequest leaveProcessDtoRequest,
			@PathVariable("leaverequestid") Long leaveRequestId, @PathVariable("processuserid") Long processUserId) {
		if (leaveProcessDtoRequest != null) {
			return new ResponseEntity<>(leaveRequestProcessService.processLeaveRequest(leaveRequestId, processUserId,
					leaveProcessDtoRequest), HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

}
