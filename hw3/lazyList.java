public class lazyList {
  node head;
  int length;
  public lazyList (int n) {
    length = 0;
    head.data = 0;
    head.next = NULL;
    head.prev = NULL;
    head.marked = 0;
  }
  private boolean validate(node prev, node curr, node next) {
    return !prev.marked && !curr.marked && !next.marked && (prev.next == curr) && (next.prev == curr);
  }
  // Add needs only two locks because it is manipulating the pointing of only
  // two existing nodes
  public boolean add(node item) {
    int data = item.data;
    while(true){
      if(length <= 3) {
        switch (length) {
          case 0:
            head = new node(item);
            length++;
            break;
          case 1:
            node n;
            head.lock();
            try {
              if(head.data < data) {
                n = new node(item);
                n.next = head;
                length++;
                head.prev = n;
                head = n;
              } else {
                n = new node(item);
                n.prev = head;
                length++;
                head.next = n;
              }
            } finally {
              head.unlock();
            }
            break;
          case 2:
            node n;

        }
      } else {
        node pred = head;
        node curr = head.next;
        node next = head.next.next;
        while(curr.data < data) {
          pred = curr;
          curr = curr.next;
          next = curr.next;
        }
        pred.lock();
        try {
          curr.lock();
          try {
            if(validate(pred, curr, next)) {
              if(curr.data == data) {
                return false;
              } else {
                node n = new node(item);
                curr.prev = n;
                n.next = curr;
                n.prev = pred;
                pred.next = n;
                length++;
                return true;
              }
            }
          } finally {
            curr.unlock();
          }
        } finally {
          pred.unlock();
        }
      }
    }
  }

  // Remove needs three locks to make sure neither the previous or next nodes
  // are removed while removing the curr node.
  public boolean remove(node item) {
    int data = item.data;
    while(true) {
      node prev = head;
      node curr = head.next;
      node next = head.next.next;
      while(curr.data < data) {
        prev = curr;
        curr = curr.next;
        next = curr.next;
      }
      prev.lock();
      try {
        curr.lock();
        try{
          next.lock();
          try {
            if(validate(prev, curr, next)) {
              if(curr.data != data) {
                return false;
              } else {
                curr.marked = true;
                prev.next = curr.next;
                next.prev = curr.prev;
                length--;
                return true;
              }
            }
          } finally {
            next.unlock();
          }
        } finally {
          curr.unlock();
        }
      } finally {
        prev.unlock();
      }
    }
  }
  public boolean contains(node item) {
    int data = item.data;
    node curr = head;
    while(curr.data < data) {
      curr = curr.next;
    }
    return curr.data == data && !curr.marked;
  }
  private makeNode(node n, int data, node next, node prev, boolean marked) {
    n.data = data;
    n.next = next;
    n.prev = prev;
    n.marked = marked;
  }
}
