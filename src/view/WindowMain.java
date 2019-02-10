package view;

import controller.FileManager;
import model.Images;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.DefaultEditorKit;
import javax.swing.text.Document;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class WindowMain {

    //Ventana Principal
    JFrame jFWindow;
    //Area de texto principal
    JTextArea jTAMainEditor;
//    JPanel jPNorth,jPSouth;

    //Menu
    JMenuBar menuBar;
    JMenu menu;
    JMenu menu2;
    JMenuItem menuItemLoad;
    JMenuItem menuItemSave;
    JMenuItem menuItemSaveAs;
    JMenuItem menuItemExit;
    JMenuItem menuItemRemplace;

    //Barra de herramientas
    JToolBar jTBMain;

    JButton jBSave;
    JButton jBLoad;
    JButton jBSaveAs;
    JButton jBRemplace;
    JButton jBCopy;
    JButton jBPaste;
    JTextField jTFSearch;
    JButton jBSearch;
    JButton jBCheck;

    Images images;

    //Gestor de ficheros
    boolean checker;
    FileManager fileManager;
    JDialog dialog;
    JTextPane jTPOrigin;
    JTextPane jTPDestiny;

    public WindowMain() {
        this.jFWindow = new JFrame();
        jFWindow.setBounds(100, 100, 600, 900);
        jFWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        fileManager = new FileManager();
        images = new Images(fileManager);
    }

    private void initializeComponents() {
        checker = true;
//        jFWindow.setLayout(new GridLayout());
        //JTextArea componentes del editor principal
        jTAMainEditor = new JTextArea();
        //Hace que siga en la siguente linea
        jTAMainEditor.setLineWrap(true);
        //Hace que salte la linea por palabra
        jTAMainEditor.setWrapStyleWord(true);
        jTAMainEditor.setEnabled(false);
        //Barra de Scroll
        JScrollPane jScrollPane = new JScrollPane(jTAMainEditor);
        jFWindow.add(jScrollPane, BorderLayout.CENTER);

        //Barra Archivo del menu superior
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
        menuItemSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        menuItemSave.setEnabled(false);
        menuItemSaveAs = new JMenuItem("Guardar como...");
        menuItemSaveAs.setMnemonic(KeyEvent.VK_G);
        menuItemSaveAs.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK + ActionEvent.SHIFT_MASK));
        menuItemSaveAs.setEnabled(false);
        menuItemExit = new JMenuItem("Salir");
        menuItemExit.setMnemonic(KeyEvent.VK_S);
        menuItemExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.CTRL_MASK));
        menu.add(menuItemLoad);
        menu.add(menuItemSave);
        menu.add(menuItemSaveAs);
        menu.add(menuItemExit);

        //Barra Editar del menu superior
        menu2 = new JMenu("Editar");
        menu2.setMnemonic(KeyEvent.VK_E);
        menuBar.add(menu2);
        menuItemRemplace = new JMenuItem("Reemplazar", KeyEvent.VK_CONTROL);
        menuItemRemplace.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.CTRL_MASK));
        menuItemRemplace.setMnemonic(KeyEvent.VK_R);
        menuItemRemplace.setEnabled(false);
        menu2.add(menuItemRemplace);


        //Barra de herramientas
        jTBMain = new JToolBar();
        //Impide mover barra de herramientas
        jTBMain.setFloatable(false);

        //Botones dentro de la barra de herramientas
        jBLoad = new JButton(images.getImageLoad());
        jTBMain.add(jBLoad);
        jBSave = new JButton(images.getImageSave());
        jTBMain.add(jBSave);
        jBSaveAs = new JButton(images.getImageSaveAs());
        jTBMain.add(jBSaveAs);
        jBCopy = new JButton(images.getImageCopy());
        jTBMain.add(jBCopy);
        jBPaste = new JButton(images.getImagePaste());
        jTBMain.add(jBPaste);

        jTFSearch = new JTextField();
        jTBMain.add(jTFSearch, BorderLayout.EAST);
        jBSearch = new JButton(images.getImageSearch());
        jTBMain.add(jBSearch, BorderLayout.EAST);
