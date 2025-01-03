package com.site.enterprise.lomboktesting;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TestLombok {
	private String name = "Naol Daba";
	
	public static void main(String[]args) {
		TestLombok lombok = new TestLombok();
		System.out.println(lombok.getName());
		
		lombok.setName("naol daba");
		System.out.println(lombok.getName());
	}
}