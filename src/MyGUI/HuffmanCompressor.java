package MyGUI;

import java.io.*;
import java.util.HashMap;
import java.util.PriorityQueue;




public class HuffmanCompressor {
    HashMap<Character, String> huffmanTable;
    String output;
    public void compress(String stream){
        output = "";
        huffmanTable = new HashMap<>();
        PriorityQueue<HuffmanNode> pq = new PriorityQueue<>();
        HashMap<Character, Integer> freq = new HashMap<>();
        for(int i = 0; i < stream.length(); i++){
            if(freq.containsKey(stream.charAt(i))){
                freq.replace(stream.charAt(i), freq.get(stream.charAt(i)) + 1);
            } else {
                freq.put(stream.charAt(i), 1);
            }
        }

        freq.forEach((key, value) ->{
            pq.add(new HuffmanNode(key, value));
        });

        while(pq.size() > 1){
            HuffmanNode node1 = pq.remove();
            HuffmanNode node2 = pq.remove();
            HuffmanNode node3 = new HuffmanNode('0', node1.frequency + node2.frequency);
            node3.data = null;
            node3.left = node1;
            node3.right = node2;
            pq.add(node3);
        }

        traverse(pq.peek(),"");
        for(char c: stream.toCharArray()){
            output += huffmanTable.get(c);
        }
    }

    public void traverse(HuffmanNode node, String code){
        if(node.left == null && node.right == null){
            huffmanTable.put(node.data, code);
            return;
        }

        traverse(node.left, code + "0");
        traverse(node.right, code + "1");
//        writeToFile();
    }

//    public void writeToFile(){
//        File file = new File("output.bin");
//        try {
//            FileOutputStream fos = new FileOutputStream(file);
//            fos.write(huffmanTable.size());
//            huffmanTable.forEach((key, value) ->{
//                try {
//                    fos.write(key);
//                    String codeLength = Integer.toBinaryString(value.length());
//                    if(codeLength.length() < 4){
//                        String temp = "0".repeat(4 - codeLength.length());
//                        codeLength = temp + codeLength;
//                    }
//                    String code = "";
//                    while(code.length() + value.length() < 12){
//                        code += "0";
//                    }
//                    code += value;
//                    String codeBytes = codeLength + code;
//                    fos.write((char)Integer.parseInt(codeBytes.substring(0, 7), 2));
//                    fos.write((char)Integer.parseInt(codeBytes.substring(8), 2));
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                }
//            });
//
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }

//    public void readBin(){
//        File file = new File("output.bin");
//        try {
//            FileInputStream fis = new FileInputStream(file);
//            int numOfTableEntries = fis.read();
//            for(int i = 0; i < numOfTableEntries; i++){
//                char key = (char)fis.read();
//                String code = "";
//                String firstByte = Integer.toBinaryString(fis.read());
//                firstByte += "0";
//                if(firstByte.length() < 8){
//                    while(code.length() + firstByte.length() < 8){
//                        code += "0";
//                    }
//                }
//                code += firstByte;
//                String secondByte = Integer.toBinaryString(fis.read());
//                if(secondByte.length() < 8){
//                    while(code.length() + secondByte.length() < 16){
//                        code += "0";
//                    }
//                }
//                code += secondByte;
//                int codeLength = Integer.parseInt(code.substring(0, 4), 2);
//                String value = code.substring(16 - codeLength);
//                System.out.println(key + " " + value);
//            }
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
}
