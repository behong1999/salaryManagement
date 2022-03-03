package com.practice.classes

open class Employee(var id: Int = 1, var name: String = "", open var type: String = "") {

  open val monthly_salary: Int = 0
  open val hours_worked: Int = 0
  open val hour_rate: Int = 0
  open val commission: Int = 0

  open fun print() {
    println("Id: $id Name: $name")
  }

  fun askName() {
    print("Please enter employee name:")
    this.name = readLine()!!.toString()
  }

  open fun askSalary() {
  }

  open fun calculateSalary(): Int {
    return 0
  }
}