import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class GridEditorTest {

    @Test
    public void testValidGridSizeInput() {
        String input = "5"; // Valid input within range
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        assertEquals(5, GridEditor.getValidGridSize());
    }

    @Test
    public void testInvalidGridSizeInput() {
        String input = "2\n81\n5"; // Test below min, above max, then valid
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        assertEquals(5, GridEditor.getValidGridSize());
    }

    @Test
    public void testInvalidNonIntegerInput() {
        String input = "abc\nxyz\n5"; // Test non-integer inputs
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        assertEquals(5, GridEditor.getValidGridSize());
    }

    @Test
    public void testInitializeGridWithBorder() {
        GridEditor.n = 5; // Assuming a 3x3 grid + border
        GridEditor.grid = new char[5][5];
        GridEditor.initializeGrid();
        char[][] expectedGrid = {
                {'_', '_', '_', '_', '_'},
                {'|', '.', '.', '.', '|'},
                {'|', '.', '.', '.', '|'},
                {'|', '.', '.', '.', '|'},
                {'_', '_', '_', '_', '_'}
        };
        assertArrayEquals(expectedGrid, GridEditor.grid);
    }

    @Test
    public void testGetValidCoordinates() {
        String input = "2,2"; // Valid input
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        assertArrayEquals(new int[]{2, 2}, GridEditor.getValidCoordinates());
    }

    @Test
    public void testGetInvalidCoordinates() {
        String input = "0,0\n5,5\n2,2"; // Out of bounds, then valid
        GridEditor.n = 5; // Assuming a 3x3 grid + border
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        assertArrayEquals(new int[]{2, 2}, GridEditor.getValidCoordinates());
    }

    @Test
    public void testInvalidCoordinateFormat() {
        String input = "abc\n2,2"; // Invalid format, then valid
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        assertArrayEquals(new int[]{2, 2}, GridEditor.getValidCoordinates());
    }

    @Test
    public void testGetCharacter() {
        String input = "A"; 
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        assertEquals('A', GridEditor.getCharacter());
    }

    @Test
    public void testUpdateGridWithNewCharacter() {
        // ... (Assume grid is initialized)
        String input = "2,2\nA"; // Update coordinates 2,2 with 'A'
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        GridEditor.grid = new char[5][5];
        GridEditor.initializeGrid();
        GridEditor.main(new String[]{}); 
        assertEquals('A', GridEditor.grid[2][2]); 
    }


    // Additional tests can be added to cover more scenarios...
}
