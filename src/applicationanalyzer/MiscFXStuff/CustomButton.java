/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applicationanalyzer.MiscFXStuff;

import javafx.scene.control.Button;


/**
 *
 * @author Hundisilm
 */
public class CustomButton {
    
    public static Button createButton(String title){
        Button newButton = new Button(title);
        newButton.setText("Create " + title);
        newButton.setId(title);
        return newButton;
    }
    
}
