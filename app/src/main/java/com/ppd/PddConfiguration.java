/***************************************************************************
 * @author Blaise Siani                    (*_*)                        ***
 * Juillet 2015.                                                            ****
 * *************************************************************************
 */

package com.ppd;
import java.util.Calendar;

///import android.R;
import com.prd.R;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

public class PddConfiguration extends Activity {
 TextView execice_du_jour;
 ImageButton next;
 ImageButton previous;
 TextView current;
 TextView  verse_biblic;
 TextView exos_jour;
 TextView lecture;
 private static final String PREF_DAY = "day";
 public static  ReadFile file;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ma_fiche_de_priere_layou);
		execice_du_jour = (TextView) findViewById(R.id.excercice_du_jour);
		next = (ImageButton) findViewById(R.id.next);
		previous = (ImageButton) findViewById(R.id.previous);
		current = (TextView) findViewById(R.id.current);
		verse_biblic = (TextView) findViewById(R.id.verse_biblique);
		exos_jour = (TextView) findViewById(R.id.verse_biblique1);
		lecture = (TextView) findViewById(R.id.lecture);
		Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/Alison Regular.ttf");
		execice_du_jour.setTypeface(custom_font);
		Typeface custom_font1 = Typeface.createFromAsset(getAssets(), "fonts/Amphion Italic.ttf");
		Typeface custom_font2 = Typeface.createFromAsset(getAssets(), "fonts/Tangerine_Regular.ttf");
		Typeface custom_font3 = Typeface.createFromAsset(getAssets(), "fonts/cac_champagne.ttf");
		current.setTypeface(custom_font);
		exos_jour.setTypeface(custom_font3);
		verse_biblic.setTypeface(custom_font2);
		lecture.setTypeface(custom_font1);		
		//  Lecture des information dans le fichier
		file = new ReadFile(PddConfiguration.this);
    	file.readToFileResouce();
    	afficherinfojour();
		current.setOnClickListener(new OnClickListener() {
			//lecture des information dans 
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(PddConfiguration.this);
	            sp.edit().remove(PREF_DAY).apply();
	            afficherinfojour();
				
			}
		});
		
		next.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(PddConfiguration.this);
	        	Calendar cal = Calendar.getInstance();
	        	int todays = sp.getInt(PREF_DAY, cal.get(Calendar.DAY_OF_MONTH));
	            cal.set(Calendar.DAY_OF_MONTH, todays);
	            cal.add(Calendar.DAY_OF_MONTH, 1);
	            sp.edit()
	                    .putInt(PREF_DAY, cal.get(Calendar.DAY_OF_MONTH)) 
	                    .apply();	
	            afficherinfojour();
			}
		});
		
	   previous.setOnClickListener(new OnClickListener() {
		
		@Override
			public void onClick(View v) {
			// TODO Auto-generated method stub
			Calendar cal = Calendar.getInstance();
        	SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(PddConfiguration.this);
        	int today = sp.getInt(PREF_DAY, cal.get(Calendar.DAY_OF_MONTH));
            cal.set(Calendar.DAY_OF_MONTH, today);
            cal.add(Calendar.DAY_OF_MONTH, -1);
            sp.edit()
                    .putInt(PREF_DAY, cal.get(Calendar.DAY_OF_MONTH))
                    .apply();
            afficherinfojour();
		}
	});	
	}
    public void afficherinfojour(){
    	SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(PddConfiguration.this);
    	Calendar cal = Calendar.getInstance();
        int today = sp.getInt(PREF_DAY, cal.get(Calendar.DAY_OF_MONTH));
        String [] exercice_du_jour = file.all_infor.get(today-1);
        current.setText(exercice_du_jour[1]+" le "+exercice_du_jour[0]);
        verse_biblic.setText(exercice_du_jour[2]);
        exos_jour.setText(exercice_du_jour[3]);
        lecture.setText(exercice_du_jour[4]);
        boolean isfirst =  cal.getActualMinimum(Calendar.DAY_OF_MONTH) == today;
        boolean islast =  cal.getActualMaximum(Calendar.DAY_OF_MONTH) == today;
        if(islast){
        	next.setVisibility(View.GONE);
        }else{
        	next.setVisibility(View.VISIBLE);
        }
        if(isfirst){
        	previous.setVisibility(View.GONE);
        }else{
        	previous.setVisibility(View.VISIBLE);
        }
    }
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.pdd_configuration, menu);
		return true;
	}
}
