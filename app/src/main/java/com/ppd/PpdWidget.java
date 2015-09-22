package com.ppd;
/***************************************************************************
 * @author Blaise Siani                    (*_*)                        ***
 * Juillet 2015.                                                            ****
 * *************************************************************************
 */
import java.util.Calendar;

import com.prd.R;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.RemoteViews;

public class PpdWidget extends AppWidgetProvider {
		private static final String ACTION_PREVIOUS_DAY
			= "com.prd.action.PREVIOUS_DAY";
		private static final String ACTION_NEXT_DAY
			= "com.prd.action.NEXT_DAY";
		private static final String ACTION_RESET_DAY
			= "com.prd.action.RESET_DAY";
		 private static final String PREF_DAY = "day";
		  public static  ReadFile file;
	 @Override
	    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
	        super.onUpdate(context, appWidgetManager, appWidgetIds);
	        //final int N = appWidgetIds.length;
	        file = new ReadFile(context);
	    	file.readToFileResouce();
	    	 for (int appWidgetId : appWidgetIds) {
	             drawWidget(context, appWidgetId);
	         }
		}
	 private void redrawWidgets(Context context) {
	        int[] appWidgetIds = AppWidgetManager.getInstance(context).getAppWidgetIds(
	                new ComponentName(context, PpdWidget.class));
	        for (int appWidgetId : appWidgetIds) {
	            drawWidget(context, appWidgetId);
	        }
	    }
	 @Override
	  	 public void onReceive(Context context, Intent intent) {
	        super.onReceive(context, intent);

	        String action = intent.getAction();
	        if(ACTION_NEXT_DAY.equals(action)){
	        	// affichage de l'exercice du mois suivant
	        	
	        	SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
	        	Calendar cal = Calendar.getInstance();
	        	int todays = sp.getInt(PREF_DAY, cal.get(Calendar.DAY_OF_MONTH));
	            cal.set(Calendar.DAY_OF_MONTH, todays);
	            cal.add(Calendar.DAY_OF_MONTH, 1);
	            sp.edit()
	                    .putInt(PREF_DAY, cal.get(Calendar.DAY_OF_MONTH)) 
	                    .apply();
	        	redrawWidgets(context);
	        }
	        if(ACTION_PREVIOUS_DAY.equals(action)){
	        	//affichage de l'excercice du jour precedent  
	        	Calendar cal = Calendar.getInstance();
	        	SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
	        	int today = sp.getInt(PREF_DAY, cal.get(Calendar.DAY_OF_MONTH));
	            cal.set(Calendar.DAY_OF_MONTH, today);
	            cal.add(Calendar.DAY_OF_MONTH, -1);
	            sp.edit()
	                    .putInt(PREF_DAY, cal.get(Calendar.DAY_OF_MONTH))
	                    .apply();
	        	redrawWidgets(context);
	        }
	        if (ACTION_RESET_DAY.equals(action)) {
	        	// affichage de l'image du jour en cour
	        	SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
	            sp.edit().remove(PREF_DAY).apply();
	            redrawWidgets(context);
	        	
				
			}
	 }
	 	
	 private void drawWidget(Context context, int appWidgetId) {
	
	AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
     Resources res = context.getResources();
     Bundle widgetOptions = appWidgetManager.getAppWidgetOptions(appWidgetId);
     SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
     RemoteViews rv = new RemoteViews(context.getPackageName(), R.layout.widgetlayout);
     Calendar cal = Calendar.getInstance();
     int today = sp.getInt(PREF_DAY, cal.get(Calendar.DAY_OF_MONTH));
   /*  
     Log.e("last day number ", ""+cal.getActualMaximum(Calendar.DAY_OF_MONTH));
     Log.e("Le premier jour du mois",""+cal.getActualMinimum(Calendar.DAY_OF_MONTH)); */
    boolean isfirst =  cal.getActualMinimum(Calendar.DAY_OF_MONTH) == today;
    boolean islast =  cal.getActualMaximum(Calendar.DAY_OF_MONTH) == today;
     // recuperation de la liste des informations dans la classe ReadFile
		 String [] exercice_du_jour = file.all_infor.get(today-1);
		 RemoteViews headerRowRv = new RemoteViews(context.getPackageName(),
	               R.layout.header);
		 RemoteViews parole_bibliq = new RemoteViews(context.getPackageName(), R.layout.parole_biblique);
		// parole_bibliq.setTextViewText(R.id.parole_biblique_jour, exercice_du_jour[2]);
		 headerRowRv.addView(R.id.header, parole_bibliq);
		 
		 RemoteViews exo_du_jour = new RemoteViews(context.getPackageName(), R.layout.exercice_du_jour);
		 	//exo_du_jour.setTextViewText(R.id.exercice_du_jour, exercice_du_jour[3]);
		 RemoteViews lecture_cnc = new RemoteViews(context.getPackageName(), R.layout.lecture_cnc);
		 //lecture_cnc.setTextViewText(R.id.parole_cnc, exercice_du_jour[3]);
		 Typeface tf = Typeface.createFromAsset(context.getAssets(), "fonts/Alison Regular.ttf");
		 
		RemoteViews body_principale = new RemoteViews(context.getPackageName(), R.layout.boby_layout);
		rv.setTextViewText(R.id.current_day, exercice_du_jour[1]+" le "+exercice_du_jour[0]);
		rv.setTextViewText(R.id.textView1, exercice_du_jour[2]);
		rv.setTextViewText(R.id.textView2, exercice_du_jour[3]);
		rv.setTextViewText(R.id.textView3, exercice_du_jour[4]);
		            //gestion des evenement 
					rv.setViewVisibility(R.id.previous_day, isfirst ? View.GONE : View.VISIBLE);
		             rv.setOnClickPendingIntent(R.id.previous_day,
		                     PendingIntent.getBroadcast(context, 0,
		                             new Intent(context, PpdWidget.class)
		                                     .setAction(ACTION_PREVIOUS_DAY),
		                             PendingIntent.FLAG_UPDATE_CURRENT));
		             rv.setViewVisibility(R.id.next_day, islast? View.GONE : View.VISIBLE);
		             rv.setOnClickPendingIntent(R.id.next_day,
		                     PendingIntent.getBroadcast(context, 0,
		                             new Intent(context, PpdWidget.class)
		                                     .setAction(ACTION_NEXT_DAY),
		                             PendingIntent.FLAG_UPDATE_CURRENT));
		             rv.setOnClickPendingIntent(R.id.current_day,
		                     PendingIntent.getBroadcast(context, 0,
		                             new Intent(context, PpdWidget.class)
		                                     .setAction(ACTION_RESET_DAY),
		                             PendingIntent.FLAG_UPDATE_CURRENT));
		             appWidgetManager.updateAppWidget(appWidgetId, rv);
	 }
		static void updateAppWidget(Context context, AppWidgetManager appWidgetManager, int appWidgetId) 
		{
			
			RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widgetlayout); // On r�cup�re les Views de notre layout
			views.setTextViewText(R.id.parole_biblique_jour, "Hello Developpez !"); // On peut agir sur ces vues
			appWidgetManager.updateAppWidget(appWidgetId, views); // On met ensuite � jour l'affichage du widget
		}
}
