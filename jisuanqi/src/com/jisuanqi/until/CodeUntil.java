package com.jisuanqi.until;

import java.util.Random;

public class CodeUntil {
	public static String createCheckCode(){
		String[] ch = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
		Random random = new Random();
		int number = 0;
		String str = "";
		for(int i=0;i<4;i++){
			number = random.nextInt(ch.length);
			str+=ch[number];
		}
		return str;
	}
	//²âÊÔ
	//public static void main(String[] args) {
		//String s = createCheckCode();
		//System.out.println(s);
	//}
}
