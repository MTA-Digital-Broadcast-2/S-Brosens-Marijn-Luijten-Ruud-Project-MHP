package hellotvxlet;

import javax.tv.xlet.*;
import org.dvb.ui.*;

import java.util.Arrays;

import java.awt.*;
import java.awt.event.*;
import java.awt.event.KeyEvent;

import org.havi.ui.*;
import org.havi.ui.event.*;
import org.dvb.event.*;

public class HelloTVXlet implements Xlet, UserEventListener, HActionListener 
{
    HelloTVXlet xlet;
    
    private XletContext actueleXletContext;
    private HScene scene;
    private boolean debug = true;    
    private HStaticText title, vraag, antwoordLabel, scoreLabel;    
    private HTextButton startknop, stopknop, knop1, knop2, knop3, knop4;    
    private String isJuist = "";    
    private int score = 0;
    private DrawImage background;
    
    private String[][] questions = 
    { 
        {"1.Welk zoogdier maakt de prachtigste bouwwerken in rivieren?", "A.Otter", "B.Nerts","C.Bever","D.Marter","3"},
        {"2.Welke vogel is geen dagroofvogel?", "A.Buizerd", "B.Bosuil","C.Smient","D.Torenvalk","3"},
        {"3.Welke inheemse plant eet insecten?", "A.Veenbes", "B.Venusvliegenval","C.Paardenstaart","D.Zonnedauw","4"},
        {"4.Wat is een vliegend hert?", "A.Een soort vleermuis", "B.Een soort buizerd","C.Een soort kever","D.Een soort ree","3"},      
        {"5.Welk dier is een reptiel?", "A.Hazelworm", "B.Alpenwatersalamander","C.Bladhaantje","D.Regenworm","1"},
        {"6.Welke vogel is geen zangvogel?", "A.Nachtegaal", "B.Kraai","C.Zomertaling","D.Koolmees","3"},        
        {"7.Wat is geen bestaande plant?", "A.Oranje Drieblad", "B.Berenklauw","C.Klaverzuring","D.Moeraspirea","1"},
        {"8.Welke bloemensoort bestaat niet?", "A.Wespbloemigen", "B.Lipbloemigen","C.Vlinderbloemigen","D.Schermbloemigen","1"},
        {"9.Wat is geen vlinder?", "A.Witje", "B.Blauwtje","C.Groentje","D.Roodje","4"},
        {"10.Welk dier graaft geen holen?", "A.Konijn", "B.Haas","C.Muskusrat","D.Hamster","2"},
        {"11.Wat is een kikkerbeet?", "A.Netje om kikkers mee te vangen", "B.Dier","C.Plant","D.Insect","3"},
        {"13.Welke muis bestaat niet?", "A.Woelmuis", "B.Hazelnootmuis","C.Stekelmuis","D.Spitsmuis","2"},
        {"14.Welke vogel kan ondersteboven langs een stam naar beneden kruipen?", "A.Boompieper", "B.Boomkruiper","C.Boomklever","D.Boomhanger","3"},
        {"15.Welk dier bestaat niet?", "A.Heldenbok", "B.Vliegend hert","C.Franjestaart","D.Ankerhoorn","4"},
        {"16.Welke plant bestaat niet?", "A.Bitterzoet", "B.Grote wederik","C.Plaatjesaar","D.Speenkruid","3"}
    };   
        
    private int randomvraagIndex = 0 + ( int )( Math.random()*questions.length ); 
    
    public HelloTVXlet() {}

