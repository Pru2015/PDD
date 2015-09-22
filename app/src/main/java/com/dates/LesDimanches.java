package com.dates;
/***************************************************************************
 * @author Blaise Siani                    (*_*)                        ***
 * Juin 2015.                                                            ****
 * *************************************************************************
 */
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;


public class LesDimanches {
	//
	  public ArrayList<Calendar> dimanche;
	  public ArrayList<Calendar> dayOfMonth;
	public LesDimanches() {
		// TODO Auto-generated constructor stub
	}
	/**********************************************************************************************************************************************
	 * *********************************************************** List of all sundays inside a month *********************************************
	 * this function take in parameter a a month and a year and return all sunday 
	 * in the month 
	 * @param year
	 * @param mois
	 */
	public void dimancheDuMois(int year, int mois){
		int[] dimanches = null ;
		int i = 0;
		Calendar cal = new GregorianCalendar(year, mois, 1);
		dimanche = new  ArrayList<Calendar>();
		do{
			int day = cal.get(Calendar.DAY_OF_WEEK);
			if(day == Calendar.SUNDAY){
			//dimanches[i] = day;
			Calendar	day1 = new GregorianCalendar(year, mois, cal.get(Calendar.DAY_OF_MONTH));
			dimanche.add(day1);
			}
			 cal.add(Calendar.DAY_OF_YEAR, 1);	
		}while(cal.get(Calendar.MONTH) == mois);
	}
	/**************************************************************************************************************************************************
	 * ************************************************* three days before the number three sunday in a month *****************************************
	 * @param year represented the year
	 * @param month represented the week of the day
	 * @param day   represented the sunday of the number three week in a month
	 * @return
	 */
	public int threeDayBeforeSunday(int year, int month, int day){
		 Calendar cal = new GregorianCalendar(year, month, day);
		   int day1 = cal.get(Calendar.DAY_OF_WEEK);
		   if(day1 == Calendar.SUNDAY){
			cal.add(Calendar.DAY_OF_MONTH, -3 );
			//System.out.println("nous sommes trois jour avans le 3 eme dimanche"+ cal.get(Calendar.DAY_OF_MONTH));
		   }
		  return cal.get(Calendar.DAY_OF_MONTH); 
	}
	/*************************************************************************************************************************************************
	 * *********************************************** Two days before the number three sunday in a month *********************************************
	 * 
	 * @param year
	 * @param month
	 * @param day  
	 * @return return two day before sunday 
	 */
 
	public int twoDayBeforeSunday(int year, int month,int day){
		 Calendar cal = new GregorianCalendar(year, month, day);
		   int day1 = cal.get(Calendar.DAY_OF_WEEK);
		   if(day1 == Calendar.SUNDAY){
			cal.add(Calendar.DAY_OF_MONTH, -2 );
			//System.out.println("nous sommes trois jour avans le 3 eme dimanche"+ cal.get(Calendar.DAY_OF_MONTH));
		   }
		  return cal.get(Calendar.DAY_OF_MONTH); 
	}

	/************************************************************************************************************************************************
	 * ******************************************* One Day before the number three sunday in a month *************************************************
	 * 
	 * @param year
	 * @param month
	 * @param day
	 * @return the satuday before the number three sunday in a month 
	 */
	public int oneDayBeforeSunday(int year, int month, int day){
		 Calendar cal = new GregorianCalendar(year, month, day);
		   int day1 = cal.get(Calendar.DAY_OF_WEEK);
		   if(day1 == Calendar.SUNDAY){
			cal.add(Calendar.DAY_OF_MONTH, -1);
			//System.out.println("nous sommes trois jour avans le 3 eme dimanche"+ cal.get(Calendar.DAY_OF_MONTH));
		   }
		  return cal.get(Calendar.DAY_OF_MONTH); 
	}
	 /**********************************************************************************************************************************************
	  * ******************************************************* The Days of a week *****************************************************************
	    * this function take in parameter integer and return the corresponding day in a week
	    * @param day number of the day in a week 
	    * @return  the day in the week to string
	    */
	   public String lesJourDeLaSemaine(int day){
		   switch (day) {
		case 0:
			return "Dimanche";
		case 1:
			return "Lundi";
		case 2: 
			return "Mardi";
		case 3:
			return "Mercredi";
		case 4:
			return "Jeudi";
		case 5:
			return "Vendredi";
		case 6 :
			return "Samedi";
		default:
			break;
		   }
		   return null;
	   }
	   
	   /****************************************************************************************************************************************
	    * ******************************************************* All days inside a Month ******************************************************
	    * @param year
	    * @param month
	    * @return all day inside a month 
	    */
	   public ArrayList<Calendar> allDayInAMonth(int year, int month){
	   	Calendar cal = new GregorianCalendar(year, month, 1);
	   	ArrayList<Calendar> dayofmonth = new ArrayList<Calendar>();
	   	dayOfMonth = new ArrayList<Calendar>();
	   	//System.out.println("le premier jour du mois " + cal.getTime());
	   	do
	   	{
	   		if(cal.get(Calendar.MONTH) == month){
	   		Calendar day1  = new GregorianCalendar(year, month, cal.get(Calendar.DAY_OF_MONTH)); 
	   		dayofmonth.add(day1);
	   		dayOfMonth.add(day1);
	   		}
	   		cal.add(Calendar.DAY_OF_YEAR, 1);
	   		//System.out.println("le prochain jour est "+cal.getTime());
	   	}while(cal.get(Calendar.MONTH) == month);
	   	return dayofmonth;
	   }   
	/**********************************************************************************************************************************************
	 * ****************************************************** Rappel du prochaine  troisieme dimanche ********************************************* 
	 */
	   /***************
	    * cette fonction permet de rappeler la date du prochain troisieme dimanche tous les dimanches
	    * @param dimanchesdumois the list set in parameter is a list of all sundays on a month 
	    */
	   public void rappelDuTroisiemeDimanche(ArrayList<Calendar> dimanchesdumois){
	   	Calendar cal = Calendar.getInstance();
	   	for (int j = 0; j < dimanchesdumois.size(); j++) {
	   		if(cal.get(Calendar.DAY_OF_YEAR) == dimanchesdumois.get(j).get(Calendar.DAY_OF_YEAR)){
	   		    if(j == 2){
	   		    	//faire vibrer le telephone a 6 heure en affichant une alerte avec ce message 
	   		    	System.out.println("vous etes le troisieme dimanche du mois");
	   		    }
	   		    else{
	   		    	//faire vibrer le telephone a 6 heure en affichant une alerte avec ce message 
	   				System.out.println("le prochains troisieme dimanche est " +dimanchesdumois.get(2).get(Calendar.DAY_OF_YEAR));
	   		    }
	   		}
	   	}
	   }  
}
