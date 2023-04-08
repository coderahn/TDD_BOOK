package chap07.autoDebitRegisterTest;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class CardNumberValidator {
    public CardValidity validate(String cardNumber) throws IOException {
        URL url = new URL("http://some-external-pg.com/card");
        URLConnection urlConnection = url.openConnection();

        InputStream is = urlConnection.getInputStream();
        Scanner scan = new Scanner(is);

        int line = 1;
        while(scan.hasNext()) {
            String str = scan.nextLine();
            System.out.println((line++) + ":" + str);
        }
        scan.close();

        //
        return CardValidity.VALID;
    }
}
