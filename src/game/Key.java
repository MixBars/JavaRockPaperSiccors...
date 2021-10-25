package game;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class Key {

    private int variants;
    private int move;
    private String key;
    private String HMAC;

    final String HMAC_ALGO = "HmacSHA256";


    public Key(String[] options){
        variants = options.length;
    }
    public String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder(bytes.length * 2);
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    public void KeyGen() throws NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException {

        SecureRandom secureRandom = new SecureRandom();
        byte []bytes = new byte[16];
        secureRandom.nextBytes(bytes);
        Mac signer = Mac.getInstance(HMAC_ALGO);
        SecretKeySpec keySpec = new SecretKeySpec(bytes, HMAC_ALGO);
        signer.init(keySpec);


        int i = secureRandom.nextInt(variants);

        String messageStr = Integer.toString(i);
        byte[] digest = signer.doFinal(messageStr.getBytes("utf-8"));

        move = i;
        key = bytesToHex(bytes);
        HMAC = bytesToHex(digest);
    }

    public String getHMAC() {
        return HMAC;
    }
    public int getMove() {
        return move;
    }
    public String getKey() {
        return key;
    }
}
