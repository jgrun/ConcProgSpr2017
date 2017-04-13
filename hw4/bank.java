import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

public class bank {
  private static int balance;
  private static ReentrantLock lock;
  static Condition insufficientFunds;
  static Condition prefCustomer;
  private static int prefs = 0;
  public bank(int initBal) {
    balance = initBal;
    lock = new ReentrantLock();
    insufficientFunds = lock.newCondition();
    prefCustomer = lock.newCondition();
  }
  public static void deposit(int value) {
    lock.lock();
    try{
      balance += value;
      System.out.print("deposit balance: ");
      System.out.println(balance);
      insufficientFunds.signalAll();
    }
    finally {
      lock.unlock();
    }
  }
  public static void withdraw(int value) {
    lock.lock();
    try {
      while(value > balance) {
        try {
          System.out.println("Insufficient funds. Waiting.");
          insufficientFunds.await();
        } catch(InterruptedException e) {}
      }
      balance -= value;
      System.out.print("withdrawal balance: ");
      System.out.println(balance);
    }
    finally {
      lock.unlock();
    }
  }
  public static int getbalance() {
    lock.lock();
    try {
      return balance;
    }
    finally {
      lock.unlock();
    }
  }
  public static void prefWithdraw(int value) {
    lock.lock();
    prefs++;
    try {
      while(value > balance) {
        try {
          System.out.println("Insufficient funds. Waiting.");
          insufficientFunds.await();
        } catch(InterruptedException e) {}
      }
      balance -= value;
      System.out.print("* preferred balance: ");
      System.out.println(balance);
      prefs--;
      if(prefs == 0) {
        prefCustomer.signalAll();
      }
    }
    finally {
      lock.unlock();
    }
  }
  public static void ordWithdraw(int value) {
    lock.lock();
    while(prefs > 0) {
      System.out.println("+ Waiting on preferred customer.");
      try {
        prefCustomer.await();
      } catch(InterruptedException e) {}
    }
    try {
      while(value > balance) {
        try {
          System.out.println("Insufficient funds. Waiting.");
          insufficientFunds.await();
        } catch(InterruptedException e) {}
      }
      balance -= value;
      System.out.print("- ordinary balance: ");
      System.out.println(balance);
    }
    finally {
      lock.unlock();
    }
  }
}
