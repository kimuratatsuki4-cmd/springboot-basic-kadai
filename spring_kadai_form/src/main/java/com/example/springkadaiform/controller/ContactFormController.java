package com.example.springkadaiform.controller;

import org.springframework.core.Conventions;
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
	public String login() {
		return "/contactFormView";
	}

	@PostMapping("/form")
	public String registerUser(RedirectAttributes redirectAttributes,
			@Validated ContactForm form, BindingResult result) {
		// バリデーションNGの場合は/formへリダイレクト
		if (result.hasErrors()) {
			redirectAttributes.addFlashAttribute("ContactForm", form);
			redirectAttributes.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX
					+ Conventions.getVariableName(form), result);
			return "redirect:/contactformView";
		} else {
			//	バリデーションOKの場合は/confirmのまま、確認画面に入力データが表示される
			redirectAttributes.addFlashAttribute("ContactForm", form);
			return "redirect:/confimView";
		}

	}
}
