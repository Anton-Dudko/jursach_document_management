package server;


import dataBase.Database;
import repository.HandlerRepository;

import java.io.*;
import java.net.Socket;


public class ClientHandler extends Thread{

    private UserHandlerRepositoryService usersHandlerUser;
    private HandlerRepository handlerRepository;
    private Socket socket;
    private BufferedReader messageFromServer;
    private BufferedWriter writeMessage;
    public ClientHandler(Socket socket, Database database,int count) {


        this.socket = socket;
        try {
            messageFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writeMessage = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            usersHandlerUser = new UserHandlerRepositoryService(count,socket,database,messageFromServer,writeMessage);
            handlerRepository = new DocumentHandlerRepositoryService(count,socket,database,messageFromServer,writeMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
        start();
    }

    @Override
    public void run() {
        try {
            userHandler();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void userHandler() throws IOException {
        while (true) {
            switch (messageFromServer.readLine()) {

                case "авторизация":
                    usersHandlerUser.authorization();
                    break;

                case "добавить пользователя":
                    usersHandlerUser.addInDataBase();
                    break;



                case "добавить документ":
                    handlerRepository.addInDataBase();
                    break;

                case "вывести все документы":
                    handlerRepository.showAll();
                    break;

                case "удалить документ":
                    handlerRepository.delete();
                    break;

                case "редактировать документ":
                    handlerRepository.redaction();
                    break;

                case "все users":
                    usersHandlerUser.showAll();
                    break;
                case "редактировать user":
                    usersHandlerUser.redaction();
                    break;
                case "удалить user":
                    usersHandlerUser.delete();
                    break;



            }
        }
     }


    public void sendMessage(String msg) {
        try {
            writeMessage.write(msg + "\n");
            writeMessage.flush();
        } catch (IOException ignored) {}
    }
}


