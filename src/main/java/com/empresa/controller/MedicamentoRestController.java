package com.empresa.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.empresa.entity.Alumno;
import com.empresa.entity.Medicamento;
import com.empresa.service.MedicamentoService;

@RestController
@RequestMapping("/rest/medicamento")
public class MedicamentoRestController {


	@Autowired
	private MedicamentoService service;
	
	@GetMapping
	@ResponseBody  //salida tipo json
	public ResponseEntity<List<Medicamento>> listaMedicamento(){
		
		List<Medicamento> lista = service.listaMedicamento();
		return ResponseEntity.ok(lista);
	}	
	
	
	@PostMapping
	@ResponseBody //retorna datos en json .. inserta y devuelve el objeto insertado
	public ResponseEntity<Medicamento> registraMedicamento(@RequestBody  Medicamento obj){
	
		if(obj==null)
		{
			return ResponseEntity.noContent().build();
		}
		else
		{	
			obj.setIdMedicamento(0);
			Medicamento objSalida = service.registraActualizaMedicamento(obj);
			return ResponseEntity.ok(objSalida);
		}
	}
	

	@PutMapping
	@ResponseBody //retorna datos en json .. inserta y devuelve el objeto insertado
	public ResponseEntity<Medicamento> actualizaMedicamento(@RequestBody  Medicamento obj){
		
		
		if(obj==null)
		{
			return ResponseEntity.noContent().build();
		}
		else
		{	
			Optional<Medicamento> optMedicamento =service.ObtenerPorID(obj.getIdMedicamento());
			 
			if(optMedicamento.isEmpty())
			{
				return ResponseEntity.noContent().build();
			}
			else
			{	
				Medicamento objSalida = service.registraActualizaMedicamento(obj);
				return ResponseEntity.ok(objSalida);
			}
		}
	}
	
	 
	@GetMapping("/id/{paramId}")

	@ResponseBody

	public ResponseEntity<Medicamento> buscaPorId(@PathVariable("paramId") int idMedicamento){

		Optional<Medicamento> optMedicamnento = service.buscaPorId(idMedicamento);

		if (optMedicamnento.isPresent()) {

			return ResponseEntity.ok(optMedicamnento.get());

		}else {

			return ResponseEntity.badRequest().build();

		}

	}
	/*
	@GetMapping("/nombre/{paramName}")

	@ResponseBody

	public ResponseEntity<Medicamento> buscaPorNombre(@PathVariable("paramName") String nombreMedicamento){

		Optional<Medicamento> optMedicamnento = service.buscaPorNombre(nombreMedicamento);

		if (optMedicamnento.isPresent()) {

			return ResponseEntity.ok(optMedicamnento.get());

		}else {

			return ResponseEntity.badRequest().build();

		}

	}*/
	
	
	@GetMapping("/nombre/{paramNombre}")

	@ResponseBody

	public ResponseEntity<List<Medicamento>> buscaPorNombre(@PathVariable("paramNombre") String nombre){

		List<Medicamento> lista = service.buscaPorNombre(nombre);

		if (CollectionUtils.isEmpty(lista)) {

			return ResponseEntity.badRequest().build();

		}else {

			return ResponseEntity.ok(lista);

		}

	}
	
	@GetMapping("/stock/{paramStock}")

	@ResponseBody

	public ResponseEntity<List<Medicamento>> buscaPorNombre(@PathVariable("paramStock")int stock){

		List<Medicamento> lista = service.buscaPorStock(stock);

		if (CollectionUtils.isEmpty(lista)) {

			return ResponseEntity.badRequest().build();

		}else {

			return ResponseEntity.ok(lista);

		}

	}
 
	
}
