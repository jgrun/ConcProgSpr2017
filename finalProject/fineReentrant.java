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
    return 0; // return no error
  }
  public int remove() {
    if(bufferEmpty()) return -3; // if empty return error
    return 1; // return popped value
  }
  public int peek() {
    if(bufferEmpty()) return -3; // if empty return error
    return base[head]; // return unpopped first added item
  }
}
