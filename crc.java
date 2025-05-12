
import java.util.Scanner;

public class crc {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Frame size : ");
        int fs = sc.nextInt();
        System.out.print("Enter Frame (0/1) : ");
        int[] frame = new int[50];
        for (int i = 0; i < fs; i++) {
            frame[i] = sc.nextInt();
        }

        System.out.print("Enter Generator size : ");
        int gs = sc.nextInt();
        System.out.print("Enter Generator (0/1) : ");
        int[] generator = new int[50];
        for (int i = 0; i < gs; i++) {
            generator[i] = sc.nextInt();
        }

        System.out.println("frame size : " + fs);
        System.out.print("Frame : ");
        for (int j = 0; j < fs; j++) {
            System.out.print(frame[j]+" ");
        }
        System.out.println();
        System.out.println("Generator size : " + gs);
        System.out.print("Generator : ");
        for (int j = 0; j < gs; j++) {
            System.out.print(generator[j]+" ");
        }
        System.out.println();

        int extra = gs-1;
        int[] temp = new int[100];
        for (int i = 0; i < fs+extra; i++) {
            if(i<fs) temp[i] = frame[i];
            else temp[i] = 0;
        }

        System.out.print("Frame after Appending 0's : ");
        for (int j = 0; j < fs+extra; j++) {
            System.out.print(temp[j]+" ");
        }
        System.out.println();

        for (int i = 0; i < fs; i++) {
            if(temp[i] == 1){
                for (int j = 0; j < gs; j++) {
                    temp[i+j]^=generator[j];
                }
            }
        }

        int[] crc = new int[extra];
        for (int i = 0; i < extra; i++) {
            crc[i] = temp[fs+i];
        }

        System.out.print("CRC bits : ");
        for (int j = 0; j < extra; j++) {
            System.out.print(crc[j]+" ");
        }
        System.out.println();

        int[] sf = new int[fs+extra];
        for (int i = 0; i < fs+extra; i++) {
            if(i<fs) sf[i] = frame[i];
            else sf[i] = crc[i-fs];
        }

        System.out.print("Transmitted's Frame : ");
        for (int j = 0; j < fs+extra; j++) {
            System.out.print(sf[j]+" ");
        }
        System.out.println();

        int[] rf = new int[fs+extra];
        for (int i = 0; i < rf.length; i++) {
            rf[i] = sc.nextInt();
        }

        System.out.print("Recived's Frame : ");
        for (int j = 0; j < fs+extra; j++) {
            System.out.print(rf[j]+" ");
        }
        System.out.println();

        

        for (int i = 0; i < fs; i++) {
            if(rf[i] == 1){
                for (int j = 0; j < gs; j++) {
                    rf[i+j]^=generator[j];
                }
            }
        }

        boolean flag = true;
        for (int i = fs; i < fs + extra; i++) {
            if (rf[i] != 0) {
                flag = false;
                break;
            }
        }

        if(flag==false) System.out.println("Since Reminder is not 0 So There is error in message !");
        else System.out.println("Since Reminder is  0 So There is No error in message !");

        

    }
}
