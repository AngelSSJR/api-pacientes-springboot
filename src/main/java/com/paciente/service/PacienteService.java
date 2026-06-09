package com.paciente.service;

import com.paciente.model.Paciente; 
import java.util.List;

public interface PacienteService {
    List<Paciente> listarTodos();
    Paciente crearPaciente(Paciente paciente);
    void eliminarPaciente(Long id);
    
    
    Paciente obtenerPacientePorId(Long id);
    Paciente actualizarPaciente(Long id, Paciente paciente);
}