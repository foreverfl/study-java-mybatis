package com.example.IntroductionToMyBatis.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.IntroductionToMyBatis.model.Shop;
import com.example.IntroductionToMyBatis.respository.ShopRepository;

@Service
public class ShopService {

    @Autowired
    private ShopRepository shopRepository;

    // 모든 상점을 리스트로 반환하는 메소드
    public List<Shop> getAllShops() throws SQLException {
        shopRepository.ensureTableExists();
        return shopRepository.readAllData();
    }

    public Shop getShop(int shopNo) throws SQLException {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("shopNo", shopNo);
        return shopRepository.readData(parameters);
    }

    public void insertShop(Shop shop) throws SQLException {
        shopRepository.insertData(shop);
    }
}