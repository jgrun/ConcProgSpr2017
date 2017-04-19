import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.BrokenBarrierException;

public class ProblemOneA {
  static int numBuilders = 32;
  static int numDestroyers = 64;
  static Empire game;
  static Observer watcher;
  static int i;
  final static CyclicBarrier gate = new CyclicBarrier(numBuilders + numDestroyers + 2);

  public static void main(String[] args) {
    game = new Empire(20);
    watcher = new Observer(game);
    Thread[] builder = new Thread[numBuilders];
    Thread[] destroyer = new Thread[numDestroyers];
    Thread[] observer = new Thread[1];
    for(i = 0; i < builder.length; i++) {
      builder[i] = new Thread(new Runnable() {
        public void run() {
          try {
            gate.await();
          } catch(InterruptedException e) {}
          catch(BrokenBarrierException e) {}
          game.build();
          System.out.println("+" + game.getItems());
        }
      });
    }
    for(i = 0; i < destroyer.length; i++) {
      destroyer[i] = new Thread(new Runnable() {
        public void run() {
          try {
            gate.await();
          } catch(InterruptedException e) {}
          catch(BrokenBarrierException e) {}
          game.destroy();
          System.out.println("-" + game.getItems());
        }
      });
    }
    observer[0] = new Thread(new Runnable() {
      public void run() {
        try {
          gate.await();
        } catch(InterruptedException e) {}
        catch(BrokenBarrierException e) {}
        watcher.result();
      }
    });
    observer[0].start();
    for(i = 0; i < builder.length; i++) {
      builder[i].start();
    }
    for(i = 0; i < destroyer.length; i++) {
      destroyer[i].start();
    }
    try {
      gate.await();
    } catch(InterruptedException e) {}
    catch(BrokenBarrierException e) {}
    for(i = 0; i < builder.length; i++) {
      try {
        builder[i].join();
      }
      catch(InterruptedException e) {}
    }
    for(i = 0; i < destroyer.length; i++) {
      try {
        destroyer[i].join();
      }
      catch(InterruptedException e) {}
    }
    try {
      observer[0].join();
    }
    catch(InterruptedException e) {}
  }

}
