/***************************************************************\
* coarseReentrant.java                                          *
*                                                               *
* This file will use Reentrant Locks to coarsely lock the any   *
* important adding, removing and peeking functions. Coarse      *
* locks are used to provide a baseline execution time to        *
* fine grained locks against. The Reentrant lock is used        *
* for its known functionality and simplicity in writing code    *
* with. This will be used as a baseline for future tests with   *
* other locking algorithms.                                     *
\***************************************************************/

import java.util.concurrent.locks.ReentrantLock;

public class coarseReentrant extends circBuf {
  private static ReentrantLock lock;
  public int add(int item) {
    lock.lock(); // Lock at the beginning of the adding item
    try {
      if(bufferFull()) return 1; // if full, return error
      base[tail] = item; // Place item
      if(tail == length - 1) tail = 0; // If tail at end, put at base
      else tail++; // otherwise increment tail
      numItems++; // Increment number of items in buffer
      return 0; // return no error
    finally{
      lock.unlock(); // unlock regardless of try block exit status
    }
  }
  public int remove() {
    lock.lock(); // Lock at beginning of remove item
    try {
      if(bufferEmpty()) return null; // if empty return error
      int ret; // Instantiate return variable
      ret = base[head]; // pop first added item
      if(head == length - 1) head = 0; // if head is at end, put at base
      else head++; // otherwise increment head
      numItems--; // decrement number of items in buffer
      return ret; // return popped value
    }
    finally {
      lock.unlock(); // unlock regardless of try block exit status
    }
  }
  public int peek() {
    lock.lock(); // Lock at beginning of peek
    try{
      if(bufferEmpty()) return null; // if empty return error
      return base[head]; // return unpopped first added item
    }
    finally {
      lock.unlock(); // unlock regardless of try exit status
    }
  }
}
