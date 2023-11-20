package com.uth.ejercicio_2_3;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.uth.e23.R;
import com.uth.ejercicio_2_3.database.databa_picture;
import com.uth.ejercicio_2_3.models.cPhotograh;

import java.util.Objects;


public class picture_data extends AppCompatActivity
{

    private databa_picture oDBPICTURE;

    private TextInputEditText oTextInputEditTextDescripcion;
    private MaterialButton oMaterialButtonSalvar;

    private ImageView oSignatureView;

    private Bitmap oBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_signature);

        this.oTextInputEditTextDescripcion = this.findViewById(R.id.txtDescripcion);
        this.oMaterialButtonSalvar = this.findViewById(R.id.btn_salvar);
        this.oSignatureView = this.findViewById(R.id.signature_view);
        oDBPICTURE = new databa_picture(picture_data.this);
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        this.oMaterialButtonSalvar.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("UseCompatLoadingForDrawables")
            @Override
            public void onClick(View view)
            {
                if(oBitmap != null && !Objects.requireNonNull(oTextInputEditTextDescripcion.getText()).toString().isEmpty())
                {
                    cPhotograh oS = new cPhotograh();
                    oS.setDescripcion(oTextInputEditTextDescripcion.getText().toString());
                    oS.setFoto(oBitmap);
                    if(oDBPICTURE.createSignature(oS)){
                        Toast.makeText(picture_data.this, "USUARIO GUARDA CON EXITO", Toast.LENGTH_SHORT).show();
                        oTextInputEditTextDescripcion.setText("");
                        oSignatureView.setImageDrawable(getResources().getDrawable(R.drawable.profile2));
                    }else{
                        Toast.makeText(picture_data.this, "ERROR AL GUARDAR LA FIRMA", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(picture_data.this, "EXISTEN DATOS VACIOS", Toast.LENGTH_SHORT).show();
                }
            }
        });

        this.oSignatureView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirCamera();
            }
        });
    }



    @SuppressLint("QueryPermissionsNeeded")
    public void abrirCamera(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(intent.resolveActivity(getPackageManager()) != null)
        {
            startActivityForResult(intent,1);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1 && resultCode == RESULT_OK)
        {
            assert data != null;
            Bundle extras = data.getExtras();
            oBitmap = (Bitmap) extras.get("data");
            oSignatureView.setImageBitmap(oBitmap);
        }
    }

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }
}