package br.com.desafio;

public class TextoUtils {
	
	public static boolean isVogal(char c){
		switch(c){
			case 'a':
			case 'e':
			case 'i':
			case 'o':
			case 'u':
			case 'A':
			case 'E':
			case 'I':
			case 'O':
			case 'U':return true;
		}
		return false;
	}

}
