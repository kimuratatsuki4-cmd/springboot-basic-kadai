package com.example.springkadaiform.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.springkadaiform.form.ContactForm;

@Controller
public class ContactFormController {

	@GetMapping("/form")
	public String showForm() {
		return "contactFormView";
	}

	@PostMapping("/confirm")
	public String registerUser(RedirectAttributes redirectAttributes,
			@Validated ContactForm form, BindingResult result) {
		// バリデーションNGの場合は/formへリダイレクト
		if (result.hasErrors()) {
			redirectAttributes.addFlashAttribute("ContactForm", form);
			//	バリデーションエラーが発生した際に、リダイレクト先の画面でもエラーメッセージを表示したい場合に実装		
//			redirectAttributes.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX
//					+ Conventions.getVariableName(form), result);
			return "contactFormView";
		} else {
			//	バリデーションOKの場合は/confirmのまま、確認画面（リダイレクト先）に入力データが表示される
			redirectAttributes.addFlashAttribute("ContactForm", form);
			return "redirect:/confirmView";
		}

	}
}
