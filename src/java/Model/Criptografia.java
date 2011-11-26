package Model;

import javax.crypto.*;
import javax.crypto.spec.*;
import java.security.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.KeySpec;
import sun.misc.BASE64Encoder;
import sun.misc.BASE64Decoder;

public final class Criptografia {

    private static SecretKey skey;
    private static KeySpec ks;
    private static PBEParameterSpec ps;
    private static final String algoritimo = "PBEWithMD5AndDES";
    private static BASE64Encoder enc = new BASE64Encoder();
    private static BASE64Decoder dec = new BASE64Decoder();

    static {
        try {
            SecretKeyFactory skf = SecretKeyFactory.getInstance(algoritimo);
            ps = new PBEParameterSpec(new byte[]{3, 1, 4, 1, 5, 9, 2, 6}, 20);

            ks = new PBEKeySpec("EAlGeEen3/m8/YkO".toCharArray());


            skey = skf.generateSecret(ks);
        } catch (java.security.NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        } catch (java.security.spec.InvalidKeySpecException ex) {
            ex.printStackTrace();
        }
    }

    public String encripta(final String senha)
            throws
            BadPaddingException,
            NoSuchPaddingException,
            IllegalBlockSizeException,
            InvalidKeyException,
            NoSuchAlgorithmException,
            InvalidAlgorithmParameterException {

        final Cipher cipher = Cipher.getInstance(algoritimo);
        cipher.init(Cipher.ENCRYPT_MODE, skey, ps);
        return enc.encode(cipher.doFinal(senha.getBytes()));
    }

    public String decripta(final String senha)
            throws
            BadPaddingException,
            NoSuchPaddingException,
            IllegalBlockSizeException,
            InvalidKeyException,
            NoSuchAlgorithmException,
            InvalidAlgorithmParameterException {

        final Cipher cipher = Cipher.getInstance(algoritimo);
        cipher.init(Cipher.DECRYPT_MODE, skey, ps);
        String ret = null;
        try {
            ret = new String(cipher.doFinal(dec.decodeBuffer(senha)));
        } catch (Exception ex) {
        }
        return ret;
    }
}
