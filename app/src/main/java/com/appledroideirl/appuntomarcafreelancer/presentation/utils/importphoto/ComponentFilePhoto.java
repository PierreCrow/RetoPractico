package com.appledroideirl.appuntomarcafreelancer.presentation.utils.importphoto;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.appledroideirl.appuntomarcafreelancer.presentation.utils.Constants;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

import pl.aprilapps.easyphotopicker.EasyImage;

public class ComponentFilePhoto {

    public static final int CAMERA = 1;
    public static final int GALLERY = 2;

    private File file;
    private File fileMin;
    private String description;
    private int source;

    public ComponentFilePhoto(Context context, File file, int source, String description) {
        String name = UUID.randomUUID().toString();
        this.file = this.setFile(name, context, file);
        this.fileMin = this.setFileMin(name, context, file);
        this.source = source;
        this.description = description;
    }

    // region getter and setter

    public File getFile() {
        return file;
    }

    private File setFile(String name, Context context, File file) {

        byte[] fileContents = getFileToByte(file);
        String filename = name.concat(Constants.IMAGE_EXTENSION.DOT_JPG);
        File newFile = new File(context.getFilesDir(), filename);

        FileOutputStream outputStream;

        try {
            outputStream = context.openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream.write(fileContents);
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return newFile;
    }

    public int getSource() {
        return source;
    }

    public void setSource(int source) {
        this.source = source;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // endregion

    public static int getTypeFileSource(EasyImage.ImageSource imageSource) {
        int typeFileSource = CAMERA;
        switch (imageSource) {
            case GALLERY:
                typeFileSource = GALLERY;
                break;
            case CAMERA_IMAGE:
                typeFileSource = CAMERA;
                break;
        }
        return typeFileSource;
    }

    public String getFileExtension() {
        String path = this.file.getAbsolutePath();
        String extension = path.substring(path.lastIndexOf(".") + 1);
        return extension;
    }

    public int getFileSizeKb() {
        int fileSize = Integer.parseInt(String.valueOf(this.file.length() / 1024));
        return fileSize;
    }

    public String getFileName() {
        String path = this.file.getAbsolutePath();
        String filename = path.substring(path.lastIndexOf("/") + 1, path.lastIndexOf("."));
        return filename;
    }

    public static byte[] getFileToByte(File _file) {
        byte[] bytesArray = null;
        try {
            bytesArray = new byte[(int) _file.length()];
            FileInputStream fis = new FileInputStream(_file);
            fis.read(bytesArray); //read file into bytes[]
            fis.close();
        } catch (FileNotFoundException e1) {
            bytesArray = null;
        } catch (IOException e2) {
            bytesArray = null;
        }
        return bytesArray;
    }

    public String getDevicePath() {
        return this.file.getAbsolutePath();
    }

    // region file min

    public File getFileMin() {
        return fileMin;
    }

    private File setFileMin(String name, Context context, File file) {

        byte[] fileContents = getFileMinToByte(file);
        String filename = name.concat(Constants.IMAGE_EXTENSION.DASH_MIN).concat(Constants.IMAGE_EXTENSION.DOT_JPG);
        File newFile = new File(context.getFilesDir(), filename);

        FileOutputStream outputStream;

        try {
            outputStream = context.openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream.write(fileContents);
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return newFile;
    }

    public String getFileMinExtension() {
        String path = this.fileMin.getAbsolutePath();
        String extension = path.substring(path.lastIndexOf(".") + 1);
        return extension;
    }

    public int getFileMinSizeKb() {
        int fileSize = Integer.parseInt(String.valueOf(this.fileMin.length() / 1024));
        return fileSize;
    }

    public String getFileMinName() {
        String path = this.fileMin.getAbsolutePath();
        String filename = path.substring(path.lastIndexOf("/") + 1, path.lastIndexOf("."));
        return filename;
    }

    public String getDevicePathMin() {
        return this.fileMin.getAbsolutePath();
    }

    public static byte[] getFileMinToByte(File _file) {
        byte[] bytesArray = null;
        try {
            final int THUMBNAIL_SIZE = 248;
            FileInputStream fis = new FileInputStream(_file);
            Bitmap imageBitmap = BitmapFactory.decodeStream(fis);
            imageBitmap = Bitmap.createScaledBitmap(imageBitmap, THUMBNAIL_SIZE, THUMBNAIL_SIZE, false);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            bytesArray = baos.toByteArray();
        } catch (Exception ex) {
            bytesArray = null;
        }
        return bytesArray;
    }

    // endregion
}
