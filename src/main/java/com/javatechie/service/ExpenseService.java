package com.javatechie.service;

import com.javatechie.dto.Expense;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExpenseService {

    private List<Expense> expenseList = new ArrayList<>();
    private long currentId = 1;

    // Get all expenses
    public List<Expense> getAllExpenses() {
        return expenseList;
    }

    // Get a specific expense by id
    public Expense getExpenseById(Long id) {
        return expenseList.stream()
                .filter(expense -> expense.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    // Save or update an expense
    public void saveExpense(Expense expense) {
        if (expense.getId() == null) {
            // Add new expense with new id
            expense.setId(Long.valueOf(currentId++));
            expenseList.add(expense);
        } else {
            // Update existing expense if id is not null
            Expense existingExpense = getExpenseById(expense.getId());
            if (existingExpense != null) {
                // Update fields of the existing expense
                existingExpense.setDescription(expense.getDescription());
                existingExpense.setAmount(expense.getAmount());
                existingExpense.setDate(expense.getDate());
                existingExpense.setCategory(expense.getCategory());
            }
        }
    }

    // Delete an expense by id
    public void deleteExpense(Long id) {
        expenseList.removeIf(expense -> expense.getId().equals(id));
    }
}
