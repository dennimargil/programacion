import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;
 
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
 
import java.io.IOException;
 
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
 
public class Calc extends JFrame implements ActionListener,MouseListener    
{
 
  private JButton      jb_uno,jb_dos,jb_tres,jb_cuatro,jb_cinco,jb_seis,jb_siete,jb_ocho,jb_nueve,jb_cero;
  private JButton      jb_sumar,jb_restar,jb_dividir,jb_multiplicar;
  private JButton      jb_sin,jb_cos,jb_tan,jb_asin,jb_acos,jb_atan,jb_pie,jb_E;
  private JButton      jb_puntodecimal,jb_iguala,jb_fact,jb_on,jb_cambiarsigno,jb_reciporcal;
  private JButton      jb_todeg,jb_torad,jb_redondear,jb_CA,jb_CE,jb_retroceso;
  private JRadioButton jrb_deg,jrb_rad;
  private ButtonGroup  jrb_group;
  private JTextField   jtf_mostrar;
  private JLabel       jl_url;
 
  private double       tempdisplayNum;
  boolean              MAS_PRESIONADO,MENOS_PRESIONADO,MUL_PRESIONADO,DIV_PRESIONADO,IGUAL_PRESIONADO,ON_PRESIONADO;
 
 
  public Calc()
  {
 
      super("Calculadora Cietifica ( JVM Version " + System.getProperty("java.version") + " )");
 
 
      //setIconImage(Toolkit.getDefaultToolkit().getImage(Calc.class.getResource("calc.gif")));
 
 
	  setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
 
 
      addWindowListener(new WindowAdapter()
      {
         public void windowClosing(WindowEvent e)
            {
               Calc.this.dispose();
               System.runFinalization();
               System.gc();
               System.exit(0);
            }
      });
 
      tempdisplayNum = 0;
      limpiar();
      IGUAL_PRESIONADO = false;
 
 
      JPanel jp_main = new JPanel();
      jp_main.setBackground(Color.PINK);
      jp_main.setLayout(new BorderLayout());
      jp_main.setBorder(new EmptyBorder(new Insets(3,5,5,5)));
      JPanel jp_top = new JPanel();
      jp_top.setBackground(Color.GREEN);
      jp_top.setLayout(new BorderLayout());
      JPanel jp_top_down = new JPanel();
      jp_top_down.setLayout(new BorderLayout());
      jp_top_down.setBackground(Color.GREEN);
      
      JPanel jp_top_west = new JPanel();
      jp_top_west.setBackground(Color.GREEN);
      jb_retroceso = new JButton("Retroceso");
      jp_top_west.setLayout(new FlowLayout(FlowLayout.LEFT,0,5));
      jp_top_west.add(jb_retroceso);
      JPanel jp_top_east = new JPanel();
      jp_top_east.setBackground(Color.GREEN);
      jp_top_east.setLayout(new FlowLayout(FlowLayout.RIGHT));
      jtf_mostrar = new JTextField();
      jtf_mostrar.setEditable(false);
      jtf_mostrar.setHorizontalAlignment( JTextField.RIGHT );
      
 
      jtf_mostrar.setRequestFocusEnabled(false);
      jtf_mostrar.setBackground(Color.white);
      jrb_deg = new JRadioButton("Grados");
      jrb_rad = new JRadioButton("Radian");
      jrb_deg.setSelected(true);
      jrb_group = new ButtonGroup();
      jrb_group.add(jrb_deg);
      jrb_group.add(jrb_rad);
      jp_top_east.add(jrb_deg);
      jp_top_east.add(jrb_rad);
      jp_top_down.add(jp_top_east,BorderLayout.EAST);
      jp_top_down.add(jp_top_west,BorderLayout.WEST);
      jp_top.setLayout(new BorderLayout());
 
      JPanel jp_top_top = new JPanel();
      jp_top_top.setLayout(new BorderLayout());
      jl_url = new JLabel("Unerg");
      //jl_url.setForeground(Color.blue);
      //jl_url.addMouseListener(Calc.this);
      jp_top_top.add(new JLabel("Calculadora Cientifica"),BorderLayout.WEST);
      jp_top_top.add(jl_url,BorderLayout.EAST);
      jp_top.add(jp_top_top,BorderLayout.NORTH);
      jp_top.add(jtf_mostrar,BorderLayout.CENTER);
      jp_top.add(jp_top_down,BorderLayout.SOUTH);
      JPanel jp_center = new JPanel();
      jp_center.setLayout(new GridLayout(5,7,5,5));
      jb_uno = new JButton("1");
      jb_dos = new JButton("2");
      jb_tres = new JButton("3");
      jb_cuatro = new JButton("4");
      jb_cinco = new JButton("5");
      jb_seis  = new JButton("6");
      jb_siete = new JButton("7");
      jb_ocho = new JButton("8");
      jb_nueve = new JButton("9");
      jb_cero = new JButton("0");
      jb_sumar = new JButton("+");
      jb_restar = new JButton("-");
      jb_dividir = new JButton("/");
      jb_multiplicar = new JButton("*");
      jb_sin = new JButton("Sin");
      jb_cos = new JButton("Cos");
      jb_tan = new JButton("Tan");
      jb_asin = new JButton("aSin");
      jb_acos = new JButton("aCos");
      jb_atan = new JButton("aTan");
      jb_pie = new JButton("PI");
      jb_E = new JButton("E");
      jb_puntodecimal = new JButton(".");
      jb_iguala = new JButton("=");
      jb_fact = new JButton("x!");
      jb_on = new JButton("x^n");
      jb_cambiarsigno = new JButton("+/-");
      jb_reciporcal = new JButton("1/x");
      jb_todeg = new JButton("Deg");
      jb_torad = new JButton("Rad");
      jb_redondear = new JButton("Red");
      jb_CA = new JButton("CA");
      jb_CE = new JButton("CE");
 
       jb_uno.addActionListener(Calc.this);
       jb_dos.addActionListener(Calc.this);
       jb_tres.addActionListener(Calc.this);
       jb_cuatro.addActionListener(Calc.this);
       jb_cinco.addActionListener(Calc.this);
       jb_seis.addActionListener(Calc.this);
       jb_siete.addActionListener(Calc.this);
       jb_ocho.addActionListener(Calc.this);
       jb_nueve.addActionListener(Calc.this);
       jb_cero.addActionListener(Calc.this);
       jb_sumar.addActionListener(Calc.this);
       jb_restar.addActionListener(Calc.this);
       jb_dividir.addActionListener(Calc.this);
       jb_multiplicar.addActionListener(Calc.this);
       jb_sin.addActionListener(Calc.this);
       jb_cos.addActionListener(Calc.this);
       jb_tan.addActionListener(Calc.this);
       jb_asin.addActionListener(Calc.this);
       jb_acos.addActionListener(Calc.this);
       jb_atan.addActionListener(Calc.this);
       jb_pie.addActionListener(Calc.this);
       jb_E.addActionListener(Calc.this);
       jb_puntodecimal.addActionListener(Calc.this);
       jb_iguala.addActionListener(Calc.this);
       jb_fact.addActionListener(Calc.this);
       jb_on.addActionListener(Calc.this);
       jb_cambiarsigno.addActionListener(Calc.this);
       jb_reciporcal.addActionListener(Calc.this);
       jb_todeg.addActionListener(Calc.this);
       jb_torad.addActionListener(Calc.this);
       jb_redondear.addActionListener(Calc.this);
       jb_CA.addActionListener(Calc.this);
       jb_CE.addActionListener(Calc.this);
       jb_retroceso.addActionListener(Calc.this);
 
 
       jp_center.add(jb_uno);
       jp_center.add(jb_dos);
       jp_center.add(jb_tres);
       jp_center.add(jb_multiplicar);
       jp_center.add(jb_reciporcal);
       jp_center.add(jb_sin);
       jp_center.add(jb_asin);
       jp_center.add(jb_cuatro);
       jp_center.add(jb_cinco);
       jp_center.add(jb_seis);
       jp_center.add(jb_dividir);
       jp_center.add(jb_on);
       jp_center.add(jb_cos);
       jp_center.add(jb_acos);
       jp_center.add(jb_siete);
       jp_center.add(jb_ocho);
       jp_center.add(jb_nueve);
       jp_center.add(jb_sumar);
       jp_center.add(jb_cambiarsigno);
       jp_center.add(jb_tan);
       jp_center.add(jb_atan);
       jp_center.add(jb_cero);
       jp_center.add(jb_puntodecimal);
       jp_center.add(jb_iguala);
       jp_center.add(jb_restar);
       jp_center.add(jb_fact);
       jp_center.add(jb_pie);
       jp_center.add(jb_E);
       jp_center.add(jb_CA);
       jp_center.add(jb_CE);
       jp_center.add(jb_redondear);
       jp_center.add(jb_todeg);
       jp_center.add(jb_torad);
 
       Container cp = this.getContentPane();
       jp_main.add(jp_top,BorderLayout.NORTH);
       jp_main.add(jp_center,BorderLayout.CENTER);
       cp.add(jp_main,BorderLayout.CENTER);
       setSize(520,250);
       pack();
       Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
       Dimension frameSize = this.getPreferredSize();
       setLocation(screenSize.width/2 - (frameSize.width/2),screenSize.height/2 - (frameSize.height/2));
       setVisible(true);
 
       this.requestFocus();
   }
 
 
  public void limpiar()
  {
     MAS_PRESIONADO  = false;
     MENOS_PRESIONADO = false;
     MUL_PRESIONADO   = false;
     DIV_PRESIONADO   = false;
     ON_PRESIONADO = false;
  }
 
 
  public String factorial(double num)
  {
   int theNum = (int)num;
 
     if (theNum < 1)
     {
       JOptionPane.showMessageDialog(Calc.this,"No se puede encontrar el factorial de n�meros menores que 1.","Factorial Error",JOptionPane.WARNING_MESSAGE);
         return ("0");
      }
      else
      {
         for (int i=(theNum -1); i > 1; --i)
         theNum *= i;
         return Integer.toString(theNum);
       }
    }
 
