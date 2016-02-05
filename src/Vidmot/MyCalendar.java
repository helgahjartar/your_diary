/*
 * @author Helga Hjartard�ttir
 * heh54@hi.is
 * 29.mars 2015
 */
package Vidmot;

import com.toedter.calendar.JCalendar;

/** MyCalendar er smidur sem erfir fra JCalendar og VidburdurEvaluator
 *  b�tir vi� hann.
 * 
 *  Forriti� var ��tt og keyrt � Netbeans.
 */
public class MyCalendar extends JCalendar{
    
    /**
     * MyCalendar(b) smidar hlut af gerd MyCalendar
     * og baetir vid hann VidburdurEvaluator.
     * b er VidburdurEvaluator fyrir MyCalendar.
     */
    public MyCalendar (VidburdurEvaluator b) {
        super(false);
        this.getDayChooser().addDateEvaluator(b);
    }
}
