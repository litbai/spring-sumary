package cc.lzy.spring.util;

import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;

/**
 * 参考：https://stackoverflow.com/questions/10008989/library-for-converting-native2ascii-and-vice-versa
 *
 * @author taigai
 * @date 2022/02/18 3:19 PM
 */
public class Converter {

    /**
     * 将非ASCII字符转化为\u674E的形式
     */
    public static String native2ascii(String unicodeString) {
        final CharsetEncoder asciiEncoder = Charset.forName("US-ASCII").newEncoder();
        final StringBuilder result = new StringBuilder();
        for (final Character character : unicodeString.toCharArray()) {
            if (asciiEncoder.canEncode(character)) {
                result.append(character);
            } else {
                result.append("\\u");
                result.append(Integer.toHexString(0x10000 | character).substring(1).toUpperCase());
            }
        }
        return result.toString();
    }

    public static void main(String[] args) {
        // Hello \u674E\u6CFD\u6D0B
        String s = Converter.native2ascii("Hello 李泽洋");
        System.out.println(s);
    }
}