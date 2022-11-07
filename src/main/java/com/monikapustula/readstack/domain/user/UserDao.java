package com.monikapustula.readstack.domain.user;

import com.monikapustula.readstack.domain.common.BaseDao;

import java.sql.*;

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
            user.setId(generatedKeys.getInt(1));

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
}
