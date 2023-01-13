package com.example.finalprojectprototype;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class CustomLoginDialog extends DialogFragment {
    private EditText studentName;
    private EditText studentNum;
    private DialogListener listener;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view = getActivity().getLayoutInflater().inflate(R.layout.login_layout, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle("Login");
        builder.setView(view);

        studentName = (EditText) view.findViewById(R.id.userName);
        studentNum = (EditText) view.findViewById(R.id.userNum);

        builder.setNegativeButton("Cancel", null);
        builder.setPositiveButton("Sign In", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String user = studentName.getText().toString();
                String userNum = studentNum.getText().toString();
                if(userNum.length() != 8) {
                    Toast.makeText(getActivity(),"Please enter a valid student number",Toast.LENGTH_SHORT).show();
                } else {
                    listener.pullInputs(user,userNum);
                    Toast.makeText(getActivity(), "Sign-In successful", Toast.LENGTH_SHORT).show();

                }
            }
        });
        return builder.create();
    }
    @Override
    public void onCancel(@NonNull DialogInterface dialog) {
        Toast.makeText(getActivity(), "Sign-In cancelled", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            listener = (DialogListener) context;
        } catch (ClassCastException e) {
           throw new ClassCastException(context.toString() + "Must implement DialogListener");
        }
    }
    public interface DialogListener{
        void pullInputs(String u, String n);
    }
}

