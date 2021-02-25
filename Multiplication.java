package sample;

public class Multiplication {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int a[][]={{1,10,8},{2,2,9},{1,3,9}};    
		int b[][]={{1,0,5},{6,3,5},{3,4,3}};    
		    
		    
		int c[][]=new int[3][3];  
		       
		for(int i=0;i<3;i++){    
		for(int j=0;j<3;j++){    
		c[i][j]=0;      
		for(int k=0;k<3;k++)      
		{      
		c[i][j]+=a[i][k]*b[k][j];      
		} 
		System.out.print(c[i][j]+" ");    
		}
		System.out.println();  
		}  

	}

}
