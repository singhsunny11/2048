package ttfe.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
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
	public void testgetBoardWidth(){
		assertTrue("The board width is not 4",4==game.getBoardWidth());
    }

	@Test
	public void testgetBoardHeight(){
		assertTrue("The board width is not 4",4==game.getBoardHeight());
	}

	@Test
	public void testaddPiece(){
			for(int i=0;i<4;i++){
				for(int j=0;j<4;j++){
					game.setPieceAt(i, j, 2);
				}
			}
		assertThrows("Exception expected when using add piece on full board",IllegalStateException.class,()->{
			game.addPiece();
		});
	}


	@Test
	public void testgetNumMoves(){
		assertEquals("The initial number of moves should be 0",0,game.getNumMoves());
		boolean check = game.performMove(MoveDirection.EAST);
		if(check){
			assertTrue("Incorrect num of moves",1==game.getNumMoves());
		}
		else{
			assertTrue("Incorrect num of moves",0==game.getNumMoves());
        }
    }

	@Test
	public void testgetNumPieces(){
		assertEquals("Initial no. of pieces should be 2",2,game.getNumPieces());
		game.addPiece();
		assertEquals("no. of pieces incorrect",3,game.getNumPieces());
	}

	@Test
	public void testgetPoints(){
		for(int i=0;i<4;i++){
			for(int j=0;j<4;j++){
				game.setPieceAt(i, j, 0);
			}
		}
        game.setPieceAt(0, 0, 2);
		game.setPieceAt(1,0,2);
		game.performMove(MoveDirection.WEST);
		assertTrue("incorrect points calculated",4==game.getPoints());
		for(int i=1;i<4;i++){
			game.setPieceAt(i, 0, 4);
		}
		game.performMove(MoveDirection.EAST);
		assertTrue("incorrect points calculated",20==game.getPoints());
		
	}

	@Test
	public void testgetPieceAt1(){
		game.setPieceAt(0, 0, 2);
		assertEquals("Piece at 0,0 should be 2",2,game.getPieceAt(0, 0));
	}

	@Test
	public void testgetPieceAt2(){
		assertThrows("Invalid coordinate: less than 0",IllegalArgumentException.class,()->{
			game.getPieceAt(-1,-3);
		});
	}

	@Test
	public void testgetPieceAt3(){
		assertThrows("Invalid coordinate: more than 4",IllegalArgumentException.class,()->{
			game.getPieceAt(5, 5);
		});
	}

	@Test
	public void testsetPieceAt(){

	}

	@Test 
    public void testisMovePossible1(){
		assertThrows("Move direction cannot be null",IllegalArgumentException.class,()->{
			game.isMovePossible(null);
		});
	}

	@Test
	public void testisMovePossible2(){
		int fullboard[][]={
			{4,2,8,2},
			{2,4,32,4},
			{8,16,2,128},
			{16,32,128,4}
		};

		
	}

	@Test
	public void testisSpaceLeft1(){
		assertTrue("space is left",game.isSpaceLeft());
	}

	@Test
	public void testisSpaceLeft2(){
		for(int i=0;i<4;i++){
			for(int j=0;j<4;j++){
				game.setPieceAt(i, j, 2);
			}
		}
		assertFalse("no space left",game.isSpaceLeft());
        
		game.setPieceAt(2, 3, 0);
		assertTrue("one space left",game.isSpaceLeft());

		game.setPieceAt(2, 3, 2);
		assertFalse("no space left",game.isSpaceLeft());

	}

	@Test
	public void testperformMove1(){
		assertThrows("Move direction cannot be null",IllegalArgumentException.class,()->{
			game.performMove(null);
		});
	}

	@Test
	public void testperformMove2(){
		for(int i=0;i<4;i++){
			for(int j=0;j<4;j++){
				game.setPieceAt(i, j, 0);
			}
		}
		game.setPieceAt(0, 0, 2);
		game.setPieceAt(3, 0, 2);
		assertTrue("move is possible in east",game.isMovePossible());
		assertTrue("move is possible in south",game.isMovePossible());
		assertTrue("move is possible in west",game.isMovePossible());
		assertTrue("move is possible in north",game.isMovePossible());

	}

}