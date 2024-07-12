import org.junit.jupiter.api.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

class AgentIn2DSpaceTest {

   private AgentIn2DSpace agent;

   @BeforeEach
   void setUp() {
       agent = new AgentIn2DSpace(5);
   }

   @Test
   @DisplayName("Test grid creation with valid size")
   void testGridCreationWithValidSize() {
       assertEquals(5, agent.gridSize);
   }

   @Test
   @DisplayName("Test agent's initial position")
   void testAgentInitialPosition() {
       assertEquals(0, agent.agentX);
       assertEquals(0, agent.agentY);
       assertEquals(AgentIn2DSpace.AGENT_NORTH, agent.agentOrientation);
   }  

   @Test
   @DisplayName("Test display grid with default agent position")
   void testDisplayGrid() {
       StringBuilder expectedOutput = new StringBuilder();
       expectedOutput.append("_|_|_|_|_|\n");
       expectedOutput.append("| | | | | |\n");
       expectedOutput.append("| | | | | |\n");
       expectedOutput.append("| | | | | |\n");
       expectedOutput.append("| ^ |_|_|_|\n");
       expectedOutput.append("_|_|_|_|_|");      
       assertEquals(expectedOutput.toString(), getOutput(() -> agent.displayGrid()));
   }
  
   @Test
   @DisplayName("Test agent rotation - counterclockwise")
   void testRotateAgentCounterClockwise() {
       agent.rotateAgentCounterClockwise();
       assertEquals(AgentIn2DSpace.AGENT_WEST, agent.agentOrientation);
   }

   @Test
   @DisplayName("Test agent rotation - clockwise")
   void testRotateAgentClockwise() {
       agent.rotateAgentClockwise();
       assertEquals(AgentIn2DSpace.AGENT_EAST, agent.agentOrientation);
   }

   @Test
   @DisplayName("Test agent movement - 1 space")
   void testMoveAgent1Space() {
       agent.moveAgent(1);
       assertEquals(0, agent.agentX);
       assertEquals(1, agent.agentY);
   }
 
   @Test
   @DisplayName("Test agent movement - multiple spaces")
   void testMoveAgentMultipleSpaces() {
       agent.moveAgent(3);
       assertEquals(0, agent.agentX);
       assertEquals(3, agent.agentY);
   }

   @Test
   @DisplayName("Test agent movement - Invalid move")
   void testMoveAgentInvalidMove() {
       // Agent will be at the top edge after this move
       agent.moveAgent(4);
       String output = getOutput(() -> agent.moveAgent(1));
       assertEquals("Invalid move. There is not enough space on the grid.", output.trim());
   }
 
   @Test
   @DisplayName("Test invalid rotation input")
   void testInvalidRotationInput() {
       String output = getOutput(() -> 
               agent.executeInstruction("invalidRotationInput"));
       assertEquals("Invalid input. Please enter an integer for movement, '-' for counter-clockwise rotation, '+' for clockwise rotation, or 'q' to exit.", 
               output.trim());
   }
   @Test
   @DisplayName("Test Quit Instruction")
   void testQuitInstruction() {
       String output = getOutput(() -> 
               agent.executeInstruction("q"));
       assertEquals("Exiting the program...", output.trim());
   }

   private static String getOutput(Runnable action) {
       ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
       System.setOut(new PrintStream(outputStream));
       action.run();
       System.setOut(System.out);
       return outputStream.toString();
   }
   
}
 
