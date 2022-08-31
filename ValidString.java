public class ValidString {
    public static void main(String[] args) {
        if(checkValidString("(*))")){
            System.out.println("Valid String");
        }else{
            System.out.println("Invalid String");
        }
    }
    public static boolean checkValidString(String s) {

        int x = 0;
        int asterik_count =0;
        for (char c:s.toCharArray()){
            if(c=='('){
                x+=1;
            }else if(c==')'){
                x-=1;
            }else{
                asterik_count++;
            }
            if(x<0){
                return false;
            }
        }
        if(x-asterik_count<=0)
            return true;
        return false;
    }


    
}