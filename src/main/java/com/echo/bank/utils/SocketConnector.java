package com.echo.bank.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.connector.Connector;
import org.apache.commons.lang.StringUtils;

import java.io.*;
import java.net.Socket;


/**
 * 通用Socket发送方法
 *
 * @author echo
 * @version 1.0
 * Create by 2023/5/23 14:59
 */
@Slf4j
public class SocketConnector extends Connector {

    private transient String encoding = "GBK";
    private transient int port = 0;
    private transient String host = StringUtils.EMPTY;

    private int readTimeOut = 5 * 60 * 1000;

    public SocketConnector() {
    }

    public SocketConnector(String host, int port) {
        this(host, port, "GBK");
    }

    public SocketConnector(String host, int port, String encoding) {
        this.host = host;
        this.port = port;
        this.encoding = encoding;
    }

    public String request(String message) {
        log.info("Host: {} Port: {} EnCoding: {} Request: {}", host, port, encoding, message);
        String response = StringUtils.EMPTY;
        Socket socket = null;
        try {
            socket = new Socket(host, port);
            //设置连接超时(ms)
            //socket.connect(endpoint, timeout)
            //设置读超时,输入缓冲队列RecvQ中没有数据(ms)
            socket.setSoTimeout(readTimeOut);
            // 写入请求数据
            final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
                    socket.getOutputStream(), encoding));
            writer.write(message);
            writer.flush();
            // 接收响应数据
            final BufferedReader reader = new BufferedReader(new InputStreamReader(
                    socket.getInputStream(), encoding));
            String line = reader.readLine();
            final StringBuilder sb = new StringBuilder();
            while (line != null) {
                sb.append(line);
                sb.append("\r\n");
                line = reader.readLine();
            }
            reader.close();
            writer.close();
            response = sb.toString();
            log.info("Response:" + sb.toString());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            if (null != socket) {
                try {
                    socket.close();
                } catch (IOException e) {
                    log.error(e.getMessage(), e);
                }
            }
        }
        return response;
    }

}
