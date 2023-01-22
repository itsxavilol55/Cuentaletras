import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
//javier eduardo lopez ontiveros
//topicos avanzados de programacion
//examen recuperacion de componentes 
public class CuentaLetras extends JPanel implements ActionListener, KeyListener, ComponentListener
{
    private JButton btnfrecuencia, btnLimpiar;
    private JLabel lbl1, lbl2;
    private JTextField txt1;
    private DefaultTableModel modeloLetras;
    private JScrollPane spLetras, spLeidos;
    private JPanel leidos;
    private int[] contador;
    public CuentaLetras()
    {
        contador = new int[27];
        interfaz();
        eventos();
    }
    private void interfaz()
    {
        setSize(700, 500);
        setLayout(null);
        setComponentOrientation(getComponentOrientation());
        lbl1 = new JLabel("Texto");
        lbl2 = new JLabel("Texto leido");
        txt1 = new JTextField();
        btnfrecuencia = new JButton("Frecuencia");
        btnLimpiar = new JButton("Limpiar");
        modeloLetras = new DefaultTableModel(1, 27);
        modeloLetras.setColumnIdentifiers(new Object[]
        { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "Ñ", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z" });
        JTable tablaLetras = new JTable(modeloLetras);
        tablaLetras.setModel(modeloLetras);
        tablaLetras.setRowHeight(30);
        tablaLetras.setEnabled(false);
        spLetras = new JScrollPane(tablaLetras);
        leidos = new JPanel();
        leidos.setBackground(Color.white);
        leidos.setLayout(new BoxLayout(leidos, 1));
        spLeidos = new JScrollPane(leidos);
        spLeidos.setBorder(null);
        add(lbl1);
        add(lbl2);
        add(txt1);
        add(btnfrecuencia);
        add(btnLimpiar);
        add(spLetras);
        add(spLeidos);
        setVisible(true);
    }
    private void eventos()
    {
        txt1.addKeyListener(this);
        btnLimpiar.addActionListener(this);
        btnfrecuencia.addActionListener(this);
        this.addComponentListener(this);
    }
    private int getAltoo(JPanel panel, double d)
    {
        return (int) (panel.getHeight() * d);
    }
    private int getAncho(JPanel panel, double d)
    {
        return (int) (panel.getWidth() * d);
    }
    public int[] getContador()
    {
        return contador;
    }
    public String[] getPalabras()// regresa las palabras leidas
    {
        String[] array = new String[leidos.getComponentCount()];
        for (int i = 0; i < array.length; i++)
        {
            JButton btnaux = (JButton) leidos.getComponent(i);
            array[i] = btnaux.getText();
        }
        return array;
    }
    @Override public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == btnfrecuencia)
        {
            spLetras.setVisible(!spLetras.isVisible());
            return;
        }
        if (e.getSource() == btnLimpiar)
        {
            txt1.setText("");
            leidos.removeAll();
            for (int i = 0; i < contador.length; i++)
                modeloLetras.setValueAt(0, 0, i);
            return;
        } // al no ser ninguno de los otros 2 botones entra los de la lista leidos
        if (txt1.getText().trim().length() != 0)// en caso de que el txt tenga texto se volvera a añadir a la lista
        {
            meteLista();
        }
        JButton btn = (JButton) e.getSource();
        txt1.setText(btn.getText());
        leidos.remove(btn);
        txt1.requestFocus();
        repaint();
        revalidate();
    }
    @Override public void keyPressed(KeyEvent e)
    {
        if (e.getExtendedKeyCode() == 10)
        {
            meteLista();
            txt1.setText("");
            txt1.requestFocus();
            revalidate();
            return;
        }
        if (e.getExtendedKeyCode() == 8)
        {
            try
            {
                String textaux = txt1.getText();
                int medida = textaux.length();
                int valor = (int) textaux.charAt(medida - 1);
                if (valor == 241 || valor == 209)
                {
                    contador[14]--;
                    modeloLetras.setValueAt(contador[14], 0, 14);
                    return;
                }
                if (valor >= 65 && valor <= 90) valor -= 65;
                else if (valor >= 97 && valor <= 122) valor -= 97;
                else return;
                if (valor >= 14) valor++;
                contador[valor]--;
                modeloLetras.setValueAt(contador[valor], 0, valor);
            }
            catch (IndexOutOfBoundsException er)
            {
            }
            return;
        }
        if (e.getKeyChar() == 'ñ' || e.getKeyChar() == 'Ñ')
        {
            contador[14]++;
            modeloLetras.setValueAt(contador[14], 0, 14);
            return;
        }
        if (e.getKeyCode() >= 65 && e.getKeyCode() <= 90)
        {
            int valor = e.getKeyCode() - 65;
            if (valor >= 14) valor++;
            contador[valor]++;
            modeloLetras.setValueAt(contador[valor], 0, valor);
            return;
        }
    }
    private void meteLista()
    {
        JButton btn = new JButton(txt1.getText());
        btn.setBorder(null);
        btn.setBackground(Color.white);
        leidos.add(btn);
        btn.addActionListener(this);
    }
    @Override public void componentResized(ComponentEvent e)
    {
        lbl1.setBounds(getAncho(this, 0.1), getAltoo(this, 0.05), getAncho(this, 0.3), 30);
        lbl2.setBounds(getAncho(this, 0.7), getAltoo(this, 0.05), getAncho(this, 0.3), 30);
        txt1.setBounds(getAncho(this, 0.1), getAltoo(this, 0.05) + 30, getAncho(this, 0.4), 30);
        btnfrecuencia.setBounds(getAncho(this, 0.1), getAltoo(this, 0.05) + 90, getAncho(this, 0.25), 30);
        btnLimpiar.setBounds(getAncho(this, 0.1), getAltoo(this, 0.05) + 140, getAncho(this, 0.25), 30);
        spLetras.setBounds(getAncho(this, 0.1), getAltoo(this, 0.7), getAncho(this, 0.85), 55);
        spLeidos.setBounds(getAncho(this, 0.7), getAltoo(this, 0.05) + 30, getAncho(this, 0.3), getAltoo(this, 0.65) - 30);
        revalidate();
    }
    @Override public void componentMoved(ComponentEvent e)
    {
    }
    @Override public void componentShown(ComponentEvent e)
    {
    }
    @Override public void componentHidden(ComponentEvent e)
    {
    }
    @Override public void keyTyped(KeyEvent e)
    {
    }
    @Override public void keyReleased(KeyEvent e)
    {
    }
}//