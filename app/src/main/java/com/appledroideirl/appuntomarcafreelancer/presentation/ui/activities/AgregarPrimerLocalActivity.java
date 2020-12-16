package com.appledroideirl.appuntomarcafreelancer.presentation.ui.activities;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

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
import com.appledroideirl.appuntomarcafreelancer.presentation.ui.dialogfragment.NoEntryLoginDialog;
import com.appledroideirl.appuntomarcafreelancer.presentation.utils.Constants;
import com.appledroideirl.appuntomarcafreelancer.presentation.utils.Helper;
import com.appledroideirl.appuntomarcafreelancer.presentation.utils.SingleClick;
import com.appledroideirl.appuntomarcafreelancer.presentation.utils.TransparentProgressDialog;
import com.appledroideirl.appuntomarcafreelancer.presentation.utils.UserLocation;
import com.appledroideirl.appuntomarcafreelancer.presentation.view.UserView;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class AgregarPrimerLocalActivity extends BaseActivity implements LocationListener,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, UserView {

    TransparentProgressDialog loading;
    SingleClick singleClick;

    GoogleApiClient mGoogleApiClient;
    Double myLat, myLng;
    MapView mMapView;
    public static GoogleMap mMap;

    LatLng myLocation;

    @BindView(R.id.ivClose)
    ImageView ivClose;

    @BindView(R.id.ivContinue)
    ImageView ivContinue;

    @BindView(R.id.etLocalAdress)
    EditText etLocalAdress;

    @BindView(R.id.etLocalName)
    EditText etLocalName;

    @BindView(R.id.tvTittle)
    TextView tvTittle;


    Bundle savedInstanceStateee;

    private Marker marker;
    public static Double latitude;
    public static Double longitude;

    String localName, localAdress;

    UserPresenter userPresenter;

    Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        if (hasLocationPermission()) {
            UserLocation userLocation = new UserLocation(getApplication(), this);
            userLocation.getLocation();

            savedInstanceStateee = savedInstanceState;
            setContentView(R.layout.appu_agregar_primer_local_activity);
            injectView();
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
            buildGoogleApiClient();
            initUI(savedInstanceState);
            initGoogleMap();

        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION},
                    Constants.REQUEST_CODES.REQUEST_CODE_LOCATION);
        }

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
                    setContentView(R.layout.appu_agregar_primer_local_activity);
                    injectView();
                    getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
                    buildGoogleApiClient();
                    initUI(savedInstanceStateee);
                    initGoogleMap();
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


    void timerParaCargarUbicacion(Integer seconds) {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                setContentView(R.layout.appu_agregar_primer_local_activity);
                injectView();
                getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
                buildGoogleApiClient();
                initUI(savedInstanceStateee);
                initGoogleMap();
            }
        }, 500);
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

    private void displayCameraOrGallery(int i) {
        if (i == Constants.TYPE_PHOTO_IMPORT.CAMERA) {
            // openCamera();
        } else {
            // openGallery();
        }
    }

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                // .addApi(Places.GEO_DATA_API)
                .build();
        mGoogleApiClient.connect();
    }

    private void initGoogleMap() {
        try {
            MapsInitializer.initialize(this.getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        //inicializo mapa y le doy al boton mylocation activado
        mMapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap myMap) {
                mMap = myMap;

                CameraPosition cameraPosition = new CameraPosition.Builder().
                        target(myLocation).//posicion actual
                        //   tilt(90).//angulo de inclinacion
                                zoom(16).//zoom
                        build();

                mMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
            }
        });
    }

    private void onClickListener() {
        singleClick = new SingleClick() {
            @Override
            public void onSingleClick(View v) {
                switch (v.getId()) {
                    case R.id.ivContinue:

                        if(etLocalName.getText().toString().equals(""))
                        {
                            Toast.makeText(getContext(), "Ingrese nombre de local", Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            if(etLocalAdress.getText().toString().equals(""))
                            {
                                Toast.makeText(getContext(), "Ingrese dirección de local", Toast.LENGTH_LONG).show();
                            }
                            else
                            {
                                if(longitude==null & latitude==null)
                                {
                                    Toast.makeText(getContext(), "Toque el mapa en un punto", Toast.LENGTH_LONG).show();
                                }
                                else
                                {
                                    if (!loading.isShowing()) {
                                        loading.show();
                                    }

                                    ADDUSER();
                                }
                            }
                        }




                        break;
                    case R.id.ivClose:
                        finish();
                        break;

                }
            }
        };
    }

    void initUI(Bundle savedInstanceState) {

        if (CuentanosEresActivity.wsParameterAgregarUsuario.getType_user().equals("MARCA")) {
            tvTittle.setText("Agregar primer local");
        } else {
            tvTittle.setText("Estoy ubicado en");
        }

        onClickListener();
        ivContinue.setOnClickListener(singleClick);
        ivClose.setOnClickListener(singleClick);

        usuario = Helper.getUserAppPreference(getContext());
        loading = new TransparentProgressDialog(getContext());

        userPresenter = new UserPresenter();
        userPresenter.addView(this);

        mMapView = (MapView) findViewById(R.id.mapviewFreelancer);
        mMapView.onCreate(savedInstanceState);
        mMapView.onResume();

        Location userLocation = new Location("");

        if (Helper.getUserAppPreference(getApplicationContext()).isHasLocation()) {
            userLocation.setLatitude(Double.parseDouble(Helper.getUserAppPreference(getApplicationContext()).getLat()));
            userLocation.setLongitude(Double.parseDouble(Helper.getUserAppPreference(getApplicationContext()).getLng()));
        }

        myLat = userLocation.getLatitude();
        myLng = userLocation.getLongitude();
        myLocation = new LatLng(myLat, myLng);

    }

    void ADDUSER() {
        localName = etLocalName.getText().toString();
        localAdress = etLocalAdress.getText().toString();

        List<WsParameterDataUserStore> locales = new ArrayList<>();

        WsParameterDataUserStore wsParameterDataUserStore = new WsParameterDataUserStore();
        wsParameterDataUserStore.setAddress(localAdress);
        wsParameterDataUserStore.setFull_name(localName);
        wsParameterDataUserStore.setLatitude(latitude);
        wsParameterDataUserStore.setLongitude(longitude);
        wsParameterDataUserStore.setMain(1);
        wsParameterDataUserStore.setId_user("");

        locales.add(wsParameterDataUserStore);
        CuentanosEresActivity.wsParameterAgregarUsuario.setWsParameterDataUserStores(locales);
        CuentanosEresActivity.wsParameterAgregarUsuario.setId("");

        userPresenter.addUser(Helper.getUserAppPreference(getContext()).getToken(), CuentanosEresActivity.wsParameterAgregarUsuario);
    }

    @Override
    public void onResume() {
        super.onResume();
    }


    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

        CameraPosition cameraPosition=null;

        if(myLat==0.0 && myLng==0.0)
        {
            LatLng posiconN= new LatLng(-12.0567891,-77.039606);
            cameraPosition = new CameraPosition.Builder().
                    target(posiconN).//posicion actual
                    //   tilt(90).//angulo de inclinacion
                            zoom(18).//zoom
                    build();
        }
        else
        {
             cameraPosition = new CameraPosition.Builder().
                    target(myLocation).//posicion actual
                    //   tilt(90).//angulo de inclinacion
                            zoom(18).//zoom
                    build();
        }



        mMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {

                Double lat = latLng.latitude;
                Double lng = latLng.longitude;
                latitude = lat;
                longitude = lng;

                //remove previously placed Marker
                if (marker != null) {
                    marker.remove();
                }
                //place marker where user just clicked
                marker = mMap.addMarker(new MarkerOptions().position(latLng).title("Mi dirección").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
            }
        });

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

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

    void showAlertNoEntry(String mensaje)
    {

        Bundle args = new Bundle();
        args.putString("mensaje", mensaje);

        NoEntryLoginDialog df = new NoEntryLoginDialog();
        df.setArguments(args);
        df.setCancelable(false);
        df.show(getSupportFragmentManager(), "");

    }

    @Override
    public void userAddedSuccess(WsResponseAgregarUsuario wsResponseAgregarUsuario) {

        if (loading.isShowing()) {
            loading.dismiss();
        }


        if(wsResponseAgregarUsuario.getStatus()==-2)
        {
            showAlertNoEntry(wsResponseAgregarUsuario.getMessage());
        }
        else
        {
            SharedPreferences preferenciasssee = getApplicationContext().getSharedPreferences("FCM", Context.MODE_PRIVATE);
            String holaaatoken = preferenciasssee.getString("tokenfcm", "");
            usuario.setFcm(holaaatoken);
            usuario.setPassword(CuentanosEresActivity.wsParameterAgregarUsuario.getPassword());
            usuario.setLogged(true);
            usuario.setId(Integer.parseInt(wsResponseAgregarUsuario.getWsDataAgregarUsuario().getId()));
            usuario.setFull_name(wsResponseAgregarUsuario.getWsDataAgregarUsuario().getFull_name());
            usuario.setMail(wsResponseAgregarUsuario.getWsDataAgregarUsuario().getMail());
            usuario.setAbout(wsResponseAgregarUsuario.getWsDataAgregarUsuario().getAbout());
            usuario.setPhoto(wsResponseAgregarUsuario.getWsDataAgregarUsuario().getPhoto());
            usuario.setType_user(wsResponseAgregarUsuario.getWsDataAgregarUsuario().getType_user());

            usuario.setType_user(wsResponseAgregarUsuario.getWsDataAgregarUsuario().getType_user());
            usuario.setId_type_document(wsResponseAgregarUsuario.getWsDataAgregarUsuario().getId_type_document());
            usuario.setDocument_number(wsResponseAgregarUsuario.getWsDataAgregarUsuario().getDocument_number());

            usuario.setCellphone(wsResponseAgregarUsuario.getWsDataAgregarUsuario().getCellphone());

            if(wsResponseAgregarUsuario.getWsDataAgregarUsuario().getAvg_rate()!=null)
            {
                usuario.setAvg_rate(wsResponseAgregarUsuario.getWsDataAgregarUsuario().getAvg_rate());
            }


            Helper.saveUserAppPreference(getApplicationContext(), usuario);

            Toast.makeText(getContext(), "Registro exitoso", Toast.LENGTH_LONG).show();
            next(MainActivity.class, null);
        }


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