import java.util.Random;

public class bankTest {
  static int numAdder = 30;
  static int numWithdraw = 8;
  static int i;
  static int amount = 1;
  static Random rand = new Random();
  static bank test = new bank(0);
  public static void main(String[] args) {
    Thread[] adder = new Thread[numAdder];
    Thread[] withdraw = new Thread[numWithdraw];
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
          amount *= 2;
          bank.withdraw(rand.nextInt(60));
        }
      });
    }
    for(i = 0; i < withdraw.length; i++) {
      withdraw[i].start();
    }
    for(i = 0; i < adder.length; i++) {
      adder[i].start();
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
