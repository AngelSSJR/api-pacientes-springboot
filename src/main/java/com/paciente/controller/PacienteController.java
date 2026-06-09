package com.paciente.controller;

import com.paciente.model.Paciente;
import com.paciente.service.PacienteService;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pacientes")
@CrossOrigin(origins = "*")
public class PacienteController {
	
	private final PacienteService pacienteService;
	
	public PacienteController(PacienteService pacienteService) {
		this.pacienteService = pacienteService;
	}
	
	@GetMapping
	public List<Paciente> obtenerTodos(){
		return pacienteService.listarTodos();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Paciente> obtenerPorId(@PathVariable Long id){
		return ResponseEntity.ok(pacienteService.obtenerPacientePorId(id));
	}
	
	@PostMapping 
	public ResponseEntity<Paciente> guardar(@Valid @RequestBody Paciente paciente){
		return ResponseEntity.ok(pacienteService.crearPaciente(paciente));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Paciente> actualizar(@PathVariable Long id, @Valid @RequestBody Paciente paciente) {
		return ResponseEntity.ok(pacienteService.actualizarPaciente(id, paciente));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable Long id) {
		pacienteService.eliminarPaciente(id);
		return ResponseEntity.noContent().build();
	}
}
