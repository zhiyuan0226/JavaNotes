package com.wzy;

import java.util.Scanner;

/**
 * 二 求“相反数”问题
 * 为了得到一个数的"相反数",我们将这个数的数字顺序颠倒,然后再加上原先的数得到"相反数"。例如,为了得到1325的"相反数",首先我们将该数的数字顺序颠倒,我们得到5231,之后再加上原先的数,我们得到5231+1325=6556.如果颠倒之后的数字有前缀零,前缀零将会被忽略。例如n
 * = 100, 颠倒之后是1.
 * 输入描述: 输入包括一个整数n,(1 ≤ n ≤ 10^5)
 * 输出描述: 输出一个整数,表示n的相反数
 * 输入例子1: 1325
 * 输出例子1: 6556
 * 
 * @author admin
 *
 */
public class Demo2 {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		// 接受输入的字符
		String n = input.next();
		// 转换为数字
		int numN = Integer.parseInt(n);
		// 将数字首位互换
		// 使用StringBuild的reverse()方法
		int numM = Integer.parseInt(new StringBuilder(n).reverse().toString());
		// 输出结果
		System.out.println(numM + numN);
	}
}
