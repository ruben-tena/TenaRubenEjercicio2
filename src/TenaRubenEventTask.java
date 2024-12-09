public class TenaRubenEventTask {
    private String text;
    private boolean isCompleted;

    public TenaRubenEventTask(String text) {
        this.text = text;
        this.isCompleted = false;
    }

    public void toggleCompletion() {
        this.isCompleted = !this.isCompleted;
    }

    @Override
    public String toString() {
        return "Tarea: " + text + " | Estado: " + (isCompleted ? "Completada" : "Pendiente");
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public String getText() {
        return text;
    }
}
