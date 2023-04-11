import java.io.PipedInputStream;
import java.io.PipedOutputStream;

public class App {
    public static void main(String[] args) throws Exception {
        PipedOutputStream pis = new PipedOutputStream();
        PipedInputStream pos = new PipedInputStream(pis);
        pis.write(3);
        int n = pos.read();

        System.out.println(n);
    }
}
