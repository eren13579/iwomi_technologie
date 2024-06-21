package com.iwomi.projet.file.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

public class ImageController {


    public static byte[] compressImage(byte[] data) throws IOException {
        Deflater deflater = new Deflater();
        deflater.setLevel(Deflater.BEST_COMPRESSION);
        deflater.setInput(data);
        deflater.finish();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] tmp = new byte[4*1024];
        while(!deflater.finished()){
            int size = deflater.deflate(tmp);

            OutputStream outputStream1 = new OutputStream() {
                @Override
                public void write(int b) throws IOException {

                }
            };
            outputStream1.write(tmp, 0, size);
        }
        try{
            outputStream.close();
        }catch (Exception ignored){
        }
        return outputStream.toByteArray();
    }

    public static byte[] decompressImage(byte[] data){
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] tmp = new byte[4*1024];
        try {
            while(!inflater.finished()){
                int count = inflater.inflate(tmp);
                outputStream.write(tmp, 0,count);
            }
            outputStream.close();
        }catch (Exception ignored){}
        return outputStream.toByteArray();
    }
}
