import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
//javier eduardo lopez ontiveros
//topicos avanzados de programacion
//examen recuperacion de componentes
public class App extends JFrame
{
    public static void main(String[] args) throws Exception
    {
        new App();
    }
    public App()
    {
        setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        CuentaLetras mycompo = new CuentaLetras();
        add(mycompo);
        setVisible(true);
    }
}