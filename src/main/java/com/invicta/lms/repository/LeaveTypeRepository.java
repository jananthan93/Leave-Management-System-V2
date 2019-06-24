package com.invicta.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.invicta.lms.entity.LeaveType;

public interface LeaveTypeRepository extends JpaRepository<LeaveType, Integer> {
	LeaveType findLeaveTypeById(Integer id);
}
