/****@author Blaise Siani
 * June 2015.
 * 
 */
package com.ppd;

/***************************************************************************
 * @author Blaise Siani                    (*_*)                            ***
 * Juillet 2015.                                                            ****
 * *************************************************************************
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

import com.prd.R;
//import android.R;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;


public class ReadFile {
	Context context;
	public static HashMap<Integer, String[]> all_infor; // cette structure de donnees doit contenir tous
	//les informations neccessaire pour une journee et pour le mois cour.
	public static String[] info_jour;
	/**** constructor
	 * 
	 * @param cont 
	 */
	public ReadFile(Context cont){
		this.context = cont;
	}
	/******
	 * le but de cette fonction est de lire tous les informations enregistre dans 
	 * un fichier et les range dans une structures de donnees pour exploitation.
	 */
	@SuppressLint("UseSparseArrays")
	public void readToFileResouce(){
		String src ="";
		 InputStream text = context.getResources().openRawResource(R.raw.fiche_de_prier);
		 BufferedReader read = new BufferedReader(new InputStreamReader(text));
		 StringBuffer buff = new StringBuffer();
		 try {
			while((src = read.readLine()) != null){
				if(!src.equals(" ")){
					//src.replaceAll("\\s+","");
					buff.append(src);	
				}
			}
			Log.e("contenue du fichier", buff.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String [] info_par_jour = buff.toString().split("##");
		 all_infor = new HashMap< Integer , String[]>();
		for (int i = 0; i < info_par_jour.length; i++) {
			Log.e("information du jour numero "+i, info_par_jour[i]);
			 info_jour = info_par_jour[i].split("#");
			all_infor.put(i, info_jour);
		}
		for (int i = 0; i < all_infor.size(); i++) {
			Log.e("exercice de jours numero "+ i, ""+all_infor.get(i));
		}
	}
}
 /*TODO*/
//The apps bug with a month of 30 days