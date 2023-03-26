package Model;

import javafx.application.Platform;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Cliente implements Runnable{
    private AnchorPane anchor;
    private Restaurant restaurant;
    private static String[] positions;
    public Cliente(AnchorPane anchor, Restaurant restaurant){
        this.anchor = anchor;
        this.restaurant=restaurant;
        positions = new String[24];
        positions[0] = "235 60";
        positions[1] = "336 60";
        positions[2] = "438 60";
        positions[3] = "546 60";
        positions[4] = "644 60";
        positions[5] = "750 60";
        positions[6] = "833 60";

        positions[7] = "929 114";
        positions[8] = "929 224";
        positions[9] = "929 307";
        positions[10] = "836 398";

        positions[11] = "740 398";
        positions[12] = "644 389";
        positions[13] = "550 389";
        positions[14] = "445 389";
        positions[15] = "336 389";
        positions[16] = "235 389";
        positions[17] = "166 315";

        positions[18] = "166 224";
        positions[19] = "166 127";
    }
    @Override
    public void run() {
        Circle cliente = new Circle(15, Color.BURLYWOOD);
        Platform.runLater(() -> {
            cliente.setLayoutX(519);
            cliente.setLayoutY(1133);
            anchor.getChildren().add(cliente);
        });
        //Reserver una mesa
        for(int i=0;i<10;i++){
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Platform.runLater(()-> cliente.setLayoutY(cliente.getLayoutY()-50));
        }
        boolean reservation = restaurant.reservar(Thread.currentThread().getName());
        if(reservation) {
            Platform.runLater(()-> cliente.setFill(Color.TRANSPARENT));
        }
        else {
            Platform.runLater(()-> cliente.setFill(Color.TRANSPARENT));
            System.out.println("estoy saliendo");
        }

        //Posicionar a los clientes en lase mesas con forme el id
        int numMesa = restaurant.entrar(Thread.currentThread().getName());
        String[] layout = positions[numMesa].split(" ");
        Platform.runLater(()-> {
            cliente.setLayoutX(Integer.parseInt(layout[0]));
            cliente.setLayoutY(Integer.parseInt(layout[1])+50);
        });

        try {
            Thread.sleep(800);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Ordenar
        Platform.runLater(()-> cliente.setFill(Color.MAGENTA));
        restaurant.ordenar();

        try {
            Thread.sleep(700);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Comer
        Platform.runLater(()-> cliente.setFill(Color.MAROON));
        restaurant.comer();
        try {
            Thread.sleep(19000);
        } catch (InterruptedException e) {
            e.printStackTrace();}

        //Salir
        Platform.runLater(() -> cliente.setFill(Color.TRANSPARENT));

        restaurant.salir(numMesa);
    }
}