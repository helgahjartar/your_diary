/*
* @author Helga Hjartardóttir
 * heh54@hi.is
 * 29.mars 2015
 */
package Vidmot;

import Virkni.DiaryValues;
import com.toedter.calendar.IDateEvaluator;
import is.hi.snorri.transport.Value;
import java.awt.Color;
import java.util.Date;

/** VidburdurEvaluator utfaerir IDateEvaluator. 
 * Útlit vidmotsins breytist eftir thvi hvar buid er ad skra vidburdi.
 * 
 * Forriti› var ﬂ‡tt og keyrt í Netbeans.
 */
public class VidburdurEvaluator implements IDateEvaluator {
    
    private Value vidburdir;
    // Geymir toflu med vidburdum.
    private static final Color specialForeground = Color.WHITE;
    // Geymir lit fyrir letur dagsetninga sem geyma vidburdi
    private static final Color specialBackground = Color.PINK;
    // Geymir lit fyrir bakgrunn dagsetninga sem geyma vidburdi
    
    /**
     * isSpecial skilar boolean gildi true ef buid er ad skra vidburd
     * a dagsetningu sem valin var.
     */
    @Override
    public boolean isSpecial(Date date) {
        String askingDate = DiaryValues.dateToString(date);
        return(! ("".equals(DiaryValues.getDateText(vidburdir, askingDate))));
    }
    
    /**
     * getSpecialForegroundColor() skilar lit leturs fyrir dagsetningar
     * med skradum vidburdum
     */
    @Override
    public Color getSpecialForegroundColor() {
        return specialForeground;
    }
    
    /**
     * getSpecialBackgroundColor() skilar lit bakgrunns fyrir dagsetningar
     * med skradum vidburdum
     */
    @Override
    public Color getSpecialBackroundColor()  {
        return specialBackground;
    }
    
    /**
     * getSpecialTooltip() skilar String sem JDayChooser notar.
     */
    @Override
    public String getSpecialTooltip() {
        return "Vi›bur›ur";
    }
    
    /**
     * SetVidburdir frumstillit tilviksbreytuna vidburdir.
     * days heldur utan um toflu med vidburdum
     */
    void setVidburdir(Value days) {
        vidburdir = days;
    }

    /**
     * Skilar true ef ekki er haegt ad velja umraedda dagsetningu.
     * date er dagsetning sem var valin
     */
    @Override
    public boolean isInvalid(Date date) {
        return false;
    }

    /**
     * Ekki utfaert.
     */
    @Override
    public Color getInvalidForegroundColor() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Ekki utfaert.
     */
    @Override
    public Color getInvalidBackroundColor() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Ekki utfaert.
     */
    @Override
    public String getInvalidTooltip() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
