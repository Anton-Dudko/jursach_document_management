package clientActions;


import javafx.scene.control.Button;
import model.Document;
import model.User;
import windowsAlert.AlertWindow;
import windowsAlert.NewWindowOpen;

import java.io.*;
import java.net.Socket;
import java.util.LinkedList;

public class ClientActionsWithServer {

    private BufferedReader acceptMessage;
    private BufferedWriter sendMessage;

    public ClientActionsWithServer(Socket clientSocket) {
        try {
            acceptMessage = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            sendMessage = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void send(String message) {
        try {
            sendMessage.write(message + "\n");
            sendMessage.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void chekUserInDatabase(Button button){
        try {
            String answer = acceptMessage.readLine();
            if(!answer.equals("false")){

                String[] string = answer.split(" ");
                User.currentUser = new User(string[0],string[1],string[2]);
                if(User.currentUser.getRoll().equals("admin")) {
                    NewWindowOpen.newWindowOpen.openWindow(button, "../views/adminWindow.fxml");

                }else{
                    NewWindowOpen.newWindowOpen.openWindow(button, "../views/userWindow.fxml");

                }
            }else{

               AlertWindow.alertWindow.alertWindow("Неверный логин или пароль");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public LinkedList<Document> allDocumet() throws IOException {

        send("вывести все документы");
        LinkedList<Document> documents = new LinkedList<>();

        int sizeList = Integer.parseInt(acceptMessage.readLine());
        for(int i=0;i<sizeList;i++){
            addDocumentInList(acceptMessage.readLine(), documents);
        }

        return documents;
    }

    private void addDocumentInList(String string, LinkedList<Document> documents){
        String[] product;
        product = string.split(" ");
        documents.add(new Document(Integer.parseInt(product[0]),product[1],product[2],product[3],product[4]));

    }


    public void redactionDoc(int id, String name, String cost, String count, String type) {
        send("редактировать документ");
        send(id+" "+name+" "+cost+" "+count+" "+type);
    }

    public void addNewUserInDataBase(String name, String lastName, String email, String password ,String roll) {
        send("добавить пользователя");
        send(name+" "+lastName+" "+email+" "+password+" "+roll);
    }


    public void deleteDocument(int id) {
        send("удалить документ");
        send(String.valueOf(id));
    }
    public LinkedList<User> showAllUsers() throws IOException {
        send("все users");
        LinkedList<User> lisUsers = new LinkedList<>();

        int sizeList = Integer.parseInt(acceptMessage.readLine());
        System.out.println(sizeList);
        for(int i=0;i<sizeList;i++){
            parseStringUsers(acceptMessage.readLine(),lisUsers);

        }


        return lisUsers;
    }

    public void changeUser(String user) {

        send("редактировать user");
        send(user);
    }

    public void deleteUser(int count) {
        send("удалить user");
        send(String.valueOf(count));
    }

    private void parseStringUsers(String readLine, LinkedList<User> lisUsers) {
        if(!readLine.equals("")) {
            String[] subStr;
            subStr = readLine.split(" ");

            lisUsers.add(new User(Integer.parseInt(subStr[0]), subStr[1], subStr[2], subStr[3]));
        }
        else{
            System.out.println(readLine);
        }
    }
    public void addNewDocumentInDataBase(String name, String data, String about, String check) {
        send("добавить документ");
        send(name+" "+data+" "+about+" "+check);
    }
}
