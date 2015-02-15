package hellotvxlet;

import javax.tv.xlet.*;
import org.dvb.ui.*;
import java.awt.*;
import java.awt.event.*;

import org.havi.ui.*;
import org.havi.ui.event.*;
import org.dvb.event.*;


public class HelloTVXlet implements Xlet, UserEventListener, HActionListener {
    
    private XletContext actueleXletContext;
    private HScene scene;
    //debuggen activeren of niet?
    private boolean debug = true;    
    private HStaticText title, question, answerLable;    
    private HTextButton knop1, knop2, knop3, knop4;    
    private String isTrue = "";

    public HelloTVXlet() {
        
    }

    public void initXlet(XletContext context) throws XletStateChangeException {
      
        if(debug) System.out.println("Xlet Initialiseren");
        
        this.actueleXletContext = context;
        //template maken
        HSceneTemplate sceneTemplate = new HSceneTemplate();
        
        sceneTemplate.setPreference(HSceneTemplate.SCENE_SCREEN_DIMENSION,
                new HScreenDimension(1.0f, 1.0f),   //Procentueel
                HSceneTemplate.REQUIRED);
        
        sceneTemplate.setPreference(HSceneTemplate.SCENE_SCREEN_LOCATION,
                new HScreenPoint(0.0f, 0.0f),
                HSceneTemplate.REQUIRED);
        
        scene = HSceneFactory.getInstance().getBestScene(sceneTemplate);
        
        title = new HStaticText("Logo quiz");        
        title.setLocation(0,0);
        title.setSize(740,100);
        title.setBackground(new DVBColor(0,0,0, 100));
        title.setBackgroundMode(HVisible.BACKGROUND_FILL);
        
        question = new HStaticText("Van welk merk is het logo?");        
        question.setLocation(0,100);
        question.setSize(740,60);
        question.setBackground(new DVBColor(0,0,0, 150));
        question.setBackgroundMode(HVisible.BACKGROUND_FILL);
        
        answerLable = new HStaticText(isTrue);        
        answerLable.setLocation(0,500);
        answerLable.setSize(740,80);
        answerLable.setBackground(new DVBColor(0,0,0, 150));
        answerLable.setBackgroundMode(HVisible.BACKGROUND_FILL);
        
        scene.add(title);
        scene.add(question);
        
        knop1 = new HTextButton("BMW");        
        knop1.setLocation(220,180); //in pixels
        knop1.setSize(300,50);
        knop1.setBackground(new DVBColor(100,100,100, 179));
        knop1.setBackgroundMode(HVisible.BACKGROUND_FILL);
        
        knop2 = new HTextButton("Mercedes");        
        knop2.setLocation(220,240); //in pixels
        knop2.setSize(300,50);
        knop2.setBackground(new DVBColor(100,100,100, 179));
        knop2.setBackgroundMode(HVisible.BACKGROUND_FILL);
        
        knop3 = new HTextButton("Peugeot");        
        knop3.setLocation(220,300); //in pixels
        knop3.setSize(300,50);
        knop3.setBackground(new DVBColor(100,100,100, 179));
        knop3.setBackgroundMode(HVisible.BACKGROUND_FILL);
        
        knop4 = new HTextButton("Volvo");       
        knop4.setLocation(220,360); //in pixels
        knop4.setSize(300,50);
        knop4.setBackground(new DVBColor(100,100,100, 179));
        knop4.setBackgroundMode(HVisible.BACKGROUND_FILL);
        
        //Interactiviteit implementeren
        knop1.setFocusTraversal(knop4, knop2, null, null);//op, neer, links, rechts
        knop2.setFocusTraversal(knop1, knop3, null, null);//op, neer, links, rechts
        knop3.setFocusTraversal(knop2, knop4, null, null);//op, neer, links, rechts
        knop4.setFocusTraversal(knop3, knop1, null, null);//op, neer, links, rechts
        
        knop1.setActionCommand("1");
        knop1.addHActionListener(this);
        
        knop2.setActionCommand("2");
        knop2.addHActionListener(this);
        
        knop3.setActionCommand("3");
        knop3.addHActionListener(this);
        
        knop4.setActionCommand("4");
        knop4.addHActionListener(this);

        scene.add(knop1);
        scene.add(knop2);
        scene.add(knop3);
        scene.add(knop4);
        scene.add(answerLable);
        
        knop1.requestFocus();
     
    }

    public void startXlet() {
    
        scene.validate();
        scene.setVisible(true);
        
    }

    public void pauseXlet() {
     
    }

    public void destroyXlet(boolean unconditional) {
        
    }

    public void userEventReceived(UserEvent e) {
        
    }

    public void actionPerformed(ActionEvent e) throws UnsupportedOperationException {
        
        handleClick(e.getActionCommand());
        
    }
    
    private void handleClick(String command){
        
        if(command =="1"){
            
            isTrue = "False";
            knop1.setBackground(new DVBColor(255,0,0, 179));
            knop1.setBackgroundMode(HVisible.BACKGROUND_FILL);
            
        }else if(command == "2"){
            
            isTrue = "False";
            knop2.setBackground(new DVBColor(255,0,0, 179));
            knop2.setBackgroundMode(HVisible.BACKGROUND_FILL);
            
        }else if(command == "3"){
            
            isTrue = "Right";
            knop3.setBackground(new DVBColor(0,255,0, 179));
            knop3.setBackgroundMode(HVisible.BACKGROUND_FILL);
            
        }else if(command == "4"){
            
            isTrue = "False";
            knop4.setBackground(new DVBColor(255,0,0, 179));
            knop4.setBackgroundMode(HVisible.BACKGROUND_FILL);
            
        }else{
            
            isTrue = "False"; 
            
        }
        
        this.answerLable.setTextContent(isTrue, this.answerLable.NORMAL_STATE);
    }
}
