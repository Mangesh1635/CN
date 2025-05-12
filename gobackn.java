
import java.util.Random;

// Source code is decompiled from a .class file using FernFlower decompiler.

public class gobackn {
   public static void main(String[] args) {
    final int Total_frame = 10;
    final int Window_size = 4;
    int base = 0;

    Random rand = new Random();

    while(base<Total_frame){
        System.out.println("\n Sending frame from "+ base + " to "+Math.min(base+Window_size-1,Total_frame-1));

        boolean loss = false;
        int lostframe = -1;

        for (int i = base; i < base+Window_size && i < Total_frame; i++) {
            if(rand.nextInt(10)<2){
                System.out.println("Frame "+ i +" lost.");
                loss = true;
                lostframe=i;
                break;
            }
            else{
                System.out.println("Frame "+i+" Recived ");
            }
        }

        if(loss){
            System.out.println("Retransmitting frame from : "+ lostframe);
            base  = lostframe;
        }
        else{
            base += Window_size;
        }
    }
    System.out.println("All frames sent and recived successfully !");

   }
}
