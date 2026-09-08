package nl.tudelft.jpacman.board;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.junit.jupiter.api.Assertions.assertThrows;


/**
 * Test various aspects of board.
 *
 * @author Jeroen Roosen 
 */
class BoardTest {

    private static final int MAX_WIDTH = 2;
    private static final int MAX_HEIGHT = 3;

    private final Square[][] grid = {
        { mock(Square.class), mock(Square.class), mock(Square.class) },
        { mock(Square.class), mock(Square.class), mock(Square.class) },
    };
    private final Board board = new Board(grid);

    /**
     * Verifies the board has the correct width.
     */
    @Test
    void verifyWidth() {
        assertThat(board.getWidth()).isEqualTo(MAX_WIDTH);
    }

    /**
     * Verifies the board has the correct height.
     */
    @Test
    void verifyHeight() {
        assertThat(board.getHeight()).isEqualTo(MAX_HEIGHT);
    }

    /**
     * Verify that squares at key positions are properly set.
     * @param x Horizontal coordinate of relevant cell.
     * @param y Vertical coordinate of relevant cell.
     */
    @ParameterizedTest
    @CsvSource({
            "0, 0",
            "1, 2",
        "0, 1"
    })
    void testSquareAt(int x, int y) {
        assertThat(board.squareAt(x, y)).isEqualTo(grid[x][y]);
    }

    @Test
    void testSquareAt() {
        Square[][] grid = new Square[1][1];
        BasicSquare square = new BasicSquare();
        grid[0][0] = square;

        Board board = new Board(grid);

        assertThat(board.squareAt(0, 0)).isEqualTo(square);
    }

    @Test
    void testSquareAtNull() {
        Square[][] grid = new Square[1][1];

        assertThrows(AssertionError.class, () -> new Board(grid));
    }

    @ParameterizedTest
    @CsvSource({
        "0, 0, true",
        "1, 2, true",
        "-1, 0, false",
        "2, 0, false",
        "0, -1, false",
        "0, 3, false"
    })
    void testWithinBorders(int x, int y, boolean expected) {
        assertThat(board.withinBorders(x, y)).isEqualTo(expected);
    }
}
