import java.util.Random;

public class selectiverepate {
    public static void main(String[] args) {
        final int total_frame = 10;
        final int windoow_size = 4;
        int base = 0;
        Random rand = new Random();
        
        boolean[] ackRecived = new boolean[total_frame];

        while(base<total_frame){
            System.out.println("\nSending Frame from "+ base +" to "+ Math.min(base+windoow_size-1,total_frame-1));

            for(int i = base ; i<base+windoow_size && i<total_frame;i++){
                if(!ackRecived[i]){
                    if(rand.nextInt(10)<2){
                        System.out.println("Frame "+i+" lost !");
                    }
                    else{
                        System.out.println("Frame "+i+" Recived !");
                        ackRecived[i]=true;
                    }
                }
            }

            while(base<total_frame && ackRecived[base]){
                base++;
            }
        }
        System.out.println("All frames Are Sent and Recived Successfully !");
    }
}
