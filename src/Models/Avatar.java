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
public class Avatar extends Entity{


    int vidaMaxima;


    public Avatar() {
        vida = 100; // digamos q sea 100
        vidaMaxima = 500; // la vida maxima en el juego, por ejemplo
    }
    public void MoveAvatar(int x1 , int y1){ // nuevas coordenadas
        x = x1;
        y = y1;
        // falta verificar los limites de pantalla
    }
    
    public void SetVidaMaxima(int v) {
        vidaMaxima = v;
    }

   
    public int GetVidaMaxima() {
        return vidaMaxima;
    }


}
