package screen;

public abstract class Screen {

    protected abstract void onCreate();


    public final void dispatchCreate() {
        onCreate();
    }

}
