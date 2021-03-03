package com.appledroideirl.appuntomarcafreelancer.presentation.ui.activities;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import com.appledroideirl.appuntomarcafreelancer.R;
import com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.parameter.WsParameterDataUserStore;
import com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.response.WsResponseAcceptRequest;
import com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.response.WsResponseAddBankAccount;
import com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.response.WsResponseAddSubService;
import com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.response.WsResponseAgregarLocal;
import com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.response.WsResponseAgregarServicio;
import com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.response.WsResponseAgregarUsuario;
import com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.response.WsResponseAvailableDateHour;
import com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.response.WsResponseListBankAccount;
import com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.response.WsResponseListLocales;
import com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.response.WsResponseLogin;
import com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.response.WsResponseRefuseRequest;
import com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.response.WsResponseReportes;
import com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.response.WsResponseRequestList;
import com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.response.WsResponseServices;
import com.appledroideirl.appuntomarcafreelancer.domain.model.Usuario;
import com.appledroideirl.appuntomarcafreelancer.presentation.presenter.UserPresenter;
import com.appledroideirl.appuntomarcafreelancer.presentation.utils.Constants;
import com.appledroideirl.appuntomarcafreelancer.presentation.utils.Helper;
import com.appledroideirl.appuntomarcafreelancer.presentation.utils.TransparentProgressDialog;
import com.appledroideirl.appuntomarcafreelancer.presentation.utils.importphoto.ImportPhotoBottomFragment;
import com.appledroideirl.appuntomarcafreelancer.presentation.view.UserView;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.Timestamp;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;

