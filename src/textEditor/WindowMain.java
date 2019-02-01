package textEditor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class WindowMain {


    JFrame jFWindow;

    JTextArea jTAMainEditor;

    JMenuBar menuBar;
    JMenu menu;
    JMenuItem menuItem1;
    JMenuItem menuItem2;
    JMenuItem menuItem3;




    public WindowMain() {
        this.jFWindow = new JFrame();
        jFWindow.setBounds(100,100, 600,900);
        jFWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void initializeComponents() {
        //Barra del menu superior
        menuBar = new JMenuBar();
        jFWindow.setJMenuBar(menuBar);
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

        //JTextArea componentes del editor principal
        jTAMainEditor = new JTextArea();
        //Hace que siga en la siguente linea
        jTAMainEditor.setLineWrap(true);
        //Hace que salte la linea por palabra
        jTAMainEditor.setWrapStyleWord(true);

        jFWindow.add(jTAMainEditor);


    }

    private void initializeListeners() {


    }

    public void initialize() {
        jFWindow.setVisible(true);
        initializeComponents();
        initializeListeners();
    }

}
