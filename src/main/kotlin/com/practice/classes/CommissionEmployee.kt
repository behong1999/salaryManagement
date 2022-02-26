package com.practice.classes

class CommissionEmployee(id: Int, name: String, monthly_salary: Int, var commission: Int) : SalaryEmployee(id, name, monthly_salary) {

  override fun askSalary() {
    super.askSalary() // Call askSalary() of com.practice.classes.SalaryEmployee
    print("Please enter commission:")
    commission = readLine()!!.toInt()
  }

  override fun calculateSalary(): Int {
    return monthly_salary + commission
  }
}