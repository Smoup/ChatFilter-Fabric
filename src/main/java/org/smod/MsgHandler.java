package org.smod;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;

public class MsgHandler {

    @Getter @Setter
    private static HashSet<String> badWords = new HashSet<>();

    public static boolean check(String message) {
        List<String> msgComp = List.of(message.split(" "));
        for (String s : msgComp) {
            if (badWords.contains(s)) {
                return true;
            }
        }
        return false;
    }
}
