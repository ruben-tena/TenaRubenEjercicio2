import java.time.LocalDate;
import java.util.ArrayList;

public class TenaRubenEvent {
    public enum Priority { HIGH, MEDIUM, LOW }

    private String title;
    private LocalDate date;
    private Priority priority;
    private ArrayList<TenaRubenEventTask> tasks;

    public TenaRubenEvent(String title, LocalDate date, Priority priority) {
        this.title = title;
        this.date = date;
        this.priority = priority;
        this.tasks = new ArrayList<>();
    }

    public void addTask(TenaRubenEventTask task) {
        tasks.add(task);
    }

    @Override
    public String toString() {
        long completedTasks = tasks.stream().filter(TenaRubenEventTask::isCompleted).count();
        return "Evento: " + title +
                " | Fecha: " + date +
                " | Prioridad: " + priority +
                " | Tareas completadas: " + completedTasks + "/" + tasks.size();
    }

    public TenaRubenEventTask getTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            return tasks.get(index);
        }
        return null;
    }

    public String listTasks() {
        StringBuilder taskList = new StringBuilder("Lista de Tareas:\n");
        for (int i = 0; i < tasks.size(); i++) {
            taskList.append(i + 1).append(". ").append(tasks.get(i)).append("\n");
        }
        return taskList.toString();
    }

    public String getTitle() {
        return title;
    }
}
