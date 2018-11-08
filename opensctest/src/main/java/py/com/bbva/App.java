package py.com.bbva;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.*;
import org.apache.http.ssl.*;
import sun.security.pkcs11.SunPKCS11;

import javax.net.ssl.SSLContext;
import java.io.*;
import java.io.InputStreamReader;
import java.security.*;

public class App
{
    public static void main( String[] args ) throws Exception {

        // Access SmartCard keystore
//        Provider prov = new SunPKCS11(
//                new ByteArrayInputStream("name = SmartCard\nlibrary = /usr/local/lib/opensc-pkcs11.so".getBytes())
//        );
//        Security.addProvider(prov);
//        String pin = "123456";
//        KeyStore identityKeyStore = KeyStore.getInstance("PKCS11", prov);
//        KeyStore.PasswordProtection pp = new KeyStore.PasswordProtection(pin.toCharArray());
//        identityKeyStore.load(null ,  pp.getPassword() );


        // Configure TLS client certificates
        SSLContext sslContext = SSLContexts.custom()
//                .loadKeyMaterial(identityKeyStore, pin.toCharArray(), (a, s) -> "Certificate for PIV Authentication")
                .build();
        SSLConnectionSocketFactory sslConnectionSocketFactory = new SSLConnectionSocketFactory(
                sslContext,
                new String[]{"TLSv1.2", "TLSv1.1"},
                null,
                SSLConnectionSocketFactory.getDefaultHostnameVerifier()
        );
        CloseableHttpClient client = HttpClients.custom()
                .setSSLSocketFactory(sslConnectionSocketFactory)
                .build();

        // Call endpoint
        String endpoint = "https://apitest.hdavid.io";
        System.out.println("Calling URL: " + endpoint);
        HttpGet post = new HttpGet(endpoint);
        HttpResponse response = client.execute(post);

        int responseCode = response.getStatusLine().getStatusCode();
        System.out.println("Response Code: " + responseCode);
        System.out.println("Content:-\n");
        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        String line = "";
        while ((line = rd.readLine()) != null)
            System.out.println(line);
    }

}
