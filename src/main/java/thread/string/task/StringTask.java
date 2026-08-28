package thread.string.task;

public class StringTask implements Runnable {

    private String text;
    private int counter;
    private TaskState state = TaskState.CREATED;
    private Thread thread;
    private String result = "";

    public StringTask(String text, int counter) {
        this.counter = counter;
        this.text = text;
        thread = new Thread(this);
    }

    @Override
    public void run() {
        for (int i = 0; i < counter; i++) {
            if (thread.isInterrupted()) {
                break;
            }
            result += text;
        }
        state = TaskState.READY;


    }

    public void start() {
        thread.start();
        state = TaskState.RUNNING;
    }

    public void abort() {
        thread.interrupt();
        state = TaskState.ABORTED;
    }

    public boolean isDone() {
        return state == TaskState.READY || state == TaskState.ABORTED;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public TaskState getState() {
        return state;
    }

    public void setState(TaskState state) {
        this.state = state;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Thread getThread() {
        return thread;
    }

    public void setThread(Thread thread) {
        this.thread = thread;
    }
}
