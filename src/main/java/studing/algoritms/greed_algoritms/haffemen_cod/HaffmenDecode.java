package studing.algoritms.greed_algoritms.haffemen_cod;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class HaffmenDecode {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int symbolsCount = scanner.nextInt();
        int strLength = scanner.nextInt();
        Map<String,Character> codeMap =new HashMap<>();
        while (symbolsCount > 0) {
            String character = scanner.next();
            String code = scanner.next();
            codeMap.put(code, character.replaceAll(";", "").toCharArray()[0]);
            symbolsCount--;
        }
        String codedStr = scanner.next();
        String encodedStr = encode(codedStr, codeMap);
        System.out.println(encodedStr);

    }

    private static String encode(String codedString, Map<String, Character> haffmenTable) {
        StringBuilder builder = new StringBuilder();
        String currentCodeToken = null;
        for (char c : codedString.toCharArray()) {
            if(currentCodeToken == null) {
                currentCodeToken = String.valueOf(c);
            } else currentCodeToken += String.valueOf(c);
            if(haffmenTable.containsKey(currentCodeToken)){
                builder.append(haffmenTable.get(currentCodeToken));
                currentCodeToken = null;
            }
        }
        return builder.toString();
    }


}


