package MyGUI;

import MyGUI.BinReaderWriter;

import java.util.HashMap;

public class HuffmanDecompressor {
    HashMap<String, Character> huffmanTable;
    BinReaderWriter readerWriter;
    String inputStream;
    String outputStream;
    public HuffmanDecompressor(String fileName){
        readerWriter = new BinReaderWriter();
        huffmanTable = new HashMap<>();
        inputStream = readerWriter.readBin(huffmanTable,fileName);
    }

    public void decompress(){
        String current = "";
        outputStream = "";
        for(char c: inputStream.toCharArray()){
            current += c;
            if(huffmanTable.containsKey(current)){
                outputStream += huffmanTable.get(current);
                current = "";
            }
        }
    }
}
