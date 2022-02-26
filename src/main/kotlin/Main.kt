import com.practice.classes.*
import java.io.File
import java.lang.NumberFormatException

fun main() {
  val employees = mutableListOf<Employee>()
  val menu = "(1) Add employee to employees\n(2) Write employees to file\n(3) Read employees from file\n" +
      "(4) Print payroll\n(0) Quit\nPlease select one:"
  var opt = -1
  val quit = 0
  var id = 1

  while (opt !== quit) {
    print(menu)
    try {
      opt = readLine()!!.toInt()
      var salaryType = -1

      when (opt) {
        1 -> {
          while (salaryType != quit) {
            var employee = Employee()
            println("Please enter salary type:\n(1) monthly\n(2) hourly\n(3) commission\n(0) Quit")
            salaryType = readLine()!!.toInt()
            when (salaryType) {
              1 -> employee = SalaryEmployee(id, "", 0)
              2 -> employee = HourlyEmployee(id, "", 0, 0)
              3 -> employee = CommissionEmployee(id, "", 0, 0)
              0 -> {
                println("Quit entered.")
              }
              else -> {
                println("Incorrect selection.")
                salaryType = -1
              }
            }
            if (salaryType > 0) {
              employee.askName()
              employee.askSalary()
              employees += employee // Add to the list
              id++ // ID Increment
            }
          }
        }
        2 -> {
          val f = File("employee.csv")

          if (f.exists()) {
            f.deleteRecursively()
          }

          employees.forEach {
            val tempList: MutableList<String>
            when (it) {
              is HourlyEmployee -> {
                 tempList = mutableListOf(it.id.toString(), it.name, "H", it.hour_rate.toString(), it.hours_worked.toString())
                f.appendText("${myJoin(tempList, ',')}\n")
              }
              is CommissionEmployee -> {
                 tempList = mutableListOf(it.id.toString(), it.name, "C", it.monthly_salary.toString(), it.commission.toString())
                f.appendText("${myJoin(tempList, ',')}\n")
              }
              else -> {
                 tempList = mutableListOf(it.id.toString(), it.name, "M", it.calculateSalary().toString())
                f.appendText("${myJoin(tempList, ',')}\n")
              }
            }
          }
          println("${employees.count()} employee(s) added to employee.csv")
        }
        3 -> {
          val f = File("employee.csv")
          var ls: List<String>
          employees.clear()
          f.forEachLine {
            ls = mySplit(it, ',')
            when (ls[2]) {
              "M" -> {
                employees.add(SalaryEmployee(ls[0].toInt(), ls[1], ls[2].toInt()))
              }
              "H" -> {
                employees.add(HourlyEmployee(ls[0].toInt(), ls[1], ls[3].toInt(), ls[4].toInt()))
              }
              else -> {
                employees.add(CommissionEmployee(ls[0].toInt(), ls[1], ls[3].toInt(), ls[4].toInt()))
              }
            }
          }
          println("${employees.count()} employee(s) read from employee.csv")
        }
        4 -> {
          val payroll = PayrollSystem()
          payroll.calculatePayroll(employees)
        }
        0 -> {
          println("Service shutting down, thank you.")
        }
      }
    } catch (_: NumberFormatException) {
    }
  }
}

fun myJoin(str: List<String>, sep: Char): String {
  var result = ""
  str.dropLast(1).forEach { result += it + sep }
  result += str.last()
  return result
}

fun mySplit(str: String, c: Char): List<String> {
  return str.split(c).toList()
}