public class CompletarDatosFreelancerActivity
        extends BaseActivity implements ImportPhotoBottomFragment.Listener, UserView {

    @BindView(R.id.ivClose)
    ImageView ivClose;

    @BindView(R.id.ivContinue)
    ImageView ivContinue;

    @BindView(R.id.ivUserImage)
    ImageView ivUserImage;


    @BindView(R.id.etEmail)
    EditText etEmail;

    @BindView(R.id.etNames)
    EditText etNames;

    @BindView(R.id.etPass)
    EditText etPass;

    @BindView(R.id.etRepeatPass)
    EditText etRepeatPass;

    @BindView(R.id.etDocNumber)
    EditText etDocNumber;


    @BindView(R.id.etCellPhone)
    EditText etCellPhone;

    @BindView(R.id.etAbout)
    EditText etAbout;


    @BindView(R.id.tvSubirFoto)
    TextView tvSubirFoto;

    @BindView(R.id.tvTerminos)
    TextView tvTerminos;

    @BindView(R.id.chxTerminos)
    CheckBox chxTerminos;

    @BindView(R.id.spiTypeDocs)
    Spinner spiTypeDocs;

    private static final int REQUEST_WRITE_PERMISSION = 786;
    TransparentProgressDialog loading;

    int typeDoc = 0;
    String email, nombre, pass, repeatPass, docNumber, celPhone, about;
    Timestamp created_at;
    FirebaseStorage storage;
    StorageReference storageReference;
    String url;
    byte[] pictureData;

    UserPresenter userPresenter;

    Usuario usuario;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.appu_completar_datos_freelancer_activity);
        injectView();
        preventKeyboard();
        initUi();
    }


    @Override
    public void onResume() {
        super.onResume();
    }

    void initUi() {

        loadPresenter();

        created_at = new Timestamp(Calendar.getInstance().getTime());
        loading = new TransparentProgressDialog(getContext());
        usuario = Helper.getUserAppPreference(getContext());

        SeteaSpinner(spiTypeDocs, getApplicationContext());


        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });

        tvSubirFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestPermission();
            }
        });


        ivContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (validations()) {
                    if (!loading.isShowing()) {
                        loading.show();
                    }
                    uploadPhoto(ivUserImage);
                }

            }
        });


        tvTerminos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String filename = Constants.PDF_URL.Terminons_Partners;
                String urlFinal = "http://docs.google.com/gview?embedded=true&url=" + filename;
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(urlFinal));
                startActivity(browserIntent);

            }
        });


    }

    void loadPresenter() {
        userPresenter = new UserPresenter();
        userPresenter.addView(this);
    }

    boolean validations() {
        boolean esCorrecto = false;

        if (chxTerminos.isChecked()) {
            if (etEmail.getText().toString().equals("")) {
                etEmail.setError("Ingrese correo");
                etEmail.requestFocus();
            } else {
                if (etNames.getText().toString().equals("")) {
                    etNames.setError("Ingrese nombres");
                    etNames.requestFocus();
                } else {

                    if (etPass.getText().toString().equals("")) {
                        etPass.setError("Ingrese contraseña");
                        etPass.requestFocus();
                    } else {

                        if (etPass.getText().toString().length() < 6) {
                            etPass.setError("Mínimo 6 caracteres");
                            etPass.requestFocus();
                        } else {
                            if (etRepeatPass.getText().toString().equals("")) {
                                etRepeatPass.setError("Ingrese contraseña");
                                etRepeatPass.requestFocus();
                            } else {
                                if (!etPass.getText().toString().equals(etRepeatPass.getText().toString())) {
                                    etNames.setError("Contraseña distinta");
                                    etRepeatPass.setError("Contraseña distinta");
                                    etPass.requestFocus();
                                } else {
                                    if (etCellPhone.getText().toString().equals("")) {
                                        etCellPhone.setError("Ingrese teléfono");
                                        etCellPhone.requestFocus();
                                    } else {
                                        if (spiTypeDocs.getSelectedItem().toString().equals("Tipo de documento")) {
                                            Toast.makeText(getApplicationContext(), "Ingrese tipo de documento", Toast.LENGTH_SHORT).show();
                                        } else {
                                            if (etDocNumber.getText().toString().equals("")) {
                                                etDocNumber.setError("Ingrese número de documento");
                                                etDocNumber.requestFocus();
                                            } else {
                                                esCorrecto = true;
                                            }

                                        }
                                    }
                                }

                            }
                        }
                    }
                }
            }


        } else {
            Toast.makeText(getContext(), "Acepte los términos y condiciones", Toast.LENGTH_LONG).show();
        }


        return esCorrecto;
    }

    void saveDataUser() {
        email = etEmail.getText().toString();
        pass = etPass.getText().toString();
        repeatPass = etRepeatPass.getText().toString();
        nombre = etNames.getText().toString();
        docNumber = etDocNumber.getText().toString();
        celPhone = "51" + etCellPhone.getText().toString();
        about = etAbout.getText().toString();

        if (spiTypeDocs.getSelectedItem().toString().equals("DNI")) {
            typeDoc = 1;
        }

        if (spiTypeDocs.getSelectedItem().toString().equals("RUC")) {
            typeDoc = 2;
        }
        if (spiTypeDocs.getSelectedItem().toString().equals("Carnet Ext.")) {
            typeDoc = 3;
        }


        CuentanosEresActivity.wsParameterAgregarUsuario.setFull_name(nombre);
        CuentanosEresActivity.wsParameterAgregarUsuario.setSocial_name(nombre);
        CuentanosEresActivity.wsParameterAgregarUsuario.setMail(email);
        CuentanosEresActivity.wsParameterAgregarUsuario.setPassword(pass);
        CuentanosEresActivity.wsParameterAgregarUsuario.setCellphone(celPhone);
        CuentanosEresActivity.wsParameterAgregarUsuario.setId_type_document(typeDoc);
        CuentanosEresActivity.wsParameterAgregarUsuario.setDocument_number(docNumber);
        CuentanosEresActivity.wsParameterAgregarUsuario.setPhoto(url);
        CuentanosEresActivity.wsParameterAgregarUsuario.setAbout(about);
        CuentanosEresActivity.wsParameterAgregarUsuario.setId("");


        if (loading.isShowing()) {
            loading.dismiss();
        }
        next(AgregarPrimerLocalActivity.class, null);


        /*
        if(CuentanosEresActivity.wsParameterAgregarUsuario.getType_user().equals("FREELANCER"))
        {
            List<WsParameterDataUserStore> wsParameterDataUserStores= new ArrayList<>();
            CuentanosEresActivity.wsParameterAgregarUsuario.setWsParameterDataUserStores(wsParameterDataUserStores);
            userPresenter.addUser(Helper.getUserAppPreference(getContext()).getToken(),CuentanosEresActivity.wsParameterAgregarUsuario);
        }
        else
        {
            if (loading.isShowing()) {
                loading.dismiss();
            }
            next(AgregarPrimerLocalActivity.class, null);
        }
*/


    }


    void SeteaSpinner(Spinner spiner, Context ctx) {
        //   final List<String> almacenamientos = new ArrayList<>(Arrays.asList(Constantes.NATIONALITIES));

        List<String> lista = new ArrayList<>();
        lista.add("Tipo de documento");
        lista.add("DNI");
        lista.add("RUC");
        lista.add("Carnet Ext.");

        final ArrayAdapter<String> spinnerArrayAdapter1 = new ArrayAdapter<String>(
                ctx, R.layout.spineritem, lista) {
            @Override
            public boolean isEnabled(int position) {
                if (position == 0) {
                    return false;
                } else {
                    return true;
                }
            }

            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                return view;
            }
        };

        spinnerArrayAdapter1.setDropDownViewResource(R.layout.spineritem);
        spiner.setAdapter(spinnerArrayAdapter1);
    }


    private void requestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA}, REQUEST_WRITE_PERMISSION);
        } else {
            showBottomSourcePhoto(0);
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == REQUEST_WRITE_PERMISSION && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            showBottomSourcePhoto(0);
        } else {
            showAlert("Error", "No podra guardar su foto. ¿Desea continuar?");
        }
    }


    void showAlert(String titulo, String pregunta) {
        new AlertDialog.Builder(getApplicationContext(), R.style.AlertDialogCustom)
                .setTitle(titulo)
                .setMessage(pregunta)

                // Specifying a listener allows you to take an action before dismissing the dialog.
                // The dialog is automatically dismissed when a dialog button is clicked.
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {


                    }
                })

                // A null listener allows the button to dismiss the dialog and take no further action.
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        requestPermission();
                    }
                })
                //  .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }


    void showAlertCargaIncorrecta(String titulo, String pregunta) {
        new AlertDialog.Builder(getApplicationContext(), R.style.AlertDialogCustom)
                .setTitle(titulo)
                .setMessage(pregunta)

                // Specifying a listener allows you to take an action before dismissing the dialog.
                // The dialog is automatically dismissed when a dialog button is clicked.
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        saveDataUser();

                    }
                })

                // A null listener allows the button to dismiss the dialog and take no further action.
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                //  .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
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
            } else {
                if (requestCode == 131196 || requestCode == Constants.REQUEST_CODES.REQUEST_CODE_STORAGE) {
                    final Uri imageUri = data.getData();
                    InputStream imageStream = null;
                    try {
                        imageStream = getContentResolver().openInputStream(imageUri);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    photo = BitmapFactory.decodeStream(imageStream);
                } else {
                    if (requestCode == 197574 || requestCode == Constants.REQUEST_CODES.REQUEST_CODE_CAMERA) {
                        photo = (Bitmap) data.getExtras().get("data");
                    }
                }
            }
        }

        Bitmap bmpToUploadStorage = scaleBitmap(photo, 300, 300);
        ivUserImage.setImageBitmap(bmpToUploadStorage);

        ivUserImage.setVisibility(View.VISIBLE);

        //envia a storage firebase
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        photo.compress(Bitmap.CompressFormat.JPEG, 25, baos);
        pictureData = baos.toByteArray();

    }


    private Bitmap scaleBitmap(Bitmap bm, int maxWidth, int maxHeight) {
        int width = bm.getWidth();
        int height = bm.getHeight();

        Log.v("Pictures", "Width and height are " + width + "--" + height);

        if (width > height) {
            // landscape
            float ratio = (float) width / maxWidth;
            width = maxWidth;
            height = (int) (height / ratio);
        } else if (height > width) {
            // portrait
            float ratio = (float) height / maxHeight;
            height = maxHeight;
            width = (int) (width / ratio);
        } else {
            // square
            height = maxHeight;
            width = maxWidth;
        }

        Log.v("Pictures", "after scaling Width and height are " + width + "--" + height);

        bm = Bitmap.createScaledBitmap(bm, width, height, true);
        return bm;
    }


    private static Bitmap rotateImage(Bitmap img, int degree) {
        Matrix matrix = new Matrix();
        matrix.postRotate(degree);
        Bitmap rotatedImg = Bitmap.createBitmap(img, 0, 0, img.getWidth(), img.getHeight(), matrix, true);
        img.recycle();
        return rotatedImg;
    }


    void uploadPhoto(ImageView imageView) {
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        String timeStampado = String.valueOf(created_at.getSeconds());
        StorageReference ref = storageReference.child("mobile/users/user_" + timeStampado);

        if (pictureData != null) {
            ref.putBytes(pictureData)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            //  progressDialog.dismiss();

                            ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    url = uri.toString();
                                    saveDataUser();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception exception) {
                                    //mensaje no se puedo cargar la foto intentar luego
                                    url = "";
                                    loading.dismiss();
                                    showAlertCargaIncorrecta("Carga Incorrecta", "Foto no guardada. Intente luego");

                                }
                            });
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            loading.dismiss();
                            showAlertCargaIncorrecta("Carga Incorrecta", "Foto no guardada. Error:" + e.getMessage());

                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot
                                    .getTotalByteCount());
                            // progressDialog.setMessage("Uploaded "+(int)progress+"%");
                        }
                    });
        } else {
            url = "";
            saveDataUser();
        }

    }


    void preventKeyboard() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    @Override
    public void onBackPressed() {

    }

    @Override
    public void onErrorImportPhoto(String var1) {
        Toast.makeText(getApplicationContext(), var1, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void tokenGenerated(String token) {

    }

    @Override
    public void loginSuccess(WsResponseLogin wsResponseLogin) {

    }

    @Override
    public void requestListSuccess(WsResponseRequestList wsResponseRequestList) {

    }

    @Override
    public void requestAceptedSuccess(WsResponseAcceptRequest wsResponseAcceptRequest) {

    }

    @Override
    public void requestRefusedSuccess(WsResponseRefuseRequest responseRefuseRequest) {

    }

    @Override
    public void listAvailableDateHourSuccess(WsResponseAvailableDateHour wsResponseAvailableDateHour) {

    }

    @Override
    public void listReportSuccess(WsResponseReportes wsResponseReportes) {

    }

    @Override
    public void listServices(WsResponseServices wsResponseServices) {

    }

    @Override
    public void listUserServices(WsResponseServices wsResponseServices) {

    }

    @Override
    public void servicesAddesSuccess(WsResponseAgregarServicio wsResponseAgregarServicio) {

    }

    @Override
    public void dateAvailableAddedSuccess(String message) {

    }

    @Override
    public void userAddedSuccess(WsResponseAgregarUsuario wsResponseAgregarUsuario) {

        if (loading.isShowing()) {
            loading.dismiss();
        }
        SharedPreferences preferenciasssee = getApplicationContext().getSharedPreferences("FCM", Context.MODE_PRIVATE);
        String holaaatoken = preferenciasssee.getString("tokenfcm", "");
        usuario.setFcm(holaaatoken);
        usuario.setPassword(CuentanosEresActivity.wsParameterAgregarUsuario.getPassword());
        usuario.setLogged(true);
        usuario.setId(Integer.parseInt(wsResponseAgregarUsuario.getWsDataAgregarUsuario().getId()));
        usuario.setFull_name(wsResponseAgregarUsuario.getWsDataAgregarUsuario().getFull_name());
        usuario.setMail(wsResponseAgregarUsuario.getWsDataAgregarUsuario().getMail());
        usuario.setPhoto(wsResponseAgregarUsuario.getWsDataAgregarUsuario().getPhoto());
        usuario.setType_user(wsResponseAgregarUsuario.getWsDataAgregarUsuario().getType_user());
        usuario.setAbout(wsResponseAgregarUsuario.getWsDataAgregarUsuario().getAbout());
        usuario.setType_user(wsResponseAgregarUsuario.getWsDataAgregarUsuario().getType_user());
        usuario.setId_type_document(wsResponseAgregarUsuario.getWsDataAgregarUsuario().getId_type_document());
        usuario.setDocument_number(wsResponseAgregarUsuario.getWsDataAgregarUsuario().getDocument_number());

        if (wsResponseAgregarUsuario.getWsDataAgregarUsuario().getAvg_rate() != null) {
            usuario.setAvg_rate(wsResponseAgregarUsuario.getWsDataAgregarUsuario().getAvg_rate());
        }


        Helper.saveUserAppPreference(getApplicationContext(), usuario);

        Toast.makeText(getContext(), "Registro exitoso", Toast.LENGTH_LONG).show();
        next(MainActivity.class, null);

    }

    @Override
    public void bankAccountAddedSuccess(WsResponseAddBankAccount wsResponseAddBankAccount) {

    }

    @Override
    public void listBankAccount(WsResponseListBankAccount wsResponseListBankAccount) {

    }

    @Override
    public void subServiceAddedSuccess(WsResponseAddSubService wsResponseAddSubService) {

    }

    @Override
    public void userEditedSuccess(String message) {

    }

    @Override
    public void listLocalesSuccess(WsResponseListLocales wsResponseListLocales) {

    }

    @Override
    public void localAddedSuccess(WsResponseAgregarLocal wsResponseAgregarLocal) {

    }

    @Override
    public void recoveryPasswordSuccess(String mensaje) {

    }

    @Override
    public void deleteLocalSuccess(String mensaje) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showErrorMessage(String message) {
        if (loading.isShowing()) {
            loading.dismiss();
        }
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public Context getContext() {
        return this;
    }
}