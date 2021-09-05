package com.empresa.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.empresa.entity.Alumno;
import com.empresa.service.AlumnoService;

@RestController
@RequestMapping("/rest/alumno")
public class AlumnoController {

	@Autowired
	private AlumnoService service;

	@GetMapping
	@ResponseBody
	public ResponseEntity<List<Alumno>> listaAlumno(){
		List<Alumno> lista = service.listaAlumno();
		return ResponseEntity.ok(lista);
	}
	
	@PostMapping

	@ResponseBody

	public ResponseEntity<Alumno> insertaAlumno(@RequestBody Alumno obj){

		if (obj == null) {

			return ResponseEntity.noContent().build();	

		}else {

			obj.setIdAlumno(0);

			Alumno objSalida = service.insertaActualizaAlumno(obj);

			return ResponseEntity.ok(objSalida);

		}

	}

	

	@PutMapping

	@ResponseBody

	public ResponseEntity<Alumno> actualizaAlumno(@RequestBody Alumno obj){

		if (obj == null) {

			return ResponseEntity.noContent().build();	

		}else {

			Optional<Alumno> optAlumno = service.ObtenerPorId(obj.getIdAlumno());

			if (optAlumno.isEmpty()) {

				return ResponseEntity.noContent().build();

			}else {

				Alumno objSalida = service.insertaActualizaAlumno(obj);

				return ResponseEntity.ok(objSalida);	

			}

		}

	}
}
