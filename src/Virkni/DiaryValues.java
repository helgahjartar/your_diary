// D�mi um notkun Value klasans � Java til a� geyma dagb�karuppl�singar.
//
// H�fundur:  Snorri Agnarsson
//
// A�fer�irnar � �essum klasa geta hugsanlega veri� gagnlegar til a�
// leysa dagb�karverkefni� � Swing.  �i� munu� einnig �urfa a� gr�pa
// atbur�inn propertyChange � JCalendar hlutnum ef �i� noti� sl�kan
// hlut.
//
// Skr� �essa m� ���a og keyra me� eftirfarandi skipunum � Windows:
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
    //         date er strengur sem inniheldur dagsetningu ����MMDD
    // Eftir:  text er textinn fyrir dagsetninguna, "" ef enginn texti.
    public static String getDateText( Value days, String date )
    {
        Value res = days.get(Value.makeString(date));
        if( res==null ) return "";
        return res.asString();
    }
    
    // Notkun: breytt = DiaryValues.setDateText(days,date,text);
    // Fyrir:  days er Value tafla sem inniheldur texta allra dagsetninga.
    //         date er strengur sem inniheldur dagsetningu ����MMDD,
    //         text er strengur (ekki null).
    // Eftir:  text er n� textinn fyrir dagsetninguna � days, breytt
    //         er satt ߇ og �v� a�eins a� n�ji textinn s� ekki s� sami
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
    // Eftir:  strDate er strengur � sni�i ����MMDD sem stendur
    //         fyrir s�mu dagsetnigu.
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
    // Eftir:  Breytan days inniheldur Value t�flu �ar sem lyklarnir eru
    //         dagsetningar, �.e. strengir � sni�i ����MDD, og samsvarandi
    //         gildi eru textar dagsetninganna � dagb�kinni.  Taflan var
    //         lesin �r skr�nni Diary.dat � heimasv��i n�vernadi notanda.
    //         Ef engin sl�k skr� var til ߇ er taflan t�m.
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
    // Fyrir:  Gildi� days inniheldur Value t�flu eins og loadDays skilar.
    // Eftir:  Taflan hefur veri� skrifu� � skr�na Diary.dat � heimasv��i
    //         n�vernadi notanda.
    public static void saveDays( Value days )
        throws java.io.FileNotFoundException, java.io.IOException
    {
        Value.saveFile(days,System.getenv("USERPROFILE")+"\\Diary.dat");
    }

     public static void main( String[] args )
        throws Exception
    {
        Value days = DiaryValues.loadDays();
        boolean breytt = DiaryValues.setDateText(days,"20121231","Gaml�rskv�ld");
        System.out.println("Textinn breyttist: "+breytt);
        System.out.println("N�ji textinn: "+DiaryValues.getDateText(days,"20121231"));
        DiaryValues.saveDays(days);
    }
}

