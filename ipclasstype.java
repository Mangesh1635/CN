
import java.util.Scanner;

public class ipclasstype {
    public static String ipclass(int[] octate){
        if(octate[0]>=0 && octate[0]<=127) return "A";
        else if(octate[0]>=128 && octate[0]<=191) return "B";
        else if(octate[0]>=192 && octate[0]<=223) return "C";
        else if(octate[0]>=224 && octate[0]<=239) return "D";
        else if(octate[0]>=240 && octate[0]<=255) return "E";
        else return "3.invalid";
    }

    public static Boolean isPrivate(int[] octate){
        if(octate[0] == 10) return true;
        else if(octate[0] == 172 && octate[1]>=16 &&octate[1]<=31) return true;
        else if(octate[0] == 192 && octate[1] == 168) return true;
        else if(octate[0] == 100 && octate[2]>=64 && octate[2]<=127) return true;
        else return false;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter IP (xxx.xxx.xxx.xxx) : ");
        String ip = sc.nextLine().trim();

        String[] parts = ip.split("\\.");

        if(parts.length!=4){
            System.out.println(" 1.Invalid Ip Address !");
            return;
        }

        int[] octate = new int[4];
        for (int i = 0; i < 4; i++) {
            octate[i] = Integer.parseInt(parts[i]);

            if(octate[i]<0 || octate[i]>255){
                System.out.println("2.Invalid Ip !");
                return;
            }
        }

        String ipClass = ipclass(octate);
        if(ipClass.equals("3.invalid") ){
            System.out.println("Invalid Ip Address !");
            return;
        }

        
        Boolean isPrivate =  isPrivate(octate);
        
        System.out.print("IP Address : " + ip +"\n");
        System.out.print("Ip Class : " + ipClass +"\n");
        if(octate[0] == 0 && octate[1] == 0 && octate[2] == 0 && octate[3] == 0){
            System.out.println("IP Type : Reserved IP .");
        }
        else if(isPrivate){
            System.out.print("IP Type : Private.");
        }
        else{
            System.out.print("IP Type : Public.");
        }
    }
}
