/**************************************************************\
* fineReentrant.java                                           *
*                                                              *
* This file will use Reentrant Locks to finely lock a circular *
* buffer. This is expected to be more efficient than the       *
* coarsely locked version and will be compared primarily with  *
* the coarsely locked version. Will also be compared with the  *
* finely locked versions of other used locking methods. This   *
* is the most basic of the finely grained locks.               *
\**************************************************************/

import java.util.concurrent.locks.ReentrantLock;

public class fineReentrant extends circBuf {
  private static ReentrantLock lock;
  public int add(int item) {
    if(bufferFull()) return 1; // if full, return error
    base[tail] = item; // Place item
    if(tail == length - 1) tail = 0; // If tail at end, put at base
    else tail++; // otherwise increment tail
    numItems++; // Increment number of items in buffer
    return 0; // return no error
  }
  public int remove() {
    if(bufferEmpty()) return null; // if empty return error
    int ret; // Instantiate return variable
    ret = base[head]; // pop first added item
    if(head == length - 1) head = 0; // if head is at end, put at base
    else head++; // otherwise increment head
    numItems--; // decrement number of items in buffer
    return ret; // return popped value
  }
  public int peek() {
    if(bufferEmpty()) return null; // if empty return error
    return base[head]; // return unpopped first added item
  }
}
