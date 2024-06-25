package ttfe;

import java.util.Random;

public class SimulatorImplementation implements SimulatorInterface{


    private int width;
    private int height;
    private int[][] board;
    private int numMoves;
    private int points;
    private Random r;

    public SimulatorImplementation(int width, int height, Random r){
        if(width < 2 || height < 2 || r == null){
			throw new IllegalArgumentException("invalid dimensions or random number generator");
		}
        this.width=width;
        this.height=height;
        this.board= new int[width][height];
        this.numMoves=0;
        this.points=0;
        this.r=r;

        addPiece();
        addPiece();
    }


    @Override
    public void addPiece() {
        if(!isSpaceLeft()){
            throw new IllegalStateException("Board is full");
        }
        int x,y;
        do{
            x=r.nextInt(width);
            y=r.nextInt(height);
        }while(board[x][y]!=0);

        board[x][y]=r.nextDouble() < 0.9 ? 2 : 4;
    }

    @Override
    public int getBoardHeight() {
        return height;
    }

    @Override
    public int getBoardWidth() {
        return width;
    }

    @Override
    public int getNumMoves() {
        return numMoves;
    }

    @Override
    public int getNumPieces() {
       int numPieces =0;
       for(int i=0;i<width;i++){
        for (int j=0;j<height;j++){
            if(board[i][j] != 0){
                numPieces++;
            }
        }
       }
       return numPieces;
    }

    @Override
    public int getPieceAt(int x, int y) {
       if (x<0 || x>=width || y<0 || y>=height){
        throw new IllegalArgumentException("Invalid coordinates");
       }
       return board[y][x];
    }

    @Override
    public int getPoints() {
        return points;
    }

    @Override
    public boolean isMovePossible() {
        return (isMovePossible(MoveDirection.EAST) || isMovePossible(MoveDirection.NORTH)
         || isMovePossible(MoveDirection.SOUTH) || isMovePossible(MoveDirection.WEST));
    }

    @Override
    public boolean isMovePossible(MoveDirection direction) {
        if (direction == null){
            throw new IllegalArgumentException("direction cannot be null");
        }
        switch (direction) {
            case NORTH:
            for(int i=0;i<width;i++){
                for(int j=1;j<height;j++){
                    if(board[j][i] != 0 && (board[j-1][i]==0 || board[j-1][i]==board[j][i])){
                        return true;
                    }
                }
            }
            break;
        
            case SOUTH:
            for(int i=0;i<width;i++){
                for(int j=0;j<height-1;j++){
                    if(board[j][i] != 0 && (board[j+1][i]==0 || board[j+1][i]==board[j][i])){
                        return true;
                    }
                }
            }
            break;
            
            case WEST:
            for(int i=1;i<width;i++){
                for(int j=0;j<height;j++){
                    if(board[j][i] != 0 && (board[j][i-1]==0 || board[j][i-1]==board[j][i])){
                        return true;
                    }
                }
            }
            break;

            case EAST:
            for(int i=0;i<width-1;i++){
                for(int j=0;j<height;j++){
                    if(board[j][i] != 0 && (board[j][i+1]==0 || board[j][i+1]==board[j][i])){
                        return true;
                    }
                }
            }
            break;
    }
    return false;
    }

    @Override
    public boolean isSpaceLeft() {
        for(int i=0;i<width;i++){
            for(int j=0;j<height;j++){
                if(board[i][j]==0){
                    return true;
                }
            }
        }
    return false;
    }

    @Override
    public boolean performMove(MoveDirection direction) {
      if(direction == null){
       throw new IllegalArgumentException("direction cannot be null");
      }
      boolean moveperformed=false;
      switch (direction) {
        case NORTH:
        for(int i=0;i<width;i++){
            for(int j=1;j<height;j++){
                if(board[j][i]!=0){
                    int pos=j;
                    while(pos>0 && board[pos-1][i]==0){
                    pos--;
                    }
                
                    if(pos>0 && board[pos-1][i]==board[j][i]){
                        board[pos-1][i] *= 2;
                        points += board[pos-1][i];
                        board[j][i]=0;
                        moveperformed=true;
                    }
                    else if(pos!=j){
                        board[pos][i]=board[j][i];
                        board[j][i]=0;
                        moveperformed=true;
                    }
                }
            }
        }     
            break;

        case SOUTH:
        for(int i=0;i<width;i++){
            for(int j=height-2;j>=0;j--){
                if(board[j][i]!=0){
                    int pos=j;
                    while(pos<height-1 && board[pos+1][i]==0){
                        pos++;
                    }
                    if(pos<height-1 && board[pos+1][i]==board[j][i]){
                        board[pos+1][i] *= 2;
                        points += board[pos+1][i];
                        board[j][i]=0;
                        moveperformed=true;
                    }
                    else if(pos!=j){
                        board[pos][i]=board[j][i];
                        board[j][i]=0;
                        moveperformed=true;
                    }
                }

            }

        }
            break;

        case WEST:
        for(int j=0;j<height;j++){
            for(int i=1;i<width;i++){
                if(board[i][j]!=0){
                    int pos =i;
                    while(pos>0 && board[j][pos-1]==0){
                        pos--;
                    }
                    if(pos>0 && board[j][pos-1]==board[j][i]){
                        board[j][pos-1] *= 2;
                        points += board[j][pos-1] ;
                        board[j][i] =0;
                        moveperformed = true;
                    }
                    else if(pos!=i){
                        board[j][pos] = board[j][i];
                        board[j][i]=0;
                        moveperformed = true;
                    }
                }
            }
       } 
           break;

        case EAST:
        for(int j=0;j<height;j++){
            for(int i=width-2;i>=0;i--){
                if(board[j][i] !=0){
                    int pos=i;
                    while(pos<width-1 && board[j][pos+1]==0){
                        pos++;
                    }
                    if(pos<width-1 && board[j][pos+1]==board[j][i]){
                        board[j][pos+1] *= 2;
                        points += board[j][pos+1];
                        board[j][i] =0;
                        moveperformed = true;
                    }
                    else if(pos!=i){
                        board[j][pos] = board[j][i];
                        board[j][i] = 0;
                        moveperformed = true;
                    }
                }
            }
        }
        break;
      }
      if(moveperformed){
        numMoves++;
        addPiece();
    }
    return moveperformed;
    }

    @Override
    public void run(PlayerInterface player, UserInterface ui) {
        if (player==null || ui == null){
            throw new IllegalArgumentException("Player or ui cant be null");
        }
        while(isMovePossible()){
            ui.updateScreen(this);
            MoveDirection direction = player.getPlayerMove(this, ui);
            if(performMove(direction)){
                ui.updateScreen(this);
            }
        }
        ui.showGameOverScreen(this);
    }

    @Override
    public void setPieceAt(int x, int y, int piece) {
       if(x<0 || x>= width || y<0 || y>=height || piece<0){
        throw new IllegalArgumentException("Illegal dimensions or piece is less than 0");
       }
       board[y][x]= piece;
    }
    
}
