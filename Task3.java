public class DoubleStack {
    private int[] arr;
    private int redTop = -1;
    private int blueTop;

    public DoubleStack(int N) {
        arr = new int[N];
        blueTop = N;
    }

    public void redPush(int x) {
        if (redTop + 1 == blueTop) {
            throw new IllegalStateException("Stack overflow");
        }
        arr[++redTop] = x;
    }

    public void bluePush(int x) {
        if (blueTop - 1 == redTop) {
            throw new IllegalStateException("Stack overflow");
        }
        arr[--blueTop] = x;
    }

    public int redPop() {
        if (redTop == -1) {
            throw new IllegalStateException("Red Stack underflow");
        }
        return arr[redTop--];
    }

    public int bluePop() {
        if (blueTop == arr.length) {
            throw new IllegalStateException("Blue Stack underflow");
        }
        return arr[blueTop++];
    }
}
