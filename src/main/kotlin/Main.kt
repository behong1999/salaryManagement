import com.practice.classes.*
import java.io.File
import java.lang.NumberFormatException

fun main() {
  var employees = mutableListOf<Employee>()
  val menu = "(1) Add employee to employees\n(2) Write employees to file\n(3) Read employees from file\n" + "(4) Print payroll\n(0) Quit\nPlease select one:"
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
              1 -> employee = SalaryEmployee(id, "", "M", 0)
              2 -> employee = HourlyEmployee(id, "", 0, "H", 0)
              3 -> employee = CommissionEmployee(id, "", 0, "C", 0)
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
              employees.add(employee) // Add to the list
              id++ // ID Increment
            }
          }
        }
        2 -> {
          val f = File("employee.csv")

          if (f.exists()) {
            f.deleteRecursively()
          }

          with(employees) {
            var lst = mutableListOf<String>()
            forEach {
              lst.add(it.id.toString())
              lst.add(it.name)
              lst.add(it.type)
              when (it.type) {
                "M" -> {
                  lst.add(it.monthly_salary.toString())
                }
                "H" -> {
                  lst.add(it.hours_worked.toString())
                  lst.add(it.hour_rate.toString())
                }
                "C" -> {
                  lst.add(it.monthly_salary.toString())
                  lst.add(it.commission.toString())
                }
                else -> {
                  lst.add(it.calculateSalary().toString())
                }
              }
              var line = myJoin(lst, ',')
              f.appendText(line + "\n")
              lst = mutableListOf()
            }
          }
          println("${employees.count()} employee(s) added to employee.csv")
        }
        3 -> {
          employees = mutableListOf()
          val f = File("employee.csv")
          f.forEachLine {
            var ls = mySplit(it, ',')
            when (ls[2]) {
              "M" -> {
                employees.add(SalaryEmployee(ls[0].toInt(), ls[1], "M", ls[3].toInt()))
              }
              "H" -> {
                employees.add(HourlyEmployee(ls[0].toInt(), ls[1], ls[3].toInt(), "H", ls[4].toInt()))
              }
              else -> {
                employees.add(CommissionEmployee(ls[0].toInt(), ls[1], ls[3].toInt(), "C", ls[4].toInt()))
              }
            }
          }
          println("${employees.count()} employee(s) read from employee.csv")
          id = employees.count() + 1
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








