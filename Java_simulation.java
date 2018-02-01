package finalproject;
import java.util.Scanner;

interface Rectification1{public float reflux2();}
interface Rectification2{public double topConc();}
interface FeedPlate1{public int feedConc();}
interface FeedPlate2{public float feedWays();}
abstract class Stripping{public abstract double bottomConc();}
abstract class Characteristic{public abstract int EQLine();}


interface Reflux extends Rectification1{
     public float reflux2();
}

class Reflux1 implements Reflux{
     public float reflux;
      float r; 
     public float reflux2(){reflux=r;
     return reflux;}
}

interface TopConc extends Rectification2{
      public double topConc();
}

class TopConc1 implements TopConc{
     double t; 
     public double topConc(){return t;}
}

interface FeedConc extends FeedPlate1{
      public int feedConc();
}

class FeedConc1 implements FeedConc{
    int f;
     public int feedConc(){return f;}
}

interface FeedWays extends FeedPlate2{
     public float feedWays();
}

class FeedWays1 implements FeedWays{
      float w;
     public float feedWays(){return w;}
}

abstract class BottomConc extends Stripping{
     public abstract double bottomConc();
}

class BottomConc1 extends BottomConc{
       double b;
      public double bottomConc(){return b;}
}

abstract class EQLine extends Characteristic{
     public abstract int EQLine();
}


interface ROPLine extends Reflux,TopConc{
     public float reflux2();     
     public double topConc();
     
}


interface FeedLine extends FeedConc,FeedWays{
     public int feedConc();
     public float feedWays();
}

class Crosspiont implements ROPLine,FeedLine{
    private double xc,yc;
    private double xc1,yc1; 
    public void crosspiont(float Xf,float r,double t,float f,float w){
        int g,a;
        
        if(w!=1){
           for(a=1;a<10;a++){
              xc=100*(((double)r/((double)r+1)*a+0.0235/((double)r+1)- Xf/(1-(double)w))* (1-(double)w)/(-(double)w));
              yc=(r/(r+1)*xc+t/(r+1));
              g=(int)xc-a;
             if(g<=100){
               System.out.println("進料線與操作線焦點(xc,yc)="+"("+(float)xc/100+","+(float)yc/100+")");
               xc1=xc;
               yc1=yc;               
               break;
                       
             }
            }       
       }else{
          yc=(r/(r+1)*Xf+t/(r+1));
          System.out.println("進料線與操作線焦點(xc,yc)="+"("+Xf+","+yc+")");
          xc1=Xf;
          yc1=yc; 
        }      
     }
     
     float r;
     public float reflux2(){return r;}
     double t;
     public double topConc(){return t;}
     int f;
     public int feedConc(){return f;}
     float w;
     public float feedWays(){return w;}
     
     public double getcrosspiontx(){
        return xc1;
        
     }

     public double getcrosspionty(){
        return yc1;
        
     }
}

class test1{
  int number1;
  void f(double Xc1,float r,double a,double b,double c){
     int i=0;
     double an;
     double[] x=new double[100];      
     double[] y=new double[100];
     double[] C=new double[100];   
     double[] k=new double[100];
     double[] s=new double[100];
      C[i]=0.974-c; 
      while(i<100){
       k[i]=(b*b)-4*a*C[i];
        if(k[i]>0){    
           s[i]=Math.sqrt(k[i]);
           x[i]=(-b-s[i])/(2*a); 
           y[i]=r/(r+1)*x[i]+0.974/(r+1);
           C[i]=y[i]-c;
               if(x[i]<=Xc1){
                   System.out.println(+i);
                   number1=i;
                   break;}
            i++;
         }else if (k[i]==0){
           x[i]=-b/(2*a);
           y[i]=r/(r+1)*x[i]+0.974/(r+1);
           C[i]=y[i]-c;
             if(x[i]<=Xc1){
                   System.out.println(+i);
                   number1=i;
                   break;}
            i++;
          }
         else{
           System.out.println("none");
           break;
         }

      }
    
                                                                                                                                               
  }

  public int getnumber1(){
        return number1;
     }
}     

class test2{
  int number2;
   void q(double Xc1,double Yc1){
     int i=0;
     double[] xs=new double[100];      
     double[] ys=new double[100];
     double[] C=new double[100];
     xs[0]=0.0235 ; 
   for(i=0;i<100;i++){
    
     ys[i]=-0.8311*xs[i]*xs[i]+ 1.7793*xs[i] + 0.0267;
     xs[i+1]= (ys[i]-(Yc1-Xc1)/(0.0235-Xc1))*(0.0235-Xc1)/(0.0235-Yc1);
     if(xs[i]>=Xc1){System.out.println(+i);
                   number2=i;
                   break;}
   
    }
  }

