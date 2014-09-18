package application_logic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import user_interface.SeaField;

/**
 * klasa koja upravlja poljima na glavnom GUI-u
 * 
 * funkcije:
 * kreira polja (creteSeaField())
 * Postavlja eventListenere na polje
 * 
 *
 */
public class SeaFieldManager {


	
	
	public SeaField createSeaField(){
		return new SeaField();
	}
	
/**
 * metoda postavlja action listenere na polja na
 *  glavnom gui-u.
 * @param btn
 * @param isMyGameBoard true ako je akcija za moju tablu, false ako postavljam za protivnikove table
 */
		public void setClickActionListener(final JButton[][] btn, boolean isMyGameBoard){
			
			final boolean a = isMyGameBoard;
			
			for (int i = 0; i < btn.length; i++) 
				for (int j = 0; j < btn.length; j++) 
					btn[i][j].addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
					
					if(a){
						
					}else{
						
					}
					
					
				}
			});
		}
		
}	
	
	
	
	
	
	
	
	
	
	

