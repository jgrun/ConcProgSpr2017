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
import java.util.concurrent.locks.Condition;

public class coarseReentrant extends circBuf {
  private static ReentrantLock lock;
  public coarseReentrant(int l) {
    length = l;
    base = new int[length]; // Initialize buffer of length l
    numItems = 0; // Init number of items in buffer to 0
    head = 0; // Init head index to 0
    lock = new ReentrantLock();
  }
  public int add(int item, int location) {
    lock.lock(); // Lock at the beginning of the adding item
    try {
      if(bufferFull()) {
        return 1;
      }
      base[location] = item;
      numItems++;
      return 0; // return no error
    }
    finally{
      lock.unlock(); // unlock regardless of try block exit status
    }
  }
  public int remove(int location) {
    lock.lock(); // Lock at beginning of remove item
    try {
      if(bufferEmpty()) {
        return -3;
      }
      numItems--;
      return base[location]; // return popped value
    }
    finally {
      lock.unlock(); // unlock regardless of try block exit status
    }
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
