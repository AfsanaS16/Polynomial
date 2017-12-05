public class X123 extends Polynomial {
	   public static void main(String args[]) throws Exception {
	      Polynomial p = new X123(" X^5"), 
	    		  		q = new X123("X^2 - X + 1");
	      	Utility.run(p, q);
	   }
	   
	   public X123(String s) 
	   {
			super();
			String num = s.replace("-", "+-");
			num = num.replaceAll("\\s+",""); 
			if(num.charAt(0) == '+'){ 
		   	num = num.substring(1);
		   }
		   String split[] = num.split("\\+");//split every +

		   for (int i=0; i<split.length; i++ ) {//start of loop
		   		double cparse = 0;
		   		int dparse = 0;
		   		String element = split[i];
		   		int index = element.indexOf('X');
		   		String coefficient = "";
		   		String degree = "";

		   		if(index == 0){
		   			coefficient = "1";
		   			cparse = Double.parseDouble(coefficient); 
		   		}
		   		else if( element.length() > 1 && element.substring(0,2).equals("-X")){
		   			coefficient = "-1";
		   			cparse = Double.parseDouble(coefficient);   
		   		}
		   		else{
		   			if(index == -1){
		   				coefficient = element;
		   			}
		   			else{
		   			coefficient = element.substring(0, index);
		   			}
		   			cparse = Double.parseDouble(coefficient); 
			    }   
			   	if(element.contains("^")){
			   		degree = String.valueOf((element.charAt(i+2)));
			   		dparse = Integer.parseInt(degree); 
			   	}
			   else if(element.contains("X")){
			   		degree = "1";
			   		dparse = Integer.parseInt(degree); 
			   }
			   else{ 
			   		degree = "0"; 
			   		dparse = Integer.parseInt(degree); 
			   }

			   Term t = new Term(cparse, dparse);
			   data.addLast(t);
			}//end of for loop
		}

	   public X123() {
	      super();
	   }

	   public Polynomial add(Polynomial p) {
	      Polynomial ans = new X123();
	      Term t; 
	      DList<Term> listA = this.data;
	      DList<Term> listB = p.data;
	      DList<Term> newList = new DList<Term>();
	      DNode<Term> nodeA = null;
	      DNode<Term> nodeB = null;

	      try {
			nodeA = listA.getFirst();
			nodeB = listB.getFirst();
		} catch (Exception e) {
			}

			Term a = nodeA.getData();
			Term b = nodeB.getData();      

			while(a != null || b != null){
				if(b == null){
					t = new Term(a.getCoefficient(), a.getDegree());
					newList.addLast(t);
					nodeA = nodeA.getNext();
					a = nodeA.getData();
				}
				else if(a == null){
					t = new Term(b.getCoefficient(), b.getDegree());
					newList.addLast(t);
					nodeB = nodeB.getNext();
					b = nodeB.getData();
				}
				else if(a.getDegree() > b.getDegree()){
					t = new Term(a.getCoefficient(), a.getDegree());
					newList.addLast(t);
					nodeA = nodeA.getNext();
					a = nodeA.getData();
				}
				else if(a.getDegree() < b.getDegree()){
					t = new Term(b.getCoefficient(), b.getDegree());
					newList.addLast(t);
					nodeB = nodeB.getNext();
					b = nodeB.getData();
				}
				else{
					double coef = a.getCoefficient() + b.getCoefficient();
					int exp = a.getDegree();
					nodeA = nodeA.getNext();
					a = nodeA.getData();
					nodeB = nodeB.getNext();
					b = nodeB.getData();
					if(coef == 0) continue;
					t = new Term(coef, exp);
					newList.addLast(t);
				}
			}
			ans.data = newList;
			return ans;
	   }

	   public Polynomial subtract(Polynomial p) {
	      Polynomial ans = new X123();
	      try {
				DNode<Term> nodeB = p.data.getFirst();
				int size = p.data.size();
				while(size > 0){
					Double d = nodeB.getData().getCoefficient();
					nodeB.getData().setCoefficient(d*-1d);
					nodeB = nodeB.getNext();
					size--;
				}     
				ans = this.add(p);
			} catch (Exception e) {
			}
	      return ans;
	   }

	   public Polynomial multiply(Polynomial p) {
	      Polynomial ans = new X123();
	      Term temp = new Term(0.0, 0);
	      ans.data.addFirst(temp);
	      try {
	    	  	DNode<Term> nodeA = this.data.getFirst();
	    	  	DNode<Term> nodeB = p.data.getFirst();
	    	  	while(nodeA != null || nodeB != null){
	    	  		while(nodeA != null) {
	    	  			int degA = nodeA.getData().getDegree();
	    	  			double coefA = nodeA.getData().getCoefficient();
	    	  			Polynomial result = new X123();
	    	  			while(nodeB != null) {
	    	  				int degB = nodeB.getData().getDegree();
	    	  				double coefB = nodeB.getData().getCoefficient();
	    	  				Term temp2 = new Term(-1* coefA * coefB, degA + degB);
	    	  				result.data.addLast(temp2);;
	    	  				nodeB = nodeB.getNext();
	    	  				if (nodeB.getNext() == null) break;
	    	  			}
	    	  			nodeB = p.data.getFirst();
	    	  			nodeA = nodeA.getNext();
	    	  			ans = ans.add(result);
	    	  		}
	    	  
	    	  	}
	      } 
	      catch(Exception e) {
	    	  	System.out.print("");;
	      }
          return ans;
	   }

	@Override
	public Polynomial divide(Polynomial p) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Polynomial remainder(Polynomial p) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}


