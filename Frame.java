package JavaVS;

import javax.swing.*;

public class Frame extends JFrame {
	public Frame() {
		this.add(new Game());
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("Dot Game");
		this.setVisible(true);
		this.pack();
		this.setLocationRelativeTo(null);
	}
}
