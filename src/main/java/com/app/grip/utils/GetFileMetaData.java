package com.app.grip.utils;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

@Service
public class GetFileMetaData {


    public static int fileMetaData(String url) {
        try {
            String name = url.substring(34);
            //파일 객체 생성
            String path = "/home/ubuntu/video/" + name;
            File file = new File(name);
            FileReader filereader = new FileReader(file);

            //입력 스트림 생성
            BufferedReader bufReader = new BufferedReader(filereader);
            String line = "";
            double sum = 0.0;
            while ((line = bufReader.readLine()) != null) {
                if (line.contains("#EXTINF")) {
                    sum += Double.parseDouble(line.substring(8, line.length() - 1));
                }
            }
            //.readLine()은 끝에 개행문자를 읽지 않는다.
            bufReader.close();
            return (int) sum;
        } catch (IOException e) {
            return 0;
        }
    }
}
