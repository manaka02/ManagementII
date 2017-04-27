/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manaka.management.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;

/**
 *
 * @author Toavina RALAMBOSOA
 */
public class Utils {
    

    
    public static int moisToInt(String mois){
        if("Janvier".equals(mois)) return 1;
        if("Fevrier".equals(mois)) return 2;
        if("Mars".equals(mois)) return 3;
        if("Avril".equals(mois)) return 4;
        if("Mai".equals(mois)) return 5;
        if("Juin".equals(mois)) return 6;
        if("Juillet".equals(mois)) return 7;
        if("Aout".equals(mois)) return 8;
        if("Septembre".equals(mois)) return 9;
        if("Octobre".equals(mois)) return 10;
        if("Novembre".equals(mois)) return 11;
        if("Decembre".equals(mois)) return 12;
        else return 0;
    }
    
    public static String[] allMois(){
        String[] array = new String[]{"Janvier","Fevrier","Mars","Avril","Mai","Juin","Juillet","Aout","Septembre","Octobre","Novembre","Decembre"};
        return array;
    }
    
    public static String[] allMoisWithAll(){
        String[] array = new String[]{"Tous","Janvier","Fevrier","Mars","Avril","Mai","Juin","Juillet","Aout","Septembre","Octobre","Novembre","Decembre"};
        return array;
    }
    
    public static Object[] oneColumnOfTable(JTable table, int columnName){
        Object[] array = new Object[table.getRowCount()];
        for (int i = 0; i < array.length; i++) {
            array[i] = table.getValueAt(i, columnName);
        }
        
        return array;
    
    }
    
    public static String corrigeDate(String str) throws Exception{
        String valiny = str;
        try {
        
        valiny = valiny.replace('/', '-');
        valiny = valiny.replace(':', '-');
        valiny = valiny.replace('.', '-');
        valiny = valiny.replace(' ', '-');
        valiny = supressionDoublon(valiny, '-');
        valiny = supressionDoublon(valiny, ' ');
        String[] split = valiny.split("-");
        
            if(split!=null && split.length==3){
                int annee = Integer.parseInt(split[0]);
                int mois = Integer.parseInt(split[1]);
                int date = Integer.parseInt(split[2]);
                if(annee<date)
                {
                        valiny = Integer.toString(date)+"-"+Integer.toString(mois)+"-"+Integer.toString(annee);
                }
                else
                {
                        valiny = Integer.toString(annee)+"-"+Integer.toString(mois)+"-"+Integer.toString(date);
                }
            }
       } catch (Exception e) {
           throw new Exception("Verifier la date inserée");
       }
        return valiny;
    }
    
    public static String supressionDoublon(String str, char doublon)
	{
		if(str!=null)
		{
			int i=0;
			for(i=0; i<str.length()-1; i++)
			{
				if(str.charAt(i)==doublon && str.charAt(i+1)==doublon)
				{
					String temp1 = str.substring(0, i);
					String temp2 = str.substring(i+1);
					str = temp1+temp2;
					str = Utils.supressionDoublon(str, doublon);
				}
			}
		}
		return str;
	}
    
    public static void exportModel(Object[][] data,String filename) throws IOException{
        FileWriter fileWrite = new FileWriter(filename);
        for(int i=0;i<data.length;i++){
                for(int j=0;j<data[i].length;j++){
                        System.out.println("ato "+data[i][j]);
                        if(data[i][j] == null){
                                fileWrite.write("");
                                continue;
                        }
                        fileWrite.write(data[i][j].toString());
                        fileWrite.write(";");
                        fileWrite.flush();
                }
                fileWrite.write("\r\n");
        }
        fileWrite.close();
    }
	public static Object[][] importModel(String filename) throws IOException{
		BufferedReader reader = new BufferedReader(new FileReader(filename));
		ArrayList<Object[]> object = new ArrayList<Object[]>();
		String line = reader.readLine();
		while(line != null){
			System.out.println(line);
			object.add(line.split(";"));
			line = reader.readLine();
		}
		Object[][] ret = new Object[object.size()][10];
		for(int i=0;i<ret.length;i++){
			ret[i] = object.get(i);
		}
		reader.close();
		return ret;
	}
    
    public static boolean isEmpty(String value){
		if(value.length()<1){
			return true;
		}
		return false;
	}
    
        public static boolean isBoolean(String value){
            if(value.compareTo("true")==0  || value.compareTo("false")==0) return true;
            return false;
        }
    
	public static  boolean isNumeric(String value){
		try{
			int num = Integer.parseInt(value);
		}catch(Exception e){
			return false;
		}
		return true;
	}



