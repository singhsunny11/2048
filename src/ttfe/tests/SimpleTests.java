package ttfe.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertThrows;


import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import ttfe.MoveDirection;
import ttfe.SimulatorInterface;
import ttfe.TTFEFactory;

/**
 * This class provides a very simple example of how to write tests for this project.
 * You can implement your own tests within this class or any other class within this package.
 * Tests in other packages will not be run and considered for completion of the project.
 */
public class SimpleTests {

	private SimulatorInterface game;

	@Before
	public void setUp() {
		game = TTFEFactory.createSimulator(4, 4, new Random(0));
	}
	
	@Test
	public void testInitialGamePoints() {
		assertEquals("The initial game did not have zero points", 0,
				game.getPoints());
	}
	
	@Test
	public void testInitialBoardHeight() {
		assertTrue("The initial game board did not have correct height",
				4 == game.getBoardHeight());
	}

	@Test
	public void testConstructor(){
		assertEquals("Initial no. of pieces should be 2",2,game.getNumPieces());	
	}

	@Test
	public void testConstructorDimensions(){
		assertThrows(IllegalArgumentException.class,()->{
			TTFEFactory.createSimulator(1,1, new Random(0));
		});
	}

	@Test
	public void testConstructorRandomNumberId(){
		assertThrows(IllegalArgumentException.class,()->{
			TTFEFactory.createSimulator(4,4, null);
		});
	}

	@Test
	public void testaddPiece(){
		for(int i=0;i<16;i++){
			game.addPiece();
		}
		assertThrows("Exception excepted when using add piece on full board",IllegalStateException.class,()->{
			game.addPiece();
		});
	}

	@Test
	public void testgetBoardWidth(){
		assertTrue("The board width is not 4",4==game.getBoardWidth());
    }

	@Test
	public void testgetBoardHeight(){
		assertTrue("The board width is not 4",4==game.getBoardHeight());
	}

	@Test
	public void testgetNumMoves(){
		game.performMove(MoveDirection.EAST);
		game.performMove(MoveDirection.NORTH);
		game.performMove(MoveDirection.SOUTH);
		game.performMove(MoveDirection.WEST);

		assertEquals("Number of moves is wrong",4==game.getNumMoves());
	}


}