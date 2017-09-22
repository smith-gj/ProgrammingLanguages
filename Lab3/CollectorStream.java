import java.util.ArrayList;

public class CollectorStream extends Stream {
   
   Notifier notifier;
   TCFrame frame;   
        
   List<Producer> collection = new ArrayList<>();
   
   // Constructor
   CollectorStream (Notifier n, TCFrame f) {
      notifier = n;
      frame = f;
   }
    
   void add(Producer prod) {
      collection.add(prod);
   }

   public void run () {
      // For summing the values we get from the producers
      int sum = 0;     
           
      for (Producer prod: collection) {
         // Some casting and getting values from p,next     
         Subscriber sub = (Subscriber) prod.next();
         sum += sub.stock_value;
      }
      
      // Get the sum and then place it back so the consumer can get it
      IntObject new_sum = new IntObject(sum);
      notifier.putValue(new_sum);
      putIt(notifier);
   }
}
