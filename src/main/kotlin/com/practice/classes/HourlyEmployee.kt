package com.practice.classes

class HourlyEmployee(id: Int, name: String, private var hour_rate: Int, private var hours_worked: Int) : Employee(id, name) {

  override fun askSalary() {
    print("Please enter hours worked:")
    hours_worked = readLine()!!.toInt()
    print("Please enter hour rate:")
    hour_rate = readLine()!!.toInt()
  }

  override fun calculateSalary(): Int {
    return hour_rate * hours_worked
  }
}