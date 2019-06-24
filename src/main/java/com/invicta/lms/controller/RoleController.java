package com.invicta.lms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.invicta.lms.entity.Role;
import com.invicta.lms.service.RoleService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class RoleController {

	@Autowired
	RoleService roleService;

	@GetMapping("/role")
	public ResponseEntity<List<Role>> getRole() {
		return new ResponseEntity<>(roleService.viewAllRole(), HttpStatus.OK);
	}

	@PostMapping("/role")
	public ResponseEntity<String> addRole(@RequestBody Role role) {
		String msg = null;
		if (roleService.addRole(role)) {
			msg = "CREATED";
		} else {
			msg = "NOT CREATED";
		}
		return new ResponseEntity<>(msg, HttpStatus.OK);
	}

	@PutMapping("/role/{id}")
	public HttpStatus updateRole(@RequestBody Role role, @PathVariable Integer id) {
		if (roleService.updateRole(id, role)) {
			return HttpStatus.OK;
		}
		return HttpStatus.BAD_REQUEST;
	}

	@DeleteMapping("/role/{id}")
	public HttpStatus deleteRole(@PathVariable Integer id) {
		if (roleService.deleteRole(id)) {
			return HttpStatus.OK;
		}
		return HttpStatus.BAD_REQUEST;
	}
}