    public static double[] objectToDoubleArrayPositif(Object[] listNewValueObject) throws Exception {
        double[] value = new double[listNewValueObject.length];
        for (int i = 0; i < value.length; i++) {
            System.out.println("valeur a transformé en double :" + listNewValueObject[i]);
            try {
                value[i] = Double.parseDouble(listNewValueObject[i].toString());  
                if(value[i]<0) throw new Exception("il y a des valeurs négatifs");
            } catch (ParseException e) {
                throw  new Exception("Attention, il y a des valeurs non approprié");
            }
            
        }
        return value;
    }

    public static String trimAtLast(String data, String string) {
        char[] listChar = data.toCharArray();
        System.out.println(listChar[listChar.length-1]);
        if(listChar[listChar.length-1] == ',') return data.substring(0, listChar.length-1);
        
        
        return data;
    }
    
    public static String upFirst(String data) {
        char[] listChar = data.toCharArray();
        listChar[0] = String.valueOf(listChar[0]).toUpperCase().toCharArray()[0];
        return new String(listChar);
    }
    
        //Calculer le nombre de jour entre 2 dates
        public long espaceJour2DateLong(Date avant, Date apres){
            final long MILLISECONDS_PER_DAY = 1000 * 60 * 60 * 24; 
            long delta = apres.getTime() - avant.getTime();
            return delta / (MILLISECONDS_PER_DAY);
        }
        
        public static int espaceJour2Date(Date avant, Date apres){
            final long MILLISECONDS_PER_DAY = 1000 * 60 * 60 * 24; 
            long delta = apres.getTime() - avant.getTime();
            return (int)(delta / (MILLISECONDS_PER_DAY));
        }
        
	public static String refact(String data){
		if(!Utils.isNumeric(data) && !Utils.isBoolean(data)){
			data = "'"+data+"'";
		}
		return data;
	}
	public boolean isEqual(String montant, Vector<String> values){
		double valeur = 0;
		for (int i = 0; i < values.size(); i++) {
			valeur+=Double.parseDouble(values.get(i));
		}
		if(Double.parseDouble(montant) == valeur){
			return true;
		}
		return false;
	}
	
	public Vector<String> lcColonne(ArrayList<String[]> res,int numColonne){
		Vector<String> ret = new Vector<String>();
		for (int i = 0; i < res.size(); i++) {
			String[] temp = res.get(i);
			ret.add(temp[numColonne]);
		}
		return ret;
	}
	public int inferior(Vector<Double> plafond,Vector<Double> oldGage,Vector<Double> newGage){
		for (int i = 0; i < plafond.size(); i++) {
			Double gage = oldGage.get(i)+newGage.get(i);
			if(plafond.get(i)<gage){
				return i;
			}
		}
		return -1;
	}
	public String simplifier(String data){
		String[] temp = data.split(" ");
		
		return temp[0];
	}
	
	public ArrayList<String[]> split(String data,String sep1, String sep2){
		String[] temp = data.split(sep1);
		String[] name = new String[data.length()-1];
		String[] value = new String[data.length()-1];
		ArrayList<String[]> ret = new ArrayList<String[]>();
		for (int i = 0; i < temp.length; i++) {
			String[] temp2 = temp[i].split(sep2);
			name[i] = temp2[0];
			value[i] = temp2[1];
		}
		ret.add(name);
		ret.add(value);
		return ret;
	}


    public static java.sql.Date stringToDate(String date) throws ParseException, Exception{
       date = Utils.corrigeDate(date);
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
        java.sql.Date sqlDate = null;
        try {
            java.util.Date myDate = dateFormat.parse(date);
            sqlDate = new java.sql.Date(myDate.getTime());
             
            
        } catch (ParseException ex) {
            dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date myDate = dateFormat.parse(date);
            sqlDate = new java.sql.Date(myDate.getTime());
        }
        
        return sqlDate;
    }
    
    public static Date iterateDate(Date value, int valueOfDay) {
        Calendar cl = Calendar.getInstance();
        cl.setTime(value);
        cl.add(Calendar.DATE,valueOfDay);
        return new Date(cl.getTimeInMillis());
    }
    
