/****************************************************************\
* noLock.java                                                    *
*                                                                *
* This file will be used to test basic functionality of the      *
* buffer without any concurrency. This will be helpful to prove  *
* that this implementation works and can also provide a baseline *
* for how quickly it can lock, unlock and peek.                  *
\****************************************************************/

public class noLock extends circBuf {
  public noLock(int l) {
    length = l;
    base = new int[length]; // Initialize buffer of length l
    numItems = 0; // Init number of items in buffer to 0
    head = 0; // Init head index to 0
  }
  public int add(int item, int location) {
    if(bufferFull()) return 1; // if full, return error
    base[location] = item;
    return 0;

  }
  public int remove(int location) {
    if(bufferEmpty()) return -3; // if empty return error
    return base[location];
  }
}
