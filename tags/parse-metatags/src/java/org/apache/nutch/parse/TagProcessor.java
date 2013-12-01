
package org.apache.nutch.parse;
import java.lang.StringBuilder;
import java.lang.System;
import java.util.ArrayList;
import java.util.regex.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.lang.String;

public class TagProcessor {
    public static ArrayList<String> tags = FileReader.readFile("tags.txt");

    //parse String to match the tag
    public static final ArrayList<String> parseStringToTags(String content) {
        ArrayList<String> results = new ArrayList<String>();
        int length = TagProcessor.tags.size();

        String patterString = "\\b(" + joinArrayList(tags, "|") + ")\\b";

        Pattern pattern = Pattern.compile(patterString, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);

        Matcher matcher = pattern.matcher(content);

        while (matcher.find()) {
            results.add(matcher.group(1));
        }

        return results;
    }

    public static  String joinArrayList(ArrayList<String> list, String pattern) {
        StringBuilder result = new StringBuilder();
        for (String str: list) {
            result.append(str + pattern);
        }
        String res = result.toString();
        if (res.length() > 0) {
            res = res.substring(0, res.length() - 1);
        }
        return res;
    }

    public static  void main(String[] args) {
        System.out.println(TagProcessor.parseStringToTags("Hello jack").toString());
    }
}
