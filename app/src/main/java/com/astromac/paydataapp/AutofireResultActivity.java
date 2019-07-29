package com.astromac.paydataapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class AutofireResultActivity extends AppCompatActivity {
    public int d6(boolean explode) {
        int roll = (int) (Math.random() * 6 + 1);
        if (roll == 6 && explode == true) {
            roll += d6(true);
        }
        return roll;
    }
    public String[] getDmgLevels() {
        String[] levels = new String[]{getString(R.string.opt_dmg_lvl_0),
                getString(R.string.opt_dmg_lvl_1),
                getString(R.string.opt_dmg_lvl_2),
                getString(R.string.opt_dmg_lvl_3),
                getString(R.string.opt_dmg_lvl_4)};
        return levels;
    }
    public String getDmgLabel(String dmgLevel) {
        String[] levels = getDmgLevels();
        int dmgIndex = -1;
        for (int x = 0; x < levels.length; x++) if (dmgLevel.contentEquals(levels[x])) dmgIndex = x;
        String[] labels = {getString(R.string.label_dmg_lvl_0), getString(R.string.label_dmg_lvl_1),
                getString(R.string.label_dmg_lvl_2), getString(R.string.label_dmg_lvl_3),
                getString(R.string.label_dmg_lvl_4)};
        return labels[dmgIndex];
    }
    public String changeDmgCategory(String dmgWas, int modBy) {
        String[] levels = getDmgLevels();
        int indexWas = -1;
        for (int x = 0; x < levels.length; x++) if (dmgWas.contentEquals(levels[x])) indexWas = x;
        int indexNew = indexWas + modBy;
        if (indexNew > levels.length -1) indexNew = levels.length -1;
        if (indexNew < 0) indexNew = 0;
        return levels[indexNew];
    }
    public void Reroll() {
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_autofire_result);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Reroll();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        String prf_name = intent.getStringExtra(MainActivity.PRF_NAME);
        String atk_rank = intent.getStringExtra(MainActivity.ATK_RANK);
        String def_rank = intent.getStringExtra(MainActivity.DEF_RANK);
        String nbr_shots = intent.getStringExtra(MainActivity.NBR_SHOTS);
        String base_tn = intent.getStringExtra(MainActivity.BASE_TN);
        String target_mods = intent.getStringExtra(MainActivity.TGT_MODS);
        String dmg_power = intent.getStringExtra(MainActivity.DMG_POWER);
        String dmg_level = intent.getStringExtra(MainActivity.DMG_LEVEL);
        String dmg_stage = intent.getStringExtra(MainActivity.DMG_STAGE);
        String armor_worn = intent.getStringExtra(MainActivity.ARMOR_WORN);
        String armor_derm = intent.getStringExtra(MainActivity.ARMOR_DERM);

        String message = getString(R.string.AutofireResult_Msg_DodgePool);
        String rollLog = "";
        int iNbrShots = Integer.parseInt(nbr_shots);
        int iAtkRank = Integer.parseInt(atk_rank);
        int iDefRank = Integer.parseInt(def_rank);
        int iArmDerm = Integer.parseInt(armor_derm);
        int iArmWorn = Integer.parseInt(armor_worn);
        int iMaxShots = iAtkRank + 1;
        int iDmgPower = Integer.parseInt(dmg_power);
        int iDmgStage = Integer.parseInt(dmg_stage);
        String max_shots = String.valueOf(iMaxShots);
        int atk_successes = 0;
        int def_successes = 0;
        int modified_tn = Integer.parseInt(base_tn) + Integer.parseInt(target_mods);
        // modify power for autofire
        iDmgPower++; // note: related vars now reflect different values (string input vs int)
        String mod_power = String.valueOf(iDmgPower);

        if (iNbrShots > iMaxShots) {
            iNbrShots = iMaxShots;
            nbr_shots = String.valueOf(iNbrShots);
            message = message + getString(R.string.AutofireResult_Msg_NbrShotsSkillCap);
        }
        if (iNbrShots > 7) {
            iNbrShots = 7;
            nbr_shots = String.valueOf(iNbrShots);
            message = message + getString(R.string.AutofireResult_Msg_NbrShotsRuleCap);
        }

        // take the shots
        for (int shot = 1; shot <= iNbrShots; shot++) {
            // reset
            modified_tn = Integer.parseInt(base_tn) + Integer.parseInt(target_mods);
            int recoil_mod = 0;
            atk_successes = 0;
            def_successes = 0;
            rollLog = rollLog + "\n\n---- SHOT #" + shot + " of " + nbr_shots + " ----";
            // recoil mod
            if (shot > 1) {
                recoil_mod = shot-1;
                // recoil_mod = iNbrShots; // uncomment this line for 1e Rules As Written
                modified_tn += recoil_mod;
                rollLog += getString(R.string.AutofireResult_RollLog_AddingRecoil) + recoil_mod
                        + " (TN " + modified_tn + ")";
            }
            // other mods
            // Success Test
            rollLog = rollLog + "\n  ፨ Shooter: ";
            for (int die = 1; die <= iAtkRank; die++) {
                int roll = d6(true);
                if (roll >= modified_tn) { atk_successes++; }
                String comma = (die > 1) ? ", " : "";
                rollLog = rollLog + comma + roll;
            }
            rollLog = rollLog + "; " + atk_successes + " successes.";
            // Calculate damage
            String dmgLevel = dmg_level;
            int modBy = (int) (Math.floor((atk_successes-1) / iDmgStage));
            if ((atk_successes - 1) / iDmgStage >= 1) {
                dmgLevel = changeDmgCategory(dmgLevel, modBy);
                rollLog += "\n  ▲ Damage raised " + modBy + " categories to " + dmgLevel + ".";
            }
            if (atk_successes > 0) {
                // Resistance Test
                rollLog += "\n  ፨ Target: ";
                for (int die = 1; die <= iDefRank+iArmDerm; die++) {
                    int roll = d6(true);
                    if (roll >= iDmgPower) {
                        def_successes++;
                    }
                    String comma = (die > 1) ? ", " : "";
                    rollLog += comma + roll;
                }
                // add worn ballistic armor as automatic successes - 1e Rules As Written
                def_successes += iArmWorn;
                rollLog += "; " + def_successes + " successes.";
                // Calculate damage reduction
                if (def_successes / iDmgStage >= 1) {
                    modBy = (int) (Math.floor(def_successes / iDmgStage));
                    dmgLevel = changeDmgCategory(dmgLevel, -modBy);
                    rollLog += "\n  ▼ Damage reduced " + modBy + " categories to " + dmgLevel + ".";
                }
                rollLog += "\n" + getDmgLabel(dmgLevel);
            } else rollLog += "\n" + getString(R.string.label_dmg_lvl_miss);

        }

        TextView textView = findViewById(R.id.resultTextScrollable);
        textView.setText(message + rollLog
                + "\n----"
                + "\nProfile: " + prf_name
                + "\nShooter Skill Rank: " + atk_rank
                + "\nTarget Body Rank: " + def_rank
                + "\nTarget Dermal Armor: " + ((Integer.parseInt(armor_derm) > 0) ? armor_derm : "none")
                + "\nTarget Wearing Ballistic Armor: " + ((Integer.parseInt(armor_worn) > 0) ? armor_worn : "no")
                + "\nShots Fired: " + nbr_shots
                + "\nTarget Number: " + base_tn + ((target_mods.contentEquals("0")) ? "" : "; Mods: " + target_mods)
                + "\nBase Damage Code: " + dmg_power + dmg_level + dmg_stage
                + "\nModified Damage Power: " + mod_power + "\n"
        );
    }
}
