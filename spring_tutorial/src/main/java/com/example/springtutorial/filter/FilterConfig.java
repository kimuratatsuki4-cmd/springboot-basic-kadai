package com.example.springtutorial.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
//フィルターを登録するためのクラス
public class FilterConfig {
    @Bean
    FilterRegistrationBean<HeaderFooterFilter> registerCorrectFilter() {

        // フィルター登録用のオブジェクトを生成（入れ物）
        FilterRegistrationBean<HeaderFooterFilter> regBean = new FilterRegistrationBean<>();

        // フィルターを設定
        regBean.setFilter(new HeaderFooterFilter());

        // 全リクエスト・レスポンスがフィルターの対象
        regBean.addUrlPatterns("/*");

        // オブジェクトを返すことでDIコンテナに登録
        return regBean;
    }
}