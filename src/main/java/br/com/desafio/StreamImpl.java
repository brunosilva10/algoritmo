package br.com.desafio;

public class StreamImpl implements Stream{
	
	private char[] chars;
	private String string;
	private int index;
	
	public StreamImpl(String string) {
		if(string == null){
			throw new RuntimeException("A string nao pode ser nula!");
		}
		this.chars = string.toCharArray();
		index = 0;
	}

	@Override
	public char getNext() {
		return chars[index++];
	}

	@Override
	public boolean hasNext() {
		return index < chars.length;
	}
	
	@Override
	public String toString() {
		if(string == null){
			string = String.valueOf(chars);
		}
		return string;
	}
	
}