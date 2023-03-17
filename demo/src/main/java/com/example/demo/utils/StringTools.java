package com.example.demo.utils;
/**
 * @author ids.akhmadb [ids.akhmadb@xl.co.id]
 * Created At 18/02/2022 15:53
 */
import com.example.demo.exception.ErrConfig;
import org.springframework.http.HttpStatus;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class StringTools {
    /**
     * Tokenize and validate delimited string arrays is contain strTest
     *
     * @param strArray
     * @param strTest
     * @return
     */
    public static boolean isTokenizedStrContain(String strArray, String delimiter, String strTest) {
        if (StringTools.isEmptyOrNull(strTest))
            return false;

        return StringTools.getTokensWithCollection(strArray.toUpperCase(), delimiter).contains(strTest.toUpperCase());
    }

    /**
     * Check str is empty or null
     *
     * @param str a string to be test
     * @return boolean
     */
    public static boolean isEmptyOrNull(String str) {
        return (str == null || str.equals(""));
    }

    /**
     * Encapsulate as empty string if it was null
     *
     * @param str to be test
     * @return String
     */
    public static String emptyIfNull(String str) {
        if (isEmptyOrNull(str)) {
            return "";
        }
        return str;
    }

    /**
     * Tokenize String array with delimiter
     *
     * @param strArray
     * @param delimiter
     * @return List of String
     */
    public static List<String> getTokensWithCollection(String strArray, String delimiter) {
        return Collections.list(new StringTokenizer(strArray, delimiter)).stream()
                .map(token -> (String) token)
                .collect(Collectors.toList());
    }

    public static String KBtoGB(long value) {
        double data = value / 1048576.0;
        data = Math.round(data * 100.0) / 100.0;
        String res = String.valueOf(data);
        res = res.contains(".") ? res.replaceAll("0*$", "").replaceAll("\\.$", "") : res;
        return res;
    }


    public static String GetBucketId(String dataBucketSpec, int index) {
        if (!isEmptyOrNull(dataBucketSpec)) {
            List<String> list = getTokensWithCollection(dataBucketSpec, ";");
            if (list.size() > 0) {
                List<String> data = new ArrayList<>();
                if ((index + 1) <= list.size()) {
                    data = getTokensWithCollection(list.get(index), "|");
                } else {
                    data = getTokensWithCollection(list.get(list.size() - 1), "|");
                }
                return data.get(0);
            }
        }
        return null;
    }

    public static ErrConfig errorConfig(String patternEnum) {
        String[] token = patternEnum.split("\\|");
        if (token.length > 1) {
            Integer httpCode = Integer.parseInt(token[0]);
            String code = token[1];
            String msg = token[2];
            return new ErrConfig().setHttpStatus(HttpStatus.valueOf(httpCode)).setCode(code).setMessage(msg);
        } else {
            return new ErrConfig().setCode("");
        }

    }


}
