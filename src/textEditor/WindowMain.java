package textEditor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class WindowMain {


    JFrame window;

    JMenuBar menuBar;
    JMenu menu;
    JMenuItem menuItem1;
    JMenuItem menuItem2;
    JMenuItem menuItem3;


    public WindowMain() {
        this.window = new JFrame();
        window.setBounds(100,100, 250,150);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void initializeComponents() {
        menuBar = new JMenuBar();

        window.setJMenuBar(menuBar);

        menu = new JMenu("Archivo");
        menuBar.add(menu);

        menuItem1 = new JMenuItem("Cargar", KeyEvent.VK_CONTROL);
        menuItem1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, ActionEvent.CTRL_MASK));
        menuItem2= new JMenuItem("Guardar");
        menuItem2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        menuItem3 = new JMenuItem("Salir");
        menuItem3.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.CTRL_MASK));
        menu.add(menuItem1);
        menu.add(menuItem2);
        menu.add(menuItem3);

    }

    private void initializeListeners() {


    }

    public void initialize() {
        window.setVisible(true);
        initializeComponents();
        initializeListeners();
    }

}
