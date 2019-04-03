package it.sunnyvale.academy.jsenewfeatures.methodsreference;

import java.util.Comparator;
import java.util.stream.Stream;

/**
 * InstanceMethodReferenceRealObject
 * 
 * This class shows an example of an instance method reference of a real object
 */
public class InstanceMethodReferenceRealObject {

    public class Bicycle {
 
        private Integer frameSize;

        public Integer getFrameSize() {
            return frameSize;
        }

        public Bicycle(Integer frameSize){
            this.frameSize = frameSize;
        }
    
    }
     
    public class BicycleComparator implements Comparator<Bicycle> {

        @Override
        public int compare(Bicycle a, Bicycle b) {
            return a.getFrameSize().compareTo(b.getFrameSize());
        }
     
    }

    public void execute(){
        BicycleComparator bikeFrameSizeComparator = new BicycleComparator();
        Stream<Bicycle> stream = null;

        // Using a lambda expression
        stream = Stream.of(new Bicycle(100),new Bicycle(200));
        stream.sorted((a, b) -> bikeFrameSizeComparator.compare(a, b));

        // Using an instance method reference of a real object (object::method)
        stream = Stream.of(new Bicycle(100),new Bicycle(200));
        stream.sorted(bikeFrameSizeComparator::compare);
    }

    public static void main(String[] args) {
        new InstanceMethodReferenceRealObject().execute();
    }
}