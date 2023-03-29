import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.text.*;

public class App {
    public static void main(String[] args) throws Exception {
        Marco ventana = new Marco();
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setVisible(true);
    }
}

class Marco extends JFrame {
    public Marco() {
        this.setSize(500, 500);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        add(new Lamina());
    }
}

class Lamina extends JPanel {
    public Lamina() {
        this.setLayout(new BorderLayout());
        areaTexto = new JTextPane();
        this.add(areaTexto, BorderLayout.CENTER);

        // ____________________________________________________________________________
        JPanel panelMenu = new JPanel();
        JMenuBar barraMenu = new JMenuBar();
        Fuente = new JMenu("Fuente");
        Estilo = new JMenu("Estilo");
        Tamagno = new JMenu("Tamaño");
        Herramientas = new JMenu("Herramientas");

        GenerarItemsFuente("Arial");
        GenerarItemsFuente("Serif");
        GenerarItemsFuente("Verdana");
        GenerarItemsFuente("Courier");
        barraMenu.add(Fuente);

        GenerarItemsEstilo("Negrita", "Estilo", "Desktop/otraMierda/src/negrita.jpg");
        GenerarItemsEstilo("Cursiva", "Estilo", "Desktop/otraMierda/src/cursiva.png");
        barraMenu.add(Estilo);

        GenerarItemsTamano("10");
        GenerarItemsTamano("14");
        GenerarItemsTamano("18");
        GenerarItemsTamano("22");
        barraMenu.add(Tamagno);

        GenerarItemsH("Pegar", "Desktop/otraMierda/src/pegar.png");
        GenerarItemsH("Copiar", "Desktop/otraMierda/src/copiar.png");
        GenerarItemsH("Cortar", "Desktop/otraMierda/src/cortar.png");
        barraMenu.add(Herramientas);

        panelMenu.add(barraMenu);
        add(panelMenu, BorderLayout.NORTH);

        // __________________________________________________________________

        emergente = new JPopupMenu();
        GenerarItemsEstilo("Negrita", "emergente", "Desktop/otraMierda/src/negrita.jpg");
        GenerarItemsEstilo("Cursiva", "emergente", "Desktop/otraMierda/src/cursiva.png");
        areaTexto.setComponentPopupMenu(emergente);

        // ________________________________________

        barraHerramientas.setOrientation(1);
        GenerarItemsBarra("Desktop/otraMierda/src/pegar.png", "Pegar");
        GenerarItemsBarra("Desktop/otraMierda/src/copiar.png", "Copiar");
        GenerarItemsBarra("Desktop/otraMierda/src/cortar.png", "Cortar");

        barraHerramientas.addSeparator();

        GenerarItemsBarra("Desktop/otraMierda/src/cursiva.png", "Cursiva");
        GenerarItemsBarra("Desktop/otraMierda/src/negrita.jpg", "Negrita");
        GenerarItemsBarra("Desktop/otraMierda/src/subrayado.jpg", "Subrayado");

        barraHerramientas.addSeparator();

        GenerarItemsBarra("Desktop/otraMierda/src/botonAmarillo.png", "Amarillo");
        GenerarItemsBarra("Desktop/otraMierda/src/botonAzul.png", "Azul");
        GenerarItemsBarra("Desktop/otraMierda/src/botonRojo.png", "Rojo");

        barraHerramientas.addSeparator();

        GenerarItemsBarra("Desktop/otraMierda/src/alinearIzquierda.png", "Izquierda");
        GenerarItemsBarra("Desktop/otraMierda/src/alinearCentro.png", "Centro");
        GenerarItemsBarra("Desktop/otraMierda/src/AlinearDerecha.png", "Derecha");

        this.add(barraHerramientas, BorderLayout.EAST);

    }
    // _______________________________________________________________________________________________________-

    public void GenerarItemsFuente(String rotulo) {
        JMenuItem itemMenu = new JMenuItem(rotulo);
        itemMenu.addActionListener(new StyledEditorKit.FontFamilyAction("Accion", rotulo));
        Fuente.add(itemMenu);
    }
    // __________________________________________________________________________________________________________

