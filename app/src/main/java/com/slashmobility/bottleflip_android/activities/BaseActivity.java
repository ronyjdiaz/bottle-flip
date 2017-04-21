package com.slashmobility.bottleflip_android.activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;

import com.slashmobility.bottleflip_android.R;
import com.slashmobility.bottleflip_android.utils.BaseUtils;


/**
 * @author Rony Diaz
 * @version %I, %G
 * @since 1.0
 */
public class BaseActivity extends AppCompatActivity {

    private BaseUtils mSlashBaseUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Init base utils
        mSlashBaseUtils = new BaseUtils(BaseActivity.this);
    }

    /**
     * Muestra un progress dialog que puede ser cancelable o no
     *
     * @param cancelable
     */
    public void showProgressDialog(boolean cancelable) {
        mSlashBaseUtils.showProgressDialog(cancelable);
    }

    /**
     * Oculta el progress dialog si se está mostrando
     */
    public void hideProgressDialog() {
        mSlashBaseUtils.hideProgressDialog();
    }

    /**
     * Muestra un dialogo con el mensaje pasado por parametro
     *
     * @param message
     */
    public void showMessageDialog(String message) {
        mSlashBaseUtils.showMessageDialog(null, message, true);
    }

    /**
     * Muestra un dialogo con el mensaje pasado por parametro
     *
     * @param message
     * @param cancelable
     */
    protected void showMessageDialog(String message, boolean cancelable) {
        mSlashBaseUtils.showMessageDialog(null, message, cancelable);
    }

    /**
     * Muestra un dialogo con el titulo y mensaje pasado por parametro
     *
     * @param title
     * @param message
     * @param cancelable
     */
    public void showMessageDialog(String title, String message, boolean cancelable) {
        mSlashBaseUtils.showMessageDialog(title, message, cancelable);
    }

    /**
     * Ocultar el mensaje
     */
    public void hideMessageDialog() {
        mSlashBaseUtils.hideMessageDialog();
    }

    /**
     * Oculta el teclado del editText pasado por parámetro en caso de que esté abierto
     *
     * @param editText
     */
    protected void hideKeyboard(EditText editText) {
        mSlashBaseUtils.hideKeyboard(editText);
    }

    public void openActivity(Class<?> calledActivity) {
        Intent myIntent = new Intent(this, calledActivity);
        this.startActivity(myIntent);
    }

    public void openActivity(Class<?> calledActivity, Bundle bundle) {
        Intent myIntent = new Intent(this, calledActivity);
        myIntent.putExtras(bundle);
        this.startActivity(myIntent);
    }

    public void changeColorBarNotification(int color){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(ContextCompat.getColor(this, color));
        }
    }

    public void changeToFragment(Fragment fragment) {
        if(fragment!=null){
           android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container,fragment);
            fragmentTransaction.commit();
        }
    }

}