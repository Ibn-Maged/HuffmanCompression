package MyGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;


public class FileCompressionApp {
    public static void main(String[] args) {
        JFrame frame = new JFrame("File Compression Tool");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setLayout(new FlowLayout());

        createComponents(frame);

        frame.setVisible(true);
    }

    private static void createComponents(JFrame frame) {
        JFileChooser fileChooser = new JFileChooser();

        String[] compressionAlgorithms = {"None", "Huffman"};
        JComboBox<String> compressionDropdown = new JComboBox<>(compressionAlgorithms);

        JButton compressButton = new JButton("Compress");
        JButton decompressButton = new JButton("Decompress");

        compressButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedAlgorithm = (String) compressionDropdown.getSelectedItem();
                File selectedFile = fileChooser.getSelectedFile();

                if (selectedAlgorithm.equals("Huffman")) {
                    BinReaderWriter binRW=new BinReaderWriter();
                    HuffmanCompressor huffmanCompressor=new HuffmanCompressor();
                    try {
                        huffmanCompressor.compress(binRW.readFile(selectedFile.toString()));
                        binRW.writeBinary(huffmanCompressor.output,huffmanCompressor.huffmanTable);
                    } catch (FileNotFoundException ex) {
                        throw new RuntimeException(ex);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }

                }
            }
        });

        decompressButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedAlgorithm = (String) compressionDropdown.getSelectedItem();
                File selectedFile = fileChooser.getSelectedFile();

                if (selectedAlgorithm.equals("Huffman")) {
                    BinReaderWriter binRW=new BinReaderWriter();
                    HuffmanDecompressor decompressor = new HuffmanDecompressor(selectedFile.toString());
                    decompressor.decompress();
                    try {
                        binRW.writeFile( "output1.txt",decompressor.outputStream);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }

                }
            }
        });

        frame.add(fileChooser);
        frame.add(compressionDropdown);
        frame.add(compressButton);
        frame.add(decompressButton);
    }
}
