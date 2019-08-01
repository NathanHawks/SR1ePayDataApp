package com.astromac.paydataapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class AutofireCalculatorActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

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
    private boolean isDmgMenuOpen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_autofire_calculator);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    public void showDmgLvlsList(View view) {
        isDmgMenuOpen = true;
        TextView tv1 = findViewById(R.id.dmgLevelMenu1);
        TextView tv2 = findViewById(R.id.dmgLevelMenu2);
        TextView tv3 = findViewById(R.id.dmgLevelMenu3);
        TextView tv4 = findViewById(R.id.dmgLevelMenu4);
        TextView tv5 = findViewById(R.id.dmgLevelMenu5);
        tv1.setVisibility(View.VISIBLE);
        tv2.setVisibility(View.VISIBLE);
        tv3.setVisibility(View.VISIBLE);
        tv4.setVisibility(View.VISIBLE);
        tv5.setVisibility(View.VISIBLE);
    }

    public void closeDmgMenu() {
        TextView tv1 = findViewById(R.id.dmgLevelMenu1);
        TextView tv2 = findViewById(R.id.dmgLevelMenu2);
        TextView tv3 = findViewById(R.id.dmgLevelMenu3);
        TextView tv4 = findViewById(R.id.dmgLevelMenu4);
        TextView tv5 = findViewById(R.id.dmgLevelMenu5);
        tv1.setVisibility(View.GONE);
        tv2.setVisibility(View.GONE);
        tv3.setVisibility(View.GONE);
        tv4.setVisibility(View.GONE);
        tv5.setVisibility(View.GONE);
        isDmgMenuOpen = false;
    }
    public void onItemClick(View view) {
        TextView dmgText = findViewById(R.id.dmgLevelText);
        // wtf, why is this necessary, why can't i just cast & call in one go?
        TextView tmp = (TextView) view;
        String dmgLevelSelection = (String) tmp.getText();
        dmgText.setText(dmgLevelSelection);
        dmgText.setTextColor(0xFFFF8844);
        dmgText.refreshDrawableState();
        closeDmgMenu();
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
        String dmg_level = ((TextView)findViewById(R.id.dmgLevelText)).getText().toString();
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

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) { drawer.closeDrawer(GravityCompat.START); }
        else if (isDmgMenuOpen) { closeDmgMenu(); } else { super.onBackPressed(); }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_dashboard) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_autofire) {
            Intent intent = new Intent(this, AutofireCalculatorActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_grenade) {

        } else if (id == R.id.nav_database) {

        } else if (id == R.id.nav_full_combat) {

        } else if (id == R.id.nav_settings) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
