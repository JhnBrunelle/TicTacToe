
public class TicTacToe {

    private char[][] gameBoard;     // GameBoard
    private int inLine;             // Required number of squares in a row to win


    /**
     * Creates a new Game Board
     * @param board_size The size of the length and width of the board
     * @param inline Required number of square in a row to win
     * @param max_levels the max level of the game tree
     */
    public TicTacToe (int board_size, int inline, int max_levels) {

        // Create a new Game Board of size board_size
        gameBoard = new char[board_size][board_size];

        // Fill board with empty characters
        for (int i = 0; i < board_size; i++) {
            for (int j = 0; j < board_size; j++) {
                gameBoard[i][j] = ' ';
            }
        }

        inLine = inline;
    }

    /**
     * Creates a new dictionary of size 4500
     * @return TTTDictionary
     */
    public Dictionary createDictionary() {
        Dictionary dictionary = new Dictionary(4599);
        return dictionary;
    }

    /**
     * Checks if gameBoard string is in the Configurations Dictionary
     * @param configurations Dictionary
     * @return Score if repeated, -1 if not
     */
    public int repeatedConfig(Dictionary configurations) {
        String board = getGBString();


        if(configurations.get(board) != null){
            return configurations.get(board).getScore();

        } else {
            return -1;

        }
    }

