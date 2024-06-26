package ttfe.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertThrows;


import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import ttfe.MoveDirection;
import ttfe.PlayerInterface;
import ttfe.SimulatorInterface;
import ttfe.TTFEFactory;
import ttfe.UserInterface;

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
	public void testConstructorDimensions1(){
		assertThrows(IllegalArgumentException.class,()->{
			TTFEFactory.createSimulator(1,4, new Random(0));
		});
	}

	@Test
	public void testConstructorDimensions2(){
		assertThrows(IllegalArgumentException.class,()->{
			TTFEFactory.createSimulator(4,1, new Random(0));
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
	public void testNPieces(){
		for(int i=0;i<4;i++){
			for(int j=0;j<4;j++){
				game.setPieceAt(i, j, 0);
			}
		}
		game.setPieceAt(0, 0, 4);
		game.setPieceAt(1, 1, 4);
		game.setPieceAt(2, 2, 4);
		game.setPieceAt(3, 3, 4);

		assertEquals("no. of pieces incorrect",4,game.getNumPieces());
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
	public void testPoints1(){
		for(int i=0;i<4;i++){
			for(int j=0;j<4;j++){
				game.setPieceAt(i, j, 2);
			}
		}
		game.performMove(MoveDirection.EAST);
		assertTrue("incorrect",0==game.getPieceAt(0, 0) );
		assertTrue("incorrect",0==game.getPieceAt(1, 0) );
		assertTrue("incorrect",4==game.getPieceAt(2, 0) );

		assertTrue("incorrect points calculated",32==game.getPoints());
		game.performMove(MoveDirection.WEST);
		assertTrue("incorrect points calculated",64==game.getPoints());

	}

	@Test
	public void testPoints4(){
		for(int i=0;i<4;i++){
			for (int j=0;j<4;j++){
				game.setPieceAt(i, j, 0);
			}
		}
		game.setPieceAt(3, 0, 8);
		game.setPieceAt(3, 1, 8);
		game.performMove(MoveDirection.SOUTH);
		assertTrue("incorrect",16==game.getPieceAt(3, 3));

	}

	@Test
	public void testPoints2(){
		for(int i=0;i<4;i++){
			for (int j=0;j<4;j++){
				game.setPieceAt(i, j, 0);
			}
		}
		game.setPieceAt(0, 0, 4);
		game.setPieceAt(0, 1, 2);
		game.setPieceAt(0, 2, 2);
		game.setPieceAt(0, 3, 0);
		game.setPieceAt(1, 0, 4);
		game.setPieceAt(1, 1, 2);
		game.setPieceAt(1, 2, 2);
		game.setPieceAt(1, 3, 0);
		game.performMove(MoveDirection.NORTH);
		assertTrue("incorrect",4==game.getPieceAt(0,0));
		assertTrue("incorrect",4==game.getPieceAt(0,1));
		assertTrue("incorrect",0==game.getPieceAt(0,2));
		assertTrue("incorrect",0==game.getPieceAt(0,3));
		assertTrue("incorrect",4==game.getPieceAt(1,0));
		assertTrue("incorrect",4==game.getPieceAt(1,1));

		game.performMove(MoveDirection.EAST);
		assertTrue("incorrect",8==game.getPieceAt(3,0));
		assertTrue("incorrect",8==game.getPieceAt(3,1));
		game.setPieceAt(3, 2, 0);
		game.setPieceAt(3, 3, 0);


		game.performMove(MoveDirection.SOUTH);
		assertTrue("incorrect",16==game.getPieceAt(3,3));

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
		assertThrows("Invalid coordinates",IllegalArgumentException.class,()->{
			game.setPieceAt(-1, 0, 2);
		});

		assertThrows("Invalid coordinates",IllegalArgumentException.class,()->{
			game.setPieceAt(0, -1, 2);
		});
		
		assertThrows("Invalid coordinates",IllegalArgumentException.class,()->{
			game.setPieceAt(4, 4, 2);
		});

		game.setPieceAt(0, 0, 2);
		assertEquals("tile at 0,0 should be 2",2,game.getPieceAt(0,0));

		game.setPieceAt(0, 0, 4);
		assertEquals("tile at 0,0 should be 4 as overriden",4,game.getPieceAt(0,0));

		game.setPieceAt(0, 0, 0);
		assertEquals("tile at 0,0 should be 0 as it is removes",0,game.getPieceAt(0,0));
    }

	@Test 
    public void testisMovePossible1(){
		assertThrows("Move direction cannot be null",IllegalArgumentException.class,()->{
			game.isMovePossible(null);
		});
	}

	@Test
	public void testisMovePossibleFullBoard(){
		int[][] fullboard={
			{4,2,8,2},
			{2,4,32,4},
			{8,16,2,128},
			{16,32,128,4}
		};
    for(int i=0;i<4;i++){
		for(int j=0;j<4;j++){
			game.setPieceAt(i, j, fullboard[i][j]);
		}
    }
	assertFalse("no move possible in east",game.isMovePossible(MoveDirection.EAST));
	assertFalse("no move possible in west",game.isMovePossible(MoveDirection.WEST));
	assertFalse("no move possible in north",game.isMovePossible(MoveDirection.NORTH));
	assertFalse("no move possible in south",game.isMovePossible(MoveDirection.SOUTH));	
	assertFalse("no move is possible",game.isMovePossible());
	}



    @Test
	public void testisMovePossibleEdgeCase2(){
		int figedge[][]={
			{2,2,4,8},
			{16,16,32,64},
			{128,128,256,8},
			{64,64,4,2}
		};
		
		for(int i=0;i<4;i++){
			for(int j=0;j<4;j++){
				game.setPieceAt(j, i, figedge[i][j]);
			}
		}

		assertTrue("move possible",game.isMovePossible());
		assertTrue("move possible in east",game.isMovePossible(MoveDirection.EAST));
		assertTrue("move possible in west",game.isMovePossible(MoveDirection.WEST));
		assertFalse("no move possible in north",game.isMovePossible(MoveDirection.NORTH));
		assertFalse("no move possible in south",game.isMovePossible(MoveDirection.SOUTH));

	}

	@Test	
	public void testisMovePossibleEdgeCase3(){
		int figedge2[][]={
			{2,4,8,16},
			{2,4,8,16},
			{32,64,128,256},
			{32,64,128,256}
	};
	
	for(int i=0;i<4;i++){
		for(int j=0;j<4;j++){
			game.setPieceAt(j, i, figedge2[i][j]);
		}
	}

	assertTrue("move possible",game.isMovePossible());
	assertFalse("no move possible in east",game.isMovePossible(MoveDirection.EAST));
	assertFalse("no move possible in west",game.isMovePossible(MoveDirection.WEST));
	assertTrue("move possible in north",game.isMovePossible(MoveDirection.NORTH));
	assertTrue("move possible in south",game.isMovePossible(MoveDirection.SOUTH));

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
		assertTrue("move is possible in east",game.performMove(MoveDirection.EAST));
		assertTrue("move is possible in south",game.performMove(MoveDirection.SOUTH));
		assertTrue("move is possible in west",game.performMove(MoveDirection.WEST));
		assertTrue("move is possible in north",game.performMove(MoveDirection.NORTH));

	}
	
	@Test
	public void testrun(){
		assertThrows("Argument Exception",IllegalArgumentException.class,()->{
			game.run(null, null);
		});
	}

	@Test
	public void testrun1(){
		PlayerInterface player = TTFEFactory.createPlayer(false);
		UserInterface ui = TTFEFactory.createUserInterface(game);
		game.run(player, ui);
		assertFalse("no moves are possible after run finishes",game.isMovePossible());
	}


}