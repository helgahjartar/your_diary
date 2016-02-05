// Dæmi um notkun Value klasans í Java til a› geyma dagbókaruppl‡singar.
//
// Höfundur:  Snorri Agnarsson
//
// A›fer›irnar í ﬂessum klasa geta hugsanlega veri› gagnlegar til a›
// leysa dagbókarverkefni› í Swing.  ﬁi› munu› einnig ﬂurfa a› grípa
// atbur›inn propertyChange í JCalendar hlutnum ef ﬂi› noti› slíkan
// hlut.
//
// Skrá ﬂessa má ﬂ‡›a og keyra me› eftirfarandi skipunum í Windows:
//   javac -cp .;TransportSE.jar DiaryValues.java
//   java -cp .;TransportSE.jar DiaryValues
package Virkni;

import is.hi.snorri.transport.Value;
import java.util.Date;
import java.util.Formatter;
import java.util.Calendar;

public class DiaryValues
{
    // Notkun: text = DiaryValues.getDateText(days,date);
    // Fyrir:  days er Value tafla sem inniheldur texta allra dagsetninga.
    //         date er strengur sem inniheldur dagsetningu ÁÁÁÁMMDD
    // Eftir:  text er textinn fyrir dagsetninguna, "" ef enginn texti.
    public static String getDateText( Value days, String date )
    {
        Value res = days.get(Value.makeString(date));
        if( res==null ) return "";
        return res.asString();
    }
    
    // Notkun: breytt = DiaryValues.setDateText(days,date,text);
    // Fyrir:  days er Value tafla sem inniheldur texta allra dagsetninga.
    //         date er strengur sem inniheldur dagsetningu ÁÁÁÁMMDD,
    //         text er strengur (ekki null).
    // Eftir:  text er nú textinn fyrir dagsetninguna í days, breytt
    //         er satt ﬂá og ﬂví a›eins a› n‡ji textinn sé ekki sá sami
    //         og gamli textinn fyrir dagsetninguna.
    public static boolean setDateText( Value days, String date, String text )
    {
        String oldText = getDateText(days,date);
        Value newVal = null;
        if( !text.equals("") ) newVal = Value.makeString(text);
        days.put(Value.makeString(date),newVal);
        return !text.equals(oldText);
    }
    
    // Notkun: strDate = DiaryValues.dateToString(date);
    // Fyrir:  date er java.util.Date hlutur.
    // Eftir:  strDate er strengur á sni›i ÁÁÁÁMMDD sem stendur
    //         fyrir sömu dagsetnigu.
    public static String dateToString( Date date )
    {
        StringBuilder b = new StringBuilder();
        Formatter f = new Formatter(b);
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        f.format ( "%04d%02d%02d"
                 , c.get(Calendar.YEAR)
                 , c.get(Calendar.MONTH)
                 , c.get(Calendar.DAY_OF_MONTH)
                 );
        return b.toString();
    }
    
    // Notkun: days = DiaryValues.loadDays();
    // Fyrir:  Ekkert.
    // Eftir:  Breytan days inniheldur Value töflu ﬂar sem lyklarnir eru
    //         dagsetningar, ﬂ.e. strengir á sni›i ÁÁÁÁMDD, og samsvarandi
    //         gildi eru textar dagsetninganna í dagbókinni.  Taflan var
    //         lesin úr skránni Diary.dat í heimasvæ›i núvernadi notanda.
    //         Ef engin slík skrá var til ﬂá er taflan tóm.
    public static Value loadDays()
    {
        try
        {
            return Value.loadFile(System.getenv("USERPROFILE")+"\\Diary.dat");
        }
        catch( Exception e )
        {
            return Value.makeTable();
        }
    }
    
    // Notkun: DiaryValues.saveDays(days);
    // Fyrir:  Gildi› days inniheldur Value töflu eins og loadDays skilar.
    // Eftir:  Taflan hefur veri› skrifu› í skrána Diary.dat í heimasvæ›i
    //         núvernadi notanda.
    public static void saveDays( Value days )
        throws java.io.FileNotFoundException, java.io.IOException
    {
        Value.saveFile(days,System.getenv("USERPROFILE")+"\\Diary.dat");
    }

     public static void main( String[] args )
        throws Exception
    {
        Value days = DiaryValues.loadDays();
        boolean breytt = DiaryValues.setDateText(days,"20121231","Gamlárskvöld");
        System.out.println("Textinn breyttist: "+breytt);
        System.out.println("N‡ji textinn: "+DiaryValues.getDateText(days,"20121231"));
        DiaryValues.saveDays(days);
    }
}

