package com.paciente.service;

import com.paciente.model.Paciente;
import com.paciente.repository.PacienteRepository; 
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PacienteServiceImpl implements PacienteService {

    private final PacienteRepository pacienteRepository;

    public PacienteServiceImpl(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    @Override
    public List<Paciente> listarTodos() {
        return pacienteRepository.findAll(); 
    }

    @Override
    public Paciente crearPaciente(Paciente paciente) {
        return pacienteRepository.save(paciente); 
    }

    @Override
    public void eliminarPaciente(Long id) {
        pacienteRepository.deleteById(id); 
    }


    @Override
    public Paciente obtenerPacientePorId(Long id) {
        return pacienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado con ID: " + id));
    }

    @Override
    public Paciente actualizarPaciente(Long id, Paciente paciente) {
        
        Paciente pacienteExistente = obtenerPacientePorId(id);
        
        pacienteExistente.setNombre(paciente.getNombre());
        pacienteExistente.setEdad(paciente.getEdad());
        pacienteExistente.setCorreo(paciente.getCorreo());
        pacienteExistente.setTelefono(paciente.getTelefono());
        
        return pacienteRepository.save(pacienteExistente);
    }
}