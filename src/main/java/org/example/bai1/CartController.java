package org.example.ss09_bai1;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;


@Controller
public class CartController {

    // API 1: Thêm sản phẩm vào giỏ hàng
    @PostMapping("/add-to-cart")
    public String addToCart(@RequestParam("productId") String productId, HttpSession session) {

        // 1. Lấy giỏ hàng từ Session thay vì Request
        List<String> cart = (List<String>) session.getAttribute("myCart");

        if (cart == null) {
            cart = new ArrayList<>();
        }

        cart.add(productId);

        // 2. Lưu lại vào Session
        session.setAttribute("myCart", cart);

        // Chuyển hướng sang trang thanh toán
        return "redirect:/checkout";
    }

    // API 2: Hiển thị trang thanh toán
    @GetMapping("/checkout")
    public String viewCheckout(HttpSession session, Model model) {

        // 3. Lấy giỏ hàng từ Session ra để kiểm tra
        List<String> cart = (List<String>) session.getAttribute("myCart");

        if (cart == null || cart.isEmpty()) {
            model.addAttribute("message", "Giỏ hàng của bạn đang trống!");
        } else {
            model.addAttribute("message", "Bạn có " + cart.size() + " sản phẩm.");
        }

        return "checkout-page";
    }
}