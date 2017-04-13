import java.util.Random;

public class bankTestPreferred {
  static int numAdder = 30;
  static int numWithdrawPreferred = 4;
  static int numWithdrawOrd = 4;
  static int i;
  static Random rand = new Random();
  static bank test = new bank(0);
  public static void main(String[] args) {
    Thread[] adder = new Thread[numAdder];
    Thread[] withdraw = new Thread[numWithdrawPreferred];
    Thread[] withdrawOrd = new Thread[numWithdrawOrd];
    for(i = 0; i < adder.length; i++) {
      adder[i] = new Thread(new Runnable() {
        public void run() {
          bank.deposit(rand.nextInt(20));
        }
      });
    }
    for(i = 0; i < withdraw.length; i++) {
      withdraw[i] = new Thread(new Runnable() {
        public void run() {
          bank.prefWithdraw(rand.nextInt(60));
        }
      });
    }
    for(i = 0; i < withdrawOrd.length; i++) {
      withdrawOrd[i] = new Thread(new Runnable() {
        public void run() {
          bank.ordWithdraw(rand.nextInt(60));
        }
      });
    }
    for(i = 0; i < withdraw.length; i++) {
      withdraw[i].start();
    }
    for(i = 0; i < withdrawOrd.length; i++) {
      withdrawOrd[i].start();
    }
    for(i = 0; i < adder.length; i++) {
      adder[i].start();
    }
    for(i = 0; i < withdrawOrd.length; i++) {
      try {
        withdrawOrd[i].join();
      }
      catch(InterruptedException e) {}
    }
    for(i = 0; i < withdraw.length; i++) {
      try {
        withdraw[i].join();
      }
      catch(InterruptedException e) {}
    }
    for(i = 0; i < adder.length; i++) {
      try {
        adder[i].join();
      }
      catch(InterruptedException e) {}
    }
    System.out.print("Final balance: ");
    System.out.println(bank.getbalance());
  }
}
