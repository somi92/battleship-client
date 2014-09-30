package application_logic;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import user_interface.MainGUI;
import user_interface.SeaFieldPanel;

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
	MainGUI mainGUI;
	
	
	public SeaFieldManager(MainGUI mainGUI){
		this.mainGUI = mainGUI;
	}
	
	
	
	
	public SeaFieldPanel createSeaField(boolean mine, int opponent){
		SeaFieldPanel seaFieldPanel = new SeaFieldPanel();
//		seaField.setPreferredSize(new Dimension(250, 250));
		seaFieldPanel.setLayout(new BorderLayout(0, 0));
		
		setClickActionListener(seaFieldPanel.seaButtonMatrix, mine, opponent);
		
		
		return seaFieldPanel;
	}
	
/**
 * metoda postavlja action listenere na polja na u
 *  glavnom gui-u.
 * @param btn
 * @param isMyGameBoard true ako je akcija za moju tablu, false ako postavljam za protivnikove table
 */
		public void setClickActionListener(final JButton[][] btn, boolean isMyGameBoard, final int opponent){
			
			final boolean mine = isMyGameBoard;
		
			
			for ( int i = 0; i < btn.length; i++) 
				for (int j = 0; j < btn.length; j++) {
					
					final int iEnter = i;
					final int jEnter = j;
				
					btn[i][j].addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							if(mine && opponent==0){
								// nikada ne kl;ikcem na sebe
								// moje je sve uvek enable(false)
							}else{
								
								if(opponent==1){
									System.out.println("kliknuo sam na protivnika1.");
									mainGUI.pozoviMetoduProtokolaSendSHT(1,iEnter,jEnter);
									
								}else{//opponent2
									System.out.println("kliknuo sam na protivnika1.");
									mainGUI.pozoviMetoduProtokolaSendSHT(2,iEnter,jEnter);
									
								}
							}
						}
				
			});
		}
		}
}

	
	
	
	
	
	
	
	
	
	

