/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bus.station;

/**
 *
 * @author user
 */
public class Minibus extends Vehicle{
    public Minibus(String model)
    {
        this.multiplier = 1;
        this.type = "Minibus";
        this.model = model;
    }
    
}
