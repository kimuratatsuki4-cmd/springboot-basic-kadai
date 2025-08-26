package com.example.springpractice.event;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import lombok.Getter;

@Component
@Getter
public class ButtonPressEventListener {
	private int count;
	
	@EventListener
	public void onButtunPressEvent(ButtonPressEvent event ) {
		this.count++;
		System.out.println("ボタンが" + count +"回押されました！");
	}

}
