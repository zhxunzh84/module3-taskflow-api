package sg.edu.ntu.taskflow_api.repository;

import org.springframework.stereotype.Repository;
import sg.edu.ntu.taskflow_api.model.Task;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

@Repository
public class TaskRepository {

    private final Map<Long, Task> tasks = new HashMap<>();

    private long nextId = 1L;

    public Task save(Task task) {
        // Generate a new ID only for new tasks
        if (task.getId() == null) {
            task.setId(nextId++);
        }
        tasks.put(task.getId(), task);
        return task;
    }

    // Retrieve all tasks as a list
    public List<Task> findAll() {
        return new ArrayList<>(tasks.values());
    }

    // Find a task by its ID, return null if not found
    public Task findById(Long id) {
        return tasks.get(id);
    }

    // Delete a task by its ID, return true if deleted successfully
    public boolean deleteById(Long id) {
        return tasks.remove(id) != null;
    }

}
