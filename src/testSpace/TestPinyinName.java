package testSpace;

import com.github.stuxuhai.jpinyin.PinyinException;

import basicTool.RegistObjectWithPinyin;

public class TestPinyinName extends Test {
	public static void main(String[] args) throws PinyinException{
		String name = "我是一个大傻瓜20154好%##";
		
		RegistObjectWithPinyin pnh = new RegistObjectWithPinyin("123456", "你好新世界");
		System.out.println(pnh.getName() + ": " + pnh.getPinyin());
		
		pnh.setName(name); 
		System.out.println(pnh.getName() 
				+ ": " + pnh.getPinyin() 
				+ "\t" + pnh.getShortPinyin());
	}
}
