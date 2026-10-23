package sg.edu.ntu.taskflow_api.service;

import org.springframework.stereotype.Service;
import sg.edu.ntu.taskflow_api.repository.TaskRepository;
import sg.edu.ntu.taskflow_api.model.Task;
import java.util.List;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    // Constructor for TaskService, injects TaskRepository dependency
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    // Find all tasks
    public List<Task> findAllTasks() {
        return taskRepository.findAll();
    }

    // Find a task by its ID
    public Task findTaskById(Long id) {
        return taskRepository.findById(id);
    }

    // Create a task
    public Task createTask(Task task) {
        task.setId(null);
        return taskRepository.save(task);
    }

    // Update a task
    public Task updateTask(Long id, Task task) {
        // Step 1: Find the existing task by its ID
        Task existingTask = taskRepository.findById(id);
        // Step 2: Check whether the task exists
        if (existingTask == null) {
            return null;
        }
        // Step 3: Update the existing task's fields with the new values
        existingTask.setTitle(task.getTitle());
        existingTask.setCompleted((task.isCompleted()));
        // Step 4: Save the updated task and return it
        return taskRepository.save(existingTask);
    }

    // Delete a task by its ID
    public boolean deleteTask(Long id) {
        return taskRepository.deleteById(id);
    }

    // Mark a task as completed
    public Task markTaskAsCompleted(Long id) {
        // Step 1: Find the existing task by its ID
        Task existingTask = taskRepository.findById(id);
        // Step 2: Check whether the task exists
        if (existingTask == null) {
            return null;
        }
        // Step 3: Mark the task as completed
        existingTask.setCompleted(true);
        // Step 4: Save the updated task and return it
        return taskRepository.save(existingTask);
    }
}