   public int getnumber2(){
     return number2;
   }
}


public class Final{
  public static void main(String[] args){
      System.out.println("連續蒸餾用來分離30000kg/h的未知濃度苯(分子量78)和甲苯(分子量92)混和物，希望在塔頂有97%wt苯和3%wt甲苯(塔頂濃度0.0235)，在塔底有98%wt甲苯和2%wt苯(塔底濃度0.974)");
      System.out.println("你可以決定混和物中苯和甲苯濃度，回流比(Rd)，進料方式，目標是找到"+"\n"+"(1)進料濃度Xf"+"\n"+"(2)進料莫爾流率F"+"\n"+"(3)塔頂莫爾流率D"+"\n"+"(4)塔底莫爾流率B"+"\n"+"(5)理論版數");

//塔頂濃度
      TopConc1 T=new TopConc1();
      T.t=0.974;
      System.out.println("已知甲苯塔頂濃度為:"+T.t);

//塔底濃度
      BottomConc1 B=new BottomConc1();
      B.b=0.0235;
      System.out.println("已知甲苯塔底濃度為:"+B.b);

//混和物濃度
      System.out.println("請輸入混和物濃度苯a%(小於100的正整數)");
      FeedConc1 F=new FeedConc1();
      Scanner scanner= new Scanner(System.in);
      F.f=scanner.nextInt();
      System.out.println("苯濃度:"+F.f+"%"+"甲苯濃度:"+(100-F.f)+"%");     

//回流比
      System.out.println("請輸入回流比值(正實數)");
      Reflux1 R=new Reflux1();
      R.r= scanner.nextFloat();
      System.out.println("回流比:"+R.r);

//進料方式
      System.out.println("(1)過冷液體q＞1"+"\n"+"(2)飽和液體q=1"+"\n"+"(3)飽和液體與氣體共存0＜q＜1"+"\n"+"(4)飽和氣體q=0"+"\n"+"(5)過熱蒸氣q＜0"+"\n"+"輸入你希望的進料方式:");
      FeedWays1 W=new FeedWays1();
      W.w= scanner.nextFloat();
      System.out.println("進料方式q="+W.w);
//算Xf
      float Xf;
      int Mb=78,Mt=92;
      System.out.println("\n"+"已知混和物苯濃度為:"+F.f+"%，利用公式:XF=a/78/(a/78+(100-a)/92)來求Xf值");
      Xf=(float)F.f/Mb/((float)F.f/Mb+(100-(float)F.f)/Mt);
      System.out.print("Xf值為"+Xf);
//算F
      float Flow;
      System.out.println("\n\n"+"算進料莫耳流率F，利用公式Flow=30000/100(a/78+(100-a)/92)");
      Flow=300*((float)F.f/Mb+(100-(float)F.f)/Mt);
      System.out.print("F值為"+Flow);
//算D
      double D;
      System.out.println("\n\n"+"算塔頂莫耳流率D，利用公式D=Flow(Xf-0.0235)/(0.974-0.0235)");
      D=Flow*(Xf-0.0235)/(0.974-0.0235);
      System.out.println("D值為"+D);

//算B
      double Bflow;
      System.out.println("\n"+"算塔底莫耳流率B，利用公式B=Flow-D");
      Bflow=(double)Flow-D;
      System.out.println("B值為"+Bflow); 

//找進料線與操作線的交點
     
      System.out.println("\n"+"找進料線與操作線的交點，利用兩條公式做嘗試錯誤法求得(xc,yc)");
      Crosspiont C=new Crosspiont();
      C.crosspiont(Xf,R.r,0.974,Flow,W.w);    
      

//找精餾段上的交點,計算有幾個點

    System.out.println("\n"+"利用ROPLine和EQLine，找精餾段上的版數，計算有多少點通過此段");       
    test1 G1=new test1();
    G1.f(C.getcrosspiontx(),R.r,0.8311,-1.7793,0.0267);
                                                                                                                                              
                                                                                                                                           
//找氣堤段上的交點,計算有幾個點
    System.out.println("\n"+"利用SOPLine和EQLine，找氣堤段上的版數，計算有多少點通過此段");       
    test2 G2=new test2();
    G2.q(C.getcrosspiontx(),C.getcrosspionty());

//理想版數
    System.out.println("理想版數等於通過精餾段的點和通過氣堤段的點的總和:"+" "+(G1.getnumber1()+G2.getnumber2()));
      
  }

}
