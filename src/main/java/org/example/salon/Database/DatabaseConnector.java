package org.example.salon.Database;

import org.example.salon.Database.Model.Appointment;
import org.example.salon.Database.Model.Service;
import org.example.salon.Tableview;
import org.example.salon.Database.Model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseConnector {

    private static final String URL = "jdbc:postgresql://viaduct.proxy.rlwy.net:12244/railway";
    private static final String USER = "postgres";
    private static final String PASSWORD = "FQrpiREqkJrmYGtsvWCSYpcAjLXUpmLK";

    public static Connection getConnection() {
        try {
            // Registering the JDBC driver
            Class.forName("org.postgresql.Driver");

            // Establishing the connection
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            System.err.println("PostgreSQL JDBC Driver not found.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Connection failure.");
            e.printStackTrace();
        }
        return null;
    }

    public static void createSchema() {
        String createTableSQL = "CREATE TABLE USERS (user_id SERIAL PRIMARY KEY, username VARCHAR(50) NOT NULL UNIQUE, password VARCHAR(255) NOT NULL, role VARCHAR(10) NOT NULL CHECK (role IN ('customer', 'employee')), full_name VARCHAR(100) NOT NULL, email VARCHAR(100) NOT NULL UNIQUE, phone_number VARCHAR(20), address TEXT, created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP)";

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(createTableSQL);
            System.out.println("Table 'users' created or already exists.");
        } catch (SQLException e) {
            System.err.println("Error creating schema.");
            e.printStackTrace();
        }
    }

    public static void insertUser(User u) {
        String insertUserSQL = "INSERT INTO USERS (username, password, role, full_name, email, phone_number) VALUES (?,?,?,?,?,?)";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertUserSQL)) {

            preparedStatement.setString(1, u.getUserName());
            preparedStatement.setString(2, u.getPassword());
            preparedStatement.setString(3, u.getRole());
            preparedStatement.setString(4, u.getFullName());
            preparedStatement.setString(5, u.getEmail());
            preparedStatement.setString(6, u.getPhone());
            preparedStatement.executeUpdate();
            System.out.println("user inserted: " + u.getUserName());
        } catch (SQLException e) {
            System.err.println("Error inserting user.");
            e.printStackTrace();
        }
    }

    public static boolean validateUser(String username, String password) {
        String selectUserSQL = "SELECT * FROM users WHERE username = ? AND password = ?";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectUserSQL)) {

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next();
            }
        } catch (SQLException e) {
            System.err.println("Error validating user.");
            e.printStackTrace();
            return false;
        }
    }

    
    public static void insertService(Service s) {
        String insertServiceSQL = "INSERT INTO SERVICES (service_name, price, category, duration) VALUES (?,?,?,?)";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertServiceSQL)) {

            preparedStatement.setString(1, s.getName());
            preparedStatement.setDouble(2, s.getPrice());
            preparedStatement.setString(3, s.getCategory());
            preparedStatement.setInt(4, s.getDuration());
            preparedStatement.executeUpdate();
            System.out.println("Service inserted: " + s.getName());
        } catch (SQLException e) {
            System.err.println("Error inserting service.");
            e.printStackTrace();
        }

    }

    public static User getUserObjectbyUsername(String username) {
        String sql = "SELECT * FROM Users WHERE username ILIKE ?";
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int userId = rs.getInt("user_id");
                    String fullName = rs.getString("full_name");
                    String email = rs.getString("email");
                    String phoneNumber = rs.getString("phone_number");
                    String role = rs.getString("role");
                    int points = rs.getInt("points");
                    
                    return new User(userId, username, fullName, email, phoneNumber, role, points);
                } else {
                    System.out.println("User not found");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public static void addAppointment(Appointment a) {
        String insertAppointmentSQL = "INSERT INTO APPOINTMENTS (customer_id, stylist_id, service_id, appointment_date, appointment_time, status) VALUES (?,?,?,?,?,?)";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertAppointmentSQL)) {

            preparedStatement.setInt(1, a.getCustomer().getUserID());
            preparedStatement.setInt(2, a.getStylist().getUserID());
            preparedStatement.setInt(3, a.getService().getServiceID());
            preparedStatement.setDate(4, a.getDate());
            preparedStatement.setTime(5, a.getTime());
            preparedStatement.setString(6, a.getStatus());
            preparedStatement.executeUpdate();
            System.out.println("Appointment inserted.");
        } catch (SQLException e) {
            System.err.println("Error inserting appointment.");
            e.printStackTrace();
        }
    }


    public static ArrayList<Service> getServicesByCategory(String category) {
        ArrayList<Service> services = new ArrayList<>();
        String query = "SELECT * FROM Services WHERE category = ?";

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

             stmt.setString(1, category);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int serviceID = rs.getInt("service_id");
                    String service_name = rs.getString("service_name");
                    Double price = rs.getDouble("price");
                    Service s = new Service(serviceID, service_name, price);
                    services.add(s);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return services;
    }


    public static ArrayList<User> getEmployees() {
        ArrayList<User> employees = new ArrayList<>();
        String query = "SELECT * FROM Users WHERE role = 'employee'";

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int userId = rs.getInt("user_id");
                String username = rs.getString("username");
                String role = rs.getString("role");
                String fullName = rs.getString("full_name");
                String email = rs.getString("email");
                String phoneNumber = rs.getString("phone_number");

                User user = new User(userId, username, fullName, email, phoneNumber, role);
                employees.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return employees;
    }

    public static boolean changePassword(String username, String currentPassword, String newPassword) throws SQLException {
        String query = "UPDATE Users SET password = ? WHERE username = ? AND password = ?";

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, newPassword);
            stmt.setString(2, username);
            stmt.setString(3, currentPassword);

            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;  // Return true if the password was updated

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;  // Return false if the password was not updated
    }

    public static List<Tableview> getAppointmentsByStylistId(int stylistId) {
        List<Tableview> appointments = new ArrayList<>();
        String query = "SELECT a.appointment_id, u.full_name, u.phone_number, s.service_name, a.appointment_date " +
                "FROM Appointments a " +
                "JOIN Users u ON a.customer_id = u.user_id " +
                "JOIN Services s ON a.service_id = s.service_id " +
                "WHERE a.stylist_id = ?";

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, stylistId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("appointment_id");
                String customerName = rs.getString("full_name");
                String customerPhone = rs.getString("phone_number");
                String serviceName = rs.getString("service_name");
                Date date = rs.getDate("appointment_date");

                Tableview appointment = new Tableview(id, customerName, customerPhone, serviceName, date);
                appointments.add(appointment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return appointments;
    }

    public static void updateUserPoints(String username, int points) {
        String query = "UPDATE USERS SET points = points + ? WHERE username = ?";

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, points);
            stmt.setString(2, username);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void clearDatabase() {
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {

            // Retrieve all table names
            ResultSet resultSet = statement.executeQuery(
                    "SELECT tablename FROM pg_tables WHERE schemaname = 'public'");

            // Collect all table names
            List<String> tables = new ArrayList<>();
            while (resultSet.next()) {
                tables.add(resultSet.getString("tablename"));
            }

            // Drop each table
            for (String table : tables) {
                statement.execute("DROP TABLE IF EXISTS " + table + " CASCADE");
                System.out.println("Table '" + table + "' deleted if it existed.");
            }
        } catch (SQLException e) {
            System.err.println("Error clearing database.");
            e.printStackTrace();
        }
    }
}