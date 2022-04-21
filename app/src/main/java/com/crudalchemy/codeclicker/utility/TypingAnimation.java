package com.crudalchemy.codeclicker.utility;

import java.util.ArrayList;

public class TypingAnimation {


    public static ArrayList<String> setupTypingAnimStrings() {

        ArrayList<String> codeTextStringList = new ArrayList<>();

        String helloWorldCodeStr = "class Greeting{ \n\tpublic static void main(String args[]){\n\t\tSystem.out.println( \"Hello World!\");\n\t}\n} ";
        // String recursiveRemoveCodeStr = "public String globalThermonuclearWar(File dir){\n\tFile[] filesInDir = dir.listFiles();\n\tif(filesInDir != null){\n\t\tfor(File file : filesInDir) {\n\t\t\tdeleteDirectory(file);\n\t\t}\n\t\n\treturn \"Shall we play a game?\"" ;
        String infiniteOkayCodeStr = "while(true){\n\tSystem.out.println(\"EVERYTHING IS FINE\");\n} ";
        String brevityExceptionCodeStr = "public String javaCode(String perfectlyFineCode) throws VerbosityException {\n\treturn \"Abstract \" + perfectlyFineCode + \"FactoryFactory\"\n} ";
        String metaCodingCodeStr = "private void typeAnim(){\n\tif(strIdx>=str.length()){\n\t\tstrIdx = 0;\n\t}String subStr = str.substring(0,strIdx);\n\tanimTextView.setText( subStr);\n\tstrIdx++;} \n";
        String a6 = "";
        String a7 = "";
        String a8 = "";
        String a9 = "";
        String testCodeStr1 = "Testing";
        String testCodeStr2 = "Test it again";
        String testCodeStr3 = "Third test";

        codeTextStringList.add(testCodeStr1);
        codeTextStringList.add(testCodeStr2);
        codeTextStringList.add(testCodeStr3);
        //codeTextStringList.add(helloWorldCodeStr);
        //codeTextStringList.add(recursiveRemoveCodeStr);
        //codeTextStringList.add(infiniteOkayCodeStr);
        //codeTextStringList.add(brevityExceptionCodeStr);
        codeTextStringList.add(metaCodingCodeStr);

        return codeTextStringList;
    }
}
