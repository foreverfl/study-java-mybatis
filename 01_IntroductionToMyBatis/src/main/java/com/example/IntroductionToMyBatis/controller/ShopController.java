package com.example.IntroductionToMyBatis.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.IntroductionToMyBatis.model.Shop;
import com.example.IntroductionToMyBatis.service.ShopService;

@Controller
public class ShopController {

    @Autowired
    private ShopService shopService;

    // 모든 상점 정보를 대략적으로 보여주는 메서드
    @GetMapping("/")
    public String listShops(Model model) throws SQLException {
        List<Shop> shops = shopService.getAllShops();
        model.addAttribute("shops", shops);
        model.addAttribute("newShop", new Shop());
        return "shopList"; // Thymeleaf template for listing shops
    }

    @PostMapping("/addShop")
    public String addShop(Shop shop, RedirectAttributes redirectAttributes) {
        System.out.println("Adding new shop: " + shop.getShopName() + ", " + shop.getShopLocation());
        try {
            shopService.insertShop(shop);
            redirectAttributes.addFlashAttribute("successMessage", "Shop added successfully!");
        } catch (SQLException e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error adding shop: " + e.getMessage());
        }
        return "redirect:/";
    }

    // 특정 상점의 상세 정보를 보여주는 메서드
    @GetMapping("/shop")
    public String viewShop(@RequestParam("shopNo") int shopNo, Model model) throws SQLException {
        Shop shop = shopService.getShop(shopNo);
        model.addAttribute("shop", shop);
        return "shopDetail"; // Thymeleaf template for detailed view
    }
}