package com.appledroideirl.appuntomarcafreelancer.presentation.ui.activities;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.location.Location;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.Base64;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.appledroideirl.appuntomarcafreelancer.R;
import com.appledroideirl.appuntomarcafreelancer.presentation.ui.fragments.TabHome;
import com.appledroideirl.appuntomarcafreelancer.presentation.utils.Constants;
import com.appledroideirl.appuntomarcafreelancer.presentation.utils.UserLocation;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;


public class MainActivity extends BaseActivity implements
        UserLocation.CierraLocation{

    FrameLayout containerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (hasLocationPermission()) {
            UserLocation userLocation = new UserLocation(getApplication(), this);
            userLocation.getLocation();
            initUI();
         //   addNotification();
            loadTabHomeFragment();
        /*    if (!secondsToOfferViewed()) {
                timerSecondsToOffer(12);
            }*/
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION},
                    Constants.REQUEST_CODES.REQUEST_CODE_LOCATION);
        }
    }


    void addNotification() {

        String cuerpo="cuerpo", titulo="titulo";

        int notifyID = 1;
        String CHANNEL_ID = "my_channel_01";// The id of the channel.
        CharSequence name = "channel_name";// The user-visible name of the channel.

        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);


        if (android.os.Build.VERSION.SDK_INT < Build.VERSION_CODES.O_MR1) {

            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)

                    .setContentTitle(titulo)
                    .setContentText(cuerpo)
                    .setContentIntent(pendingIntent)
                    // .setSmallIcon(R.drawable.ic_launcher)
                    .setSmallIcon(R.mipmap.avataro)
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT);

            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

            // notificationId is a unique int for each notification that you must define
            notificationManager.notify(notifyID, builder.build());

        }


        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, name, importance);
            mChannel.enableVibration(true);
            mChannel.setLightColor(Color.BLUE);
            mChannel.enableLights(true);
/*
            channel.setSound(Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://" + getApplicationContext().getPackageName() + "/" + R.raw.notification),
                    new AudioAttributes.Builder()
                            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                            .setUsage(AudioAttributes.USAGE_NOTIFICATION)
                            .build());
            */

            // Create a notification and set the notification channel.
            Notification notification = new Notification.Builder(getApplicationContext())
                    .setContentTitle(titulo)
                    .setContentText(cuerpo)
                    .setContentIntent(pendingIntent)
                    //  .setSmallIcon(R.drawable.ic_launcher)
                    .setSmallIcon(R.mipmap.avataro)
                    //  .setLargeIcon(BitmapFactory.decodeResource(this.getResources(), R.mipmap.logo_appunto))
                    .setChannelId(CHANNEL_ID)
                    .build();

            NotificationManager mNotificationManager =
                    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            mNotificationManager.createNotificationChannel(mChannel);

            // Issue the notification.
            mNotificationManager.notify(notifyID, notification);


        }


    }


    private void initUI() {
        containerView = (FrameLayout) findViewById(R.id.containerView);

    }

    public boolean hasLocationPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
        ) {
            return false;
        } else {
            return true;
        }
    }


    @Override
    public void onCloseLocation(Boolean hasLocation, Location location) {
        if (hasLocation) {
        }
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap photo = null;
        if (requestCode == 66502 || requestCode == Constants.REQUEST_CODES.REQUEST_CODE_CAMERA) {
            photo = (Bitmap) data.getExtras().get("data");
        } else {
            if (requestCode == 65660 || requestCode == Constants.REQUEST_CODES.REQUEST_CODE_STORAGE) {
                final Uri imageUri = data.getData();
                InputStream imageStream = null;
                try {
                    imageStream = getContentResolver().openInputStream(imageUri);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                photo = BitmapFactory.decodeStream(imageStream);
            }
        }
        String encodedImage = encodeImage(photo);
       // ProfileFragment.goPicture(encodedImage);
    }

    private String encodeImage(Bitmap bm) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] b = baos.toByteArray();
        String encImage = Base64.encodeToString(b, Base64.DEFAULT);
        return encImage;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case Constants.REQUEST_CODES.REQUEST_CODE_LOCATION: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    UserLocation userLocation = new UserLocation(getApplication(), this);
                    userLocation.getLocation();
                    timerParaCargarUbicacion(1);

                } else {
                    initUI();
                    loadTabHomeFragment();
                  //  timerSecondsToOffer(10);
                }
                return;
            }

            case Constants.REQUEST_CODES.REQUEST_CODE_CAMERA: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    displayCameraOrGallery(Constants.TYPE_PHOTO_IMPORT.CAMERA);
                } else {
                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.main_request_permision_storage), Toast.LENGTH_LONG).show();
                }
                return;
            }
        }
    }

    private void displayCameraOrGallery(int i) {
        if (i == Constants.TYPE_PHOTO_IMPORT.CAMERA) {
            openCamera();
        } else {
            openGallery();
        }
    }

    private void openCamera() {
        Intent cameraIntent = new Intent(
                android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        if (cameraIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(cameraIntent, Constants.REQUEST_CODES.REQUEST_CODE_CAMERA);
        }
    }

    private void openGallery() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, Constants.REQUEST_CODES.REQUEST_CODE_STORAGE);
    }


    void timerParaCargarUbicacion(Integer seconds) {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                initUI();
                loadTabHomeFragment();
                //write your code here to be executed after 1 second
            }
        }, 500);
    }


    void loadTabHomeFragment() {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            TabHome homeFragment = new TabHome();
            fragmentTransaction.replace(R.id.containerView, homeFragment);
            fragmentTransaction.commit();
    }


    @Override
    public void onBackPressed() {
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

}
