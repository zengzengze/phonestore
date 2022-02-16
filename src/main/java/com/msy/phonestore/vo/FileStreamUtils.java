package com.msy.phonestore.vo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.msy.phonestore.pojo.FileData;
import com.msy.phonestore.pojo.Message;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zengfanjing
 * @Date: 2022/02/07/16:47
 * @Description:
 */
public class FileStreamUtils {

    public static void read1(String textName) {
        RandomAccessFile rf = null;
        try {
            String path = System.getProperty("user.dir") + "/src/main/resources/static/chatRecord/";
            rf = new RandomAccessFile(path + textName + ".txt", "r");
            long len = rf.length();
            long start = rf.getFilePointer();
            System.out.println(start);
            long nextend = start + len - 1;
            String line;
            rf.seek(nextend);
            int c = -1;
            int count = 1;
            while (nextend > start) {
                c = rf.read();
                if (c == '\n' || c == '\r') {
                    line = rf.readLine();
                    System.out.println(line);
                    nextend--;
                    count++;
                }
                nextend--;
                rf.seek(nextend);
                if (nextend == 0) {// 当文件指针退至文件开始处，输出第一行
                    System.out.println(rf.readLine());
                }
                if (count > 5) {
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rf != null)
                    rf.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public static void read(String textName) throws IOException {
        String path = System.getProperty("user.dir") + "/src/main/resources/static/chatRecord/";
        RandomAccessFile raf = new RandomAccessFile(path + textName + ".txt", "r");
        long len = raf.length();
        String lastLine = "";
        if (len != 0L) {
            long pos = len - 1;
            while (pos > 0) {
                pos--;
                raf.seek(pos);
                if (raf.readByte() == '\n') {
                    lastLine = raf.readLine();
                    break;
                }
            }
        }
        raf.close();
        System.out.println(lastLine);
    }

    public static List<Message> reader(String textName) throws IOException, ParseException {
        List<Message> messageList = new ArrayList<>();

        String path = System.getProperty("user.dir") + "/src/main/resources/static/chatRecord/";

        File file = new File(path + textName + ".txt");
        if (file.exists()) {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String tempStr;

            SimpleDateFormat sf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

            while ((tempStr = reader.readLine()) != null) {

                String[] hexadecimal = tempStr.split("\\\\");

                byte[] UTF_8_bytes = new byte[hexadecimal.length];
                for (int i = 0; i < hexadecimal.length; i++) {
                    UTF_8_bytes[i] = (byte) Integer.parseInt(hexadecimal[i], 16);
                }
                String s = new String(UTF_8_bytes, "UTF-8");
                Message message = JSON.parseObject(s,Message.class);
                messageList.add(message);
            }
            reader.close();
        }
        return messageList;
    }

    public static void writer(String textName, Message message) throws IOException {
        System.out.println(message);
        String path = System.getProperty("user.dir") + "/src/main/resources/static/chatRecord/";
        FileWriter fileWriter = new FileWriter(path + textName + ".txt", true);
        ObjectMapper mapper=new ObjectMapper();
        String textData = mapper.writeValueAsString(message);
        byte[] bytes = textData.getBytes("UTF-8");
        StringBuilder sbTextData = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            if (bytes[i] < 0) {
                sbTextData.append("-" + Integer.toHexString(bytes[i] * (-1)) + "\\");
            } else {
                sbTextData.append(Integer.toHexString(bytes[i]) + "\\");
            }
        }
        fileWriter.write(sbTextData.toString()+"\r\n");
        fileWriter.close();
    }

    public static void main(String[] args) throws IOException, ParseException {
//        System.out.println(reader("1106"));
//        read("test1");
//        Message message = new Message();
//        message.setId(1);
//        message.setMessage("你好");
//        message.setIsSystem(true);
//        message.setReceiver(1001);
//        message.setSender(1106);
//        message.setTime(new Date());
//        message.setChannel("1106");
//        writer("1106",message);
    }
}
