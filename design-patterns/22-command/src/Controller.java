public class Controller {
    protected int x = 0;
    protected int y = 0;
    protected final int MIN_X = 0;
    protected final int MIN_Y = 0;
    protected final int MAX_X = 7;
    protected final int MAX_Y = 7;
    private final Pixel[][] pixels;

    public Controller() {
        pixels = new Pixel[8][8];
        for (int i = 0; i < pixels.length; i++) {
            for (int j = 0; j < pixels[i].length; j++) {
                pixels[i][j] = new Pixel();
            }
        }
    }

    public void togglePixel(int x, int y) {
        pixels[x][y].toggle();
    }

    public void generateCode() {
        StringBuilder sb = new StringBuilder();
        sb.append("int[][] pixelArt = {\n");
        for (int j = 0; j < pixels[0].length; j++) {
            sb.append("    {");
            for (Pixel[] pixel : pixels) {
                sb.append(pixel[j].isOn() ? "1, " : "0, ");
            }
            sb.delete(sb.length() - 2, sb.length()); // Remove the comma from the last column
            sb.append("},\n");
        }
        sb.delete(sb.length() - 2, sb.length() - 1); // Remove the comma from the last row
        sb.append("};");
        System.out.println(sb);
    }

    public Pixel[][] getPixels() {
        return pixels;
    }
}
