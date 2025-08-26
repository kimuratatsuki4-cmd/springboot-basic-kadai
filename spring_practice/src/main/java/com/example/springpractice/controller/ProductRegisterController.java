package com.example.springpractice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.springpractice.form.ProductRegisterForm;

@Controller
public class ProductRegisterController {
    @GetMapping("/register")
    public String register(Model model) {

        // ビューにフォームクラスのインスタンスを渡す
        model.addAttribute("productRegisterForm", new ProductRegisterForm());

        return "productRegisterView";
    }

    @PostMapping("/register")
    public String registerProduct(Model model,
            @Validated ProductRegisterForm form, BindingResult result) {

        // バリデーションエラーがあったら終了
        if (result.hasErrors()) {
            return "productRegisterView";
        }

        // バリデーションに問題がなければ完了メッセージをビューに渡す
        model.addAttribute("message", "登録が完了しました。");
        return "productRegisterView";
    }
}