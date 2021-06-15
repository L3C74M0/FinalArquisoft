package controller;

import java.rmi.*;
import ui.uiiPi;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import org.osoa.sca.annotations.Reference;

public class Client implements Runnable{
	private uiiPi ui;
	private long seed;
    private int points;
    private int servers;

	@Override
    public void run() {
        try {
            ui = new uiiPi();
            ui.setLocationRelativeTo(null);
            ui.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            ui.setVisible(true);
	    } catch (Exception e) {
            e.printStackTrace();
	    }
        eventos();
    }

    public void eventos(){
        ui.getCalculate().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!ui.getSeed().getText().isEmpty()
						&& !ui.getPoints().getText().isEmpty() && !ui.getNodes().getText().isEmpty()) {
                            seed = Long.parseLong(ui.getSeed().getText());
                            points = Integer.parseInt(ui.getPoints().getText());
                            servers = Integer.parseInt(ui.getNodes().getText());

                            //double pi = c.calcularPi(seed, points, servers);
                            ui.getResult().setText("Pi es = ");
				}
            }
        });
    }
}