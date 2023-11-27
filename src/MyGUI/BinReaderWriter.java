package MyGUI;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Scanner;

public class BinReaderWriter {
    public static String toChars(String binarySeq) throws IOException {
        String res="";
        String curr="";
        for(int i=0;i<binarySeq.length();i++){
            curr+=binarySeq.charAt(i);
            if(curr.length()==8||i+1==binarySeq.length()){
                int decimal=Integer.parseInt(curr,2);
                char c = (char)decimal;
                res+=c;
                curr="";
            }
        }
        return res;
    }
    public void writeBinary(String binSequence, HashMap<Character, String> huffmanTable) throws IOException {
        int lastByteBits= binSequence.length()%8;
        String lastByteBit =Integer.toBinaryString(lastByteBits);
//        System.out.println(lastByteBit);
        while(lastByteBit.length()<8){
            lastByteBit="0"+lastByteBit;
        }
        binSequence=lastByteBit+binSequence;
        String currSeq=toChars(binSequence);
        File file = new File("output.bin");

        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(huffmanTable.size());
            huffmanTable.forEach((key, value) ->{
                try {
                    fos.write(key);
                    String codeLength = Integer.toBinaryString(value.length());
                    if(codeLength.length() < 4){
                        String temp = "0".repeat(4 - codeLength.length());
                        codeLength = temp + codeLength;
                    }
                    String code = "";
                    while(code.length() + value.length() < 12){
                        code += "0";
                    }
                    code += value;
                    String codeBytes = codeLength + code;
                    fos.write((char)Integer.parseInt(codeBytes.substring(0, 7), 2));
                    fos.write((char)Integer.parseInt(codeBytes.substring(8), 2));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });

            String content = currSeq;
            fos.write(content.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static String readBin(HashMap<String, Character> decompressionTable,String fileName) {
        File file = new File(fileName);
        try (FileInputStream fis = new FileInputStream(file)) {
            InputStreamReader reader = new InputStreamReader(fis, StandardCharsets.UTF_8);
            BufferedReader b=new BufferedReader(reader);

            int numOfTableEntries = fis.read();
            for(int i = 0; i < numOfTableEntries; i++){
                char key = (char)fis.read();
                String code = "";
                String firstByte = Integer.toBinaryString(fis.read());
                firstByte += "0";
                if(firstByte.length() < 8){
                    while(code.length() + firstByte.length() < 8){
                        code += "0";
                    }
                }
                code += firstByte;
                String secondByte = Integer.toBinaryString(fis.read());
                if(secondByte.length() < 8){
                    while(code.length() + secondByte.length() < 16){
                        code += "0";
                    }
                }
                code += secondByte;
                int codeLength = Integer.parseInt(code.substring(0, 4), 2);
                String value = code.substring(16 - codeLength);
//                System.out.println(key + " " + value);
                decompressionTable.put(value, key);
            }

            String res="";
            int content;
            boolean first=true;
            int lastByteBits=0;


            while ((content = b.read()) != -1) {
                if(first){
                    String firstByte="";
                    firstByte+=content;
                    lastByteBits=Integer.parseInt(Integer.toBinaryString((char) content),2);
                    first=false;
                    continue;
                }
//                System.out.println((char)content);
                String currenRes=Integer.toBinaryString((char) content);
                while(currenRes.length()<8){
                    currenRes='0'+currenRes;
                }
                res+=currenRes;

            }
            String lastByte=res.substring(res.length()-lastByteBits);
            res=res.substring(0,res.length()-8);
            res+=lastByte;
            System.out.println(res);

            return res;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public String readFile(String path) throws FileNotFoundException {
        File file = new File(path);
        Scanner sc;
        if(file.isAbsolute()){

            sc = new Scanner(file);
        }else{
            sc = new Scanner(file.getAbsoluteFile());
        }
        String text="";
        while (sc.hasNextLine()){
            text+=sc.nextLine();
        }
        return text;
    }
    public void writeFile(String path,String content) throws IOException {
        File file = new File(path);
        BufferedWriter out;

        if(file.isAbsolute()){
            out = new BufferedWriter(new FileWriter(file));

        }else{
            out = new BufferedWriter(new FileWriter(file.getAbsoluteFile()));

        }
        out.write(content);
        out.close();
    }
}
