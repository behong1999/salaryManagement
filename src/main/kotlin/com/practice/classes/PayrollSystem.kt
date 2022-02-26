package com.practice.classes

class PayrollSystem {

  fun calculatePayroll(employees: MutableList<Employee>) {
    for (employee in employees) {
      println("Employee Payroll\n" +
          "================\n" +
          "Payroll for: ${employee.id} - ${employee.name}\n" +
          "- Check amount: ${employee.calculateSalary()}\n")
    }
  }
}