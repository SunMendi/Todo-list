package com.example.todo.controller;

import com.example.todo.entity.Task;
import com.example.todo.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("tasks", taskService.findAllTasks());
        model.addAttribute("newTask", new Task());
        return "index";
    }

    @PostMapping("/tasks")
    public String addTask(@ModelAttribute Task newTask) {
        taskService.saveTask(newTask);
        return "redirect:/";
    }

    @PostMapping("/tasks/complete/{id}")
    public String completeTask(@PathVariable Long id) {
        taskService.markTaskAsCompleted(id);
        return "redirect:/";
    }

    @PostMapping("/tasks/delete/{id}")
    public String deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return "redirect:/";
    }
}
