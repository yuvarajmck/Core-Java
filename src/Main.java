import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
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
                .forEach(System.out::println);

        System.out.println("!---------------------------!");

        //Query : 2
        System.out.println("\nEmployee whose mobile numbers are not upto date : \n");
        employees.stream()
                .filter(x -> x.getMobileNumber().equals(" "))
                .forEach(System.out::println);


        System.out.println("!---------------------------!");

        //Query : 3
        System.out.println("\nQA Department Engineers who get more than 10000 as salary :  \n");
        employees.stream()
                .filter(x -> x.getDepartment().equalsIgnoreCase("QA"))
                .filter(x -> Double.parseDouble(x.getSalary().toString())>10000.00)
                .forEach(System.out::println);

        System.out.println("!---------------------------!");


        //Query : 4
        System.out.println("\nEmployees from It : \n");
        employees.stream()
                .filter(x -> x.getDepartment().equalsIgnoreCase("IT"))
                .forEach(System.out::println);


        System.out.println("!---------------------------!");

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


        System.out.println("!---------------------------!");

        //Query : 6
        System.out.println("\nList of employees joined between 01-Feb-2021 and 01-Apr-2021: ");
        employees.stream()
                .filter(x -> x.getJoiningDate().compareTo(String.valueOf(LocalDate.of(2021, 2, 1))) >= 0)
                .filter(x -> x.getJoiningDate().compareTo(String.valueOf(LocalDate.of(2021, 4, 1))) <= 0)
                .forEach(System.out::println);


        System.out.println("!---------------------------!");

        //Query : 7
        System.out.println("\nLowest salary of employee : \n");
        employees.stream()
                .map(x -> Double.parseDouble(x.getSalary().toString()))
                .sorted()
                .findFirst()
                .ifPresent(System.out::println);


        System.out.println("!---------------------------!");


        //Query : 8
        System.out.println("\nRecently joined employees : ");
        employees.stream()
                .sorted(Comparator.comparing(Employee::getJoiningDate).reversed())
                .limit(3)
                .forEach(System.out::println);

        System.out.println("!---------------------------!");

        //Query : 9
        System.out.println("\n Total sum of all salary joined in Feb 2021 ");
        double sum = employees.stream()
                .filter(x -> x.getJoiningDate().compareTo(String.valueOf(LocalDate.of(2021, 2, 1))) >= 0)
                .filter(x -> x.getJoiningDate().compareTo(String.valueOf(LocalDate.of(2021, 3, 1))) <= 0)
                .map(x -> x.getSalary())
                .reduce(0.0, (Double a, Double b)->a+b);
        System.out.println(sum);


        System.out.println("!---------------------------!");


        //Query : 10
        System.out.println("\n Total sum of all salary joined on 14-Mar-2021 ");
        double sum1 = employees.stream()
                .filter(x -> x.getJoiningDate().compareTo(String.valueOf(LocalDate.of(2021, 3, 13))) >= 0)
                .filter(x -> x.getJoiningDate().compareTo(String.valueOf(LocalDate.of(2021, 3, 15))) <= 0)
                .map(x -> x.getSalary())
                .reduce(0.0, (Double a, Double b)->a+b);
        System.out.println(sum1);


        System.out.println("!---------------------------!");


        //Query : 11
        System.out.println("\n Data map with  Salary and employees count");
        Map map = new HashMap();
        List<Double> salList = employees.stream()
                .map(x -> x.getSalary())
                .toList();


        List<Double> list = new ArrayList<>();

        for (double sal : salList){
            if(!list.contains(sal)){
                map.put(sal,salList.stream().filter(x->x==sal).count());
                list.add(sal);
            }

        }
        System.out.println(map);



        System.out.println("!---------------------------!");


        //Query : 12
        System.out.println("\n Data map with employee records grouped by department");

        List qaList = employees
                .stream()
                .map(x -> x.getDepartment())
                .filter(x->x.equalsIgnoreCase("qa"))
                .toList();
        List itList = employees
                .stream()
                .map(x -> x.getDepartment())
                .filter(x->x.equalsIgnoreCase("it"))
                .toList();
        List devList = employees
                .stream()
                .map(x -> x.getDepartment())
                .filter(x->x.equalsIgnoreCase("dev"))
                .toList();
        List managerList = employees
                .stream()
                .map(x -> x.getDepartment())
                .filter(x->x.equalsIgnoreCase("manager"))
                .toList();

        long qaCount = qaList.stream().count();

        long itCount = itList.stream().count();

        long devCount = devList.stream().count();

        long managerCount = managerList.stream().count();



        Map mapDept = new HashMap();

        mapDept.put("Dev", devCount);
        mapDept.put("It",itCount);
        mapDept.put("Manager", managerCount);
        mapDept.put("QA", qaCount);

        System.out.println(mapDept);

        System.out.println("!---------------------------!");

        //Query : 13
        System.out.println("\n\nData map with department and their salary");
        Map map2 = new HashMap();

        List qaSalList = employees
                .stream()
                .filter(x->x.getDepartment().equalsIgnoreCase("qa"))
                .map(x->x.getSalary())
                .toList();
        List itSalList = employees
                .stream()
                .filter(x->x.getDepartment().equalsIgnoreCase("it"))
                .map(x->x.getSalary())
                .toList();
        List devSalList = employees
                .stream()
                .filter(x->x.getDepartment().equalsIgnoreCase("dev"))
                .map(x->x.getSalary())
                .toList();
        List managerSalList = employees
                .stream()
                .filter(x->x.getDepartment().equalsIgnoreCase("manager"))
                .map(x->x.getSalary())
                .toList();

        map2.put("Dev", devSalList);
        map2.put("It",itSalList);
        map2.put("Manager", managerSalList);
        map2.put("QA", qaSalList);

        System.out.println(map2);

        System.out.println("!---------------------------!");


        //Query : 14
        System.out.println("\nMost highest paid by category");
        Map<String,List<Double>> map3 = new HashMap<>();
        map3 = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment,Collectors.mapping(Employee::getSalary,Collectors.toList())));
        Iterator<Map.Entry<String , List<Double>>> mapIterator = map3.entrySet().iterator();

        sum=0;
        String dept=" ";
        while (mapIterator.hasNext()){
            Map.Entry <String,List<Double>> entry = mapIterator.next();
            List<Double> temp = entry.getValue();

            double cur = temp.stream()
                    .mapToDouble(Employee -> Employee.doubleValue())
                    .sum();
            if(sum<cur){
                sum=cur;
                dept = entry.getKey();
            }



        }
        System.out.println("Department : " + dept +" \n"+ "Sum : "+  sum);





    }





}

