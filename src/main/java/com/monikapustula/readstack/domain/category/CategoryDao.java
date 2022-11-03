package com.monikapustula.readstack.domain.category;

import com.monikapustula.readstack.config.DataSourceProvider;
import com.monikapustula.readstack.domain.api.CategoryFullInfo;

import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CategoryDao {
    private final DataSource dataSource;

    public CategoryDao() {
        try {
            this.dataSource = DataSourceProvider.getDataSource();
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Category> findAllCategories() {
        final String sql = """
                SELECT
                id, category_name, category_description
                FROM
                category""";
        try(Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            List<Category> categories = new ArrayList<>();
            while (resultSet.next()) {
                Category category = mapRow(resultSet);
                categories.add(category);
            }
            return categories;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<Category> findById(Integer id) {
        final String sql = """
                SELECT
                id, category_name, category_description
                FROM
                category
                WHERE
                id = ?""";
        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Category category = mapRow(resultSet);
                return Optional.of(category);
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Category mapRow(ResultSet resultSet) throws SQLException {
        Integer id = resultSet.getInt("id");
        String name = resultSet.getString("category_name");
        String description = resultSet.getString("category_description");
        return new Category(id, name, description);
    }
}
