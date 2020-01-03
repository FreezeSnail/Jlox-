package com.craftinginterpreters.lox;

import java.util.List;

public class LoxArray extends LoxInstance{
	private final Object[] elements;
	
	LoxArray(int size){
		super(null);
		elements = new Object[size];
	}
	
	@Override
	Object get(Token name) {
		if(name.lexeme.contentEquals("get")) {
			return new LoxCallable() {
				@Override
				public int arity() { return 1; }
				
				@Override
				public Object call(Interpreter interpreter, List<Object> arguments) {
					int index = (int)(double)arguments.get(0);
					return elements[index];
				}
			};
			
		} else if(name.lexeme.contentEquals("set")) {
			return new LoxCallable() {

				@Override
				public int arity() {
					// TODO Auto-generated method stub
					return 2;
				}

				@Override
				public Object call(Interpreter interpreter, List<Object> arguments) {
					int index = (int)(double)arguments.get(0);
					Object value = arguments.get(1);
					return elements[index] = value;
				}
				
			};
		} else if (name.lexeme.equals("length")) {
			return elements.length;
		}
		
		throw new RuntimeError(name, "Undefined property '"+ name.lexeme + "'.");
		
	}
	
	@Override 
	public String toString() {
		String buffer = "[ ";
		for(Object obj : elements) {
			buffer += obj + ", ";
		}
		buffer =buffer.substring(0, buffer.length()-2) + " ]";
		
		return buffer;
	}

}
