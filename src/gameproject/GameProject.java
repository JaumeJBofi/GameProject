/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameproject;
import Controllers.Dibujador;
import Controllers.DungeonManager;
import Foundation.CellInformation;
import Foundation.DIRECTIONS;
import Models.Avatar;
import Foundation.Options;
import Foundation.Options.ACTION;
import java.util.Random;

/**
 *
 * @author Jauma
 */
public class GameProject {

    /**
     * @param args the command line arguments
     */
    
    
    public static void main(String[] args) {               
        DungeonManager myManager = new DungeonManager();                        
        
        // Aca hacemos random de las dimensiones
        //Tambien podemos ya ir creando los otros laberintos
        Random randManager = new Random();
        
        int varM = randManager.nextInt(40-20)+20;
        int varN = randManager.nextInt(40-20)+20;
        
        Avatar player = new Avatar(myManager.CreateDungeonDistribution(varM,varN,0.15,0,0.3),(int)(varM*0.4),(int)(varN*0.20),"Jaume Bofi");
        //Avatar player = new Avatar(myManager.CreateDungeonDistribution(40,5,0.5,0),40,10);
        
        Dibujador Renderer = new Dibujador();
        
        Options choiceTaken = new Options(ACTION.INTERACT);
        
        CellInformation nextCellInformation;
        
        Renderer.mostrarLaberinto(myManager.GetActiveDungeon(), player);
        
        while((ACTION.EXIT!=((Renderer.mostrarMenu(choiceTaken)).taken)))
        {                        
            switch(choiceTaken.taken){
                case MOVE:{
                    if(!(nextCellInformation = myManager.ValidMoveAndChange(player.GetPosition(),choiceTaken.path)).isWall()&&nextCellInformation.GetType()==CellInformation.CELLTYPE.ADENTRO)
                            {
                        player.Move(choiceTaken.path, 1);                    
                    }else{
                        switch(nextCellInformation.GetType())
                        {
                            case PARED:
                                System.out.println("\n\nNo puedes atravezar paredes....aún...");
                            break;
                            case ARTIFACT:
                                System.out.println("\n\nUn misterioso objeto bloquea tu camino. Puedes intentar cogerlo\n");
                            break;
                            case ENEMY:
                                System.out.printf("Cuidado! Un enemigo? Preparate para una posible batalla");
                            break;
                        }                        
                        System.out.println("\n");
                    }                    
                }break;
                case INTERACT:{  
                    if(!(nextCellInformation = myManager.ValidMoveAndChange(player.GetPosition(),choiceTaken.path)).isWall()){
                        
                        
                        switch(nextCellInformation.GetType())
                        {
                            case ARTIFACT:
                            {
                                myManager.Interactuar(player,choiceTaken.path); // borra el artefacto de la matriz                                
                                System.out.println("\n\n INTERACT CORRECTO");
                                System.out.println("");                                  
                            }break;
                            default:
                            {
                                System.out.println("\n\nNo puedes interactuar con esa casilla");
                                System.out.println("");                                
                            }break;                            
                        }                        
                    }else{
                        System.out.println("\n\nNo puedes interactuar con esa pared");
                        System.out.println("");
                    }
                }break;
                default:{
                    System.out.println("\n\nAcción no definida\n");
                        System.out.println("");
                }break;
            }
            Renderer.mostrarLaberinto(myManager.GetActiveDungeon(), player);
        }
    }
    
}
