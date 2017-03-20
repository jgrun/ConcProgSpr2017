public class circBuf {
  private int numItems; // Contains the number of items in the buffer
  private int length; // length of the buffer
  private int head; // Head index value
  private int tail; // Tail index value
  private int[] base; // Actual buffer
  public circBuf(int l) {
    length = l;
    base = new int[length]; // Initialize buffer of length l
    numItems = 0; // Init number of items in buffer to 0
    head = 0; // Init head index to 0
    tail = 0; // Init tail index to 0
  }
  private int bufferFull() {
    if(numItems == length) return 1;
    else return 0;
  }
  private int bufferEmpty() {
    if(numItems == 0) return 1;
    else return 0;
  }
}
