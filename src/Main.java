import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Stream;



public class Main {

    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            Class.forName("org.mariadb.jdbc.Driver");
            String url = "jdbc:mariadb://localhost:3306/employee";
            String username = "root";
            String password = "root";
            connection = DriverManager.getConnection(url, username, password);
            statement = connection.createStatement();



            String query = "SELECT id, Name, Email, Mobile_no, Department, Salary, JoiningDate FROM employee";
            resultSet = statement.executeQuery(query);


            while (resultSet.next()) {


                int idValue = resultSet.getInt("id");
                String nameValue = resultSet.getString("Name");
                String emailValue = resultSet.getString("Email");
                String phoneValue = resultSet.getString("Mobile_no");
                String department = resultSet.getString("Department");
                String salary = resultSet.getString("Salary");
                String joinDate = resultSet.getString("JoiningDate");


                System.out.println("!---------------------------!");

                System.out.println("ID: " + idValue);
                System.out.println("Name: " + nameValue);
                System.out.println("Email: " + emailValue);
                System.out.println("Phone: " + phoneValue);
                System.out.println("Depoartment: " + department);
                System.out.println("Salary :" + salary);
                System.out.println("Joining Date : " + joinDate);




                System.out.println("!---------------------------!");

                Employee employee = new Employee();
                employee.setName(nameValue);
                employee.setEmail(emailValue);
                employee.setMobile(phoneValue);
                employee.setDepartment(department);
                employee.setSalary(Double.parseDouble(salary));
                employee.setJoiningDate(joinDate);
                employees.add(employee);




            }

            runThreads( employees);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public static void runThreads(List<Employee> employees) {
        System.out.println("\n\n");
        System.out.println("Below are the Queries : \n\n");

        //Query : 1
        System.out.println("Employee name starts with \"D\"\n");


        employees.stream()
                .filter(x -> x.getName().startsWith("D"))
                .map(x -> x.getName()+" ")
                .forEach(System.out::println);

        //Query : 2
        System.out.println("\nEmployee whose mobile numbers are not upto date : \n");
        employees.stream()
                .filter(x -> x.getMobileNumber().equals(" "))
                .map(x -> x.getName()+" ")
                .forEach(System.out::println);

        //Query : 3
        System.out.println("\nQA Department Engineers who get more than 10000 as salary :  \n");
        employees.stream()
                .filter(x -> x.getDepartment().equalsIgnoreCase("QA"))
                .filter(x -> Double.parseDouble(x.getSalary().toString())>10000.00)
                .map(x -> x.getSalary()+" ")
                .forEach(System.out::println);

        //Query : 4
        System.out.println("\nEmployees from It : \n");
        employees.stream()
                .filter(x -> x.getDepartment().equalsIgnoreCase("IT"))
                .map(x -> x.getName()+" ")
                .forEach(System.out::println);

        //Query : 5
        System.out.println("\nDEV team employees salary before increment: \n");
        employees.stream()
                .filter(x -> x.getDepartment().equalsIgnoreCase("DEV"))
                .map(x-> x.getSalary()* 1.0)
                .forEach(System.out::println);

        System.out.println("\nDEV team employees salary after increment: \n");
        employees.stream()
                .filter(x -> x.getDepartment().equalsIgnoreCase("DEV"))
                .map(x -> x.getSalary()+((x.getSalary() * 0.1)))
                .forEach(System.out::println);

        //Query : 6
        System.out.println("\nList of employees joined between 01-Feb-2021 and 01-Apr-2021: ");
        employees.stream()
                .filter(x -> x.getJoiningDate().compareTo(String.valueOf(LocalDate.of(2021, 2, 1))) >= 0)
                .filter(x -> x.getJoiningDate().compareTo(String.valueOf(LocalDate.of(2021, 4, 1))) <= 0)
                .forEach(System.out::println);

        //Query : 7
        System.out.println("\nLowest salary of employee : \n");
        employees.stream()
                .map(x -> Double.parseDouble(x.getSalary().toString()))
                .sorted()
                .findFirst()
                .ifPresent(System.out::println);



        //Query : 8


    }


}