    public void initXlet(XletContext context) throws XletStateChangeException 
    {        
        this.actueleXletContext = context;        
        HSceneTemplate sceneTemplate = new HSceneTemplate();        
        sceneTemplate.setPreference( HSceneTemplate.SCENE_SCREEN_DIMENSION, new HScreenDimension( 1.0f, 1.0f ), HSceneTemplate.REQUIRED );        
        sceneTemplate.setPreference( HSceneTemplate.SCENE_SCREEN_LOCATION, new HScreenPoint( 0.0f, 0.0f ), HSceneTemplate.REQUIRED );
        
        scene = HSceneFactory.getInstance().getBestScene( sceneTemplate );
  
        int i=0;
        while( i< questions.length)
        {            
            vraag = new HStaticText( questions[randomvraagIndex][0] );               
            knop1 = new HTextButton( questions[randomvraagIndex][1] ); 
            knop2 = new HTextButton( questions[randomvraagIndex][2] );  
            knop3 = new HTextButton( questions[randomvraagIndex][3] );  
            knop4 = new HTextButton( questions[randomvraagIndex][4] );         
    
            i++;
        } 
        
        title = new HStaticText( "Natuurvragen." );        
        title.setLocation( 0,0 );
        title.setSize( 740,100 );
        title.setBackground( new DVBColor( 0,0,0, 150 ) );
        title.setBackgroundMode( HVisible.BACKGROUND_FILL ); 
               
        vraag.setLocation( 0,100 );
        vraag.setSize( 740,60 );
        vraag.setBackground( new DVBColor( 0,0,0, 150 ) );
        vraag.setBackgroundMode( HVisible.BACKGROUND_FILL );
        
        antwoordLabel = new HStaticText( isJuist );        
        antwoordLabel.setLocation( 0,500 );
        antwoordLabel.setSize( 740,80 );
        antwoordLabel.setBackground( new DVBColor( 0,0,0, 150 ) );
        antwoordLabel.setBackgroundMode( HVisible.BACKGROUND_FILL );
        
        scoreLabel = new HStaticText( Integer.toString( score ) );        
        scoreLabel.setLocation( 30,250 );
        scoreLabel.setSize( 100,100 );
        
        scene.add( title );
        scene.add( vraag );
        
        startknop = new HTextButton( "Start." );        
        startknop.setLocation( 170,250 );
        startknop.setSize( 400,100 );
        startknop.setBackground( new DVBColor( 0,0,0, 200 ) );
        startknop.setBackgroundMode( HVisible.BACKGROUND_FILL );
        
        stopknop = new HTextButton( "Stop." );        
        stopknop.setLocation( 30,180 ); 
        stopknop.setSize( 100,100 );
        stopknop.setBackground( new DVBColor( 0,0,0, 200 ) );
        stopknop.setBackgroundMode( HVisible.BACKGROUND_FILL );
       
        knop1.setLocation( 170,180 ); 
        knop1.setSize( 400,70 );
        knop1.setBackground( new DVBColor( 100,100,100, 179 ) );
        knop1.setBackgroundMode( HVisible.BACKGROUND_FILL );
              
        knop2.setLocation( 170,250 ); 
        knop2.setSize( 400,70 );
        knop2.setBackground( new DVBColor( 100,100,100, 179 ) );
        knop2.setBackgroundMode( HVisible.BACKGROUND_FILL );
             
        knop3.setLocation( 170,320 ); 
        knop3.setSize( 400,70 );
        knop3.setBackground( new DVBColor( 100,100,100, 179 ) );
        knop3.setBackgroundMode( HVisible.BACKGROUND_FILL );
             
        knop4.setLocation( 170,390 ); 
        knop4.setSize( 400,70 );
        knop4.setBackground( new DVBColor( 100,100,100, 179 ) );
        knop4.setBackgroundMode( HVisible.BACKGROUND_FILL );
        
        knop1.setFocusTraversal( knop4, knop2, stopknop, stopknop );//op, neer, links, rechts
        knop2.setFocusTraversal( knop1, knop3, stopknop, stopknop );//op, neer, links, rechts
        knop3.setFocusTraversal( knop2, knop4, stopknop, stopknop );//op, neer, links, rechts
        knop4.setFocusTraversal( knop3, knop1, stopknop, stopknop );//op, neer, links, rechts
        stopknop.setFocusTraversal( null, null, knop1, knop4 );//op, neer, links, rechts
        
        startknop.setActionCommand( "play" );
        startknop.addHActionListener( this );
        
        stopknop.setActionCommand( "stop" );
        stopknop.addHActionListener( this );
        
        knop1.setActionCommand( "1" );
        knop1.addHActionListener( this );
        
        knop2.setActionCommand( "2" );
        knop2.addHActionListener( this );
        
        knop3.setActionCommand( "3" );
        knop3.addHActionListener( this );
        
        knop4.setActionCommand( "4" );
        knop4.addHActionListener( this );
        
        background = new DrawImage("bg.png", 0, -35);
        
        scene.add( startknop );
        scene.add( stopknop );
        scene.add( knop1 );
        scene.add( knop2 );
        scene.add( knop3 );
        scene.add( knop4 );
        scene.add( antwoordLabel );
        scene.add( scoreLabel );
        scene.add( background );
        
        startknop.requestFocus();  
    }

