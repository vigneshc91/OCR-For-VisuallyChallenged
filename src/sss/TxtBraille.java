package sss;



public class TxtBraille {

	/**
	 * @param args
	 */
	
	String space = "\u2800";
	String a = "\u2801";
	String b = "\u2803";
	String c = "\u2809";
	String d = "\u2819";
	String e = "\u2811";
	String f = "\u280B";
	String g = "\u281B";
	String h = "\u2813";
	String i = "\u280A";
	String j = "\u281A";
	String k = "\u2805";
	String l = "\u2807";
	String m = "\u280D";
	String n = "\u281D";
	String o = "\u2815";
	String p = "\u280F";
	String q = "\u281F";
	String r = "\u2817";
	String s = "\u280E";
	String t = "\u281E";
	String u = "\u2825";
	String v = "\u2827";
	String w = "\u283A";
	String x = "\u282D";
	String y = "\u283D";
	String z = "\u2835";
	
	String zero = "\u281A";
	String one = "\u2801";
	String two = "\u2803";
	String three = "\u2809";
	String four = "\u2819";
	String five = "\u2811";
	String six = "\u280B";
	String seven = "\u281B";
	String eight = "\u2813";
	String nine = "\u280A";
	

	static String res = "";
	
	
	String getBraille(String text){
		
		for(int i=0; i<text.length(); i++){
			if(text.charAt(i) == ' '){
				res += this.space; 
			} else if(text.charAt(i) == 'a'){
				res += this.a;
			} else if(text.charAt(i) == 'b'){
				res += this.b;
			} else if(text.charAt(i) == 'c'){
				res += this.c;
			} else if(text.charAt(i) == 'd'){
				res += this.d;
			} else if(text.charAt(i) == 'e'){
				res += this.e;
			} else if(text.charAt(i) == 'f'){
				res += this.f;
			} else if(text.charAt(i) == 'g'){
				res += this.g;
			} else if(text.charAt(i) == 'h'){
				res += this.h;
			} else if(text.charAt(i) == 'i'){
				res += this.i;
			} else if(text.charAt(i) == 'j'){
				res += this.j;
			} else if(text.charAt(i) == 'k'){
				res += this.k;
			} else if(text.charAt(i) == 'l'){
				res += this.l;
			} else if(text.charAt(i) == 'm'){
				res += this.m;
			} else if(text.charAt(i) == 'n'){
				res += this.n;
			} else if(text.charAt(i) == 'o'){
				res += this.o;
			} else if(text.charAt(i) == 'p'){
				res += this.p;
			} else if(text.charAt(i) == 'q'){
				res += this.q;
			} else if(text.charAt(i) == 'r'){
				res += this.r;
			} else if(text.charAt(i) == 's'){
				res += this.s;
			} else if(text.charAt(i) == 't'){
				res += this.t;
			} else if(text.charAt(i) == 'u'){
				res += this.u;
			} else if(text.charAt(i) == 'v'){
				res += this.v;
			} else if(text.charAt(i) == 'w'){
				res += this.w;
			} else if(text.charAt(i) == 'x'){
				res += this.x;
			} else if(text.charAt(i) == 'y'){
				res += this.y;
			} else if(text.charAt(i) == 'z'){
				res += this.z;
			} else if(text.charAt(i) == '0'){
				res += this.zero;
			} else if(text.charAt(i) == '1'){
				res += this.one;
			} else if(text.charAt(i) == '2'){
				res += this.two;
			}  else if(text.charAt(i) == '3'){
				res += this.three;
			} else if(text.charAt(i) == '4'){
				res += this.four;
			} else if(text.charAt(i) == '5'){
				res += this.five;
			} else if(text.charAt(i) == '6'){
				res += this.six;
			} else if(text.charAt(i) == '7'){
				res += this.seven;
			} else if(text.charAt(i) == '8'){
				res += this.eight;
			} else if(text.charAt(i) == '9'){
				res += this.nine;
			} else if(text.charAt(i) == '\n'){
				res += '\n';
			}
		}
		
		return res;
		
	}

}
