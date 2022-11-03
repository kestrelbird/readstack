package com.monikapustula.readstack.domain.discovery;

import com.monikapustula.readstack.config.DataSourceProvider;
import com.monikapustula.readstack.domain.category.Category;

import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DiscoveryDao {
    private final DataSource dataSource;

    public DiscoveryDao() {
        try {
            this.dataSource = DataSourceProvider.getDataSource();
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Discovery> findAllDiscoveries() {
        String sql = """
                SELECT
                 id, title, url, description, date_added, category_id
                FROM
                 discovery""";
        try(Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            List<Discovery> allDiscoveries = new ArrayList<>();
            while (resultSet.next()) {
                Discovery discovery = mapRow(resultSet);
                allDiscoveries.add(discovery);
            }
            return allDiscoveries;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Discovery> findByCategory(int categoryId) {
        String sql = """
                SELECT
                id, title, url, description, date_added, category_id
                FROM
                discovery
                WHERE
                category_id = ?""";
        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, categoryId);
            ResultSet resultSet = statement.executeQuery();
            List<Discovery> discoveries = new ArrayList<>();
            while (resultSet.next()) {
                Discovery discovery = mapRow(resultSet);
                discoveries.add(discovery);
            }
            return discoveries;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Discovery mapRow(ResultSet resultSet) throws SQLException {
        Integer id = resultSet.getInt("id");
        String title = resultSet.getString("title");
        String url = resultSet.getString("url");
        String description = resultSet.getString("description");
        LocalDateTime datetime = resultSet.getTimestamp("date_added").toLocalDateTime();
        Integer categoryId = resultSet.getInt("category_id");
        return new Discovery(id, title, url, description, datetime, categoryId);
    }

}
