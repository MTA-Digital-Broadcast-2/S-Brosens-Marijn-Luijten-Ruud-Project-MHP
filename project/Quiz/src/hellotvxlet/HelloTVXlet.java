package hellotvxlet;

import javax.tv.xlet.*;
import org.dvb.ui.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;

import org.havi.ui.*;
import org.havi.ui.event.*;
import org.dvb.event.*;


public class HelloTVXlet implements Xlet, UserEventListener, HActionListener {
    
    private XletContext actueleXletContext;
    private HScene scene;
    HelloTVXlet xlet;
    //debuggen activeren of niet?
    private boolean debug = true;    
    private HStaticText title, vraag, antwoordLabel;    
    private HTextButton knop1, knop2, knop3, knop4, knopHulp;    
    private String isJuist = "";    
    
    String[][] questions = { 
            {"vraag1", "antwoord11", "antwoord12","antwoord13","antwoord14","1"},
            {"vraag2", "antwoord21", "antwoord22","antwoord23","antwoord24","2"},
            {"vraag3", "antwoord31", "antwoord32","antwoord32","antwoord34","3"},
            {"vraag4", "antwoord41", "antwoord42","antwoord43","antwoord44","4"}
        };   
    
    private int randomvraagIndex = 0 + (int)(Math.random()*questions.length); 

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
        
        title = new HStaticText("Wie wordt multimiljonair?");        
        title.setLocation(0,0); //in pixels
        title.setSize(740,100);
        title.setBackground(new DVBColor(0,0,0, 100));
        title.setBackgroundMode(HVisible.BACKGROUND_FILL);
        
        
        int i=0;
        while( i< questions.length)
        {            
            vraag = new HStaticText(questions[randomvraagIndex][0]);   
            
            knop1 = new HTextButton(questions[randomvraagIndex][1]); 
            knop2 = new HTextButton(questions[randomvraagIndex][2]);  
            knop3 = new HTextButton(questions[randomvraagIndex][3]);  
            knop4 = new HTextButton(questions[randomvraagIndex][4]);         
    
            i++;
        } 
        
        
        //vraag = new HStaticText("Hoe maak je rood in RGB?");        
        vraag.setLocation(0,100);
        vraag.setSize(740,60);
        vraag.setBackground(new DVBColor(0,0,0, 150));
        vraag.setBackgroundMode(HVisible.BACKGROUND_FILL);
        
        antwoordLabel = new HStaticText(isJuist);        
        antwoordLabel.setLocation(0,500);
        antwoordLabel.setSize(740,80);
        antwoordLabel.setBackground(new DVBColor(0,0,0, 150));
        antwoordLabel.setBackgroundMode(HVisible.BACKGROUND_FILL);
        
        scene.add(title);
        scene.add(vraag);
                
        knop1.setLocation(220,180); //in pixels
        knop1.setSize(300,50);
        knop1.setBackground(new DVBColor(100,100,100, 179));
        knop1.setBackgroundMode(HVisible.BACKGROUND_FILL);
              
        knop2.setLocation(220,240); //in pixels
        knop2.setSize(300,50);
        knop2.setBackground(new DVBColor(100,100,100, 179));
        knop2.setBackgroundMode(HVisible.BACKGROUND_FILL);
             
        knop3.setLocation(220,300); //in pixels
        knop3.setSize(300,50);
        knop3.setBackground(new DVBColor(100,100,100, 179));
        knop3.setBackgroundMode(HVisible.BACKGROUND_FILL);
             
        knop4.setLocation(220,360); //in pixels
        knop4.setSize(300,50);
        knop4.setBackground(new DVBColor(100,100,100, 179));
        knop4.setBackgroundMode(HVisible.BACKGROUND_FILL);
        

        
        //Interactiviteit implementeren
        knop1.setFocusTraversal(knop4, knop2, null, null);//op, neer, links, rechts
        knop2.setFocusTraversal(knop1, knop3, null, null);//op, neer, links, rechts
        knop3.setFocusTraversal(knop2, knop4, null, null);//op, neer, links, rechts
        knop4.setFocusTraversal(knop3, knop1, null, null);//op, neer, links, rechts
//        knopHulp.setFocusTraversal(knop2, null, null, null);
        
        knop1.setActionCommand("1");
        knop1.addHActionListener(this);
        
        knop2.setActionCommand("2");
        knop2.addHActionListener(this);
        
        knop3.setActionCommand("3");
        knop3.addHActionListener(this);
        
        knop4.setActionCommand("4");
        knop4.addHActionListener(this);
//        knopHulp.setActionCommand("Help");
//        knopHulp.addHActionListener(this);

        scene.add(knop1);
        scene.add(knop2);
        scene.add(knop3);
        scene.add(knop4);
//        scene.add(knopHulp);
        scene.add(antwoordLabel);
        
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
        
        int i=0;
        while( i< questions.length)
        {            
            vraag = new HStaticText(questions[randomvraagIndex][0]);   
            
            knop1 = new HTextButton(questions[randomvraagIndex][1]); 
            knop2 = new HTextButton(questions[randomvraagIndex][2]);  
            knop3 = new HTextButton(questions[randomvraagIndex][3]);  
            knop4 = new HTextButton(questions[randomvraagIndex][4]);         
    
            i++;
        }  
        
        if(command == questions[randomvraagIndex][5]){
            
            isJuist = "Juist";
            randomvraagIndex = 0 + (int)(Math.random()*questions.length); 
            this.xlet = xlet;
            //knop1.setBackground(new DVBColor(255,0,0, 179));
            //knop1.setBackgroundMode(HVisible.BACKGROUND_FILL);
        }else{
            isJuist = "fout";
            //knop2.setBackground(new DVBColor(255,0,0, 179));
            //knop2.setBackgroundMode(HVisible.BACKGROUND_FILL); 
        }
        /*else if(command == "Help"){
            getHelp();
        }*/
        
        this.antwoordLabel.setTextContent(isJuist, this.antwoordLabel.NORMAL_STATE);
    }
    
/*    private void getHelp(){
        knop1.setVisible(false);
        knop4.setVisible(false);
        knop2.setFocusTraversal(knop3, knopHulp, null, knop3);
        knop3.setFocusTraversal(null, knop2, knop2, null);
    }*/
}
