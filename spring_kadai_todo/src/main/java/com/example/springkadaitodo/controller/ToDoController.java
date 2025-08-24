package com.example.springkadaitodo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.springkadaitodo.entity.ToDo;
import com.example.springkadaitodo.service.ToDoService;

@Controller
public class ToDoController {
	private final ToDoService toDoService;

	//DIコンテナから依存性の注入(サービス→コントローラー)
	public ToDoController(ToDoService toDoService) {
		this.toDoService = toDoService;
	}

	@GetMapping("/todo")
	public String toDo(Model model) {
		//コントローラーからビューへモデルのデータを受け渡す
		//	①最新の情報を取得し、addAttribute()メソッドを使ってコントローラのデータを保存。
		List<ToDo> toDos = toDoService.getAllToDos();
		model.addAttribute("toDos", toDos);
		return "todoView";
	}

	//フィードバックを提供するコントローラーの作成
	//コントローラでフォームデータを受け取る
	@PostMapping("/register")
	public String registerToDo(RedirectAttributes redirectAttributes,
			@RequestParam("title") String title,
			@RequestParam("priority") String priority,
			@RequestParam("status") String status) {
		try {
//			＠RequestParamからの入力値を用いて登録作業
			toDoService.createToDo(title, priority, status);
			//リダイレクト先にデータの受け渡し（登録成否メッセージ出力のため）
			redirectAttributes.addFlashAttribute("successMessage","ユーザー登録が完了しました。");

		} catch (IllegalArgumentException e) {
			// TODO: handle exception
			redirectAttributes.addFlashAttribute("failureMessage", e.getMessage());
			redirectAttributes.addFlashAttribute("title", title);
			redirectAttributes.addFlashAttribute("priority", priority);
			redirectAttributes.addFlashAttribute("status", status);
		}
		return "redirect:/admintodo";
	}

}
