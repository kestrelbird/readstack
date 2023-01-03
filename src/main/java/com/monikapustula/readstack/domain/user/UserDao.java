package com.monikapustula.readstack.domain.user;

import com.monikapustula.readstack.domain.common.BaseDao;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.Optional;

public class UserDao extends BaseDao {

    public void save(User user) {
        saveUser(user);
        saveUserRole(user);
    }

    private void saveUser(User user) {
        final String sql = """
                INSERT INTO
                user
                (username, email, password, registration_date)
                VALUES
                (?, ?, ?, ?)""";
        try(Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());
            statement.setTimestamp(4, Timestamp.valueOf(user.getRegistrationDate()));

            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if(generatedKeys.next()) {
                user.setId(generatedKeys.getInt(1));
            }
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void saveUserRole(User user) {
        final String sql = """
                INSERT INTO
                user_role
                (username)
                VALUES
                (?)""";
        try(Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, user.getUsername());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<User> findByUsername(String userName) {
        final String query = """
                SELECT
                id, username, email, password, registration_date
                FROM
                user
                WHERE
                username = ?""";
        try(Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, userName);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(mapRow(resultSet));
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private User mapRow(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String username = resultSet.getString("username");
        String email = resultSet.getString("email");
        String password = resultSet.getString("password");
        LocalDateTime registrationDate = resultSet.getObject("registration_date", LocalDateTime.class);
        return new User(id, username, email, password, registrationDate);
    }

}
