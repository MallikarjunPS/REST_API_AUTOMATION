package resources;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.ApplicationFrame;

import javax.swing.*;

public class PieChart_AWT extends ApplicationFrame {
	
	static double p;
	static double f;
	static double s;
   
   public PieChart_AWT( String title, double pass, double fail, double skip ) {
      super( title ); 
      p=pass;
      f=fail;
      s=skip;
      setContentPane(createDemoPanel( ));
   }
   
   private static PieDataset createDataset(double p1,double f1,double s1 ) {
      DefaultPieDataset dataset = new DefaultPieDataset( );
      dataset.setValue( "PASS" , p1 );  
      dataset.setValue( "FAIL" , f1 );   
      dataset.setValue( "SKIP" , s1 );      
      return dataset;         
   }
   
   private static JFreeChart createChart( PieDataset dataset ) {
      JFreeChart chart = ChartFactory.createPieChart(      
         "API TEST REPORT ",   // chart title 
         dataset,          // data    
         true,             // include legend   
         true, 
         false);

      return chart;
   }
   
   public static JPanel createDemoPanel( ) {
      JFreeChart chart = createChart(createDataset(p,f,s));  
      return new ChartPanel( chart ); 
   }

 
}