package com.example.cliente_semana9.main;

import android.util.Log;

import com.example.cliente_semana9.event.OnMessageListener;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class UDPConnection extends Thread {

    private DatagramSocket socket;
    private OnMessageListener observer;
    private MainActivity mainActivity;

    /*public void setObserver(MainActivity mainActivity)
    {
        this.mainActivity = mainActivity;
    }*/

    public UDPConnection(OnMessageListener observer){
        this.observer = observer;
    }

    @Override
        public void run() {
            try {
                // Escuchar
                socket = new DatagramSocket(9000);

                // 2.Esperar mensaje: Datagram se llaman

                while (true) {
                    byte[] buffer = new byte[100];

                    DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                    // Esperando datagrama
                    System.out.println("Esperando datagrama");
                    Log.e(">>>", "Esperando datagrama");
                    socket.receive(packet);

                    String mensaje = new String(packet.getData()).trim(); // trim quita los bytes vacio
                    Log.e(">>>","datagrama recibido: " + mensaje);
                    observer.mensajeRecibido(mensaje);
                }

            } catch (SocketException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        public void enviarMensaje(String mensaje) {
            new Thread(

                    () -> {

                        try {
                            InetAddress ip = InetAddress.getByName("192.168.1.55"); // 10.0.2.2
                           DatagramPacket packet = new DatagramPacket(mensaje.getBytes(), mensaje.getBytes().length, ip,9001);
                            socket.send(packet);

                        }
                        catch (Exception e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }

                    }

            ).start();

        }

    public OnMessageListener getObserver() {
        return observer;
    }

    public void setObserver(MainActivity mainActivity) {
    }

   /* public void setObserver(OnMessageListener observer) {
        this.observer = observer;
    }*/
}




