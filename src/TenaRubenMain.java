import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class TenaRubenMain {
    private static final ArrayList<TenaRubenEvent> events = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int option;

        do {
            showMenu();
            while (!scanner.hasNextInt()) {
                System.out.println("Opción no válida. Introduzca un número.");
                scanner.next();
            }
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1 -> addEvent(scanner);
                case 2 -> deleteEvent(scanner);
                case 3 -> listEvents();
                case 4 -> toggleTaskCompletion(scanner);
                case 5 -> System.out.println("Adiós");
                default -> System.out.println("Opción no válida. Inténtelo de nuevo.");
            }
        } while (option != 5);

        scanner.close();
    }

    private static void showMenu() {
        System.out.println("\nBienvenido a Event Planner. Seleccione una opción:");
        System.out.println("[1] Añadir evento");
        System.out.println("[2] Borrar evento");
        System.out.println("[3] Listar eventos");
        System.out.println("[4] Marcar/desmarcar tarea de un evento como completada");
        System.out.println("[5] Salir");
        System.out.print("Opción: ");
    }

    private static void addEvent(Scanner scanner) {
        System.out.print("Título del evento: ");
        String title = scanner.nextLine();

        System.out.print("Fecha del evento (YYYY-MM-DD): ");
        LocalDate date = LocalDate.parse(scanner.nextLine());

        System.out.print("Prioridad (HIGH, MEDIUM, LOW): ");
        TenaRubenEvent.Priority priority = TenaRubenEvent.Priority.valueOf(scanner.nextLine().toUpperCase());

        TenaRubenEvent event = new TenaRubenEvent(title, date, priority);

        System.out.print("¿Quiere añadir tareas ahora? (s/n): ");
        if (scanner.nextLine().equalsIgnoreCase("s")) {
            while (true) {
                System.out.print("Descripción de la tarea (o 'salir' para finalizar): ");
                String taskText = scanner.nextLine();
                if (taskText.equalsIgnoreCase("salir")) break;
                event.addTask(new TenaRubenEventTask(taskText));
            }
        }

        events.add(event);
        System.out.println("Evento agregado con éxito.");
    }

    private static void deleteEvent(Scanner scanner) {
        System.out.print("Título del evento a borrar: ");
        String title = scanner.nextLine();

        boolean removed = events.removeIf(event -> event.getTitle().equalsIgnoreCase(title));
        if (removed) {
            System.out.println("Evento eliminado con éxito.");
        } else {
            System.out.println("No se encontró ningún evento con ese título.");
        }
    }

    private static void listEvents() {
        if (events.isEmpty()) {
            System.out.println("No hay eventos registrados.");
        } else {
            events.forEach(event -> System.out.println(event + "\n"));
        }
    }

    private static void toggleTaskCompletion(Scanner scanner) {
        System.out.print("Título del evento: ");
        String title = scanner.nextLine();

        TenaRubenEvent event = events.stream()
                .filter(e -> e.getTitle().equalsIgnoreCase(title))
                .findFirst()
                .orElse(null);

        if (event == null) {
            System.out.println("No se encontró ningún evento con ese título.");
            return;
        }

        System.out.println(event.listTasks());
        System.out.print("Seleccione el número de la tarea a cambiar de estado: ");
        int taskIndex = scanner.nextInt() - 1;
        scanner.nextLine(); // Limpiar buffer

        TenaRubenEventTask task = event.getTask(taskIndex);
        if (task != null) {
            task.toggleCompletion();
            System.out.println("Estado de la tarea cambiado.");
        } else {
            System.out.println("Tarea no encontrada.");
        }
    }
}
