import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class Phone {
	public static void main(String[] args) {
		PFrame p = new PFrame("MyPhone");
		p.launchFrame();
	}
}

class PFrame extends Frame {

	Button num1,num2,num3,num4,num5,num6,num7,num8,
		   num9,num0,xing,jing,on,off;
	TextField tf;
	
	int state = 0;
	
	boolean err =false;
	
	public PFrame(String s){
		super(s);
	}
	
	public void launchFrame() {
		
		num1 = new Button("1");
		num1.addActionListener(new numMonitor());
		num2 = new Button("2");
		num2.addActionListener(new numMonitor());
		num3 = new Button("3");
		num3.addActionListener(new numMonitor());
		num4 = new Button("4");
		num4.addActionListener(new numMonitor());
		num5 = new Button("5");
		num5.addActionListener(new numMonitor());
		num6 = new Button("6");
		num6.addActionListener(new numMonitor());
		num7 = new Button("7");
		num7.addActionListener(new numMonitor());
		num8 = new Button("8");
		num8.addActionListener(new numMonitor());
		num9 = new Button("9");
		num9.addActionListener(new numMonitor());
		num0 = new Button("0");
		num0.addActionListener(new numMonitor());
		xing = new Button("*");
		xing.addActionListener(new numMonitor());
		jing = new Button("#");
		jing.addActionListener(new numMonitor());
		on = new Button("拨号");
		on.addActionListener(new eventMonitor());
		off = new Button("挂断");
		off.addActionListener(new eventMonitor());
		tf = new TextField("   欢迎使用   ");
		
		
		this.setLocation(300, 300);
		this.setSize( 150,280);
		this.setBackground(Color.gray);
		this.setResizable(false);
		this.setLayout(null);
		this.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}

		});
		
		Panel p1 = new Panel( new FlowLayout() );
		p1.setBounds( 0, 40, 150, 40);
		Panel p2 = new Panel( new GridLayout(1,2) );
		p2.setBounds( 0, 80, 150, 40);
		Panel p3 = new Panel( new GridLayout(4,3) );
		p3.setBounds( 0, 120, 150, 160);
		
		p1.add(tf);
		
		p2.add(on);
		p2.add(off);		
		
		p3.add(num1);
		p3.add(num2);
		p3.add(num3);
		p3.add(num4);
		p3.add(num5);
		p3.add(num6);
		p3.add(num7);
		p3.add(num8);
		p3.add(num9);
		p3.add(xing);
		p3.add(num0);
		p3.add(jing);
		
		this.add(p1);
		this.add(p2);
		this.add(p3);
		
		this.setVisible(true);
	}
	
	private class numMonitor implements ActionListener  {
		public void actionPerformed(ActionEvent e) {
			
			if(e.getActionCommand()=="#" || e.getActionCommand()=="*")
				err = true;
            
            switch (state) {
            case 0:
            	tf.setText( e.getActionCommand() );
            	state = 1;
            	break;
            case 1:
            	tf.setText(tf.getText() + e.getActionCommand() );
            	break;
            case 2:
            	break;
            }                     
            
		}
	}
	
	private class eventMonitor implements ActionListener  {
		
		private Random r = new Random();
		
		public void actionPerformed(ActionEvent e) {
            if( state == 1 & e.getActionCommand() == "拨号"){
            	
            	if(err == true){
            		tf.setText("   号码错误   ");
            		state = 2;
            	}
            	else
            		switch (r.nextInt(4)) {
                    case 0:
                    	tf.setText("   开始通话   ");
                    	state = 2;
                    	break;
                    case 1:
                    	tf.setText(" 对方正在通话 ");
                    	state = 2;
                    	break;
                    case 2:
                    	tf.setText("   无人接听   ");
                    	state = 2;
                    	break;
                    case 3:
                    	tf.setText("   号码错误   ");
                    	state = 2;
                    	break;
            		}           	

		    }else if( state==2 & e.getActionCommand() == "挂断"){
		    	tf.setText("    已挂机    ");
		    	state = 0;
		    	err = false;
		    }
		}
	}
	
}