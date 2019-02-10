package textEditor;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class WindowMain {


    JFrame jFWindow;

    JTextArea jTAMainEditor;

    JMenuBar menuBar;
    JMenu menu;
    JMenuItem menuItemLoad;
    JMenuItem menuItemSave;
    JMenuItem menuItemSaveAs;
    JMenuItem menuItemExit;

    FileManager fileManager;


    public WindowMain() {
        this.jFWindow = new JFrame();
        jFWindow.setBounds(100, 100, 600, 900);
        jFWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        fileManager = new FileManager();
    }

    private void initializeComponents() {
        //Barra del menu superior
        menuBar = new JMenuBar();
        jFWindow.setJMenuBar(menuBar);
        menu = new JMenu("Archivo");
        menu.setMnemonic(KeyEvent.VK_A);
        menuBar.add(menu);
        menuItemLoad = new JMenuItem("Cargar", KeyEvent.VK_CONTROL);
        menuItemLoad.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, ActionEvent.CTRL_MASK));
        menuItemLoad.setMnemonic(KeyEvent.VK_C);
        menuItemSave = new JMenuItem("Guardar");
        menuItemSave.setMnemonic(KeyEvent.VK_G);
        menuItemSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK + ActionEvent.SHIFT_MASK));
        menuItemSave.setEnabled(false);
        menuItemSaveAs = new JMenuItem("Guardar como...");
        menuItemSaveAs.setMnemonic(KeyEvent.VK_G);
        menuItemSaveAs.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        menuItemSaveAs.setEnabled(false);
        menuItemExit = new JMenuItem("Salir");
        menuItemExit.setMnemonic(KeyEvent.VK_S);
        menuItemExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.CTRL_MASK));
        menu.add(menuItemLoad);
        menu.add(menuItemSave);
        menu.add(menuItemSaveAs);
        menu.add(menuItemExit);

        //JTextArea componentes del editor principal
        jTAMainEditor = new JTextArea();
        //Hace que siga en la siguente linea
        jTAMainEditor.setLineWrap(true);
        //Hace que salte la linea por palabra
        jTAMainEditor.setWrapStyleWord(true);

        JScrollPane jScrollPane = new JScrollPane(jTAMainEditor);
        jFWindow.add(jScrollPane);


    }

    private void initializeListeners() {
        menuItemExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        menuItemLoad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setCurrentDirectory(fileManager.getFile());
                fileChooser.setFileFilter(new FileNameExtensionFilter("Ficheros de texto", "txt"));
                fileChooser.setAcceptAllFileFilterUsed(false);
                int result = fileChooser.showOpenDialog(jFWindow);
                if(result == JFileChooser.APPROVE_OPTION){
                    fileManager.setFile(fileChooser.getSelectedFile());
                    try{
                        jTAMainEditor.setText(fileManager.readFile());
                        jTAMainEditor.requestFocus();
                        menuItemSave.setEnabled(true);
                        menuItemSaveAs.setEnabled(true);
                    } catch (Exception e1){
                        JOptionPane.showMessageDialog(jFWindow, "Error al abrir el fichero");
                        e1.printStackTrace();
                    }
                }
            }
        });
        menuItemSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                try {
//                    fileManager.writeFile(jTAMainEditor.getText());
//                    JOptionPane.showMessageDialog(jFWindow, "Fichero guardado");
//                } catch (Exception e1){
//                    JOptionPane.showMessageDialog(jFWindow, "Error al guardar el fichero");
//                    e1.printStackTrace();
//                }
            }
        });
        menuItemSaveAs.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });



    }

    public void initialize() {
        jFWindow.setVisible(true);
        initializeComponents();
        initializeListeners();
    }

}
