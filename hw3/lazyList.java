public class lazyList {
  node head;
  int length;
  public lazyList (int n) {
    length = 0;
    head.data = 0;
    head.next = head;
    head.prev = head;
    head.marked = false;
  }
  private boolean validate(node prev, node curr, node next) {
    return !prev.marked && !curr.marked && !next.marked && (prev.next == curr) && (next.prev == curr);
  }
  // Add needs only two locks because it is manipulating the pointing of only
  // two existing nodes
  public boolean add(node item) {
    int data = item.data;
    node n;
    node pred;
    node curr;
    node next;
    while(true){
      switch (length) {
        case 0:
          n = new node(item);
          n.prev = head;
          head.next = n;
          length++;
          return true;
        case 1:
          curr = head.next;
          head.lock();
          try {
            curr.lock();
            try {
              if(!curr.marked && head.next == curr.next) {
                if(curr.data == data) return false;
                else if(curr.data < data) {
                  n = new node(item);
                  n.prev = curr;
                  length++;
                  curr.next = n;
                  return true;
                } else {
                  n = new node(item);
                  n.next = curr;
                  length++;
                  curr.prev = n;
                  head.next = curr;
                  return true;
                }
              }
            } finally {
              curr.unlock();
            }
          } finally {
            head.unlock();
          }
          break;
        default:
          pred = head;
          curr = head.next;
          next = head.next.next;
          while(curr.data < data) {
            if(curr.next == curr) {
              pred.lock();
              try {
                curr.lock();
                try {
                  if(validate(pred, curr, next)) {
                    n = new node(item);
                    curr.next = n;
                    n.prev = curr;
                    length++;
                    return true;
                  }
                  break;
                }finally {
                  curr.unlock();
                }
              } finally {
                pred.unlock();
              }
            }
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
                  n = new node(item);
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
    node prev;
    node curr;
    node next;
    while(true) {
      switch(length) {
        case 0:
          return false;
        case 1:
          prev = head;
          curr = head.next;
          if(curr.data == data && !curr.marked && head.next == curr) {
            head.lock();
            try{
              curr.lock();
              try{
                curr.marked = true;
                head.next = head;
                length--;
                return true;
              } finally {
                curr.unlock();
              }
            } finally {
              head.unlock();
            }
          }
          break;
        default:
        prev = head;
        curr = head.next;
        next = head.next.next;
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
  }
  public boolean contains(node item) {
    int data = item.data;
    node curr = head;
    while(curr.data < data) {
      curr = curr.next;
    }
    return curr.data == data && !curr.marked;
  }
}
