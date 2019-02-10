package model;

import controller.FileManager;

import javax.swing.*;
import java.awt.*;

public class Images {

    private FileManager fileManager;


    private ImageIcon imagePath;
    private ImageIcon imageLoad;
    private ImageIcon imageSave;
    private ImageIcon imageSaveAs;

    public Images(FileManager fileManager) {
        this.fileManager = fileManager;
        getBuferedImage();
    }

    private void getBuferedImage() {
        //ImagePath || Pirula! x.x, seguro que hay una forma mas elegante de hacer esto, lo siento. u.u
        this.imagePath = new ImageIcon(this.fileManager.getImageLoad());
        this.imageLoad = new ImageIcon(this.imagePath.getImage().getScaledInstance(20,20, Image.SCALE_DEFAULT));
        this.imagePath = new ImageIcon(this.fileManager.getImageSave());
        this.imageSave = new ImageIcon(this.imagePath.getImage().getScaledInstance(20,20, Image.SCALE_DEFAULT));
        this.imagePath = new ImageIcon(this.fileManager.getImageSaveAs());
        this.imageSaveAs = new ImageIcon(this.imagePath.getImage().getScaledInstance(20,20, Image.SCALE_DEFAULT));
    }

    public ImageIcon getImageLoad() {
        return imageLoad;
    }

    public ImageIcon getImageSave() {
        return imageSave;
    }

    public ImageIcon getImageSaveAs() {
        return imageSaveAs;
    }
}
