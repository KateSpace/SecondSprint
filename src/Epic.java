import java.util.ArrayList;

public class Epic extends Task{

    ArrayList<Subtask> subtasksListOfEpic;

    public Epic(String name, String description) {
        super(name, description);
        subtasksListOfEpic = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Epic{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                ", id=" + id +
                '}';
    }
}
