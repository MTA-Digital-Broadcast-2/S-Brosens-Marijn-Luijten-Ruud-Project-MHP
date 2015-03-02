
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
    private HTextButton knop1, knop2, knop3, knop4, knop5, knop6, knop7, knop8;
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
        
        knop5 = new HTextButton("Volkswagen");
        knop5.setLocation(220,180); //in pixels
        knop5.setSize(300,50);
        knop5.setBackground(new DVBColor(100,100,100, 179));
        knop5.setBackgroundMode(HVisible.BACKGROUND_FILL);
        
        knop6 = new HTextButton("Audi");
        knop6.setLocation(220,240); //in pixels
        knop6.setSize(300,50);
        knop6.setBackground(new DVBColor(100,100,100, 179));
        knop6.setBackgroundMode(HVisible.BACKGROUND_FILL);
        
        knop7 = new HTextButton("Renault");
        knop7.setLocation(220,300); //in pixels
        knop7.setSize(300,50);
        knop7.setBackground(new DVBColor(100,100,100, 179));
        knop7.setBackgroundMode(HVisible.BACKGROUND_FILL);
        
        knop8 = new HTextButton("CitroÎn");
        knop8.setLocation(220,360); //in pixels
        knop8.setSize(300,50);
        knop8.setBackground(new DVBColor(100,100,100, 179));
        knop8.setBackgroundMode(HVisible.BACKGROUND_FILL);
        
        //Interactiviteit implementeren
        knop5.setFocusTraversal(knop8, knop6, null, null);//op, neer, links, rechts
        knop6.setFocusTraversal(knop5, knop7, null, null);//op, neer, links, rechts
        knop7.setFocusTraversal(knop6, knop8, null, null);//op, neer, links, rechts
        knop8.setFocusTraversal(knop7, knop5, null, null);//op, neer, links, rechts
        
        knop5.setActionCommand("5");
        knop5.addHActionListener(this);
        
        knop6.setActionCommand("6");
        knop6.addHActionListener(this);
        
        knop7.setActionCommand("7");
        knop7.addHActionListener(this);
        
        knop8.setActionCommand("8");
        knop8.addHActionListener(this);
        
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
            
        }else if(command == "5"){
            
            isTrue = "False";
            knop5.setBackground(new DVBColor(255,0,0, 179));
            knop5.setBackgroundMode(HVisible.BACKGROUND_FILL);
            
        }else if(command == "6"){
            
            isTrue = "False";
            knop6.setBackground(new DVBColor(255,0,0, 179));
            knop6.setBackgroundMode(HVisible.BACKGROUND_FILL);
            
        }else if(command == "7"){
            
            isTrue = "False";
            knop7.setBackground(new DVBColor(255,0,0, 179));
            knop7.setBackgroundMode(HVisible.BACKGROUND_FILL);
            
        }else if(command == "8"){
            
            isTrue = "False";
            knop8.setBackground(new DVBColor(255,0,0, 179));
            knop8.setBackgroundMode(HVisible.BACKGROUND_FILL);
            
        }else{
            
            isTrue = "False";
            
        }
        
        this.answerLable.setTextContent(isTrue, this.answerLable.NORMAL_STATE);
    }
}