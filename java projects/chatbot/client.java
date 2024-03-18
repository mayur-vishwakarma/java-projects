package chatbot;
import java.net.*;
import java.io.*;

class client{

        Socket socket;
        BufferedReader br;
        PrintWriter out;

        client(){

                try{

                        System.out.println("Sending Request to server...");
                        socket = new Socket("127.0.0.1",7777);
                        System.out.println("connection done...");
                        br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        out = new PrintWriter(socket.getOutputStream());
                        startReading();
                        startWriting();
                }catch(Exception e){

                        e.printStackTrace();
                }
        }

    public void startReading(){
        //thread for reading
        Runnable r1 =()->{
                        System.out.println("Reader Started...");
                        try{
                                while(true){
                                        String msg = br.readLine();
                                        if(msg.equals("exit")){
                                                System.out.println("Server terminated the chat");
                                                socket.close();
                                                break;
                                        }
                                        System.out.println("Server : "+msg);
                                }
                        }catch(Exception e){

                                System.out.println("connection closed..");
                                //e.printStackTrace();

                        }
                };
                new Thread(r1).start();
        }
        
    public void startWriting(){
                //thread for writing and sending
                Runnable r2 =()->{
                        System.out.println("Writer Started...");
                        try{
                                while(true && !socket.isClosed()){
                                        BufferedReader br1 = new BufferedReader(new InputStreamReader(System.in));

                                        String msg1 = br1.readLine();
                                        out.println(msg1);
                                        out.flush();
                                        if(msg1.equals("exit")){

                                                socket.close();
                                                break;
                                        }
                                }
                        }catch(Exception e){
                                System.out.println("connection closed...");
                                //e.printStackTrace();
                        }
                };
                new Thread(r2).start();
        }

        public static void main(String[] args){
                System.out.println("this is client...");
                new client();
        }
}