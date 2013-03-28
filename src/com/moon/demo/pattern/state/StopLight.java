package com.moon.demo.pattern.state;

import java.util.Iterator;

public class StopLight implements Iterator<Color> {

	private Color current = Color.YELLOW;
	
	@Override
	public boolean hasNext() {
		return true;
	}

	@Override
	public Color next() {
		if (Color.YELLOW == current){
			current = Color.RED;
			return Color.YELLOW;
		} else if (Color.RED == current){
			current = Color.GREEN;
			return Color.RED;
		} else {
			current = Color.YELLOW;
			return Color.GREEN;
		}
	}

	@Override
	public void remove() {
		// TODO Auto-generated method stub
		
	}

}

enum Color{
	RED,
	GREEN,
	YELLOW;
}