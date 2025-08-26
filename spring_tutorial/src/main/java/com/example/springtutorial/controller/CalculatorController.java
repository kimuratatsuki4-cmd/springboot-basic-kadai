package com.example.springtutorial.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.springtutorial.form.CalculatorForm;

@Controller
public class CalculatorController {

    @GetMapping("/calculator")
    public String showForm(Model model) {
        model.addAttribute("calculatorForm", new CalculatorForm());
        return "calculator";
    }

    @PostMapping("/calculator")
    public String calculate(@ModelAttribute CalculatorForm calculatorForm, Model model) {
        Integer result = null;
        String errorMessage = null;

        try {
            Integer num1 = calculatorForm.getNum1();
            Integer num2 = calculatorForm.getNum2();
            String operator = calculatorForm.getOperator();

            if (num1 != null && num2 != null) {
                switch (operator) {
                    case "+":
                        result = num1 + num2;
                        break;
                    case "-":
                        result = num1 - num2;
                        break;
                    case "*":
                        result = num1 * num2;
                        break;
                    case "/":
                        if (num2 == 0) {
                            throw new ArithmeticException("Division by zero");
                        }
                        result = num1 / num2;
                        break;
                    default:
                        errorMessage = "無効な演算子です。";
                }
            } else {
                errorMessage = "数値を入力してください。";
            }
        } catch (Exception e) {
            errorMessage = "正しく計算できませんでした。";
        }

        model.addAttribute("result", result);
        model.addAttribute("errorMessage", errorMessage);
        return "calculator";
    }
}