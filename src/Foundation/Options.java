/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Foundation;

/**
 *
 * @author Jauma
 */
public class Options {
    
    public enum ACTION{
        MOVE,EXIT,INTERACT,DEBUG,TELEPORT,ATTACK,HELP,EQUIP,NULA,SAVE
    }    
    
    public DIRECTIONS path;    
    public ACTION taken;    
    public int indice_item;
    
    public Options(ACTION taken){
        this.taken = taken;
        path = DIRECTIONS.BOT;
    }
    
    public Options(ACTION taken,DIRECTIONS dir){
        this.taken = taken;
        path = dir;
    }        
        
    public void SetAction(ACTION act){
        taken = act;
    }    
    
    public void SetPath(DIRECTIONS dir){        
        path = dir;
    }
}
