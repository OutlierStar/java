package demo2;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

public class demo2 {
    public static void main(String[] args){
        PipedOutputStream pos = new PipedOutputStream();
        
        try {
            PipedInputStream pis = new PipedInputStream(pos);
            pos.write("Hello.".getBytes());
            int n = (int)pis.read();
            pos.close();
            while(n!=-1){
                System.out.println(n);
                n =  (int)pis.read();
            }
            System.out.println(n);
            pis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
