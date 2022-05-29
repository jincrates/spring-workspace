package me.jincrates.redis.example;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

//레디스 명령행 클라이언트와 파이프라인
public class PipelineData {
    private BufferedWriter writer;
    private String fileNamePrefix = "./redis_data";
    private String fileNamePostfix = ".txt";

    //작성할 데이터 건수: 천만 건
    private final int TOTAL_NUMBER_OF_COMMAND = 10000000;

    public static void main(String[] args) throws IOException {
        PipelineData data = new PipelineData();
        data.makeDataFileAsProtocol();
        data.makeDataFileAsCommand();
    }

    private void makeDataFileAsCommand() throws IOException {
        //명령어 형식 데이터 파일의 이름을 지정
        String fileName = fileNamePrefix + "_command" + fileNamePostfix;
        writer = new BufferedWriter(new FileWriter(fileName));

        String key, value;

        for (int i = 0; i < TOTAL_NUMBER_OF_COMMAND; i++) {
            key = String.valueOf("key" + (10000000 + i));
            value = String.valueOf("data" + (100000000 + i));

            //명령어 형식의 데이터 파일을 생성
            //키와 값은 12바이트로 고정
            writer.write("set " + key + " " + value + "\r\n");
        }

        writer.flush();
        writer.close();
    }

    private void makeDataFileAsProtocol() throws IOException {
        //프로토콜 형식 데이터 파일의 이름을 지정
        String fileName = fileNamePrefix + "_protocol" + fileNamePostfix;
        writer = new BufferedWriter(new FileWriter(fileName));

        String key, value;

        for (int i = 0; i < TOTAL_NUMBER_OF_COMMAND; i++) {
            key = String.valueOf("key" + (10000000 + i));
            value = String.valueOf("data" + (100000000 + i));

            //프로토콜 형식의 데이터를 생성
            writer.write("*3\r\n");
            writer.write("$3\r\n");
            writer.write("set\r\n");
            writer.write("$" + key.length() + "\r\n");
            writer.write(key + "\r\n");
            writer.write("$" + value.length() + "\r\n");
            writer.write(value + "\r\n");
        }

        writer.flush();
        writer.close();
    }
}
