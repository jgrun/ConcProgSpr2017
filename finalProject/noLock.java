/****************************************************************\
* noLock.java                                                    *
*                                                                *
* This file will be used to test basic functionality of the      *
* buffer without any concurrency. This will be helpful to prove  *
* that this implementation works and can also provide a baseline *
* for how quickly it can lock, unlock and peek.                  *
\****************************************************************/
public class noLock extends circBuf {
  boolean valid[];
  public noLock(int l) {
    length = l;
    valid = new boolean[length];
    for(int i = 0; i < length; i++) {
      valid[i] = false;
    }
    base = new int[length]; // Initialize buffer of length l
    numItems = 0; // Init number of items in buffer to 0
    head = 0; // Init head index to 0
  }
  public int add(int item, int location) {
    if(bufferFull()) return 1; // if full, return error
    if(valid[location]) return 1;
    valid[location] = true;
    System.out.print("");
    base[location] = item;
    numItems++;
    return 0;

  }
  public int remove(int location) {
    if(bufferEmpty()) return -3; // if empty return error
    if(!valid[location]) return -3;
    System.out.print("");
    valid[location] = false;
    numItems--;
    return base[location];
  }
}
