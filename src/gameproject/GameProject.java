/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameproject;
import Interfaz.Dibujador;
import Mundo.DungeonManager;
import Foundation.CellInformation;
import Models.Avatar;
import Interfaz.Lore;
import Foundation.Options;
import Foundation.Options.ACTION;
import java.util.Random;
import java.util.Scanner;


/**
 *
 * @author Jauma
 */
public class GameProject {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {  
               
        // Aca hacemos random de las dimensiones
        //Tambien podemos ya ir creando los otros laberintos
        Random randManager = new Random();
        Scanner in = new Scanner(System.in);
        //Lore nos ayuda a narrar la historia
        Lore historia = new Lore();
        //Decidimos que intro deseamos leer
        String name;
        System.out.println("Presione enter para empezar:");
        if(in.nextLine().compareToIgnoreCase("re")==0) name = historia.nacer();
        else name = historia.IntroMenu(in);
        
        DungeonManager myManager = new DungeonManager(randManager.nextInt(7-3)+3);
        //DungeonManager myManager = new DungeonManager(2);
        
        int varM = randManager.nextInt(50-25)+25;
        int varN = randManager.nextInt(35-25)+25;                
        
        Avatar player = new Avatar(myManager.CreateDungeonDistribution(varM,varN,0.15,5,0.3),10,6,100,name,5);
        //Avatar player = new Avatar(myManager.CreateDungeonDistribution(40,5,0.5,0),40,10);
        
        Dibujador Renderer = new Dibujador();
        
        Options choiceTaken = new Options(ACTION.INTERACT);
        
        CellInformation nextCellInformation;
        
