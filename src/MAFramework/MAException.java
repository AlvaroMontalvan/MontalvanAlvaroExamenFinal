package MAFramework;

public class MAException extends Exception {
    
    public MAException(String e, String clase, String metodo){
        System.out.println("[ERROR APP -->> LOG]" + clase + "." + metodo + " : " + e);
    }
    @Override
    public String getMessage(){
        return "No sea sapo..";
    }
}