    /**
     * Inserts current configuration into the Dictionary
     * @param configurations The Dictionary
     * @param score The current score
     * @param level The current level
     */
    public void insertConfig(Dictionary configurations, int score, int level){
        String board = getGBString();

        try {
            configurations.put(new Record(board, score, level));
        } catch (DuplicatedKeyException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Stores the character in the gameBoard at played location
     * @param row Row played at
     * @param col Column played at
     * @param symbol Player X or Computer O
     */
    public void storePlay (int row, int col, char symbol) {
        gameBoard[row][col] = symbol;
    }

    /**
     * Checks is a square in the GameBoard is empty
     * @param row row to check
     * @param col column to check
     * @return whether the square is empty or not
     */
    public boolean squareIsEmpty (int row, int col){

        try {
            return gameBoard[row][col] == ' ';
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Checks if player or computer has wont the game
     * @param symbol Symbol to check for
     * @return Whether or not the Symbol has a winning set
     */
    public boolean wins (char symbol) {

        int inlineCount = 0;            // Records the number in a row

        // Checks each Row for a win
        for (int i = 0; i < gameBoard.length; i++) {

            // Reset the counter for the row
            inlineCount = 0;

            for (int j = 0; j < gameBoard.length; j++) {
                if (gameBoard[i][j] == symbol) {
                    inlineCount = inlineCount + 1;      // Add one onto count

                } else {
                    inlineCount = 0;                     // Reset count
                }

                if (inlineCount == getInLine()) {       // Check if count is enough to win
                    return true;
                }
            }
        }

        // Checks each Column for a win
        for (int k = 0; k < gameBoard.length; k++) {

            // Reset the counter for the row
            inlineCount = 0;

            for (int l = 0; l < gameBoard.length; l++) {
                if (gameBoard[l][k] == symbol) {
                    inlineCount = inlineCount + 1;       // Add one onto count

                } else {
                    inlineCount = 0;                     // Reset count
                }

                if (inlineCount == getInLine()) {        // Check if count is enough to win
                    return true;
                }
            }
        }

        // Check Left Diagonal (top half - Along rows - Descending)
        for (int m = 0; m < gameBoard.length; m++) {
            inlineCount = 0;
            if((gameBoard.length - m) >= inLine){
                for (int n = 0; n < gameBoard.length; n++) {

                    if ( (m + n) < gameBoard.length) {            // Keep index in array bounds
                        if (gameBoard[n][n + m] == symbol) {
                            inlineCount = inlineCount + 1;        // Add one onto count

                        } else {
                            inlineCount = 0;                      // Reset count
                        }

                        if (inlineCount == getInLine()) {         // Check if count is enough to win
                            return true;
                        }
                    } else {
                        inlineCount = 0;
                    }
                }
            }
        }

        // Check Left Diagonal (bottom half - Along Columns - Descending)
        for (int m = 0; m < gameBoard.length; m++) {
            inlineCount = 0;
            if((gameBoard.length - m) >= inLine){
                for (int n = 0; n < gameBoard.length; n++) {

                    if ( (m + n) < gameBoard.length) {            // Keep index in array bounds
                        if (gameBoard[n + m][n] == symbol) {
                            inlineCount = inlineCount + 1;        // Add one onto count

                        } else {
                            inlineCount = 0;                      // Reset count
                        }

                        if (inlineCount == getInLine()) {         // Check if count is enough to win
                            return true;
                        }
                    } else {
                        inlineCount = 0;
                    }
                }
            }
        }


        // Check Left Diagonal (top half - Along Rows - Ascending)
        for (int q = 0; q < gameBoard.length; q++) {
            inlineCount = 0;
            if((gameBoard.length - q) >= getInLine()){
                for (int r = gameBoard.length - 1; r >= 0; r--) {

                    if ( (r - q) >= 0) {                                             // Keep index in array bounds
                        if (gameBoard[gameBoard.length-r-1][r - q] == symbol) {
                            inlineCount = inlineCount + 1;                           // Add one onto count

                        } else {
                            inlineCount = 0;                                         // Reset count
                        }

                        if (inlineCount == getInLine()) {                            // Check if count is enough to win
                            return true;
                        }

                    }



                }
            }
        }

        // Check Left Diagonal (top half - Along Columns - Ascending)
        for (int q = 0; q < gameBoard.length; q++) {
            inlineCount = 0;
            if((gameBoard.length - q) >= getInLine()){
                for (int r = gameBoard.length - 1; r >= 0; r--){

                    if ( (r - q) >= 0 && gameBoard.length-r < gameBoard.length) {     // Keep index in array bounds
                        if (gameBoard[r-q][gameBoard.length-r] == symbol) {
                            inlineCount = inlineCount + 1;                            // Add one onto count

                        } else {
                            inlineCount = 0;                                          // Reset count
                        }

                        if (inlineCount == getInLine()) {                             // Check if count is enough to win
                            return true;
                        }

                    }



                }
            }
        }

        return false;
    }

    /**
     * Checks if the match is a draw (no moves left and no one has won)
     * @return whether or not the match is a draw
     */
    public boolean isDraw(){

        // Check for empty spots
        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard.length; j++) {
                if (getTileType(i,j) == ' '){
                    return false;

                }
            }
        }

        // Check if player or computer has won the game
        if (wins('o') || wins('x')) {
            return false;
        }

        // Otherwise, return it's a draw
        return true;
    }

    /**
     * Checks if any player has won, if the game is still running, or if the match is a draw
     * @return 3 if O won, 0 if X won, 1 if draw, 2 if still undecided
     */
    public int evalBoard(){

        if (wins('o')){
            return 3;

        } else if (wins('x')) {
            return 0;

        } else if (isDraw()) {
            return 1;

        } else {
            return 2;
        }

    }

    /**
     * Creates and returns a String representation of the current game board
     * @return String representation of gameBoard
     */
    private String getGBString(){
        String board = "";

        // Iterate through board double array
        for (int i = 0; i < gameBoard.length; i++){
            for (int j = 0; j < gameBoard.length; j++){
                // Add onto board string
                board = board + gameBoard[i][j];
            }
        }

        return board;
    }


    /**
     * Returns the type of a square
     * @param row row location of square
     * @param col column location of square
     * @return Type of Square x = Human, o = Computer, 'b' = empty/blocked
     */
    private int getTileType(int row, int col){
        try {
            // Attempt to look up tile type and return it
            return gameBoard[row][col];
        } catch (IndexOutOfBoundsException e) {
            // If out of bounds, return blocked (since this cannot be played)
            return 'b';
        }
    }

    /**
     * Accessor method for inLine (Number in a row to win)
     * @return inLine
     */
    private int getInLine(){
        return inLine;
    }
}
