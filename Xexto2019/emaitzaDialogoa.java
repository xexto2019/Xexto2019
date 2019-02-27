package Xexto2019;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class emaitzaDialogoa extends JDialog implements ActionListener {
	
	JButton button;
	JTextField txtTalde1;
	JTextField txtTalde2;
	Partida partida;
	Liga nireLiga;

	  public emaitzaDialogoa(Liga nireLiga, Partida partida) {
		  this.setModal(true);
          this.setLayout(null);
          this.setSize(400, 300);
          this.setTitle("Partidaren emaitza");
          this.partida = partida;
          this.nireLiga=nireLiga;


          // create a label 
          JLabel lblTalde1 = new JLabel(partida.getTalde1().getIzena()); 
          JLabel lblTalde2 = new JLabel(partida.getTalde2().getIzena()); 	              
          txtTalde1 = new JTextField(); 
          txtTalde2 = new JTextField();
          JButton sartuEmaitza = new JButton("Sartu");
          sartuEmaitza.addActionListener(this);
  		
  		  lblTalde1.setBounds(10, 10, 136, 23);
          lblTalde2.setBounds(10, 30, 136, 23); 
          txtTalde1.setBounds(200, 10, 30, 23);
          txtTalde1.setColumns(3);
          txtTalde2.setBounds(200, 30, 30, 23);
          txtTalde2.setColumns(3);
          sartuEmaitza.setBounds(200, 60, 130, 23);

          this.add(lblTalde1); 
          this.add(lblTalde2); 
          this.add(txtTalde1);
          this.add(txtTalde2);
          this.add(sartuEmaitza);

          // set visibility of dialog 
          this.setVisible(true); 
        }
	     
	  @Override
	  public void actionPerformed(ActionEvent e) {

			int emaitza1 = 0;
			int emaitza2 = 0;
			if (txtTalde1.getText().length()>0) {
				emaitza1 = Integer.parseInt(txtTalde1.getText());
			}

			if (txtTalde2.getText().length()>0) {
				emaitza2 = Integer.parseInt(txtTalde2.getText());
			}
	
			partida.setEmaitza1(emaitza1);
			partida.setEmaitza2(emaitza2);
			eguneratuPartida(partida);
			
			this.setVisible(false); 
			this.dispose();
	
			}
	  
	  public void eguneratuPartida(Partida partida){
			boolean aurkitua=false;
			for (int j = 0; j < nireLiga.getJardunaldiak().size() && !aurkitua; j++) {
				Jardunaldia jardunaldia = nireLiga.getJardunaldiak().get(j);
				for (int p = 0; p < jardunaldia.getPartidak().size() && !aurkitua; p++) {
					System.out.println(partida + " ... " + p);
					if (jardunaldia.getPartidak().get(p).equals(partida)) {
						aurkitua=true;
						jardunaldia.getPartidak().get(p).setEmaitza1(partida.getEmaitza1());
						jardunaldia.getPartidak().get(p).setEmaitza2(partida.getEmaitza2());
					}
				}
			}
		}
  }

	

	 