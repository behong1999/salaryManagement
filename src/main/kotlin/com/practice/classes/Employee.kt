package com.practice.classes

open class Employee(var id: Int = 1, var name: String = "") {

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