/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author Arthuro
 */
public class Arma extends Artefacto{
    int danho_min;
    int danho_max;
    
    public Arma(){
        danho_min = 0;
        danho_max = 0;
    }
    
    public void SetDanhoMin(int min){
        danho_min = min;
    }
    
    public void SetDanhoMax(int max){
        danho_max = max;
    }
    
    public int GetDanhoMin(){
        return danho_min;
    }
    
    public int GetDanhoMax(){
        return danho_max;
    }
}
