package modelo;

import excepciones.DatoInvalidoException;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Paciente {
    private String nombreCompleto;
    private String tipoDocumento;
    private String numeroIdentificacion;
    private LocalDate fechaNacimiento;
    private String tipoSangre;
    private List<String> alergias;
    private String telefono;
    private String correoElectronico;

    private static final List<String> TIPOS_SANGRE = Arrays.asList(
            "A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-"
    );

    public Paciente(String nombreCompleto,
                    String tipoDocumento,
                    String numeroIdentificacion,
                    LocalDate fechaNacimiento,
                    String tipoSangre,
                    List<String> alergias,
                    String telefono,
                    String correoElectronico) throws DatoInvalidoException {
        setNombreCompleto(nombreCompleto);
        setTipoDocumento(tipoDocumento);
        setNumeroIdentificacion(numeroIdentificacion);
        setFechaNacimiento(fechaNacimiento);
        setTipoSangre(tipoSangre);
        setAlergias(alergias);
        setTelefono(telefono);
        setCorreoElectronico(correoElectronico);
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) throws DatoInvalidoException {
        if (nombreCompleto == null || nombreCompleto.trim().isEmpty()) {
            throw new DatoInvalidoException("El nombre completo es obligatorio.");
        }
        this.nombreCompleto = nombreCompleto.trim().replaceAll("\\s+", " ");
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) throws DatoInvalidoException {
        if (tipoDocumento == null) {
            throw new DatoInvalidoException("El tipo de documento es obligatorio.");
        }

        String tipo = tipoDocumento.trim().toUpperCase();
        if (!tipo.equals("DNI") && !tipo.equals("PASAPORTE") && !tipo.equals("CE")) {
            throw new DatoInvalidoException("Tipo de documento inválido. Use DNI, PASAPORTE o CE.");
        }
        this.tipoDocumento = tipo;
    }

    public String getNumeroIdentificacion() {
        return numeroIdentificacion;
    }

    public void setNumeroIdentificacion(String numeroIdentificacion) throws DatoInvalidoException {
        if (numeroIdentificacion == null || numeroIdentificacion.trim().isEmpty()) {
            throw new DatoInvalidoException("La identificación es obligatoria.");
        }

        String identificacion = numeroIdentificacion.trim();

        if ("DNI".equals(tipoDocumento)) {
            if (!identificacion.matches("\\d{8}")) {
                throw new DatoInvalidoException("El DNI debe contener exactamente 8 dígitos.");
            }
        } else {
            if (!identificacion.matches("[A-Za-z0-9-]{6,20}")) {
                throw new DatoInvalidoException(
                        "El documento extranjero debe contener entre 6 y 20 caracteres válidos."
                );
            }
        }

        this.numeroIdentificacion = identificacion;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) throws DatoInvalidoException {
        if (fechaNacimiento == null) {
            throw new DatoInvalidoException("La fecha de nacimiento es obligatoria.");
        }
        if (fechaNacimiento.isAfter(LocalDate.now())) {
            throw new DatoInvalidoException("La fecha de nacimiento no puede estar en el futuro.");
        }

        int edad = Period.between(fechaNacimiento, LocalDate.now()).getYears();
        if (edad > 120) {
            throw new DatoInvalidoException("La fecha de nacimiento no es válida.");
        }
        this.fechaNacimiento = fechaNacimiento;
    }

    public int calcularEdad() {
        return Period.between(fechaNacimiento, LocalDate.now()).getYears();
    }

    public String getTipoSangre() {
        return tipoSangre;
    }

    public void setTipoSangre(String tipoSangre) throws DatoInvalidoException {
        if (tipoSangre == null || !TIPOS_SANGRE.contains(tipoSangre.trim().toUpperCase())) {
            throw new DatoInvalidoException("Tipo de sangre inválido.");
        }
        this.tipoSangre = tipoSangre.trim().toUpperCase();
    }

    public List<String> getAlergias() {
        return new ArrayList<>(alergias);
    }

    public void setAlergias(List<String> alergias) throws DatoInvalidoException {
        if (alergias == null || alergias.isEmpty()) {
            throw new DatoInvalidoException("Debe registrar las alergias o escribir NINGUNA.");
        }

        List<String> listaLimpia = new ArrayList<>();
        for (String alergia : alergias) {
            if (alergia != null && !alergia.trim().isEmpty()) {
                listaLimpia.add(alergia.trim());
            }
        }

        if (listaLimpia.isEmpty()) {
            throw new DatoInvalidoException("Debe registrar las alergias o escribir NINGUNA.");
        }
        this.alergias = listaLimpia;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) throws DatoInvalidoException {
        if (telefono == null || !telefono.trim().matches("\\d{9}")) {
            throw new DatoInvalidoException("El teléfono debe contener exactamente 9 dígitos.");
        }
        this.telefono = telefono.trim();
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) throws DatoInvalidoException {
        if (correoElectronico == null || correoElectronico.trim().isEmpty()) {
            throw new DatoInvalidoException("El correo electrónico es obligatorio.");
        }

        String correo = correoElectronico.trim();
        String expresion = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";

        if (!correo.matches(expresion)) {
            throw new DatoInvalidoException("Formato de correo electrónico inválido.");
        }
        if (correo.toLowerCase().endsWith(".con")) {
            throw new DatoInvalidoException(
                    "El dominio '.con' parece ser un error. Verifique si corresponde '.com'."
            );
        }
        this.correoElectronico = correo;
    }

    public boolean validarIdentidad() {
        return numeroIdentificacion != null
                && !numeroIdentificacion.isBlank()
                && tipoDocumento != null
                && !tipoDocumento.isBlank();
    }

    public void actualizarContacto(String telefono, String correoElectronico)
            throws DatoInvalidoException {
        setTelefono(telefono);
        setCorreoElectronico(correoElectronico);
    }

    public String emitirAlertaMedica() {
        if (alergias.size() == 1 && alergias.get(0).equalsIgnoreCase("NINGUNA")) {
            return "Sin alergias conocidas registradas.";
        }
        return "ALERTA MÉDICA - Alergias: " + String.join(", ", alergias);
    }

    @Override
    public String toString() {
        return "\n-------------------------------"
                + "\nPaciente: " + nombreCompleto
                + "\nDocumento: " + tipoDocumento + " - " + numeroIdentificacion
                + "\nFecha de nacimiento: " + fechaNacimiento
                + "\nEdad: " + calcularEdad() + " años"
                + "\nTipo de sangre: " + tipoSangre
                + "\nAlergias: " + String.join(", ", alergias)
                + "\nTeléfono: " + telefono
                + "\nCorreo: " + correoElectronico
                + "\n" + emitirAlertaMedica()
                + "\n-------------------------------";
    }
}
