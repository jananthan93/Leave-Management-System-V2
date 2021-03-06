package com.invicta.lms.validation;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.invicta.lms.dto.RoleDto;
import com.invicta.lms.service.RoleService;

@Service
public class RoleValidation {
	@Autowired
	RoleService roleService;

	Map<String, String> errors = new HashMap<String, String>();

	public void validateRole(RoleDto roleDto) {

		errors.clear();

		if (roleDto.getRoleName() == null) {
			errors.put("role", "Role cannot be null");
		}

		if (roleDto.getRoleName() == "") {
			errors.put("role", "Role cannot be Empty");
		}

		if (roleService.existsByRole(roleDto.getRoleName())) {
			errors.put("role", "Role Already exist");
		}

	}

	public Map<String, String> getErrors() {
		return errors;
	}

}
