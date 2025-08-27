package com.example.springtutorial.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LogInterceptor implements HandlerInterceptor {
	// リクエストの前処理  コントローラの実行直前に前処理を行う
	@Override

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		log.info("preHandle()：リクエスト：{} メソッド：{}",
				//    request.getRequestURL() でリクエスト先URL、request.getMethod() でリクエストメソッド名（GETやPOSTなど）を取得    		
				request.getRequestURL(),
				request.getMethod());

		// trueを返すことでコントローラを呼び出す
		return true;
	}

	// レスポンスの後処理（ビュー表示前）コントローラの実行直後に後処理を行う。※ビューの表示前に呼び出される
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		//response.getStatus() で、レスポンスが持つHTTPのステータスコード（成否やエラーに関する3桁の情報）を取得
		log.info("postHandle()：ステータスコード{}",
				response.getStatus());
	}

	// ビュー表示後の完了処理
	@Override
	//    ビューを表示した後の完了処理を行う。
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		//		response.getContentType() で、レスポンスのコンテンツタイプ（Webページの形式や種類に関する情報）を取得
		log.info("afterCompletion()：コンテンツタイプ：{}",
				response.getContentType());
	}
}