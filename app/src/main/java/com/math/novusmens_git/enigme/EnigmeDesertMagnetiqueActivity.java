package com.math.novusmens_git.enigme;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.math.novusmens_git.R;

public class EnigmeDesertMagnetiqueActivity extends Enigme {

    //niveau 1
    private BTree bTreeNiv1;

    //niveau 2
    private BTree bTreeNiv2_G;
    private BTree bTreeNiv2_D;

    //niveau 3
    private BTree bTreeNiv3_G_G;
    private BTree bTreeNiv3_G_D;
    private BTree bTreeNiv3_D_G;
    private BTree bTreeNiv3_D_D;

    //reponses
    private BTree bTreeREP_G_G_G;
    private BTree bTreeREP_G_G_D;
    private BTree bTreeREP_G_D_G;
    private BTree bTreeREP_G_D_D;
    private BTree bTreeREP_D_G_G;
    private BTree bTreeREP_D_G_D;
    private BTree bTreeREP_D_D_G;
    private BTree bTreeREP_D_D_D;

    //btree en cours d'affichage
    private BTree bTreeCourant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_enigme_desert_magnetique);
        if(getSupportActionBar() != null)
            getSupportActionBar().hide();

        setNumEnigme(getResources().getInteger(R.integer.level1_enigmeNarrative2DesertMagnetique));
        setNumNiveau(getResources().getInteger(R.integer.level1));
        Log.d("data", "num niveau devrait être 1 il est : " + getNumNiveau());
        Log.d("data", "num enigme devrait être 6 il est : " + getNumEnigme());

        //initialisation des btree
        bTreeNiv1 = new BTree(getString(R.string.DM_Q1), getString(R.string.DM_Q1_RG), getString(R.string.DM_Q1_RD));

        bTreeNiv2_G = new BTree(getString(R.string.DM_Q2_G), getString(R.string.DM_Q2_G_RG), getString(R.string.DM_Q2_G_RD));
        bTreeNiv2_D = new BTree(getString(R.string.DM_Q2_D), getString(R.string.DM_Q2_D_RG), getString(R.string.DM_Q2_D_RD));

        bTreeNiv3_G_G = new BTree(getString(R.string.DM_Q3_G_G), getString(R.string.DM_Q3_G_G_RG), getString(R.string.DM_Q3_G_G_RD));
        bTreeNiv3_G_D = new BTree(getString(R.string.DM_Q3_G_D), getString(R.string.DM_Q3_G_D_RG), getString(R.string.DM_Q3_G_D_RD));
        bTreeNiv3_D_G = new BTree(getString(R.string.DM_Q3_D_G), getString(R.string.DM_Q3_D_G_RG), getString(R.string.DM_Q3_D_G_RD));
        bTreeNiv3_D_D = new BTree(getString(R.string.DM_Q3_D_D), getString(R.string.DM_Q3_D_D_RG), getString(R.string.DM_Q3_D_D_RD));

        bTreeREP_G_G_G = new BTree(getString(R.string.DM_Res_Q3_G_G_RG));
        bTreeREP_G_G_D = new BTree(getString(R.string.DM_Res_Q3_G_G_RD));
        bTreeREP_G_D_G = new BTree(getString(R.string.DM_Res_Q3_G_D_RG));
        bTreeREP_G_D_D = new BTree(getString(R.string.DM_Res_Q3_G_D_RD));
        bTreeREP_D_G_G = new BTree(getString(R.string.DM_Res_Q3_D_G_RG));
        bTreeREP_D_G_D = new BTree(getString(R.string.DM_Res_Q3_D_G_RD));
        bTreeREP_D_D_G = new BTree(getString(R.string.DM_Res_Q3_D_D_RG));
        bTreeREP_D_D_D = new BTree(getString(R.string.DM_Res_Q3_D_D_RD));
        //binding des btree
        try {
            bTreeNiv1.setLeftTree(bTreeNiv2_G);
            bTreeNiv1.setRightTree(bTreeNiv2_D);

            bTreeNiv2_G.setLeftTree(bTreeNiv3_G_G);
            bTreeNiv2_G.setRightTree(bTreeNiv3_G_D);
            bTreeNiv2_D.setLeftTree(bTreeNiv3_D_G);
            bTreeNiv2_D.setRightTree(bTreeNiv3_D_D);

            bTreeNiv3_G_G.setLeftTree(bTreeREP_G_G_G);
            bTreeNiv3_G_G.setRightTree(bTreeREP_G_G_D);
            bTreeNiv3_G_D.setLeftTree(bTreeREP_G_D_G);
            bTreeNiv3_G_D.setRightTree(bTreeREP_G_D_D);
            bTreeNiv3_D_G.setLeftTree(bTreeREP_D_G_G);
            bTreeNiv3_D_G.setRightTree(bTreeREP_D_G_D);
            bTreeNiv3_D_D.setLeftTree(bTreeREP_D_D_G);
            bTreeNiv3_D_D.setRightTree(bTreeREP_D_D_D);
        } catch (Exception e) {
            Log.d("btree", "exception lors du binding : " + e);
        }

        //initalisation des view
        ((TextView)findViewById(R.id.txtViewDesertMag)).setText(bTreeNiv1.getQuestion());
        ((Button)findViewById(R.id.btnDesertRG)).setText(bTreeNiv1.getReponseGauche());
        ((Button)findViewById(R.id.btnDesertRD)).setText(bTreeNiv1.getReponseDroite());

        bTreeCourant = bTreeNiv1;

        findViewById(R.id.btnDesertRG).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    bTreeCourant = bTreeCourant.getLeftTree();
                } catch (Exception e) {
                    Log.d("btree", "exception lors du changement de btreeCourant : " + e);
                }
                ((TextView)findViewById(R.id.txtViewDesertMag)).setText(bTreeCourant.getQuestion());
                ((Button)findViewById(R.id.btnDesertRG)).setText(bTreeCourant.getReponseGauche());
                ((Button)findViewById(R.id.btnDesertRD)).setText(bTreeCourant.getReponseDroite());
            }
        });

        findViewById(R.id.btnDesertRD).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    bTreeCourant = bTreeCourant.getRightTree();
                } catch (Exception e) {
                    Log.d("btree", "exception lors du changement de btreeCourant : " + e);
                }
                ((TextView)findViewById(R.id.txtViewDesertMag)).setText(bTreeCourant.getQuestion());
                ((Button)findViewById(R.id.btnDesertRG)).setText(bTreeCourant.getReponseGauche());
                ((Button)findViewById(R.id.btnDesertRD)).setText(bTreeCourant.getReponseDroite());
            }
        });

    }

    @Override
    public boolean estResolue() {
        return false;
    }

    @Override
    public void resultat() {

    }
}
