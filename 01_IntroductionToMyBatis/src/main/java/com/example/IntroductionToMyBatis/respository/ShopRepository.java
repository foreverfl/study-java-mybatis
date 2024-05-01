package com.example.IntroductionToMyBatis.respository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.IntroductionToMyBatis.model.Shop;
import com.example.IntroductionToMyBatis.util.DatabaseUtil;

@Repository
public class ShopRepository {

    @Autowired
    private DatabaseUtil databaseUtil;

    public void ensureTableExists() throws SQLException {
        if (!checkIfTableExists()) {
            createTable();
        }
    }

    private boolean checkIfTableExists() throws SQLException {
        String checkTable = "SELECT to_regclass('public.SHOP')"; // 테이블 이름의 오브젝트 식별자(object identifier, OID)를 반환
        try (Connection connection = databaseUtil.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(checkTable);
                ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                return resultSet.getString(1) != null;
            }
        }
        return false;
    }

    public void createTable() throws SQLException {
        String sqlCreate = "CREATE TABLE IF NOT EXISTS SHOP (" +
                "SHOP_NO SERIAL PRIMARY KEY," +
                "SHOP_NAME VARCHAR(255)," +
                "SHOP_LOCATION VARCHAR(255))";

        try (Connection connection = databaseUtil.getConnection();
                Statement statement = connection.createStatement()) {
            statement.execute(sqlCreate);
        }
    }

    public void insertData(Shop shop) throws SQLException {
        String sqlInsert = "INSERT INTO SHOP (SHOP_NAME, SHOP_LOCATION) VALUES (?, ?)";

        try (Connection connection = databaseUtil.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sqlInsert)) {
            preparedStatement.setString(1, shop.getShopName());
            preparedStatement.setString(2, shop.getShopLocation());
            preparedStatement.executeUpdate();
        }
    }

    public List<Shop> readAllData() throws SQLException {
        List<Shop> shops = new ArrayList<>();
        String sqlSelectAll = "SELECT * FROM SHOP";

        try (Connection connection = databaseUtil.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sqlSelectAll);
                ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Shop shop = new Shop();
                shop.setShopNo(resultSet.getInt("SHOP_NO"));
                shop.setShopName(resultSet.getString("SHOP_NAME"));
                shop.setShopLocation(resultSet.getString("SHOP_LOCATION"));
                shops.add(shop);
            }
        }
        return shops;
    }

    public Shop readData(Map<String, Object> parameters) throws SQLException {
        String sqlSelect = "SELECT * FROM SHOP WHERE SHOP_NO = ?";
        Shop shop = null;

        try (Connection connection = databaseUtil.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sqlSelect)) {
            preparedStatement.setInt(1, (Integer) parameters.get("shopNo"));

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    shop = new Shop();
                    shop.setShopNo(resultSet.getInt("SHOP_NO"));
                    shop.setShopName(resultSet.getString("SHOP_NAME"));
                    shop.setShopLocation(resultSet.getString("SHOP_LOCATION"));
                }
            }
        }
        return shop;
    }
}