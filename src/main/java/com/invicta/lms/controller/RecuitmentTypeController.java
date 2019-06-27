package com.invicta.lms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.invicta.lms.dto.RecuitmentTypeDto;
import com.invicta.lms.dto.mapper.RecuitmentTypeDtoMapper;
import com.invicta.lms.entity.RecuitmentType;
import com.invicta.lms.entity.mapper.RecuitmentTypeMapper;
import com.invicta.lms.service.RecuitmentTypeService;

@RestController
@RequestMapping("/recuitmentType")
public class RecuitmentTypeController {

	@Autowired
	RecuitmentTypeService recuitmentTypeService;

	@GetMapping
	public ResponseEntity<List<RecuitmentTypeDto>> getRecuitmentType() {
		return new ResponseEntity<>(RecuitmentTypeMapper.mapRecuitmentTypeListToRecuitmentTypeDtoList(
				recuitmentTypeService.viewAllRecuitmentType()), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getRecuitmentTypeById(@PathVariable("id") Long id) {
		if (recuitmentTypeService.findRecuitmentTypeById(id) != null) {
			return new ResponseEntity<>(RecuitmentTypeMapper.mapRecuitmentTypeToRecuitmentTypeDto(
					recuitmentTypeService.findRecuitmentTypeById(id)), HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	@PostMapping
	public ResponseEntity<?> addRecuitmentType(@RequestBody RecuitmentTypeDto recuitmentTypeDto) {
		return new ResponseEntity<>(
				RecuitmentTypeMapper.mapRecuitmentTypeToRecuitmentTypeDto(recuitmentTypeService.addRecuitmentType(
						RecuitmentTypeDtoMapper.mapRecuitmentTypeDtoToRecuitmentType(recuitmentTypeDto))),
				HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateRecuitmentType(@RequestBody RecuitmentTypeDto recuitmentTypeDto,
			@PathVariable Long id) {
		return new ResponseEntity<RecuitmentType>(
				recuitmentTypeService.updateRecuitmentType(id,
						RecuitmentTypeDtoMapper.mapRecuitmentTypeDtoToRecuitmentType(recuitmentTypeDto)),
				HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteRecuitmentType(@PathVariable Long id) {
		return new ResponseEntity<>(recuitmentTypeService.deleteRecuitmentType(id), HttpStatus.OK);
	}
}