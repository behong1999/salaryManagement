package com.practice.classes

open class SalaryEmployee(id: Int, name: String, var monthly_salary: Int) : Employee(id, name) {

  override fun askSalary() {
    print("Please enter monthly salary:")
    this.monthly_salary = readLine()!!.toInt()
  }

  override fun calculateSalary(): Int {
    return this.monthly_salary
  }

  override fun print() {
    println("Id: $id Name: $name Salary: ${calculateSalary()}")
  }
}