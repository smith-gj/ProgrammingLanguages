import java.io.*;
import java.util.*;

abstract class Employee {
   String name;

   Employee() {}
   Employee (String nm) { name = nm; }
   abstract double computePay();
   void   display () {}
   void   setHours(double hrs) {}
   void   setSales(double sales) {}
   void   setSalary(double salary) { System.out.println("NO!"); }
}

class WageEmployee extends Employee {
   double rate;
   double hours;

   WageEmployee(String nm) { super(nm); }
   WageEmployee(String nm, double r) { super(nm); rate = r; }
   void setRate(double r)    { rate  = r; }
   void setHours(double hrs) { hours = hrs; }
   double computePay()       { return rate*hours; }
}

class Programmer extends WageEmployee {
   Programmer (String nm, double w) { super(nm, w); }
   void display() {
      System.out.println("Name: "+name+"\tHours: "+hours
              +"\tRate: "+rate);
   }
}

class SalesPerson extends WageEmployee {
   double commission;
   double SalesMade;

   SalesPerson (String nm, double c) {
      super(nm);
      commission = c;
   }

   void setCommission(double comm) { commission = comm; }

   void setSales(double sales) { SalesMade = sales; }

   double computePay() { return commission*SalesMade; }

   void display() {
      System.out.println("Name: "+name+"\tCommission: "+commission
              +"\tSales: "+SalesMade);
   }
}

class Manager extends Employee {
   double monthlysalary;

   Manager () { super(""); }

   Manager(String nm, double w)  { super(nm); monthlysalary = w; }

   void setSalary(double salary) { monthlysalary = salary; }

   double computePay()           { return monthlysalary; }

   void display() {
      System.out.println("Name: "+name+"\tMonthly Salary: "+
              monthlysalary);
   }
}

interface ManagerInterface {
   double managerComputePay();
   void managerDisplay();
}

class SalesManager extends SalesPerson implements ManagerInterface {
   double monthlysalary;

   SalesManager(String nm, double w) {
      super(nm, w);
   }

   public double managerComputePay() {
      return monthlysalary;
   }

   double computePay() {
      System.out.println("SalesManager: " + name + " " +
              (super.computePay() + managerComputePay()));
      return super.computePay() + managerComputePay();
   }

   void setSalary(double s) {
      monthlysalary = s;
   }

   public void managerDisplay() {
      System.out.println("Name: " + name + "\tMonthly Salary: " + monthlysalary);
   }

   void display() {
      super.display();
      managerDisplay();
   }
}

class EmployeeList {

   private List<Employee> employees;

   public EmployeeList() { employees = new ArrayList<>(); }

   public void enqueue(Employee e) { employees.add(e); }

   public Employee find(String nm) {
      for (int i = 0; i < employees.size(); i++) {
         if (employees.get(i).name == nm) {
            return employees.get(i);
         }
      }
      return null;
   }

   public void setHours(String nm, double hours) {
      Employee object = find(nm);
      object.setHours(hours);
   }

   public void setSalary(String nm, double salary) {
      Employee object = find(nm);
      object.setSalary(salary);
   }

   public void setSales (String nm, double sales) {
      Employee object = find(nm);
      object.setSales(sales);
   }

   public double payroll() {
      // initialize a counter to track total pay
      double payroll = 0;
      for (int i = 0; i < employees.size(); i++) {
         payroll += employees.get(i).computePay();
      }
      return payroll;
   }

   public void display() {
      for (int i = 0; i < employees.size(); i++) {
         employees.get(i).display();
      }
   }
}

   public class Wages {
      public static void main(String argv[]) {

         EmployeeList emp = new EmployeeList();
         emp.enqueue(new SalesManager("Gee", 1000));
         emp.enqueue(new SalesManager("Gal", 1000));
         emp.enqueue(new SalesManager("Gem", 1000));
         emp.enqueue(new SalesPerson("John", 0.03));
         emp.enqueue(new SalesPerson("Joan", 0.04));
         emp.enqueue(new SalesPerson("Jack", 0.02));
         emp.enqueue(new Manager("Fred", 10000));
         emp.enqueue(new Manager("Frank", 5000));
         emp.enqueue(new Manager("Florence", 3000));
         emp.enqueue(new Programmer("Linda", 7));
         emp.enqueue(new Programmer("Larry", 5));
         emp.enqueue(new Programmer("Lewis", 3));

         emp.setHours("Linda", 35);
         emp.setHours("Larry", 23);
         emp.setHours("Lewis", 3);
         emp.setSales("John", 12000);
         emp.setSales("Joan", 10000);
         emp.setSales("Jack", 5000);
         emp.setSales("Gee", 4000);
         emp.setSales("Gal", 3000);
         emp.setSales("Gem", 2000);
         emp.setSalary("Gee", 1000);
         emp.setSalary("Gal", 2000);
         emp.setSalary("Gem", 3000);
         emp.display();
         System.out.println("Payroll: "+emp.payroll());

      }
   }
