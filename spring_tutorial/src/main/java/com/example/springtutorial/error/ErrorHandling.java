package com.example.springtutorial.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
//@controllerAdvice どのコントローラで発生したエラーでもキャッチできる
public class ErrorHandling {

	//	@ExceptionHandler(キャッチしたい例外クラス名.class) のように例外クラスを指定
	@ExceptionHandler(Exception.class)
	//    エラーの内容に応じてHTTPのステータスコードを設定
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public String handleException(Exception ex) {

		// ログにエラー内容を出力
		log.error("エラーが発生しました。メソッド名：{}, 例外クラス名：{}",
				//	最後に呼び出された（＝例外が発生した）メソッド名			
				ex.getStackTrace()[0].getMethodName(),
				//	発生した例外クラス名		
				ex.getClass().getName());

		// エラーページを表示
		return "errorView";
	}
}