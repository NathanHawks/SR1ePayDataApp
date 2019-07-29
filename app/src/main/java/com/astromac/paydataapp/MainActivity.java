package com.astromac.paydataapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {
    public static final String PRF_NAME = "com.astromac.paydataapp.PROFILE";
    public static final String ATK_RANK = "com.astromac.paydataapp.ATTACK";
    public static final String DEF_RANK = "com.astromac.paydataapp.DEFENSE";
    public static final String BASE_TN  = "com.astromac.paydataapp.BASETN";
    public static final String TGT_MODS = "com.astromac.paydayaapp.TGT_MODS";
    public static final String NBR_SHOTS = "com.astromac.paydataapp.SHOTS";
    public static final String DMG_LEVEL = "com.astromac.paydataapp.DPOWER";
    public static final String DMG_POWER = "com.astromac.paydataapp.DLEVEL";
    public static final String DMG_STAGE = "com.astromac.paydataapp.DSTAGE";
    public static final String ARMOR_WORN = "com.astromac.paydataapp.AWORN";
    public static final String ARMOR_DERM = "com.astromac.paydataapp.ADERMAL";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // initialize UI elements
        // 2nd Damage code field (wound severity)
        Spinner dmgLevel = findViewById(R.id.dmgLevelSpin);
        String[] levels = new String[]{getString(R.string.opt_dmg_lvl_0),
                getString(R.string.opt_dmg_lvl_1),
                getString(R.string.opt_dmg_lvl_2),
                getString(R.string.opt_dmg_lvl_3),
                getString(R.string.opt_dmg_lvl_4)};
        dmgLevel.setAdapter(new ArrayAdapter<>
                (this, android.R.layout.simple_spinner_dropdown_item, levels)
        );
    }

    /** Called when the user taps the Send button */
    public void sendMessage(View view) {
        Intent intent = new Intent(this, AutofireResultActivity.class);
        String prf_name = ((EditText) findViewById(R.id.pcNameText)).getText().toString();
        String atk_rank = ((EditText)findViewById(R.id.atkRankText)).getText().toString();
        String def_rank = ((EditText)findViewById(R.id.defRankText)).getText().toString();
        String nbr_shots = ((EditText)findViewById(R.id.nbrShotsText)).getText().toString();
        String base_tn = ((EditText)findViewById(R.id.baseTNText)).getText().toString();
        String target_mods = ((EditText)findViewById(R.id.targetModsText)).getText().toString();
        String dmg_power = ((EditText)findViewById(R.id.dmgPowerText)).getText().toString();
        String dmg_level = ((Spinner)findViewById(R.id.dmgLevelSpin)).getSelectedItem().toString();
        String dmg_stage = ((EditText)findViewById(R.id.dmgStagingText)).getText().toString();
        String armor_derm = ((EditText)findViewById(R.id.armorDermalText)).getText().toString();
        String armor_worn = ((EditText)findViewById(R.id.armorWornText)).getText().toString();
        if (armor_derm.contentEquals("")) armor_derm = "0";
        if (armor_worn.contentEquals("")) armor_worn = "0";
        if (target_mods.contentEquals("")) target_mods = "0";
        intent.putExtra(PRF_NAME, prf_name)
                .putExtra(ATK_RANK, atk_rank)
                .putExtra(DEF_RANK, def_rank)
                .putExtra(NBR_SHOTS, nbr_shots)
                .putExtra(BASE_TN, base_tn)
                .putExtra(TGT_MODS, target_mods)
                .putExtra(DMG_POWER, dmg_power)
                .putExtra(DMG_LEVEL, dmg_level)
                .putExtra(DMG_STAGE, dmg_stage)
                .putExtra(ARMOR_DERM, armor_derm)
                .putExtra(ARMOR_WORN, armor_worn);
        startActivity(intent);
    }
}
