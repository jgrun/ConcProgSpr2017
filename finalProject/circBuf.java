public class circBuf {
  protected int numItems; // Contains the number of items in the buffer
  protected int length; // length of the buffer
  protected int head; // Head index value
  protected int[] base; // Actual buffer
  public boolean bufferFull() {
    if(numItems == length) return true;
    else return false;
  }
  public boolean bufferEmpty() {
    if(numItems == 0) return true;
    else return false;
  }
  public void bufferDump() {
    for(int i = 0; i < length; i++) {
      System.out.println(base[i]);
    }
  }
}
