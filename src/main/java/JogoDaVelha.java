import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class JogoDaVelha extends JFrame
{
    
    final int JOGADOR_1 = 1;
    final int JOGADOR_2 = 2;
    
    int jogadorVez = JOGADOR_1;
    
    int rodadas = 0;
    int j1 = 0;
    int j2 = 0; 
    int tamFont = 150;
    
    JPanel pTela = new JPanel(new GridLayout(3,3,10,10));
    JLabel placar = new JLabel();
    JLabel lInformacao = new JLabel("Jogador "+JOGADOR_1+" Placar: "+j1+" X "+j2);
    JLabel X = new JLabel("X");
    JLabel O = new JLabel("O");
    
    Bloco [] blocos = new Bloco[9];
    
    public static void main(String[] args) 
    {        
        new JogoDaVelha();
    }

    public JogoDaVelha()
    {
        setIcon();
        configurarTela();
        configurarJanela();
    }
    
    public void configurarTela()
    {
        add(BorderLayout.CENTER, pTela);
        add(BorderLayout.NORTH, lInformacao);
        
        pTela.setBackground(Color.BLACK);
        lInformacao.setFont(new Font("Arial", Font.BOLD, 30));
        lInformacao.setForeground(Color.GREEN);
        lInformacao.setHorizontalAlignment(SwingConstants.CENTER);
        placar.setFont(new Font("Arial", Font.BOLD, 30));
        placar.setForeground(Color.BLACK);
        placar.setHorizontalAlignment(SwingConstants.RIGHT);
        
        for(int i=0; i<9; i++)
        {
            Bloco bloco = new Bloco();
            blocos[i] = bloco;
            pTela.add(bloco);
        }
    }
    
    public void mudarVez()
    {
        if(jogadorVez == 1)
        {
            jogadorVez = 2;
            lInformacao.setText("Jogador 2  Placar: " +j1+" X "+j2);
            lInformacao.setForeground(Color.RED);
        }
        else
        {
            jogadorVez = 1;
            lInformacao.setText("Jogador 1  Placar: " +j1+" X "+j2);
            lInformacao.setForeground(Color.GREEN);
        }
    }
    
    public boolean testarVitoria(int jog)
    {
        if(blocos[0].quem == jog && blocos[1].quem == jog && blocos[2].quem == jog)
        {
            return true;
        }
        if(blocos[3].quem == jog && blocos[4].quem == jog && blocos[5].quem == jog)
        {
            return true;
        }
        if(blocos[6].quem == jog && blocos[7].quem == jog && blocos[8].quem == jog)
        {
            return true;
        }
        if(blocos[0].quem == jog && blocos[3].quem == jog && blocos[6].quem == jog)
        {
            return true;
        }
        if(blocos[1].quem == jog && blocos[4].quem == jog && blocos[7].quem == jog)
        {
            return true;
        }
        if(blocos[2].quem == jog && blocos[5].quem == jog && blocos[8].quem == jog)
        {
            return true;
        }
        if(blocos[0].quem == jog && blocos[4].quem == jog && blocos[8].quem == jog)
        {
            return true;
        }
        if(blocos[2].quem == jog && blocos[4].quem == jog && blocos[6].quem == jog)
        {
            return true;
        }
        return false;
    }
    
    public void configurarJanela()
    {
        setTitle("Jogo da Velha");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500,500);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void setIcon() 
    {
        setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\heber\\OneDrive\\Documentos\\NetBeansProjects\\JogoDaVelha\\src\\main\\java\\Icone.png"));
    }
    
    public class Bloco extends JButton
    {   
        int quem = 0;
    
        public Bloco()
        {
             setBackground(Color.WHITE);
             addActionListener(e->{
                 if(quem == 0)
                 {
                     if(jogadorVez == JOGADOR_1)
                     {
                         setForeground(Color.GREEN);
                         setFont(new Font("Arial", Font.BOLD, tamFont));
                         setText("X");
                         quem = JOGADOR_1;
                     }
                     else
                     {
                         setForeground(Color.RED);
                         setFont(new Font("Arial", Font.BOLD, tamFont));
                         setText("O");
                         quem = JOGADOR_2;
                     }
                     if(testarVitoria(quem))
                     {
                         JOptionPane.showMessageDialog(null,"Jogador "+quem+" Venceu!"); 
                         
                         if(quem==1)
                         {
                             j1++;
                         }
                         else
                         {
                             j2++;
                         }
                         

                             for(int i=0; i<9; i++)
                             {
                                 blocos[i].setText("");
                                 blocos[i].quem = 0;
                                 rodadas = 0;
                                 jogadorVez = quem;
                             }
                     }
                     rodadas++;
                     if(rodadas == 9)
                     {
                         JOptionPane.showMessageDialog(null,"Deu velha");
                         
                         if(quem!=0)
                         {
                             for(int i=0; i<9; i++)
                             {
                                 blocos[i].setText("");
                                 blocos[i].quem = 0;
                                 rodadas = 0;
                                 jogadorVez = JOGADOR_1;
                             }
                         }
                     }
                     
                     mudarVez();
                 }
             });
        }
    }
}
