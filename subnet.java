
import java.util.Scanner;


public class subnet {

    public static Boolean Ipvalid(String ipA,int[] ip){
        String[] parts = ipA.split("\\.");
        if(parts.length!=4) return false;
        
        for (int i = 0; i < ip.length; i++) {
            ip[i] = Integer.parseInt(parts[i]);
            if(ip[i]<0 || ip[i]>255) return false;
        }
        return true;
    }

    public static char ipClass(int ip){
        if(ip>=0 && ip<=127) return 'A';
        else if(ip>=128 && ip<=191) return 'B';
        else if(ip>=192 && ip<=223) return 'C';
        else if(ip>=224 && ip<=239) return 'D';
        else if(ip>=240 && ip<=255) return 'E';
        else return 'X';
    }

    public static void getDefaultsubnet(char ipclass , int[] subnet){
        if(ipclass=='A') { subnet[0] = 255; subnet[1] = 0 ; subnet[2] = 0; subnet[3] = 0;}
        else if(ipclass=='B') { subnet[0] = 255; subnet[1] = 255 ; subnet[2] = 0; subnet[3] = 0;}
        else if(ipclass=='C') { subnet[0] = 255; subnet[1] = 255 ; subnet[2] = 255; subnet[3] = 0;}
        else { subnet[0] = 0; subnet[1] = 0 ; subnet[2] = 0; subnet[3] = 0;}
    }

    public static void calculatesubnetmask(int[] subnetmask , int subnetbit){
        for (int i = 0; i < 4; i++) {
            if(subnetbit>=8){
                subnetmask[i] = 255;
                subnetbit -= 8;
            }
            else if(subnetbit>0){
                subnetmask[i] = 256 - (1 << (8-subnetbit));
                subnetbit = 0;
            }
            else{
                subnetmask[i] = 0 ;
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Ip Address (xxx.xxx.xxx.xxx): ");
        String ipAdd = sc.nextLine();
        int[] ip = new int[4];

        if(!Ipvalid(ipAdd,ip)){
            System.out.println("IP is invalid !");
            return;
        }

        char ipclass = ipClass(ip[0]);
        if(ipclass == 'X'){
            System.out.println("Invalid ip");
            return;
        }

        int[] subnetMask = new int[4];
        getDefaultsubnet(ipclass,subnetMask);
        System.out.println("IP Address : "+ipAdd);
        System.out.println("IP Class : "+ipclass);
        System.out.println("Default Subnet Mask : "+subnetMask[0]+"."+subnetMask[1]+"."+subnetMask[2]+"."+subnetMask[3]);

        System.out.print("Enter Subnet bits (from 0-30) : ");
        int subnetbit = sc.nextInt();
        if(subnetbit<0 || subnetbit>30){
            System.out.println("Invalid Subnet Bits !");
            return;
        }
        calculatesubnetmask(subnetMask,subnetbit);

        System.out.println("Calculated Subnet Mask : " + subnetMask[0]+"."+subnetMask[1]+"."+subnetMask[2]+"."+subnetMask[3]);
        

    }
}
