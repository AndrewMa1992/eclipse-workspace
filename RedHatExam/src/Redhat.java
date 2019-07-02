import org.w3c.dom.css.ElementCSSInlineStyle;

public class Redhat {

	public static void main(String[] args) {

		Integer moneyInteger = Integer.parseInt(args[0]);
		if(moneyInteger < 0) {
			System.err.println("You inputted a negative number!");
		}else {
			
			System.out.print("人民币");
			if((moneyInteger % 100000000) > 0 ) {
				Integer yiInteger = moneyInteger/100000000;
				whichCharacter(yiInteger);
				System.out.print("亿");
				
			}else if (moneyInteger % 10000>0) {
					Integer wanInteger = moneyInteger/10000;
				    if(wanInteger % 1000 >0) {
				        Integer temp = wanInteger /1000;
				    	whichCharacter(temp);
				    	System.out.println("千");
				    }else if(wanInteger % 100 > 0) {
				    	Integer temp = wanInteger /1000;
				    	temp = (wanInteger - temp*1000) /100;
				    	whichCharacter(temp);
				    	System.out.print("百");
				    }
				    
			}
		}
	}
	
	public static void whichCharacter(Integer number) {
		
		switch(number){
        case 0:
            System.out.print("零");break;
        case 1:
            System.out.print("一");break;
        case 2:
            System.out.print("二");break;
        case 3:
            System.out.print("三");break;
        case 4:
            System.out.print("四");break;
        case 5:
            System.out.print("五");break;
        case 6:
            System.out.print("六");break;
        case 7:
            System.out.print("七");break;
        case 8:
            System.out.print("八");break;
        case 9:
            System.out.print("九");break;
        default:
            System.out.print("default");break;
        }
		
	}
	
}
