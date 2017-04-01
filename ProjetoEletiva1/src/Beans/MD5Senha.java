package Beans;

import java.security.*;
import java.math.*;

public class MD5Senha {
    public static String encriptarSenha(String senha) throws NoSuchAlgorithmException {
        MessageDigest m = MessageDigest.getInstance("MD5");
        m.update(senha.getBytes(), 0, senha.length());
        return new BigInteger(1, m.digest()).toString(16);
    }
}
