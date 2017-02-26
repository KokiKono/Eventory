package local.koki.android.eventory.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by 浩生 on 2017/01/28.
 */

public class Json {
    public static String is2String(InputStream is) throws IOException {
        BufferedReader reader=new BufferedReader(new InputStreamReader(is,"UTF-8"));
        StringBuffer sb=new StringBuffer();
        char[] b=new char[1024];
        int line;
        while (0<=(line=reader.read(b))){
            sb.append(b,0,line);
        }
        return sb.toString();
    }
}
