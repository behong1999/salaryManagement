package com.practice.classes

class CommissionEmployee(id: Int, name: String, monthly_salary: Int, override var type: String = "C", override var commission: Int) : SalaryEmployee(id, name, type, monthly_salary) {

  override fun askSalary() {
    super.askSalary() // Call askSalary() of com.practice.classes.SalaryEmployee
    print("Please enter commission:")
    commission = readLine()!!.toInt()
  }

  override fun calculateSalary(): Int {
    return monthly_salary + commission
  }
}