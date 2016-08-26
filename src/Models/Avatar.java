/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import Foundation.Coordinate;
import Foundation.DIRECTIONS;

/**
 *
 * @author Arthuro
 */
public class Avatar extends Entity implements IDibujable{

    private int vidaMaxima;
    private int tamShowX;
    private int tamShowY;

    public Avatar(Coordinate position) {
        super(position);
        hp = 100; // digamos q sea 100
        vidaMaxima = 500; // la vida maxima en el juego, por ejemplo        
        tamShowX = 15;
        tamShowY = 15;
    }
    
    public Avatar(Coordinate position,int varTamShowX,int varTamShowY) {
        super(position);
        hp = 100; // digamos q sea 100
        vidaMaxima = 500; // la vida maxima en el juego, por ejemplo        
        SetTamShowX(tamShowX);
        SetTamShowY(tamShowY);
    }
    
    public void MoveAvatar(DIRECTIONS way,int steps){ // nuevas coordenadas
        // Regresa False si las cooredenadas estan fuera de rango        
        int xFactor = 0, yFactor = 0;
        switch(way) {
                    case BOT:{ yFactor+=steps;                        
                    }break;
                    case TOP:{ yFactor-=steps;                        
                    }break;
                    case LEFT:{ xFactor-=steps;                        
                    }break;
                    case RIGHT:{ xFactor+=steps;                        
                    }
                }
        SetPosition(GetX()+xFactor, GetY()+yFactor);
        // falta verificar los limites de pantalla
    }   
    
    public void SetVidaMaxima(int v) {
        vidaMaxima = v;
    }
   
    public int GetVidaMaxima() {
        return vidaMaxima;
    }
    
    public int GetTamShowX(){
        return tamShowX;
    }
    
    final public void SetTamShowX(int tamShow){
        if(tamShow>=GetPosition().GetM()){
             tamShowX = 15;
        }else{
            tamShowX = tamShow;
        }           
    }
    
   final public void SetTamShowY(int tamShow){
       if(tamShow>=GetPosition().GetN()){
           tamShowY = 15;
       }else{
           tamShowY = tamShow;
       }
   }    
    public int GetTamShowY(){
        return tamShowY;
    }
    
    public void Render(){
        
    }
    
    public void LoadComponents(){
        
    }
    
    public void Dispose(){
        
    }
    
}
