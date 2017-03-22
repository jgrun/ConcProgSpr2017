public class circBuf {
  protected int numItems; // Contains the number of items in the buffer
  protected int length; // length of the buffer
  protected int head; // Head index value
  protected int tail; // Tail index value
  protected int[] base; // Actual buffer
  // public circBuf(int l) {
  //   length = l;
  //   base = new int[length]; // Initialize buffer of length l
  //   numItems = 0; // Init number of items in buffer to 0
  //   head = 0; // Init head index to 0
  //   tail = 0; // Init tail index to 0
  // }
  public boolean bufferFull() {
    if(numItems == length) return true;
    else return false;
  }
  public boolean bufferEmpty() {
    if(numItems == 0) return true;
    else return false;
  }
}
