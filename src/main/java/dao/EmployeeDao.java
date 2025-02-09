package dao;

import Model.Employee;
import Model.Exceptions.WrongPassword;
import Model.Exceptions.WrongUsername;
import Model.Room;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.util.List;

public class EmployeeDao {
    public static final String FILE_PATH = "src/main/java/DAO/employees.dat";
    private static final File DATA_FILE = new File(FILE_PATH);
    private final ObservableList<Employee> employees = FXCollections.observableArrayList();
    public ObservableList<Employee> getAll() {
        if(employees.isEmpty()) {
            loadEmployeesFromFile();
        }
        return employees;
    }

    public EmployeeDao() {
        loadEmployeesFromFile();
        //this.view
    }

    private void loadEmployeesFromFile() {
        try(ObjectInputStream reader=new ObjectInputStream(new FileInputStream(DATA_FILE))){
            Employee employee;
            while(true) {
                employee=(Employee) reader.readObject();
                employees.add(employee);
            }

        }
        catch (EOFException ignored) {
        }
        catch (IOException | ClassNotFoundException ex) {
            // log to a file
            System.out.println(ex.getMessage());
        }
    }

    public boolean create(Employee employee) {
        try(FileOutputStream outputStream = new FileOutputStream(DATA_FILE, true)) {
            ObjectOutputStream writer;
            if(DATA_FILE.length()>0){
                writer = new HeaderlessObjectOutputStream(outputStream);
            }
            else{
                writer = new ObjectOutputStream(outputStream);
            }
            writer.writeObject(employee);
            this.employees.add(employee);
            return true;
        } catch(IOException ex) {
            return false;
        }
    }

    public boolean delete(Employee employee) {
        try(ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(DATA_FILE))) {
            for (Employee e: employees) {
                if(!e.equals(employee)) {
                    outputStream.writeObject(e);
                }
            }

            employees.remove(employee);

            return true;
        } catch (IOException ex) {
            return false;
        }
    }

    public boolean deleteAll(List<Employee> employeesToRemove) {

        try (ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(DATA_FILE))) {
            for (Employee e:employees) {
                if (!employeesToRemove.contains(e)) {
                    writer.writeObject(e);
                }
            }
            return true;
        }
        catch (IOException ex) {
            return false;
        }
    }

    public boolean updateAll() {
        try(ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(DATA_FILE))) {
            for(Employee e : employees) {
                outputStream.writeObject(e);
            }

            return true;
        } catch (IOException ex) {
            return false;
        }
    }
    public Employee authLogin(String username, String password) throws WrongPassword, WrongUsername {

        for(Employee e : employees) {
            if(e.getUsername().equals(username)) {
                if(e.getPassword().equals(password)) {
                    return e;

                }
                else throw new WrongPassword("Password is incorrect");
            }

        }
       throw new WrongUsername("Username is wrong");
    }
}
