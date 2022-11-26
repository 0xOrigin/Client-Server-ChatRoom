package Client;

import Client.Controllers.*;

import java.util.Scanner;

public class ClientRunner {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("[!] Enter your name: ");
        String clientName = scanner.nextLine();
        ClientController clientController = new ClientControllerImp(clientName);
        clientController.listenToBroadcast();
        clientController.sendRegistrationMessage();
        clientController.sendToBroadcast();
    }

}
