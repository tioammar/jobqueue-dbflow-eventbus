package in.lovelacetech.pln.job.exception;

/**
 * Created by tioammar on 13/01/16.
 */
public class NetworkException extends RuntimeException {

    private int mErrorCode;

    public NetworkException(int err){
        mErrorCode = err;
    }

    public boolean shoudRetry(){
        return mErrorCode < 400 || mErrorCode > 499;
    }
}
