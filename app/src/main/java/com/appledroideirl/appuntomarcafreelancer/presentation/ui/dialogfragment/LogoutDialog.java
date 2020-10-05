package com.appledroideirl.appuntomarcafreelancer.presentation.ui.dialogfragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.fragment.app.DialogFragment;

import com.appledroideirl.appuntomarcafreelancer.R;
import com.appledroideirl.appuntomarcafreelancer.domain.model.Usuario;
import com.appledroideirl.appuntomarcafreelancer.presentation.ui.activities.LoginActivity;
import com.appledroideirl.appuntomarcafreelancer.presentation.ui.activities.Splash;
import com.appledroideirl.appuntomarcafreelancer.presentation.utils.Helper;

public class LogoutDialog extends DialogFragment {

    ImageView ivClose;
    RelativeLayout rlNotNow,rlLogOut;


    @Override
    public void onActivityCreated(Bundle arg0) {
        super.onActivityCreated(arg0);
        getDialog().getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = getActivity().getLayoutInflater().inflate(R.layout.appu_logout_dialog, new LinearLayout(getActivity()), false);
        ivClose = (ImageView) view.findViewById(R.id.ivClose);
        rlNotNow = (RelativeLayout) view.findViewById(R.id.rlNotNow);
        rlLogOut = (RelativeLayout) view.findViewById(R.id.rlLogOut);
        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dismiss();

            }
        });

        rlNotNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dismiss();

            }
        });

        rlLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Usuario usuario= Helper.getUserAppPreference(getContext());
                usuario.setPassword("");
                usuario.setLogged(false);
                usuario.setId(0);
                usuario.setFull_name("");
                usuario.setMail("");
                usuario.setAbout("");
                usuario.setPhoto("");
                usuario.setType_user("");

                Helper.saveUserAppPreference(getContext(),usuario);

                Intent intent= new Intent(getActivity(), Splash.class);
                startActivity(intent);

            }
        });


        Dialog builder = new Dialog(getActivity());
        builder.requestWindowFeature(Window.FEATURE_NO_TITLE);
        builder.setContentView(view);
        return builder;
    }


    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.WRAP_CONTENT;
            dialog.getWindow().setLayout(width, height);
            dialog.getWindow().getAttributes().alpha = 1f;
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        dismiss();
    }

}