    public void startXlet() 
    { 
        scoreLabel.setVisible(false);
        
        scene.validate(); 
        scene.setVisible(true); 
        showHome();
    }
    
    public void pauseXlet() {}
    public void destroyXlet( boolean unconditional ) throws XletStateChangeException {}
    public void userEventReceived( UserEvent e ) {}
    public void actionPerformed( ActionEvent e ) throws UnsupportedOperationException { handleClick( e.getActionCommand() ); }    
    
    public void showHome()
    {      
        antwoordLabel.setVisible( false );
        vraag.setVisible( false );
        
        startknop.setVisible( true );
        knop1.setVisible( false );
        knop2.setVisible( false );
        knop3.setVisible( false );
        knop4.setVisible( false );
        stopknop.setVisible( false );
        
        startknop.requestFocus();  
    }
    
    public void showGame()
    {
        score = 0;
        isJuist = "";
        antwoordLabel.setBackground( new DVBColor( 0,0,0, 150 ) );
        
        antwoordLabel.setVisible( true );
        scoreLabel.setVisible( true );
        vraag.setVisible( true );

        startknop.setVisible( false );
        knop1.setVisible( true );
        knop2.setVisible( true );
        knop3.setVisible( true );
        knop4.setVisible( true );
        stopknop.setVisible( true );

        knop1.requestFocus();  
    }
    
    private void handleClick( String command )
    {             
        if( command == questions[randomvraagIndex][5] )
        {            
            isJuist = "Juist";
            score += 5;
            this.xlet = xlet;
            System.out.println( randomvraagIndex );

            randomvraagIndex = 0 + ( int )( Math.random() * questions.length ); 
            System.out.println( questions[randomvraagIndex][0] );
            antwoordLabel.setBackground( new DVBColor( 0,255,0, 150 ) );    
            
        } else if( command == "stop" ){
            
            showHome();
            
        } else if( command == "play" ){
            
            showGame();
            
        } else {      
            
            if( score > 2 ){
                score -= 3;
            } else {
                score = 0; 
            }
            
            isJuist = "fout";
            antwoordLabel.setBackground(new DVBColor( 255,0,0, 150 ) );    
            
        }
     
        vraag.setTextContent( questions[randomvraagIndex][0], this.vraag.NORMAL_STATE );        
        knop1.setTextContent( questions[randomvraagIndex][1], this.knop1.NORMAL_STATE );
        knop2.setTextContent( questions[randomvraagIndex][2], this.knop2.NORMAL_STATE );
        knop3.setTextContent( questions[randomvraagIndex][3], this.knop3.NORMAL_STATE );
        knop4.setTextContent( questions[randomvraagIndex][4], this.knop4.NORMAL_STATE );      
        
        this.antwoordLabel.setTextContent( isJuist, this.antwoordLabel.NORMAL_STATE );
        this.scoreLabel.setTextContent( "Score: " + Integer.toString( score ), this.scoreLabel.NORMAL_STATE );
    }
}
