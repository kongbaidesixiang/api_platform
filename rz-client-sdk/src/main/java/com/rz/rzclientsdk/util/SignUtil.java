package com.rz.rzclientsdk.util;

import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;

public class SignUtil {

    public static String getSign(String body,String seccreKey){
        Digester md5 =new Digester(DigestAlgorithm.SHA256);
        String context = body+ "."+seccreKey;
        return md5.digestHex(context);
    }
}
