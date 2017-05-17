
//THIS FILE NEEDS TO BE EDITED BY THE STUDENT

//This class extends the abstract class binaryOperations and should include an
//implementation of the multiplication algorithm. The algorithm that should be
//implemented should be the simple O(n^2) Divide and Conquer algorithm discussed
//in class.

public class Mult6 extends binaryOperations{


    public myBinaryNumber multiplication(myBinaryNumber N1, myBinaryNumber N2){

    	int size=MakeEqualLength(N1, N2);


       ////////////////////////////////////////////////////////////////
        myBinaryNumber P=new myBinaryNumber(2*size);
        int mid= (size/2)+(size%2);
        //***************base case*************************//
        if(size==1)
        {
        	int ans= N1.getBit(size-1)*N2.getBit(size-1);
        	try {
				P.setBit(size-1, ans);
			} catch (Exception e) {
				e.printStackTrace();
			}
        	return P;
        }

        ///////////// N1 LEFT////////////////////
        myBinaryNumber N1left=leftPart(N1, size, mid);

        /*myBinaryNumber N1left=new myBinaryNumber(size-mid);
        for(int i=0;i<(size-mid);i++)
        {
        	try {
			N1left.setBit(i,N1.getBit(mid+i));
			} catch (Exception e) {
				e.printStackTrace();
			}
        }*/
        //////////////N1 RIGHT//////////////////////
        myBinaryNumber N1right=rightPart(N1, mid);
        /*myBinaryNumber N1right=new myBinaryNumber(mid);
        for(int i=0;i<mid;i++)
        {
        	try {
			N1right.setBit(i,N1.getBit(i));
			} catch (Exception e) {
				e.printStackTrace();
			}
        }*/
        //////////// N2 LEFT//////////////////////
        myBinaryNumber N2left=leftPart(N2, size, mid);
                                                        /*myBinaryNumber N2left=new myBinaryNumber(size-mid);
                                                             for(int i=0;i<(size-mid);i++)
                                                               {
        	                                                      try {
			                                                            N2left.setBit(i,N2.getBit(mid+i));
			                                                               } catch (Exception e) {
				                                                               e.printStackTrace();
			                                                           }
                                                               }*/
        ////////////// N2 RIGHT/////////////////////
        myBinaryNumber N2right=rightPart(N2, mid);
        /*myBinaryNumber N2right=new myBinaryNumber(mid);
        for(int i=0;i<mid;i++)
        {
        	try {
			N2right.setBit(i,N2.getBit(i));
			} catch (Exception e) {
				e.printStackTrace();
			}
        }*/

        myBinaryNumber P1=multiplication(N1left, N2left);
        myBinaryNumber P2=multiplication(N1left, N2right);
        myBinaryNumber P3=multiplication(N1right, N2left);
        myBinaryNumber P4=multiplication(N1right, N2right);

        myBinaryNumber P5=addition(P2, P3);

       //////////// P1 * 2^(2*mid)//////////////
        P1= zeroPadding(P1, (2*mid));
        /*myBinaryNumber P1n=new myBinaryNumber(P1.getSize()+(2*mid));
        for(int i=(2*mid);i<P1n.getSize();i++)
        {
        	try {
				P1n.setBit(i,(P1.getBit(i-(2*mid))));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }*/
        ///////////// P5* 2^mid /////////////////////
        P5=zeroPadding(P5, (mid));
        /*myBinaryNumber P5n= new myBinaryNumber(P5.getSize()+mid);
        for(int i=mid;i<P5n.getSize();i++)
        {
        	try {
				P5n.setBit(i,(P5.getBit(i-mid)));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }*/

      P=addition(addition(P1, P5),P4);
        return P;

    }

 // given two unequal sized binary numbers, converts them to
 	// same length by adding leading 0s in the smaller binary numbers. Returns the
 	// the new length
 	public int MakeEqualLength(myBinaryNumber n1,myBinaryNumber n2)
 	{
 		int size1= n1.getSize();
     	int size2= n2.getSize();

     	 if(size2<size1)
          {
     		 myBinaryNumber n4= new myBinaryNumber(size1);
          	for(int i=0;i<(size2);i++)
          	{
          		try {
  					n4.setBit(i, (n2.getBit(i)));
  				} catch (Exception e) {
  					e.printStackTrace();
  				}
          	}
          	n2=n4;
          	return size1;
          }
          else if(size1<size2)
          {
         	 myBinaryNumber n3=new myBinaryNumber(size2);
          	for(int i=0;i<(size1);i++)
          	{
          		try {
  					n3.setBit(i, (n1.getBit(i)));
  				} catch (Exception e) {
  					e.printStackTrace();
  				}
          	}
          	n1=n3;
          	return size2;
          }
          else return size2;
 	}
 	public myBinaryNumber leftPart(myBinaryNumber n,int size,int mid)
 	{
 		 myBinaryNumber nLeft=new myBinaryNumber(size-mid);
         for(int i=0;i<(size-mid);i++)
         {
         	try {
 			nLeft.setBit(i,n.getBit(mid+i));
 			} catch (Exception e) {
 				e.printStackTrace();
 			}
         }
         return nLeft;
 	}
 	public myBinaryNumber rightPart(myBinaryNumber n, int mid)
 	{
 		myBinaryNumber nRight=new myBinaryNumber(mid);
        for(int i=0;i<mid;i++)
        {
        	try {
			nRight.setBit(i,n.getBit(i));
			} catch (Exception e) {
				e.printStackTrace();
			}
        }
        return nRight;
 	}
 	public myBinaryNumber zeroPadding (myBinaryNumber N, int a)
 	{
 		 myBinaryNumber N1= new myBinaryNumber(N.getSize()+a);
         for(int i=a;i<N1.getSize();i++)
         {
         	try {
 				N1.setBit(i,(N.getBit(i-a)));
 			} catch (Exception e) {
 				e.printStackTrace();
 			}
         }
         return N1;
 	}
}
