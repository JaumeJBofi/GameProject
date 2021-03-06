/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import Foundation.Coordinate;
import Foundation.DIRECTIONS;
import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Arthuro
 */
public class Enemy extends Entity {
    
    private String description;
    private int armadura;

    //Coordinate position;
    public Enemy(Coordinate varPosition) {
        super(varPosition, "Lament", 15, 5, 5,1);
        SetLookDirection(DIRECTIONS.BOT);
        nivel = 1;
        description = " ";
    }

    //Modif
    public Enemy(Coordinate varPosition, String nomb, int vida, int lvl, int initStrength, String varDescription, int varArmor) {
        super(varPosition, nomb, vida, initStrength, varArmor,1);
        SetLookDirection(DIRECTIONS.BOT);
        SetNivel(lvl);
        SetDescription(varDescription);
    }
    
    public Enemy(Coordinate varPosition, String nomb, int vida, int lvl, int initStrength, String varDescription, int varArmor,String spriteInfo,
                    int tileSizeX,int tileSizeY) {
        super(varPosition, nomb, vida, initStrength, varArmor,1,spriteInfo,tileSizeX,tileSizeY);
        SetLookDirection(DIRECTIONS.BOT);
        SetNivel(lvl);
        SetDescription(varDescription);
    }
    
    public Enemy(Enemy baseEnemy)
    {
        super(baseEnemy);
        copySprite(baseEnemy);
        SetLookDirection(DIRECTIONS.BOT);
        SetDescription(baseEnemy.GetDescription());
    }

    final public void SetDescription(String varDesc) {
        description = varDesc;
    }

    public String GetDescription() {
        return description;
    }
 
//    public Enemy copiar() {
//        Enemy nuevo_enemigo = new Enemy(this.GetPosition(), this.GetNombre(), this.GetVida(), nivel, GetStrength(), GetDescription(), this.GetArmor());
//        return nuevo_enemigo;
//    }
    
    public Enemy copiar()
    {
        Enemy nuevo_enemigo = new Enemy(this);       
        return nuevo_enemigo;
    }

    public int GetLvl() {
        return nivel;
    }

    public void SetLvl(int varLvl) {
        nivel = varLvl;
    }

    // Podemos extender de aqui.
    public int GetEnemyDamage() {
        return GetStrength();
    }
    
    @Override
    public void Save(FileWriter fr)
    {
        try {
            fr.write(this.GetNombre() + ",");
            fr.write(this.GetDescription() + ",");
            fr.write("" + this.GetX() + ',' + this.GetY() + ',');
            fr.write("" + this.hp + ',' + this.nivel + ","+ this.GetArmor() + ',' + this.GetStrength()  + "\r\n");                        
        } catch (IOException e) {
            e.printStackTrace();
        }       
    }
    
    @Override
    public void Load(FileReader flectura, BufferedReader buffer)
    {
        
    }
    
     public void guardar_enemigo(FileWriter fr) {
        try {
            fr.write(this.GetNombre() + ",");
            fr.write(this.GetDescription() + ",");
            fr.write("" + this.GetX() + ',' + this.GetY() + ',');
            fr.write("" + this.hp + ',' + this.nivel + ","+ this.GetArmor() + ',' + this.GetStrength()  + "\r\n");
            
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void LoadComponents(String spriteInfo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void Render(Graphics g) {
<<<<<<< HEAD
        paint(g);
=======
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
>>>>>>> origin/master
    }

    @Override
    public void Dispose() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
