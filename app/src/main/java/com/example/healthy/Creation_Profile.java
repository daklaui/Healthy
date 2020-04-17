package com.example.healthy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.divyanshu.colorseekbar.ColorSeekBar;
import com.example.healthy.Activities.LoginActivity;
import com.example.healthy.Adapters.CustomViewPagerNoSwip;
import com.example.healthy.Classes.Account;
import com.example.healthy.Classes.Diet;
import com.example.healthy.Classes.Historique_Regime;
import com.example.healthy.Classes.Profile;
import com.example.healthy.Classes.Regime;
import com.example.healthy.Database.DatabaseHandler;
import com.example.healthy.Fragments_Profile.Step1;
import com.example.healthy.Fragments_Profile.Step2;
import com.example.healthy.Fragments_Profile.Step3;
import com.example.healthy.Fragments_Profile.Step4;
import com.example.healthy.Fragments_Profile.Step5;
import com.example.healthy.Fragments_Profile.Step6;
import com.example.healthy.Fragments_Profile.Step7;
import com.example.healthy.Fragments_Profile.Step8;
import com.github.anastr.speedviewlib.SpeedView;
import com.github.anastr.speedviewlib.components.Section;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.regex.Pattern;

import in.unicodelabs.kdgaugeview.KdGaugeView;