    public static String iterateDate(String dateString, int valueOfDay) throws Exception {
        Date d = null;
        try {
            d =Utils.iterateDate(stringToDate(dateString), valueOfDay);
        } catch (ParseException ex) {
            Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return d.toString();
    }
    
    public static java.sql.Time stringToTime(String temps)throws ParseException{
        DateFormat formatter = new SimpleDateFormat("HH:mm");
        java.sql.Time timeValue = new java.sql.Time(formatter.parse(temps).getTime());

        return timeValue;
    }
     
    public Time absolue(Time data){
        if(data.getTime()<0) data = new Time((-1)*data.getTime());
        return data;
    }
    public int absolue(int data){
        if(data<0) data = (-1)*data;
        return data;
    }
    public static boolean isBetween2Date(Date debut, Date fin, Date donnee){
        if(debut.getTime()<donnee.getTime() && fin.getTime()>donnee.getTime()){
            return true;
        }
        return false;
    }
    
//    public void generateEcriture(){
//        //script engagement fournisseur
//        BasicsFunctions bf = new BasicsFunctions();
//        int e = 1;
//        while(e<7){
//            for (int i = 1; i < 13; i++) {
//                String sql1 = "insert into ecriture values(default, 7, 1, 0,"+15000*5*i+",'201"+e+"-"+i+"-12')";
//
//                String sql2 = "insert into ecriture values(default, 23, 0, "+15000*5*i+",0,'201"+e+"-"+i+"-20')";
//
//                String sql3 = "insert into ecriture values(default, 7, 1, "+15000*5*i+",0,'201"+e+"-"+i+"-25')";
//
//                String sql4 = "insert into ecriture values(default, 21, 0,0,"+15000*5*i+",'201"+e+"-"+i+"-25')";
//                System.out.println(sql2);
//                try {
//                    int a = bf.insertKey(sql1);
//                    int b = bf.insertKey(sql2);
//                    int c = bf.insertKey(sql3);
//                    int d = bf.insertKey(sql4);
//                    bf.commit();
//                } catch (SQLException ex) {
//                    Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
//            e++;
//        }
//        
//        
//    }
//    
    //Prendre le lundi d'une date donnée
    public static Date getFirstDayOfWeek(Date d) throws ParseException, Exception{
        DateFormat df = new SimpleDateFormat("dd/MM/yy");
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        Date dateRet = new Date(c.getTimeInMillis());
        System.out.println(df.format(dateRet));
        return Utils.stringToDate(df.format(dateRet));
    }
    
    public static Date getFirstDayOfWeek(String dateString) throws ParseException, Exception{
        Date d = Utils.stringToDate(dateString);
        return Utils.getFirstDayOfWeek(d);
    }
    
    
    public static String getDateAuj() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
        java.util.Date date = new java.util.Date();
        return dateFormat.format(date);
    }

    public boolean isDateNorme(Date dateInsere) {
        long l = dateInsere.getTime();
        System.out.println(l + "la date insere en long : utils : 156");
        return true;
    }

    public static String getAnneeAuj() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy");
        java.util.Date date = new java.util.Date();
        return dateFormat.format(date);
    }

    public static String getHeureAuj() {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm");
        java.util.Date date = new java.util.Date();
        return dateFormat.format(date);
    }
    
    public static int getMonth(Date date){
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.MONTH);
    }
    
    public static int getYear(Date date){
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.YEAR);
    }
    
    public static Date getLastDayOfMonth(Date d) throws Exception{
        Date lastDay = null;
        try {
            Calendar c = Calendar.getInstance();
            c.setTime(d);
            int day =c.getActualMaximum(Calendar.DAY_OF_MONTH);
               
            lastDay = Utils.stringToDate(Utils.getYear(d)+"-"+Utils.getMonth(d)+"-"+day);
            System.out.println("le last Day of the month is :" + lastDay);
        } catch (ParseException ex) {
            Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return lastDay;
    }
    
    public static Date getFirstDayOfMonth(Date d) throws Exception{
        Date lastDay = null;
        try {
            Calendar c = Calendar.getInstance();
            c.setTime(d);
            int day =c.getActualMinimum(Calendar.DAY_OF_MONTH);
               
            lastDay = Utils.stringToDate(Utils.getYear(d)+"-"+Utils.getMonth(d)+"-"+day);
            System.out.println("le first Day of the month is :" + lastDay);
        } catch (ParseException ex) {
            Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return lastDay;
    }
    
    
    
    public static  ArrayList<Date> separateIntervalleDateSur2Mois(Date debut, Date fin) throws Exception{
        ArrayList<Date> array = new ArrayList<>();
        int moisDebut = Utils.getMonth(debut);
        int moisFin = Utils.getMonth(fin);
        if(moisDebut != moisFin){
            array.add(debut);
            array.add(Utils.getLastDayOfMonth(debut));
            array.add(Utils.getFirstDayOfMonth(fin));
            array.add(fin); 
        }else{
            array.add(debut); array.add(fin);
        }
        
        return array;
    }
    
    
    public static boolean isIntersect(Date date_debut1, Date date_fin1, Date date_debut2, Date date_fin2){
        if(Utils.espaceJour2Date(date_debut1, date_debut2)<0){
            if(Utils.isBetween2Date(date_debut2, date_fin2, date_fin1))return true;

        }
        if(Utils.espaceJour2Date(date_debut1, date_debut2)>0){
            if(Utils.isBetween2Date(date_debut2, date_fin2, date_debut1))return true;
            
        }
        return true;
    }
    
    public static void insertDataTable(JTable table, Object[][] data, String[] column, boolean [] canEdit){
        table.setModel(new javax.swing.table.DefaultTableModel(
            data, column ) {
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        
    }
    
    
   
}
