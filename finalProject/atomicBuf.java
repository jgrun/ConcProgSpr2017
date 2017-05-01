public class atomicBuf {
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
  public atomicBuf(int l) {
    length = l;
    base = new int[length]; // Initialize buffer of length l
    numItems = 0; // Init number of items in buffer to 0
    head = 0; // Init head index to 0
  }
  public int add(int item, int location) {
    if(bufferFull()) {
      return 1;
    }
    base[location] = item;
    numItems++;
    return 0; // return no error
  }
  public int remove(int location) {
    if(bufferEmpty()) {
      return -3;
    }
    numItems--;
    return base[location]; // return popped value
  }
  public int peek(int location) {
    lock.lock(); // Lock at beginning of peek
    try{
      if(bufferEmpty()) return -3; // if empty return error
      return base[location]; // return unpopped first added item
    }
    finally {
      lock.unlock(); // unlock regardless of try exit status
    }
  }
}