    public void GenerarItemsEstilo(String rotulo, String menu, String direccion) {
        JCheckBoxMenuItem itemMenu = new JCheckBoxMenuItem(rotulo, new ImageIcon(direccion));
        if (rotulo.equals("Negrita")) {
            itemMenu.addActionListener(new StyledEditorKit.BoldAction());
            itemMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));
        } else {
            itemMenu.addActionListener(new StyledEditorKit.ItalicAction());
            itemMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, InputEvent.CTRL_DOWN_MASK));
        }
        if (menu.equals("Estilo")) {
            Estilo.add(itemMenu);
        } else {
            emergente.add(itemMenu);
        }

    }

    // ________________________________________________________________________________________________

    public void GenerarItemsTamano(String rotulo) {
        JRadioButtonMenuItem itemMenu = new JRadioButtonMenuItem(rotulo);
        itemMenu.addActionListener(new StyledEditorKit.FontSizeAction("Accion", Integer.parseInt(rotulo)));
        grupo.add(itemMenu);
        Tamagno.add(itemMenu);
    }
    // _____________________________________________________________________________

    public void GenerarItemsH(String rotulo, String direccion) {
        JMenuItem itemMenu = new JMenuItem(rotulo, new ImageIcon(direccion));
        if (rotulo.equals("Copiar")) {
            itemMenu.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    areaTexto.copy();
                }
            });
        } else if (rotulo.equals("Pegar")) {
            itemMenu.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    areaTexto.paste();
                }

            });
        } else if (rotulo.equals("Cortar")) {
            itemMenu.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    areaTexto.cut();
                }
            });
        }
        Herramientas.add(itemMenu);

    }

    // __________________________________________________________________________

    public void GenerarItemsBarra(String direccion, String Accion) {

        JButton nuevo = new JButton(new AccionBarraHerramienta(Accion, new ImageIcon(direccion)));
        barraHerramientas.add(nuevo);
        AccionesBarraHerramientas(Accion, nuevo);
    }

    private class AccionBarraHerramienta extends AbstractAction {
        public AccionBarraHerramienta(String name, Icon icon) {
            putValue(SMALL_ICON, icon);
            putValue(SHORT_DESCRIPTION, name);
        }

        public void actionPerformed(ActionEvent e) {

        }
    }

    public void AccionesBarraHerramientas(String Accion, JButton boton) {
        if (Accion.equals("Copiar")) {
            boton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    areaTexto.copy();
                }
            });
        } else if (Accion.equals("Pegar")) {
            boton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    areaTexto.paste();
                }

            });
        } else if (Accion.equals("Cortar")) {
            boton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    areaTexto.cut();
                }
            });
        } else if (Accion.equals("Cursiva")) {
            boton.addActionListener(new StyledEditorKit.ItalicAction());
        } else if (Accion.equals("Negrita")) {
            boton.addActionListener(new StyledEditorKit.BoldAction());
        } else if (Accion.equals("Subrayado")) {
            boton.addActionListener(new StyledEditorKit.UnderlineAction());
        } else if (Accion.equals("Rojo")) {
            boton.addActionListener(new StyledEditorKit.ForegroundAction(Accion, Color.RED));
        } else if (Accion.equals("Azul")) {
            boton.addActionListener(new StyledEditorKit.ForegroundAction(Accion, Color.BLUE));
        } else if (Accion.equals("Amarillo")) {
            boton.addActionListener(new StyledEditorKit.ForegroundAction(Accion, Color.YELLOW));
        } else if (Accion.equals("Izquierda")) {
            boton.addActionListener(new StyledEditorKit.AlignmentAction(Accion, 0));
        } else if (Accion.equals("Centro")) {
            boton.addActionListener(new StyledEditorKit.AlignmentAction(Accion, 1));
        } else if (Accion.equals("Derecha")) {
            boton.addActionListener(new StyledEditorKit.AlignmentAction(Accion, 2));
        }
    }
    // ____________________________________________________________________________

    private JTextPane areaTexto;
    private JMenu Fuente, Estilo, Tamagno, Herramientas;
    private ButtonGroup grupo = new ButtonGroup();
    private JPopupMenu emergente;
    private JToolBar barraHerramientas = new JToolBar();
}