        historia.writeNLines(20);
        System.out.format("Mundo Nr° %d de %d\n",myManager.GetActiveDungeonIndex()+1,myManager.GetTotalDungeons());
        Renderer.mostrarLaberinto(myManager.GetActiveDungeon(), player);
        historia.listaAcciones();
        while((ACTION.EXIT!=((Renderer.mostrarMenu(choiceTaken)).taken)))
        {                        
            switch(choiceTaken.taken){
                case MOVE:{
                    if(!(nextCellInformation = myManager.ValidMoveAndChange(player.GetPosition(),choiceTaken.path)).isWall()&&nextCellInformation.GetType()==CellInformation.CELLTYPE.ADENTRO)
                            {
                                if(nextCellInformation.isNext()||nextCellInformation.isPrevious())
                                {
                                   if(myManager.ChangeDungeon(player,nextCellInformation.isNext())==1){
                                       historia.winGame(in);                                       
                                   }
                                }else{
                                    player.Move(choiceTaken.path, 1);                                        
                                }                       
                        historia.writeNLines(20);
                    }else{
                        historia.writeNLines(20);
                        switch(nextCellInformation.GetType())
                        {
                            case PARED:
                                System.out.println("\n\nNo puedes atravezar paredes....aún...");
                            break;
                            case ARTIFACT:
                                System.out.println("\n\nUn misterioso objeto bloquea tu camino. Puedes intentar cogerlo");
                            break;
                            case ENEMY:
                                System.out.println("\n\nCuidado! Un enemigo? Preparate para una posible batalla");
                            break;                            
                        }         
                        System.out.println("");                       
                    } 
                    //Esto hace que los enemigos se muevan
                    //# Preg 1
                    myManager.GetActiveDungeon().MoveEnemies(player.GetX(),player.GetY());
                }break;
                case INTERACT:{  
                    if(!(nextCellInformation = myManager.ValidMoveAndChange(player.GetPosition(),choiceTaken.path)).isWall()){
                        
                        
                        switch(nextCellInformation.GetType())
                        {
                            case ARTIFACT:
                            {                                
                                myManager.GetActiveDungeon().Interactuar(player,choiceTaken.path); // borra el artefacto de la matriz                                
                                historia.writeNLines(20);                              
                                System.out.println("\n\n INTERACT CORRECTO");                               
                            }break;
                            case ENEMY:
                            {
                                // #Preg 2:
                                historia.writeNLines(20);                              
                                // Podemos ponder en choice taken la posicion... luego podemos implementar eso
                                if(myManager.GetActiveDungeon().Battle(player, choiceTaken.path)){
                                    // Gana la batalla... No muere
                                }else
                                {
                                    System.out.println("Fin del juego. Ha muerto. Presione Enter\n");
                                    in.nextLine();
                                    System.exit(0);
                                    // Fin del juego
                                    
                                }
                            }break;
                            default:
                            {
                                historia.writeNLines(20);                              
                                System.out.println("\n\nNo puedes interactuar con esa casilla"); 
 
                            }break;                            
                        }                        
                        System.out.println("");
                    }else{
                        historia.writeNLines(20);
                        System.out.println("\n\nNo puedes interactuar con esa pared");                      
                    }
                    // Pregunta 1
                    //# Preg 1 Y todas las clases adentro ya estaban implementadas
                    myManager.GetActiveDungeon().MoveEnemies(player.GetX(),player.GetY());
                }break;
                case DEBUG:
                {
                    historia.writeNLines(20);                    
                    myManager.printDebugInfo(player);
                    System.err.println("");
                }break;
                case TELEPORT:
                {                   
                    int x = in.nextInt();
                    int y = in.nextInt();
                    myManager.GetActiveDungeon().TeleportPlayer(player, x, y);                            
                }case HELP:
                {
                    historia.writeNLines(20);
                    System.out.println("Comandos:\nMover [Derecha|Izquierda|Arriba|Abajo] : Te mueves a la dirección especificada si es posible. No es case sensitive\n"
                            + "Interactuar: Interactuas con el objeto al que estas mirando. Donde miras es la ultima dirección a la que te moviste o intentaste\n"
                            + "Atacar: Eliminas al enemigo al que estas mirando\nEquipar: Te equipas una armadura o arma de tu inventorio\n"
                            + "Help: Muestra la ayuda del juego\nSalir: Sale del Juego actual\n\nPresione cualquier tecla para continuar\n");
                             in.nextLine();
                    historia.writeNLines(20);     
                }break;
                case ATTACK:
                {
                    if(!(nextCellInformation = myManager.ValidMoveAndChange(player.GetPosition(),choiceTaken.path)).isWall()){
                        switch(nextCellInformation.GetType())
                        {
                            case ENEMY:
                            {
                                // Random Text
                                historia.writeNLines(20);
                                System.out.println("\n\nHyaaa!!!\nTu ataque dio en el blanco...El enemigo se desvanece silenciosamente como si estuviera aliviado");      
                                //myManager.GetActiveDungeon().Battle(player.GetX(),player.GetY(),choiceTaken.path);
                            }break;
                            default:
                            {
                                historia.writeNLines(20);                             
                                System.out.println("\n\nNo hay absolutamente nada peligroso en esa dirección.");      
                            }
                        }
                    }else
                    {
                        historia.writeNLines(20);
                        System.out.println("\n\nEsa pared no te ha hecho nada. Disculpate, Las paredes tambien tienen sentimientos...");      
                    }
                    System.out.println("");
                }break;
                case EQUIP:
                {                    
                     // Falta Implementar
                    System.out.println();              
                    System.out.println("\n\nIngrese el indice del item a equipar o usar\n");
                    String input = in.nextLine();
                    try {
                        Integer n = Integer.parseInt(input);
                        if( n > 0 ){
                            if( n <= player.getSizeSaco() )
                                player.EquipItem(n-1);
                            else{
                                System.out.println("\nIndice no valido\n\n");
                                in.nextLine();
                            }
                        }
                        else{
                            System.out.println("\nIndice no valido\n\n");
                            in.nextLine();
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Mal input. Presione Enter para continuar\n");
                        in.nextLine();
                        //int a;
                    }                             
                    historia.writeNLines(20);
                }break;
                default:{
                    historia.writeNLines(20);
                    System.out.println("\n\nAcción no definida");
                    System.out.println("");                    
                }break;
            }
            System.out.format("Mundo Nr° %d de %d\n",myManager.GetActiveDungeonIndex()+1,myManager.GetTotalDungeons());
            Renderer.mostrarLaberinto(myManager.GetActiveDungeon(), player);
            historia.listaAcciones();
            //myManager.GetActiveDungeon().Render();
        }
    }
    
}