public class Creation_Profile extends AppCompatActivity {
    private CustomViewPagerNoSwip viewPager;
    LoadingDialog loadingDialog;
    AuthenticationPagerAdapter pagerAdapter;
    Button btnNext,btnSkip,btnValide;
    EditText Email,Password,PasswordConfor,Prenom,Nom,Numero_Cin,Date_Naissence,Poids,Taille,New_Poids_Perder,New_Poids_Ga;
    CheckBox H,F,accept;
    String sexe="";
    RadioButton r1_perder,r2_perder,r3_perder,r4_perder,r1_ga,r2_ga,r0,r1,r2,r3;
    String type_frag="";
    double IMC;
    KdGaugeView speedoMeterView;
    SpeedView speedView;
    double Min_poids,Max_poids;
    DatabaseHandler db = new DatabaseHandler(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creation__profile2);
        pagerAdapter  = new AuthenticationPagerAdapter(getSupportFragmentManager());
        viewPager=(CustomViewPagerNoSwip)findViewById(R.id.viewPage);
        btnNext=findViewById(R.id.btnNext);
        btnSkip=findViewById(R.id.btnSkip);
        btnValide=findViewById(R.id.btnvValide);
        btnSkip.setVisibility(View.GONE);
        pagerAdapter.addFragmet(new Step1());
        pagerAdapter.addFragmet(new Step2());
        pagerAdapter.addFragmet(new Step3());
        pagerAdapter.addFragmet(new Step4());
        pagerAdapter.addFragmet(new Step5());
        pagerAdapter.addFragmet(new Step6());
        pagerAdapter.addFragmet(new Step7());
        pagerAdapter.addFragmet(new Step8());
        viewPager.setAdapter(pagerAdapter);
        loadingDialog = new LoadingDialog(Creation_Profile.this);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position==7) {
                    // last page. make button text to GOT IT
                    btnNext.setVisibility(View.GONE);
                    btnSkip.setVisibility(View.VISIBLE);
                    btnValide.setVisibility(View.VISIBLE);
                }

                else if(position==0)
                {
                    btnSkip.setVisibility(View.GONE);
                }
                else if(position==3)
                {
                    /*****************************************************************/
                    CardView prop1=pagerAdapter.getItem(3).getView().findViewById(R.id.prop1) ;
                    CardView prop2= pagerAdapter.getItem(3).getView().findViewById(R.id.prop2) ;
                    CardView prop3=pagerAdapter.getItem(3).getView().findViewById(R.id.prop3) ;
                    CardView prop4=pagerAdapter.getItem(3).getView().findViewById(R.id.prop4) ;
                    CardView prop5= pagerAdapter.getItem(3).getView().findViewById(R.id.prop5) ;
                    CardView prop6=pagerAdapter.getItem(3).getView().findViewById(R.id.prop6) ;
                    TextView t3 = pagerAdapter.getItem(3).getView().findViewById(R.id.TextEtatRecommandation) ;
                     speedView =pagerAdapter.getItem(3).getView().findViewById(R.id.speedView);
                    speedView.setMaxSpeed(50);
                    speedView.clearSections();
                    speedView.addSections(new Section(0f, .37f, Color.YELLOW,75, Section.Style.SQUARE)
                            ,new Section(.37f, .50f, Color.GREEN,75, Section.Style.SQUARE)
                            ,new Section(.50f, 1f, Color.RED,75, Section.Style.SQUARE)
                    );
                  /*  speedView.makeSections(3, Color.YELLOW, Section.Style.ROUND);
                    speedView.ma

                  //  speedView.clearSections();
                    //speedView.makeSections(3, Color.CYAN, Section.Style.SQUARE);
                 /* speedView.getSections().get(0).setStartOffset(0f);
                    speedView.getSections().get(0).setEndOffset(0.3f);
                    speedView.getSections().get(1).setStartOffset(0.37f);
                    speedView.getSections().get(1).setEndOffset(0.50f);
                    speedView.getSections().get(2).setStartOffset(0.50f);
                    speedView.getSections().get(2).setEndOffset(1f);

                    speedView.getSections().get(0).setColor(Color.YELLOW);
                    speedView.getSections().get(1).setColor(Color.GREEN);
                    speedView.getSections().get(2).setColor(Color.RED);

                   /* speedView.clearSections();
                    speedView.addSections(new Section(0f, .37f, Color.YELLOW)
                            ,new Section(.37f, .50f, Color.GREEN)
                            ,new Section(.50f, 1f, Color.RED)
                           );
                    speedView.getSections().get(0).setColor(Color.YELLOW);
                    speedView.getSections().get(1).setColor(Color.GREEN);
                    speedView.getSections().get(2).setColor(Color.RED);*/


                    /*********************************************************************/
                   // t1.setText("Votre IMC : "+" "+String.format("%.2f",IMC));



                    if(IMC < 18.5)
                    {
                        t3.setTextColor(Color.YELLOW);
                        prop1.setCardBackgroundColor(Color.YELLOW);
                        prop2.setCardBackgroundColor(Color.TRANSPARENT);
                        prop3.setCardBackgroundColor(Color.TRANSPARENT);
                        prop4.setCardBackgroundColor(Color.TRANSPARENT);
                        prop5.setCardBackgroundColor(Color.TRANSPARENT);
                        prop6.setCardBackgroundColor(Color.TRANSPARENT);
                     //   t2.setText("Votre Etat :  Votre IMC est trop faible : vous étes en situation de maigreur");
                        t3.setText("Recommandation :  Prendre du poids");
                       // t4.setText(" Si votre IMC est inférieur à 18,5, vous êtes maigre, au sens médical du terme. Aussi peut-il apparaître nécessaire pour vous de grossir : mais rien ne remplace une consultation chez le médecin, seul lui pourra vous donner la marche à suivre.");
                    }
                    else if((IMC > 18.5) && (IMC <= 25))
                    {
                      //  t2.setText("Votre Etat : Votre IMC est normal");
                        t3.setText("Recommandation : Continuez à manger équilibré");
                        t3.setTextColor(Color.GREEN);
                        prop2.setCardBackgroundColor(Color.GREEN);
                        prop1.setCardBackgroundColor(Color.TRANSPARENT);
                        prop3.setCardBackgroundColor(Color.TRANSPARENT);
                        prop4.setCardBackgroundColor(Color.TRANSPARENT);
                        prop5.setCardBackgroundColor(Color.TRANSPARENT);
                        prop6.setCardBackgroundColor(Color.TRANSPARENT);
                        //  t4.setText(" Si votre IMC se situe entre 18,5 et 25, vous êtes de corpulence normale, c’est-à-dire que vous n’êtes ni en surpoids, ni maigre. Continuez à manger équilibré et à faire de l’exercice régulièrement : ce mode de vie sain est garant d’une bonne santé, sans oublier" + " la notion de plaisir bien sûr !");
                    }
                    else if((IMC > 25) && (IMC <= 30))
                    {
                        //  t2.setText("Votre Etat : Votre IMC est normal");
                        t3.setText("Recommandation : Perdre du poids");
                        t3.setTextColor(Color.RED);
                        prop3.setCardBackgroundColor(Color.RED);
                        prop2.setCardBackgroundColor(Color.TRANSPARENT);
                        prop1.setCardBackgroundColor(Color.TRANSPARENT);
                        prop4.setCardBackgroundColor(Color.TRANSPARENT);
                        prop5.setCardBackgroundColor(Color.TRANSPARENT);
                        prop6.setCardBackgroundColor(Color.TRANSPARENT);
                        //  t4.setText(" Si votre IMC se situe entre 18,5 et 25, vous êtes de corpulence normale, c’est-à-dire que vous n’êtes ni en surpoids, ni maigre. Continuez à manger équilibré et à faire de l’exercice régulièrement : ce mode de vie sain est garant d’une bonne santé, sans oublier" + " la notion de plaisir bien sûr !");
                    }
                    else if((IMC > 30) && (IMC <= 35))
                    {
                        //  t2.setText("Votre Etat : Votre IMC est normal");
                        t3.setText("Recommandation : Perdre du poids");
                        t3.setTextColor(Color.RED);
                        prop4.setCardBackgroundColor(Color.RED);
                        prop2.setCardBackgroundColor(Color.TRANSPARENT);
                        prop3.setCardBackgroundColor(Color.TRANSPARENT);
                        prop1.setCardBackgroundColor(Color.TRANSPARENT);
                        prop5.setCardBackgroundColor(Color.TRANSPARENT);
                        prop6.setCardBackgroundColor(Color.TRANSPARENT);
                        //  t4.setText(" Si votre IMC se situe entre 18,5 et 25, vous êtes de corpulence normale, c’est-à-dire que vous n’êtes ni en surpoids, ni maigre. Continuez à manger équilibré et à faire de l’exercice régulièrement : ce mode de vie sain est garant d’une bonne santé, sans oublier" + " la notion de plaisir bien sûr !");
                    }
                    else if((IMC > 35) && (IMC <= 40))
                    {
                        //  t2.setText("Votre Etat : Votre IMC est normal");
                        t3.setText("Recommandation : Perdre du poids");
                        t3.setTextColor(Color.RED);
                        prop5.setCardBackgroundColor(Color.RED);
                        prop2.setCardBackgroundColor(Color.TRANSPARENT);
                        prop3.setCardBackgroundColor(Color.TRANSPARENT);
                        prop4.setCardBackgroundColor(Color.TRANSPARENT);
                        prop1.setCardBackgroundColor(Color.TRANSPARENT);
                        prop6.setCardBackgroundColor(Color.TRANSPARENT);
                        //  t4.setText(" Si votre IMC se situe entre 18,5 et 25, vous êtes de corpulence normale, c’est-à-dire que vous n’êtes ni en surpoids, ni maigre. Continuez à manger équilibré et à faire de l’exercice régulièrement : ce mode de vie sain est garant d’une bonne santé, sans oublier" + " la notion de plaisir bien sûr !");
                    }
                    else
                    {
                      //  t2.setText("Votre Etat : Votre IMC est trop élevé : vous étes en surpoids");
                        t3.setText("Recommandation : Perdre du poids");
                        t3.setTextColor(Color.RED);
                        prop6.setCardBackgroundColor(Color.RED);
                       // t4.setText("Si votre IMC est supérieur à 25 "+"\n"+",vous êtes en situation de surpoids."+"\n"+"Peut-il apparaître nécessaire pour vous de maigrir :"+"\n"+" mais rien ne remplace une consultation chez le médecin,"+"\n"+"seul lui pourra vous donner la marche à suivre. ");
                        prop2.setCardBackgroundColor(Color.TRANSPARENT);
                        prop3.setCardBackgroundColor(Color.TRANSPARENT);
                        prop4.setCardBackgroundColor(Color.TRANSPARENT);
                        prop5.setCardBackgroundColor(Color.TRANSPARENT);
                        prop1.setCardBackgroundColor(Color.TRANSPARENT);
                      //  speedView.with = false;

                    }
                    speedView.setSpeedAt((float)IMC);

                    btnNext.setVisibility(View.VISIBLE);
                    btnSkip.setVisibility(View.VISIBLE);
                    btnValide.setVisibility(View.GONE);

                }
                else {
                    btnNext.setVisibility(View.VISIBLE);
                    btnSkip.setVisibility(View.VISIBLE);
                    btnValide.setVisibility(View.GONE);
                    // still pages are left
                    btnNext.setText("suivant");

                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    public final Pattern EMAIL_ADDRESS_PATTERN = Pattern.compile(
            "[a-zA-Z0-9+._%-+]{1,256}" +
                    "@" +
                    "[a-zA-Z0-9][a-zA-Z0-9-]{0,64}" +
                    "(" +
                    "." +
                    "[a-zA-Z0-9][a-zA-Z0-9-]{0,25}" +
                    ")+"
    );

    public void next_fragment(View view) {

        if(viewPager.getCurrentItem()==0)
        {
            Email=pagerAdapter.getItem(viewPager.getCurrentItem()).getView().findViewById(R.id.email);
            Password=pagerAdapter.getItem(viewPager.getCurrentItem()).getView().findViewById(R.id.password);
            PasswordConfor=pagerAdapter.getItem(viewPager.getCurrentItem()).getView().findViewById(R.id.confirmpassword);
            String email=Email.getText().toString();
            String password=Password.getText().toString();
            String confirmpassword=PasswordConfor.getText().toString();
            if(email.isEmpty()|| password.isEmpty() || confirmpassword.isEmpty() )
            {
                // Toast.makeText(Creation_CPT.this,"merci de verifier",Toast.LENGTH_LONG).show();
                Email.setError("Ce champ est obligatoire!" );
                Password.setError("Ce champ est obligatoire!" );
                PasswordConfor.setError("Ce champ est obligatoire!" );
            }
            else if(!EMAIL_ADDRESS_PATTERN.matcher(email).matches()){
                Email.setError("Veuillez saisir une adresse email valide. Par exemple prenom.nom@domaine.com!" );
            }
            else  if(password.length()<6)
            {
                Password.setError("Veuillez saisir au moins 6 caractères" );
            }
            else  if(!password.equals(confirmpassword))
            {
                PasswordConfor.setError("Mot de passe ne correspondant pas!" );
            }

            else
            {

                viewPager.setCurrentItem(viewPager.getCurrentItem()+1);
            }
        }

        else   if(viewPager.getCurrentItem()==1)
        {


            Prenom=pagerAdapter.getItem(viewPager.getCurrentItem()).getView().findViewById(R.id.prenom);
            Nom=pagerAdapter.getItem(viewPager.getCurrentItem()).getView().findViewById(R.id.nom);
            Date_Naissence=pagerAdapter.getItem(viewPager.getCurrentItem()).getView().findViewById(R.id.date_naisse);
            H=pagerAdapter.getItem(viewPager.getCurrentItem()).getView().findViewById(R.id.H);
            F=pagerAdapter.getItem(viewPager.getCurrentItem()).getView().findViewById(R.id.F);

/****************************************************************************************/

            String prenom=Prenom.getText().toString();
            String nom=Nom.getText().toString();
            String date_nais=Date_Naissence.getText().toString();
            if(prenom.isEmpty()|| nom.isEmpty()  || date_nais.isEmpty() )
            {
                Prenom.setError("Ce champ est obligatoire!" );
                Nom.setError("Ce champ est obligatoire!" );
                Numero_Cin.setError("Ce champ est obligatoire!" );
                Date_Naissence.setError("Ce champ est obligatoire!" );
                //Toast.makeText(Creation_CPT.this,"merci de verifier",Toast.LENGTH_LONG).show();
            }


            else if (!H.isChecked() && !F.isChecked())
            {
                H.setError("");
                F.setError("");
            }

            else
            {
                viewPager.setCurrentItem(viewPager.getCurrentItem()+1);
            }
        }
        else  if(viewPager.getCurrentItem()==2) {

            Poids = pagerAdapter.getItem(viewPager.getCurrentItem()).getView().findViewById(R.id.Poids_perder);
            Taille = pagerAdapter.getItem(viewPager.getCurrentItem()).getView().findViewById(R.id.InputStep2Taille);
            String poids = Poids.getText().toString();
            String taille = Taille.getText().toString();
            if(poids.isEmpty() || taille.isEmpty())
            {
                Poids.setError("Ce champ est obligatoire!");
                Taille.setError("Ce champ est obligatoire!");
            }
            else
            {




                IMC=calculeIMC(Double.parseDouble(poids),Integer.parseInt(taille));


                viewPager.setCurrentItem(viewPager.getCurrentItem()+1);

            }
        }

        else   if(viewPager.getCurrentItem()==4)
        {
            r0=pagerAdapter.getItem(4).getView().findViewById(R.id.r);
            r1=pagerAdapter.getItem(4).getView().findViewById(R.id.r1);
            r2=pagerAdapter.getItem(4).getView().findViewById(R.id.r2);
            r3=pagerAdapter.getItem(4).getView().findViewById(R.id.r3);
            int taille=Integer.parseInt(Taille.getText().toString());
            Min_poids=calculePoidIdeal(20,taille);
            Max_poids=calculePoidIdeal(25,taille);

            if(IMC < 18.5)
            {
                Log.e("IMC",String.valueOf(IMC));

                TextView   text_ideal=pagerAdapter.getItem(6).getView().findViewById(R.id.Poididal2);

                text_ideal.setText("[ "+Min_poids+" . . "+Max_poids+"]");
                viewPager.setCurrentItem(6);
        }
            else if((IMC >= 18.5) && (IMC <= 25))
            {


                viewPager.setCurrentItem(7);
            }
            else
            {
                TextView   text_ideal=pagerAdapter.getItem(5).getView().findViewById(R.id.Poids_ideal);

                text_ideal.setText("[ "+Min_poids+" . . "+Max_poids+"]");

                viewPager.setCurrentItem(5);
            }

        }

        else   if(viewPager.getCurrentItem()==5)
        {
            r1_perder=pagerAdapter.getItem(5).getView().findViewById(R.id.r1_Perder);
            r2_perder=pagerAdapter.getItem(5).getView().findViewById(R.id.r2_Perder);
            r3_perder=pagerAdapter.getItem(5).getView().findViewById(R.id.r3_Perder);
            r4_perder=pagerAdapter.getItem(5).getView().findViewById(R.id.r4_Perder);

            New_Poids_Perder=pagerAdapter.getItem(5).getView().findViewById(R.id.Poids_Perder2);

            if(!r1_perder.isChecked()&&!r2_perder.isChecked()&&!r3_perder.isChecked()&&!r4_perder.isChecked())
            {
                r1_perder.setError("");
                r2_perder.setError("");
                r3_perder.setError("");
                r4_perder.setError("");
            }
else
            if(New_Poids_Perder.getText().toString().isEmpty())
            {
                New_Poids_Perder.setError("Ce champ est obligatoire!");
            }
            else if(Double.parseDouble(New_Poids_Perder.getText().toString())>Double.parseDouble(Poids.getText().toString()))
            {
                New_Poids_Perder.setError("");
            }
            else
            {
                viewPager.setCurrentItem(7);
            }

        }
        else   if(viewPager.getCurrentItem()==6)
        {
            r1_ga=pagerAdapter.getItem(6).getView().findViewById(R.id.r1_Ga);
            r2_ga=pagerAdapter.getItem(6).getView().findViewById(R.id.r2_Ga);
            New_Poids_Ga=pagerAdapter.getItem(6).getView().findViewById(R.id.Poids_Ga);

            if(r1_ga.isChecked()&&r2_ga.isChecked()){
                r1_ga.setError("");
                r2_ga.setError("");

            }
            else
            if(New_Poids_Ga.getText().toString().isEmpty())
            {
                New_Poids_Ga.setError("Ce champ est obligatoire!");
            }
            else
            if(Double.parseDouble(New_Poids_Ga.getText().toString())<Double.parseDouble(Poids.getText().toString()))
            {
                New_Poids_Ga.setError("");
            }
            else
            {
                viewPager.setCurrentItem(7);
            }
        }


        else
        {
            viewPager.setCurrentItem(viewPager.getCurrentItem()+1);
        }


    }

    public void valide_form(View view) {
       accept=pagerAdapter.getItem(7).getView().findViewById(R.id.accepte);
       if(accept.isChecked())
       {
           addDiet();
           if (H.isChecked())
           {
               sexe="H";
           }
           else if(F.isChecked())
           {
               sexe="F";
           }

           /*************************************************************/
           Account compte = new Account();
           compte.set_email(Email.getText().toString());
           compte.set_password(Password.getText().toString());
           db.addAccount(compte);
           /*****************************************************************/
           Profile p = new Profile();
           p.set_nom(Nom.getText().toString());
           p.set_prénom(Prenom.getText().toString());
           p.set_anniversaire(Date_Naissence.getText().toString());
           String year=Date_Naissence.getText().toString().substring(6,Date_Naissence.getText().length());
           int yearcur=Calendar.YEAR;
           p.set_age(yearcur-(Integer.parseInt(year)));
           p.set_poids(Double.parseDouble(Poids.getText().toString()));
           p.set_taille(Integer.parseInt(Taille.getText().toString()));
           p.set_imc(calculeIMC(Double.parseDouble(Poids.getText().toString()),Integer.parseInt(Taille.getText().toString())));
           p.set_sexe(sexe);
           db.addProfile(p);

           Regime r = new Regime();

           int choix1=0;
           int choix0=0;
           String type_regime="";
           if(IMC<18.5)
           {type_regime="GA";
               r.setNew_poids(Double.parseDouble(New_Poids_Perder.getText().toString()));
               if(r1_ga.isChecked())
               {
                   choix1=1;
               }
               else
               {
                   choix1=2;
               }
           }

           else if((IMC >= 18.5) && (IMC <= 25))
           {
               type_regime="IDEAL";
               r.setNew_poids(Double.parseDouble(Poids.getText().toString()));
               choix1=0;
           }
           else
           {
               r.setNew_poids(Double.parseDouble(New_Poids_Perder.getText().toString()));
               type_regime="PERDER";
               if(r1_perder.isChecked())
               {
                   choix1=1;
               }
               else  if(r2_perder.isChecked())
               {
                   choix1=2;
               }
               else  if(r3_perder.isChecked())
               {
                   choix1=3;
               }
               else
               {
                   choix1=4;
               }
           }
           r.setObjectif(choix1);
           r.setTyp_regime(type_regime);
           if(r0.isChecked())
           {
               choix0=1;
           }
           else  if(r1.isChecked())
           {
               choix0=2;
           }
           else  if(r3.isChecked())
           {
               choix0=3;
           }
           else
           {
               choix0=4;
           }
           r.setDegre_act(choix0);
           db.addregime(r);


           Historique_Regime historique_regime = new Historique_Regime();
           historique_regime.setNew_Poids(p.get_poids());
           historique_regime.setDate(getDay());
           historique_regime.setNew_IMC(calculeIMC(p.get_poids(),p.get_taille()));
           historique_regime.setEvolution(0);
           db.addHistorique_Regime(historique_regime);




           startActivity(new Intent(Creation_Profile.this, com.example.healthy.Profile.class));
       }
       else
       {
           accept.setError("");
       }

    }


    public String getDay()
    {

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        month=month+1;
        String day_1="";
        String Month_1="";
        if(day<10)
        {
            day_1="0"+day;

        }
        else
        {
            day_1=String.valueOf(day);
        }
        if(month<10)
        {
            Month_1="0"+month;
        }
        else
        {
            Month_1=String.valueOf(month);
        }
        return day_1+"-"+Month_1+"-"+year;

    }


    public void previous_fragment(View view) {
        if(viewPager.getCurrentItem()>4)
        {
            viewPager.setCurrentItem(4);
        }
        else
        {
            viewPager.setCurrentItem(viewPager.getCurrentItem()-1);
        }

    }

    public double calculeIMC(double p , int t)
    {
        double res ;
        double x = Math.pow((double)t/100,2.0);
        return res = (double)p/x ;
    }

    public void etat(double imc)
    {
        //TextView t1 = findViewById(R.id.TextEtatIMC) ;
       // TextView t2 = findViewById(R.id.TextEtatEtat) ;
        TextView t3 = findViewById(R.id.TextEtatRecommandation) ;
       // TextView t4 = findViewById(R.id.TextEtatTextRecommandation) ;
       // t1.setText(t1.getText().toString()+" "+String.format("%.2f",imc));
        if(imc < 18.5)
        {
        //    t2.setText(t2.getText().toString()+" Votre IMC est trop faible : vous étes en situation de maigreur");
            t3.setText(t3.getText().toString()+" Prendre du poids");
          //  t4.setText(t4.getText().toString()+" Si votre IMC est inférieur à 18,5, vous êtes maigre, au sens médical du terme. Aussi peut-il apparaître nécessaire pour vous de grossir : mais rien ne remplace une consultation chez le médecin, seul lui pourra vous donner la marche à suivre.");
        }
        else if((imc >= 18.5) && (imc <= 25))
        {
           // t2.setText(t2.getText().toString()+" Votre IMC est normal");
            t3.setText(t3.getText().toString()+" Continuez à manger équilibré");
          //  t4.setText(t4.getText().toString()+" Si votre IMC se situe entre 18,5 et 25, vous êtes de corpulence normale, c’est-à-dire que vous n’êtes ni en surpoids, ni maigre. Continuez à manger équilibré et à faire de l’exercice régulièrement : ce mode de vie sain est garant d’une bonne santé, sans oublier" + " la notion de plaisir bien sûr !");
        }
        else
        {
          ///  t2.setText(t2.getText().toString()+" Votre IMC est trop élevé : vous étes en surpoids");
            t3.setText(t3.getText().toString()+" Perdre du poids");
          //  t4.setText(t4.getText().toString()+"Si votre IMC est supérieur à 25, vous êtes en situation de surpoids. peut-il apparaître nécessaire pour vous de maigrir : mais rien ne remplace une consultation chez le médecin, seul lui pourra vous donner la marche à suivre. ");
        }




    }
    public double calculePoidIdeal(double imc , int t )
    {
        double res ;
        res = imc * Math.pow(((double)t/100),2.0) ;
        return res;
    }

    public void addDiet()
    {
        Diet d250 = new Diet("Perdre 250 g par semaine " , 1800);
        Diet d500 = new Diet("Perdre 500 g par semaine " , 1700);
        Diet d750 = new Diet("Perdre 750 g par semaine " , 1600);
        Diet d1000 = new Diet("Perdre 1000 g par semaine " , 1500);
        Diet d0 = new Diet("Maintenir le poids " , 2000);
        Diet dx250 = new Diet("Avoir 250 g par semaine " , 2250);
        Diet dx500 = new Diet("Avoir 500 g par semaine" , 2500);
        db.adddiet(d250);
        db.adddiet(d500);
        db.adddiet(d750);
        db.adddiet(d1000);
        db.adddiet(d0);
        db.adddiet(dx250);
        db.adddiet(dx500);
    }

}

class AuthenticationPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> fragmentList = new ArrayList<>();

    public AuthenticationPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        return fragmentList.get(i);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    void addFragmet(Fragment fragment) {
        fragmentList.add(fragment);
    }
}

