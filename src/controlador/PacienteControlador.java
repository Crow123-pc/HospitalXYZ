package controlador;

import excepciones.DatoInvalidoException;
import modelo.Paciente;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PacienteControlador {
    private final List<Paciente> pacientes;

    public PacienteControlador() {
        pacientes = new ArrayList<>();
    }

    public void agregarPaciente(Paciente paciente) throws DatoInvalidoException {
        if (paciente == null) {
            throw new DatoInvalidoException("No se puede registrar un paciente nulo.");
        }

        for (Paciente registrado : pacientes) {
            if (registrado.getNumeroIdentificacion()
                    .equalsIgnoreCase(paciente.getNumeroIdentificacion())) {
                throw new DatoInvalidoException(
                        "Ya existe un paciente con esta identificación."
                );
            }
        }

        pacientes.add(paciente);
    }

    public List<Paciente> listarPacientes() {
        return Collections.unmodifiableList(new ArrayList<>(pacientes));
    }
}
