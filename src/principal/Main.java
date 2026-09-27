package principal;

import controlador.PacienteControlador;
import excepciones.DatoInvalidoException;
import modelo.Paciente;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final PacienteControlador controlador = new PacienteControlador();
    private static final DateTimeFormatter FORMATO = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static void main(String[] args) {
        boolean continuar = true;

        while (continuar) {
            mostrarMenu();
            String opcion = scanner.nextLine();

            switch (opcion) {
                case "1":
                    registrarPaciente();
                    break;
                case "2":
                    listarPacientes();
                    break;
                case "3":
                    continuar = false;
                    System.out.println("\nSistema finalizado correctamente.");
                    break;
                default:
                    System.out.println("\nOpción inválida. Intente nuevamente.");
            }
        }

        scanner.close();
    }

    private static void mostrarMenu() {
        System.out.println("\n=================================");
        System.out.println("   HOSPITAL REGIONAL XYZ");
        System.out.println("   SISTEMA DE PACIENTES");
        System.out.println("=================================");
        System.out.println("1. Registrar paciente");
        System.out.println("2. Listar pacientes");
        System.out.println("3. Salir");
        System.out.print("Seleccione una opción: ");
    }

    private static void registrarPaciente() {
        try {
            System.out.println("\n--- REGISTRO DE PACIENTE ---");

            System.out.print("Nombre completo: ");
            String nombre = scanner.nextLine();

            System.out.print("Tipo documento (DNI/PASAPORTE/CE): ");
            String tipoDocumento = scanner.nextLine();

            System.out.print("Número de identificación: ");
            String identificacion = scanner.nextLine();

            System.out.print("Fecha nacimiento (dd/MM/yyyy): ");
            String fechaTexto = scanner.nextLine();
            LocalDate fechaNacimiento = LocalDate.parse(fechaTexto, FORMATO);

            System.out.print("Tipo de sangre (A+, A-, B+, B-, AB+, AB-, O+, O-): ");
            String tipoSangre = scanner.nextLine();

            System.out.print("Alergias separadas por coma (escriba NINGUNA si no posee): ");
            String alergiasTexto = scanner.nextLine();
            List<String> alergias = new ArrayList<>(Arrays.asList(alergiasTexto.split(",")));

            System.out.print("Teléfono de 9 dígitos: ");
            String telefono = scanner.nextLine();

            System.out.print("Correo electrónico: ");
            String correo = scanner.nextLine();

            Paciente paciente = new Paciente(
                    nombre,
                    tipoDocumento,
                    identificacion,
                    fechaNacimiento,
                    tipoSangre,
                    alergias,
                    telefono,
                    correo
            );

            controlador.agregarPaciente(paciente);
            System.out.println("\nPaciente registrado correctamente.");

        } catch (DatoInvalidoException e) {
            System.out.println("\nERROR DE VALIDACIÓN: " + e.getMessage());
        } catch (DateTimeParseException e) {
            System.out.println("\nERROR: La fecha debe ingresarse en formato dd/MM/yyyy.");
        } catch (Exception e) {
            System.out.println(
                    "\nSe produjo un error inesperado, pero el programa continuará funcionando."
            );
            System.out.println("Detalle: " + e.getMessage());
        }
    }

    private static void listarPacientes() {
        List<Paciente> pacientes = controlador.listarPacientes();

        if (pacientes.isEmpty()) {
            System.out.println("\nNo existen pacientes registrados.");
            return;
        }

        System.out.println("\n--- PACIENTES REGISTRADOS ---");
        for (Paciente paciente : pacientes) {
            System.out.println(paciente);
        }
    }
}
