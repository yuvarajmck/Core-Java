public class Employee {

        private String name;
        private String email;
        private String Mobile;
        private String Department;
        private Double Salary;
        private String joiningDate;
        public void Employee(String name, String email, String Mobile, Double Salary, String Department, String Joining_Date){
            this.name=name;
            this.Mobile=Mobile;
            this.email=email;
            this.Salary=Salary;
            this.Department=Department;
            this.joiningDate=Joining_Date;

        }
        public String getName(){
            return name;
        }
        public String getMobileNumber(){
            return Mobile;
        }

        public String getDepartment(){
            return Department;
        }

        public Double getSalary(){
            return Salary;
        }
        public String getJoiningDate(){
            return joiningDate;
        }
        public void setName(String name){
            this.name=name;
        }
        public void setEmail(String email){
            this.email=email;
        }
        public void setDepartment(String Department){
            this.Department=Department;
        }
        public void setMobile(String mobile) {
            Mobile = mobile;
        }

        public void setSalary(Double Salary){
            this.Salary=Salary;
        }

        public void setJoiningDate(String joiningDate) {
            this.joiningDate = joiningDate;
        }



}
