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
    tail = 0; // Init tail index to 0
  }
  public int add(int item) {
    if(bufferFull()) return 1; // if full, return error
    base[tail] = item; // Place item
    if(tail == length - 1) tail = 0; // If tail at end, put at base
    else tail++; // otherwise increment tail
    numItems++; // Increment number of items in buffer
    return 0; // return no error
  }
  public int remove() {
    if(bufferEmpty()) return -3; // if empty return error
    int ret; // Instantiate return variable
    ret = base[head]; // pop first added item
    if(head == length - 1) head = 0; // if head is at end, put at base
    else head++; // otherwise increment head
    numItems--; // decrement number of items in buffer
    return ret; // return popped value
  }
  public int peek() {
    if(bufferEmpty()) return -3; // if empty return error
    return base[head]; // return unpopped first added item
  }
}
