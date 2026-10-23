package sg.edu.ntu.taskflow_api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.List;
import sg.edu.ntu.taskflow_api.service.TaskService;
import sg.edu.ntu.taskflow_api.service.TaskSummaryService;
import org.springframework.http.MediaType;
import sg.edu.ntu.taskflow_api.model.Task;

@RestController
@RequestMapping("api/tasks")
public class TaskController {
    private final TaskService taskService;
    private final TaskSummaryService taskSummaryService;

    // Constructor for TaskController, injects TaskService dependency
    public TaskController(TaskService taskService, TaskSummaryService taskSummaryService) {
        this.taskService = taskService;
        this.taskSummaryService = taskSummaryService;
    }

    // Get /api/tasks
    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.findAllTasks();
    }

    // Get /api/tasks/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        Task task = taskService.findTaskById(id);
        if (task == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(task);
    }

    // Post /api/tasks
    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        Task createdTask = taskService.createTask(task);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTask);
    }

    // Put /api/tasks/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task task) {
        Task updatedTask = taskService.updateTask(id, task);
        if (updatedTask == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedTask);
    }

    // Delete /api/tasks/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        boolean deleted = taskService.deleteTask(id);
        if (!deleted) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }

    // Put /api/tasks/{id}/complete
    @PutMapping("/{id}/complete")
    public ResponseEntity<Task> completeTask(@PathVariable Long id) {
        Task completedTask = taskService.markTaskAsCompleted(id);
        if (completedTask == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(completedTask);
    }

    // Get /api/tasks/summary
    @GetMapping(value = "/summary", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> getTaskSummary() {

        String summary = taskSummaryService.summarizeTasks();

        return ResponseEntity.ok(summary);
    }
}
