package com.appledroideirl.appuntomarcafreelancer.presentation.ui.activities;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import com.appledroideirl.appuntomarcafreelancer.R;
import com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.parameter.WsParameterEditarUsuario;
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
import com.appledroideirl.appuntomarcafreelancer.presentation.utils.SingleClick;
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
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;

public class EditProfileActivity extends BaseActivity implements UserView, ImportPhotoBottomFragment.Listener {

    @BindView(R.id.ivClose)
    ImageView ivClose;

    @BindView(R.id.ivUserImage)
    ImageView ivUserImage;

    @BindView(R.id.tvCambiarFoto)
    TextView tvCambiarFoto;


    @BindView(R.id.etEmail)
    EditText etEmail;

    @BindView(R.id.etPass)
    EditText etPass;

    @BindView(R.id.etPhone)
    EditText etPhone;

    @BindView(R.id.etNames)
    EditText etNames;

    @BindView(R.id.etAbout)
    EditText etAbout;

    @BindView(R.id.etDocNumber)
    EditText etDocNumber;

    @BindView(R.id.ivApply)
    ImageView ivApply;

    @BindView(R.id.spiTypeDocs)
    Spinner spiTypeDocs;


    UserPresenter userPresenter;

    int typeDoc = 0;
    String email, nombre, pass, repeatPass, docNumber, celPhone, about;
    Usuario usuario;
    Timestamp created_at;
    FirebaseStorage storage;
    StorageReference storageReference;
    String url="";
    byte[] pictureData;

    TransparentProgressDialog loading;
    SingleClick singleClick;
    private static final int REQUEST_WRITE_PERMISSION = 786;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.appu_editar_perfil);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        injectView();
        initUi();
    }

    void loadPresenter() {
        userPresenter = new UserPresenter();
        userPresenter.addView(this);
    }

    private void onClickListener() {
        singleClick = new SingleClick() {
            @Override
            public void onSingleClick(View v) {
                switch (v.getId()) {
                    case R.id.ivApply:

                        if(Helper.isConnectedToInternet(getContext()))
                        {
                            if (validations()) {
                                if (!loading.isShowing()) {
                                    loading.show();
                                }
                                uploadPhoto(ivUserImage);
                            }
                        }
                        else
                        {
                            Toast.makeText(getContext(), "No tienes Internet", Toast.LENGTH_LONG).show();
                        }




                        break;
                    case R.id.ivClose:
                        finish();
                        break;
                    case R.id.tvCambiarFoto:
                            requestPermission();
                        break;
                }
            }
        };
    }

    boolean validations() {
        boolean esCorrecto = false;

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

                    if (etPhone.getText().toString().equals("")) {
                        etPhone.setError("Ingrese teléfono");
                        etPhone.requestFocus();
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


        return esCorrecto;
    }

    void editUser() {
        email = etEmail.getText().toString();
        pass = etPass.getText().toString();
        nombre = etNames.getText().toString();
        docNumber = etDocNumber.getText().toString();
        celPhone = etPhone.getText().toString();
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

        WsParameterEditarUsuario wsParameterEditarUsuario = new WsParameterEditarUsuario();

        wsParameterEditarUsuario.setFull_name(nombre);
        wsParameterEditarUsuario.setCellphone(celPhone);
        wsParameterEditarUsuario.setSocial_name(nombre);
        wsParameterEditarUsuario.setAbout(about);
        wsParameterEditarUsuario.setId_type_document(typeDoc);
        wsParameterEditarUsuario.setDocument_number(docNumber);
        wsParameterEditarUsuario.setId(Helper.getUserAppPreference(getContext()).getId());

        if (url.equals("")) {
            wsParameterEditarUsuario.setPhoto(Helper.getUserAppPreference(getContext()).getPhoto());
        } else {
            wsParameterEditarUsuario.setPhoto(pictureData.toString());
        }

        userPresenter.editUser(Helper.getUserAppPreference(getContext()).getToken(), wsParameterEditarUsuario);
    }

    void initUi() {

        onClickListener();

        loading = new TransparentProgressDialog(getContext());

        SeteaSpinner(spiTypeDocs, getApplicationContext());

        usuario = Helper.getUserAppPreference(getContext());
        created_at = new Timestamp(Calendar.getInstance().getTime());

        Helper.urlToImageView(usuario.getPhoto(), ivUserImage, getContext());

        loadPresenter();

        etEmail.setText(usuario.getMail());
        etNames.setText(usuario.getFull_name());
        etPhone.setText(usuario.getCellphone());
        etPass.setText(usuario.getPassword());
        etAbout.setText(usuario.getAbout());
        etDocNumber.setText(usuario.getDocument_number());

        spiTypeDocs.setSelection(usuario.getId_type_document());

        ivClose.setOnClickListener(singleClick);
        ivApply.setOnClickListener(singleClick);
        tvCambiarFoto.setOnClickListener(singleClick);

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
                        editUser();

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
                                    editUser();
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
            editUser();
        }

    }


    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onBackPressed() {

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

        if (loading.isShowing()) {
            loading.dismiss();
        }

        usuario.setPassword(CuentanosEresActivity.wsParameterAgregarUsuario.getPassword());
        usuario.setLogged(true);
        usuario.setFull_name(nombre);
        usuario.setCellphone(celPhone);
        if(url.equals(""))
        {
            usuario.setPhoto(Helper.getUserAppPreference(getContext()).getPhoto());
        }
        else
        {
            usuario.setPhoto(url);
        }

        usuario.setAbout(about);
        usuario.setId_type_document(typeDoc);
        usuario.setDocument_number(docNumber);

        Helper.saveUserAppPreference(getApplicationContext(),usuario);

        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
        next(MainActivity.class,null);
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

    @Override
    public void onErrorImportPhoto(String var1) {

    }
}