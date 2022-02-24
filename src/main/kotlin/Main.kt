import com.practice.classes.*

fun main() {
  val employees = mutableListOf<Employee>()
  var salarytype = -1
  val quit = 0
  var id = 1
  while (salarytype != quit) {
    var employee = Employee()
    println("Please enter salary type:\n(1) monthly\n(2) hourly\n(3) commission\n(0) Quit")
    salarytype = readLine()!!.toInt()
    when (salarytype) {
      1 -> employee = SalaryEmployee(id, "", 0)
      2 -> employee = HourlyEmployee(id, "", 0, 0)
      3 -> employee = CommissionEmployee(id, "", 0, 0)
      0 -> println("Quit entered.")
      else -> {
        println("Incorrect selection.")
        salarytype = -1
      }
    }
    if (salarytype > 0) {
      employee.askName()
      employee.askSalary()
      employees += employee
      id++
    }
  }
  var payroll = PayrollSystem()
  payroll.calculatePayroll(employees)
}








