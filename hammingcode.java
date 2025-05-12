
import java.util.Scanner;

public class hammingcode {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int[] data = new int[10];
        int[] recived = new int[10];

        System.out.print("Enter Data (bit by bit ): ");
        data[7]=sc.nextInt();
        data[6]=sc.nextInt();
        data[5]= sc.nextInt();
        data[3]=sc.nextInt();

        data[1] = data[3] ^ data[5] ^ data[7];
        data[2] = data[3] ^ data[6] ^ data[7];
        data[4] = data[5] ^ data[6] ^ data[7];

        System.out.print("Encoded Message : ");
        for (int i = 7; i >=1; i--) {
            System.out.print(data[i]);
        }
        System.out.println();

        System.out.print("Enter recived message : ");
        for (int i = 7; i >= 1; i--) {
            recived[i] = sc.nextInt();
        }
        System.out.println();

        System.out.print("Recived Message : ");
        for (int i = 7; i >= 1; i--) {
            System.out.print(recived[i]);
        }
        System.out.println();

        int c,c1,c2,c3;

        c1 = recived[1] ^ recived[3] ^ recived[5] ^ recived[7];
        c2 = recived[2] ^ recived[3] ^ recived[6] ^ recived[7];
        c3 = recived[4] ^ recived[5] ^ recived[6] ^ recived[7];

        c = c3*4 + c2*2 + c1;

        if(c==0) System.out.println(" There is no Error ! ");

        else{
            System.out.println("Error at bit : " + c);

            if(recived[c]==0) recived[c] = 1;
            else recived[c] = 0;


            System.out.print("corrected Message : ");
            for (int i = 7; i >=1; i--) {
                System.out.print(recived[i]);
            }
            System.out.println();
        } 

    }
}
