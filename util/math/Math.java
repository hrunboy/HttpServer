package util.math;

/** 
 * ClassName: Math <br/> 
 * Function: multiply Operator <br/> 
 * date: 2016年8月31日 下午9:15:44 <br/> 
 * 
 * @author Ron 
 * @version  1.0 
 */
public class Math {  
	
	public static StringBuilder multiply(StringBuilder a,StringBuilder b){
		int len1 = a.length();  
		int len2 = b.length();  
		
		char[] s1 = a.toString().toCharArray();  
		char[] s2 = b.toString().toCharArray();  
  
		// 高低位对调  
		covertdata(s1, len1);  
		covertdata(s2, len2);  
 
        return multiply(s1, len1, s2, len2); 
	}
	
	public static void covertdata(char data[], int len) {  
		//高低位对调  
		for (int i = 0; i < len / 2; i++) {  
			data[i] += data[len - 1 - i];  
			data[len - 1 - i] = (char) (data[i] - data[len - 1 - i]);  
			data[i] = (char) (data[i] - data[len - 1 - i]);  
		}
	}
	
	public static StringBuilder  multiply(char a[], int alen, char b[], int blen) {  
		// 相乘结果位数的极限  
		int csize = alen + blen + 3;
		// 开辟乘积数组
		int[] c = new int[csize]; 
		// 乘积数组填充0  
		for (int ii = 0; ii < csize; ii++) {  
			c[ii] = 0;  
		}  
		// 对齐逐位相乘  
		for (int j = 0; j < blen; j++) {  
			for (int i = 0; i < alen; i++) {  
				c[i + j] +=  Integer.parseInt(String.valueOf(a[i]))* Integer.parseInt(String.valueOf(b[j]));  
			}  
		}  
		int m = 0;  
		// 进位处理  
		for (m = 0; m < csize; m++) {  
			int carry = c[m] / 10;  
			c[m] = c[m] % 10;  
			if (carry > 0)  
				c[m + 1] += carry;  
		}  
		// 找到最高位  
		for (m = csize - 1; m >= 0;) {  
			if (c[m] > 0)  
				break;  
			m--;  
		}  

		char d[] = new char[m+1];
		// 翻转字符
		for (int n = 0; n <= m; n++) {  
			d[n]=(char) (c[m-n]+48);
		} 
		StringBuilder val = new StringBuilder(new String(d));
		return val;
	}
}
  