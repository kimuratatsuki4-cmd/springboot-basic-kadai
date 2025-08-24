package com.example.springkadaitodo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.springkadaitodo.entity.ToDo;
import com.example.springkadaitodo.repository.ToDoRepository;

@Service
public class ToDoService {
	private final ToDoRepository toDoRepository;

	//	依存性の注入
	public ToDoService(ToDoRepository toDoRepository) {
		// TODO 自動生成されたコンストラクター・スタブ
		this.toDoRepository = toDoRepository;
	}

	//	新規タスクの登録メソッド
	public void createToDo(String title, String priority, String status) {
		// タイトルの未入力チェック（空欄はNG）
		if (title == null || title.isEmpty()) {
			throw new IllegalArgumentException("タイトルを入力してください。");
		}

		// タイトルの重複チェック（完全一致はNG）
		if (!toDoRepository.findByTitle(title).isEmpty()) {
			throw new IllegalArgumentException("そのタイトルは既に使用されています。");
		}

		// TODOタスク登録用のエンティティを作成
		ToDo toDo = new ToDo();
		toDo.setPriority(priority);
		toDo.setStatus(status);

		// ユーザーの登録
		toDoRepository.save(toDo);
	}

	// ユーザーの一括取得メソッド
	public List<ToDo> getAllToDos() {
		return toDoRepository.findAll();
	}
}
