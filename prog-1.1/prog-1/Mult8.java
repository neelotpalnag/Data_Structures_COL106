//THIS FILE NEEDS TO BE EDITED BY THE STUDENT

//This class extends the abstract class binaryOperations and should include an
//implementation of the multiplication algorithm. The algorithm that should be
//implemented should be the O(n^{log 3}) Karatsuba aalgorithm discussed
//in class.

public class Mult8 extends binaryOperations{

    public myBinaryNumber multiplication(myBinaryNumber N1, myBinaryNumber N2){
        //To be implemented by the student
        int max;
        int size1=N1.getSize();
    	int size2=N2.getSize();
        if (size1>=size2){
        	max=size1;
        }
        else{max=size2;}

        if (size1>=size2){
        	myBinaryNumber n =new myBinaryNumber(size1);
        	for (int i=0;i<size2;i++){
        		try{
        			n.setBit(i,N2.getBit(i));
        		}catch(Exception e){e.printStackTrace();System.exit(0);}
        	}
        	N2=n;

        }
        else{myBinaryNumber n =new myBinaryNumber(size2);
        	for (int i=0;i<size1;i++){
        		try{
        			n.setBit(i,N1.getBit(i));
        		}catch(Exception e){e.printStackTrace();System.exit(0);}
        	}
        	N1=n;


        }
        if (max==1){
        	if (N1.getBit(0)==1 && N2.getBit(0)==0){
        		return N2;
        	}
        	else if (N1.getBit(0)==0 && N2.getBit(0)==1){
        		return N1;
        	}
        	else return N1;

        }

        //System.out.println("N1 =");
        //N1.printNumber();
		//System.out.println("N2 =");
        //N2.printNumber();

        size1=N1.getSize();
    	size2=N2.getSize();


        myBinaryNumber Al=new myBinaryNumber(size1- (size1/2));

        myBinaryNumber Ar=new myBinaryNumber(size1/2);

        myBinaryNumber Bl=new myBinaryNumber(size2- (size2/2));

        myBinaryNumber Br=new myBinaryNumber(size2/2);


        for (int i=0;i<(size1-(size1/2));i++){
        	try{
    		Al.setBit(i,N1.getBit(i+(size1/2)));}
    		catch(Exception e){e.printStackTrace();System.exit(0);}
    	}

    	for (int i=0;i<(size1/2);i++){
    		try{
    		Ar.setBit(i,N1.getBit(i));}
    		catch(Exception e){e.printStackTrace();System.exit(0);}
    	}

    	for (int i=0;i<(size2-(size2/2));i++){
        	try{
    		Bl.setBit(i,N2.getBit(i+(size2/2)));}
    		catch(Exception e){e.printStackTrace();System.exit(0);}
    	}

    	for (int i=0;i<(size2/2);i++){
    		try{
    		Br.setBit(i,N2.getBit(i));}
    		catch(Exception e){e.printStackTrace();System.exit(0);}
    	}

    	//System.out.println("Al =");
        //Al.printNumber();
    	//System.out.println("Ar =");
        //Ar.printNumber();
		//System.out.println("Bl =");
        //Bl.printNumber();
        //System.out.println("Br =");
        //Br.printNumber();

    	myBinaryNumber P=multiplication(Al,Bl);
    	myBinaryNumber R=multiplication(Ar,Br);



    	myBinaryNumber Q=multiplication(removetrailingZero(addition(Al,Ar)),removetrailingZero(addition(Bl,Br)));

    	//System.out.println("P =");
        //P.printNumber();
    	//System.out.println("Q =");
        //Q.printNumber();
		//System.out.println("R =");
        //R.printNumber();

    	myBinaryNumber Prem=removetrailingZero(P);
    	//System.out.println("Prem =");
        //Prem.printNumber();
    	myBinaryNumber Rrem=removetrailingZero(R);
    	//System.out.println("Rrem =");
        //Rrem.printNumber();
    	myBinaryNumber Qrem=removetrailingZero(Q);
    	//System.out.println("Qrem =");
        //Qrem.printNumber();
      	myBinaryNumber Subtract1=subtract(Qrem,Prem);
      	myBinaryNumber Subtract1rem=removetrailingZero(Subtract1);
		myBinaryNumber Subtract2=subtract(Subtract1rem,Rrem);
		//System.out.println("Subtract2 =");
        //Subtract2.printNumber();

		myBinaryNumber P1=AddZero(P,((size1/2) + (size1/2)));
		//System.out.println("P1 =");
        //P1.printNumber();
    	myBinaryNumber Q1=AddZero(Subtract2,(size1/2));
    	//System.out.println("Q1 =");
        //Q1.printNumber();


    	myBinaryNumber Sum1=addition(P1,Q1);

    	myBinaryNumber Sum2=addition(Sum1,Rrem);
    	//System.out.println("Sum2 =");
        //Sum2.printNumber();
    	return Sum2;



    }




    public myBinaryNumber AddZero(myBinaryNumber N1,int n){
    	for (int s=0; s<(n); s++){
    	int size=N1.getSize();
    	myBinaryNumber k= new myBinaryNumber(size+1);
    	for (int i=1; i<(size+1); i++){
    		try{
    		k.setBit(i,(N1.getBit(i-1)));}
    		catch(Exception e){e.printStackTrace();System.exit(0);}
    	}
    	N1=k;

    }

    return N1;}
    public myBinaryNumber removetrailingZero(myBinaryNumber N1){
    	int size=N1.getSize();

    	for (int i=size-1;i>=0;i--){
    		//System.out.println(N1.getBit(i)+"");
    		if (size==1){
    			if (N1.getBit(0)==0){
    				break;
    			}    		}
    		if (N1.getBit(i)==1)break;
    		else{
    			myBinaryNumber N=new myBinaryNumber((size-1));
    			//N.printNumber();
    			for (int k=0;k<(size-1);k++){
    				try{N.setBit(k,N1.getBit(k));}
    				catch(Exception e){e.printStackTrace();System.exit(0);}
    			}
    			N1=N;
    			//N1.printNumber();
    			size=N1.getSize();
    		}



    	}
    	return N1;
    }

    public myBinaryNumber subtract(myBinaryNumber N1,myBinaryNumber N2){
        	int size1=N1.getSize();
        	int size2=N2.getSize();

        	myBinaryNumber result = new myBinaryNumber(size1);
        	for(int i=0;i<size1;i++){
        		int p = N1.getBit(i) - N2.getBit(i);
        		if(p<0){
        			int carryPos = i+1;
        			int carry = N1.getBit(carryPos);
        			while(carry!=1){
        				try{
        					N1.setBit(carryPos,1);
        				}catch(Exception e){e.printStackTrace();System.exit(0);}
        				carryPos++;
        				carry = N1.getBit(carryPos);
        			}
        			try{
        					N1.setBit(carryPos,0);
     						result.setBit(i,1);
        				}catch(Exception e){e.printStackTrace();System.exit(0);}

        		}else{
        			try{
        					result.setBit(i,p);
        				}catch(Exception e){e.printStackTrace();System.exit(0);}
        		}
        	}

        	return result;        }
}
