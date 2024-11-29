package com.javatechie.controller;

import com.javatechie.dto.Expense;
import com.javatechie.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    // Display the main page
    @GetMapping
    public String index(Model model) {
        model.addAttribute("expenses", expenseService.getAllExpenses());
        model.addAttribute("expense", new Expense()); // For adding a new expense
        return "index";
    }

    // Add or update an expense
    @PostMapping("/save")
    public String saveExpense(@ModelAttribute Expense expense) {
        expenseService.saveExpense(expense);
        return "redirect:/";
    }

    // Display form to edit an existing expense
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        Expense expense = expenseService.getExpenseById(id);
        if (expense != null) {
            model.addAttribute("expense", expense);  // Populate the model with the existing expense
        } else {
            model.addAttribute("expense", new Expense());  // Fallback to a new expense if not found
        }
        return "index";  // Return the same index page with the form populated for editing
    }

    // Delete an expense
    @GetMapping("/delete/{id}")
    public String deleteExpense(@PathVariable Long id) {
        expenseService.deleteExpense(id);
        return "redirect:/";
    }
}
