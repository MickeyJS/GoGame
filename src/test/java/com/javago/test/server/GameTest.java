package com.javago.test.server;

import static org.junit.Assert.*;

import java.awt.Color;
import java.awt.Point;
import java.net.Socket;

import org.junit.Before;
import org.junit.Test;

import com.javago.server.Player;
import com.javago.server.Server;
import com.javago.server.Game;
import com.javago.server.Stone;


public class GameTest {
	//TODO Stwórz obiek

	String s = "Miki";
	Color w = null;
	
	//TODO Stwórz obiekt player i game taki, w którym mógłbym testować swoje metody do gry. Nie chce 
	//mi się stworzyc obiekt game, bo nie potrafię stworzyć obiektu player. Stwórz mi właśnie tego playera
	
	Socket o = new Socket("hej", 4321);
	Player l = new Player(o);
	protected Game game= new Game(1,l,15);
	

	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testisInBoard() {
		boolean a = game.isInBoard(14,14);
		assertEquals(true,a);
	}
//sprawdzam czy zainicjalizowano board z kamieniami bez kolorow	
	@Test
	public void TestgetColor(){
	
	Color h = null;
	Stone st = board[14][1];
	
	Color i = st.getColor();
	
	}
	@Test
	public void TestsetColor(){
	
	Color h = Color.WHITE;
	Stone st = board[14][1];
	st.setColor(Color.WHITE);
	Color i = st.getColor();
	assertEquals(i,h);
	}
	
	
//	// DO MIKIEGO IMPLEMENTACJI POMOCNE
//	@Test
//	public void TestgetLiberties(){
//
//	board[0][1].setColor(Color.WHITE);
//	board[1][0].setColor(Color.WHITE);
//	Color qwe= board[0][1].getColor();
//	Color rty  = board[0][1].getColor();
//	assertEquals(qwe, rty);
//	}

}