   private void doShowURL(String urlSpec){
       String strx="";
        if (System.getProperty("os.name").startsWith("Windows")){
            strx = "rundll32.exe url.dll,FileProtocolHandler " + urlSpec;
        }else{
            strx = "netscape " + urlSpec;
        }
        try{
            Runtime.getRuntime().exec(strx);
        }
        catch (IOException ex) {}
    }
 
 
   public void actionPerformed(ActionEvent e){
     String s = e.getActionCommand();
     String temptext  = jtf_mostrar.getText();
     boolean puntoDecimalEncontrado = false;
     double displayNumber = 0;
     try{
       displayNumber = Double.valueOf(jtf_mostrar.getText()).doubleValue();
     }catch(NumberFormatException nfe) {}
 
 
    if(IGUAL_PRESIONADO){
      jtf_mostrar.setText("");
      IGUAL_PRESIONADO = false;
    }
 
 
     if(s.equals("1"))
      jtf_mostrar.setText(jtf_mostrar.getText()+"1");
     if(s.equals("2"))
      jtf_mostrar.setText(jtf_mostrar.getText()+"2");
     if(s.equals("3"))
      jtf_mostrar.setText(jtf_mostrar.getText()+"3");
     if(s.equals("4"))
      jtf_mostrar.setText(jtf_mostrar.getText()+"4");
     if(s.equals("5"))
      jtf_mostrar.setText(jtf_mostrar.getText()+"5");
     if(s.equals("6"))
      jtf_mostrar.setText(jtf_mostrar.getText()+"6");
     if(s.equals("7"))
      jtf_mostrar.setText(jtf_mostrar.getText()+"7");
     if(s.equals("8"))
      jtf_mostrar.setText(jtf_mostrar.getText()+"8");
     if(s.equals("9"))
      jtf_mostrar.setText(jtf_mostrar.getText()+"9");
 
     if(s.equals("0") && !temptext.equals(""))
      jtf_mostrar.setText(jtf_mostrar.getText()+"0");
 
     if(s.equals("E"))
      jtf_mostrar.setText(Double.toString(Math.E));
     if(s.equals("PI"))
      jtf_mostrar.setText(Double.toString(Math.PI));
     if(s.equals("Deg"))
      jtf_mostrar.setText(Double.toString(Math.toDegrees(displayNumber)));
     if(s.equals("Rad"))
      jtf_mostrar.setText(Double.toString(Math.toRadians(displayNumber)));
 
     if(s.equals("CE"))
      jtf_mostrar.setText("");
     if(s.equals("CA")){
       limpiar();
       jtf_mostrar.setText("");
     }
    if(s.equals(".")){
        for (int i =0; i < temptext.length(); ++i){
          if(temptext.charAt(i) == '.'){
            puntoDecimalEncontrado = true;
            continue;
          }
        }
         if(!puntoDecimalEncontrado && temptext.length()==0)
          jtf_mostrar.setText("0.");
         if(!puntoDecimalEncontrado && temptext.length()!=0)
          jtf_mostrar.setText(jtf_mostrar.getText()+".");
     }
 
   if(s.equals("Sin")){
    if(jrb_deg.isSelected())
      jtf_mostrar.setText(Double.toString(Math.sin((displayNumber*Math.PI)/180)));
    else{
      jtf_mostrar.setText(Double.toString(Math.sin(displayNumber)));
     }
   }
 
  if(s.equals("Cos")){
    if(jrb_deg.isSelected())
      jtf_mostrar.setText(Double.toString(Math.cos((displayNumber*Math.PI)/180)));
    else{
      jtf_mostrar.setText(Double.toString(Math.cos(displayNumber)));
     }
   }
 
if(s.equals("Tan")){
    if(jrb_deg.isSelected())
      jtf_mostrar.setText(Double.toString(Math.tan((displayNumber*Math.PI)/180)));
    else{
      jtf_mostrar.setText(Double.toString(Math.tan(displayNumber)));
     }
   }
 
   if(s.equals("aSin")){
    if(jrb_deg.isSelected())
      jtf_mostrar.setText(Double.toString(Math.asin((displayNumber*Math.PI)/180)));
    else{
      jtf_mostrar.setText(Double.toString(Math.asin(displayNumber)));
     }
   }
 
if(s.equals("aCos")){
    if(jrb_deg.isSelected())
      jtf_mostrar.setText(Double.toString(Math.acos((displayNumber*Math.PI)/180)));
    else{
      jtf_mostrar.setText(Double.toString(Math.acos(displayNumber)));
     }
   }
 
  if(s.equals("aTan")){
    if(jrb_deg.isSelected())
      jtf_mostrar.setText(Double.toString(Math.atan((displayNumber*Math.PI)/180)));
    else{
      jtf_mostrar.setText(Double.toString(Math.atan(displayNumber)));
     }
   }
 
 
  if(s.equals("*")){
   limpiar();
   MUL_PRESIONADO = true;
   try{
    tempdisplayNum = displayNumber;
   }catch(NumberFormatException mule)
    { tempdisplayNum = 0; }
       jtf_mostrar.setText("");
  }
 
  if(s.equals("+")){
   limpiar();
   MAS_PRESIONADO = true;
   try{
   		tempdisplayNum = displayNumber;
   }catch(NumberFormatException pluse)
    { tempdisplayNum = 0; }
   	jtf_mostrar.setText("");
  }
 
  if(s.equals("-")){
   limpiar();
   MENOS_PRESIONADO = true;
   try
   {
   tempdisplayNum = displayNumber;
   }
   catch(NumberFormatException sube)
    { tempdisplayNum = 0; }
   jtf_mostrar.setText("");
  }
 
  if(s.equals("/"))
  {
   limpiar();
   DIV_PRESIONADO = true;
   try
   {
   tempdisplayNum = displayNumber;
   }
   catch(NumberFormatException dive)
    { tempdisplayNum = 0; }
   jtf_mostrar.setText("");
  }
 
 
  if(s.equals("x^n"))
  {
   ON_PRESIONADO = true;
   try
   {
   tempdisplayNum = displayNumber;
   }
   catch(NumberFormatException dive)
    { tempdisplayNum = 0; }
   jtf_mostrar.setText("");
  }
 
 
  if(s.equals("=")){
    if(MUL_PRESIONADO)
    jtf_mostrar.setText(Double.toString(tempdisplayNum*displayNumber));
    if(MAS_PRESIONADO)
    jtf_mostrar.setText(Double.toString(tempdisplayNum+displayNumber));
    if(MENOS_PRESIONADO)
    jtf_mostrar.setText(Double.toString(tempdisplayNum-displayNumber));
    if(DIV_PRESIONADO)
    jtf_mostrar.setText(Double.toString(tempdisplayNum/displayNumber));
    if(ON_PRESIONADO)
    jtf_mostrar.setText(Double.toString(Math.pow(tempdisplayNum,displayNumber)));
    limpiar();
    IGUAL_PRESIONADO = true;
   }
 
 
  if(s.equals("1/x"))
  jtf_mostrar.setText(Double.toString(1/displayNumber));
  if(s.equals("+/-") && displayNumber!=0)
  jtf_mostrar.setText(Double.toString(-displayNumber));
  if(s.equals("x!"))
  jtf_mostrar.setText(factorial(displayNumber));
  if(s.equals("Red"))
  jtf_mostrar.setText(Double.toString(Math.round(displayNumber)));
 
 
  if(s.equals("Retroceso"))
  {
  String temptextt = jtf_mostrar.getText();
  if(!temptextt.equals(""))
  jtf_mostrar.setText(temptextt.substring(0, temptextt.length() - 1));
  }
 
 }
 
 
  public void mouseClicked(MouseEvent me)
  {
	doShowURL("http://www.jocusoft.com");
  }
 
 
  public void mouseEntered(MouseEvent me)
  {
	  jl_url.setForeground(Color.red);
	  setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
  }
 
 
  public void mouseExited(MouseEvent me)
  {
	  jl_url.setForeground(Color.blue);
	  setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
  }
 
 
  public void mouseReleased(MouseEvent me) {}
  public void mousePressed(MouseEvent me) {}
 
 
  public static void main(String args[])
   {
 
	 try
	 {
	   UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
   	 }
   	 catch(Exception e)
   	 {}
     new Calc();
   }
}