//        jBCheck = new JButton(images.getImageCheck());
//        jTBMain.add(jBCheck);
        jFWindow.add(jTBMain, BorderLayout.NORTH);
        jBRemplace = new JButton("Reemplazar");
        jTPDestiny = new JTextPane();
        jTPOrigin = new JTextPane();


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
                if (result == JFileChooser.APPROVE_OPTION) {
                    fileManager.setFile(fileChooser.getSelectedFile());
                    try {
                        jTAMainEditor.setText(fileManager.readFile());
                        jTAMainEditor.requestFocus();
                        menuItemSave.setEnabled(true);
                        menuItemSaveAs.setEnabled(true);
                        menuItemRemplace.setEnabled(true);
                        jTAMainEditor.setEnabled(true);
                    } catch (Exception e1) {
                        JOptionPane.showMessageDialog(jFWindow, "Error al abrir el fichero");
                        e1.printStackTrace();
                    }
                }
            }
        });
        jBLoad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setCurrentDirectory(fileManager.getFile());
                fileChooser.setFileFilter(new FileNameExtensionFilter("Ficheros de texto", "txt"));
                fileChooser.setAcceptAllFileFilterUsed(false);
                int result = fileChooser.showOpenDialog(jFWindow);
                if (result == JFileChooser.APPROVE_OPTION) {
                    fileManager.setFile(fileChooser.getSelectedFile());
                    try {
                        jTAMainEditor.setText(fileManager.readFile());
                        jTAMainEditor.requestFocus();
                        menuItemSave.setEnabled(true);
                        menuItemSaveAs.setEnabled(true);
                    } catch (Exception e1) {
                        JOptionPane.showMessageDialog(jFWindow, "Error al abrir el fichero");
                        e1.printStackTrace();
                    }
                }
            }
        });
        menuItemSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    fileManager.writeFile(jTAMainEditor.getText());
                    JOptionPane.showMessageDialog(jFWindow, "Fichero guardado");
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(jFWindow, "Error al guardar el fichero");
                    e1.printStackTrace();
                }
            }
        });
        jBSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    fileManager.writeFile(jTAMainEditor.getText());
                    JOptionPane.showMessageDialog(jFWindow, "Fichero guardado");
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(jFWindow, "Error al guardar el fichero");
                    e1.printStackTrace();
                }
            }
        });
        menuItemSaveAs.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setCurrentDirectory(fileManager.getFile());
                fileChooser.setAcceptAllFileFilterUsed(false);
                fileChooser.setFileFilter(new FileNameExtensionFilter("Ficheros de texto", "txt"));
                int result = fileChooser.showOpenDialog(jFWindow);
                if (result == JFileChooser.APPROVE_OPTION) {
                    if (!fileChooser.getSelectedFile().getName().endsWith(".txt")) {
                        String aux = fileChooser.getSelectedFile() + ".txt";
                        fileChooser.setSelectedFile(new File(aux));
                    }
                    fileManager.setFile(fileChooser.getSelectedFile());
                    menuItemSave.doClick();
                }
            }
        });
        jBSaveAs.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setCurrentDirectory(fileManager.getFile());
                fileChooser.setAcceptAllFileFilterUsed(false);
                fileChooser.setFileFilter(new FileNameExtensionFilter("Ficheros de texto", "txt"));
                int result = fileChooser.showOpenDialog(jFWindow);
                if (result == JFileChooser.APPROVE_OPTION) {
                    if (!fileChooser.getSelectedFile().getName().endsWith(".txt")) {
                        String aux = fileChooser.getSelectedFile() + ".txt";
                        fileChooser.setSelectedFile(new File(aux));
                    }
                    fileManager.setFile(fileChooser.getSelectedFile());
                    menuItemSave.doClick();
                }
            }
        });
        menuItemRemplace.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checker) {
                    checker = false;
                    dialog = new JDialog(jFWindow, "Remplacar...", false);
                    dialog.setLayout(new GridLayout(5, 1));
                    dialog.add(new JLabel("Texto Origen: "));
                    dialog.add(jTPOrigin);
                    dialog.add(new JLabel("Texto Destino: "));
                    dialog.add(jTPDestiny);

                    dialog.add(jBRemplace);
                    dialog.setBounds(jFWindow.getX() * 2, jFWindow.getY() * 2, 300, 300);
                    dialog.setVisible(true);


                    //Escucha al boton de cerrar para cambiar la variable a true;
                    dialog.addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosing(WindowEvent e) {
                            super.windowClosing(e);
                            checker = true;
                        }
                    });
                }

            }
        });
        jBRemplace.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jTAMainEditor.setText(jTAMainEditor.getText().replace(jTPOrigin.getText(), jTPDestiny.getText()));
            }
        });
        jBCopy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jTAMainEditor.copy();
            }
        });
        jBPaste.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jTAMainEditor.paste();
            }
        });
        jBSearch.addActionListener(new ActionListener() {
            /**
             * Copy and Paste
             * https://stackoverflow.com/questions/13437865/java-scroll-to-specific-text-inside-jtextarea/13438455#13438455
             *
             * @param e
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                int pos = 0;
                String find = jTFSearch.getText().toLowerCase();
                jTAMainEditor.requestFocusInWindow();
                if (find != null && find.length() > 0) {
                    Document document = jTAMainEditor.getDocument();
                    int findLength = find.length();
                    try {
                        boolean found = false;
                        if (pos + findLength > document.getLength()) {
                            pos = 0;
                        }
                        while (pos + findLength <= document.getLength()) {
                            String match = document.getText(pos, findLength).toLowerCase();
                            if (match.equals(find)) {
                                found = true;
                                break;
                            }
                            pos++;
                        }

                        if (found) {
                            Rectangle viewRect = jTAMainEditor.modelToView(pos);
                            jTAMainEditor.scrollRectToVisible(viewRect);
                            jTAMainEditor.setCaretPosition(pos + findLength);
                            jTAMainEditor.moveCaretPosition(pos);
                            pos += findLength;
                        } else {
                            JOptionPane.showMessageDialog(jFWindow, "No se encuentra la palabra que buscas");
                        }

                    } catch (Exception exp) {
                        exp.printStackTrace();
                    }
                }
            }
        });

    }

    public void initialize() {
        jFWindow.setVisible(true);
        initializeComponents();
        initializeListeners();
    }

}
