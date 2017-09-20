package id.ac.tazkia.dosen.exception;

public class ResourceNotFoundException extends RuntimeException{
    
    public ResourceNotFoundException() {
    }

    public ResourceNotFoundException(String string) {
        super(string);
    }

    public ResourceNotFoundException(String string, Throwable thrwbl) {
        super(string, thrwbl);
    }

    public ResourceNotFoundException(Throwable thrwbl) {
        super(thrwbl);
    }

    public ResourceNotFoundException(String string, Throwable thrwbl, boolean bln, boolean bln1) {
        super(string, thrwbl, bln, bln1);
    }
}
