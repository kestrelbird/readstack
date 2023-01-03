package com.monikapustula.readstack.domain.discovery;

import com.monikapustula.readstack.domain.common.BaseDao;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DiscoveryDao extends BaseDao {

    public List<Discovery> findAll() {
        String sql = """
                SELECT
                 id, title, url, description, date_added, category_id, user_id
                FROM
                 discovery""";
        try(Connection connection = getConnection();
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
                id, title, url, description, date_added, category_id, user_id
                FROM
                discovery
                WHERE
                category_id = ?""";
        try(Connection connection = getConnection();
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

    public void save(Discovery discovery) {
        final String query = """
                INSERT INTO 
                discovery
                (title, url, description, date_added, category_id, user_id)
                VALUE
                (?,?,?,?,?,?)""";
        try(Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, discovery.getTitle());
            statement.setString(2, discovery.getUrl());
            statement.setString(3, discovery.getDescription());
            statement.setObject(4, discovery.getLocalDateTime());
            statement.setInt(5, discovery.getCategoryId());
            statement.setInt(6, discovery.getUserId());
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                discovery.setId(generatedKeys.getInt(1));
            }
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
        Integer user_id = resultSet.getInt("user_id");
        return new Discovery(id, title, url, description, datetime, categoryId, user_id);
    }

}
