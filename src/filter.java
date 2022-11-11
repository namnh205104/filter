import edu.duke.DirectoryResource;
import edu.duke.ImageResource;
import edu.duke.Pixel;
import java.io.File;

public class filter {
	/// Make class for RGB colors:
	public static class RGBColor {
		int red, green, blue;
	}

	/**
	 * Copy RGB pixels from old_ to new_
	 * 
	 * @param old_
	 * @param new_
	 */
	public void copy(RGBColor[][] old_, RGBColor[][] new_) {
		int height = old_.length, width = old_[0].length;
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				new_[i][j] = old_[i][j];
			}
		}
	}

	/**
	 * Take RGB color from the input image to the array of pixels
	 * 
	 * @param inImage
	 * @param old_pixel
	 */
	public void get_RGB_value(ImageResource inImage, RGBColor[][] old_pixel) {
		int height = inImage.getHeight(), width = inImage.getWidth();
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				old_pixel[i][j] = new RGBColor();
			}
		}
		for (Pixel p : inImage.pixels()) {
			old_pixel[p.getY()][p.getX()].red = p.getRed();
			old_pixel[p.getY()][p.getX()].green = p.getGreen();
			old_pixel[p.getY()][p.getX()].blue = p.getBlue();
		}
	}

	/**
	 * Setup the RGB from the array of pixels to the output image
	 * 
	 * @param outImage
	 * @param old_pixel
	 */
	public void set_RGB_value(ImageResource outImage, RGBColor[][] old_pixel) {
		for (Pixel p : outImage.pixels()) {
			p.setRed(old_pixel[p.getY()][p.getX()].red);
			p.setGreen(old_pixel[p.getY()][p.getX()].green);
			p.setBlue(old_pixel[p.getY()][p.getX()].blue);
		}
	}

	/**
	 * Blur image
	 * 
	 * @param old_pixel
	 * @param height
	 * @param width
	 */
	public void makeBlur(RGBColor[][] old_pixel) {
		int height = old_pixel.length, width = old_pixel[0].length;
		RGBColor[][] new_pic = new RGBColor[height][width];
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				new_pic[i][j] = new RGBColor();
			}
		}
		RGBColor new_pixel = new RGBColor();
		for (int j = 0; j < width; j++) {
			for (int i = 0; i < height; i++) {
				if (i == 0 && j == 0) {
					new_pixel.red = (old_pixel[i][j].red + old_pixel[i + 1][j].red + old_pixel[i][j + 1].red
							+ old_pixel[i + 1][j + 1].red) / 4;
					new_pixel.green = (old_pixel[i][j].green + old_pixel[i + 1][j].green + old_pixel[i][j + 1].green
							+ old_pixel[i + 1][j + 1].green) / 4;
					new_pixel.blue = (old_pixel[i][j].blue + old_pixel[i + 1][j].blue + old_pixel[i][j + 1].blue
							+ old_pixel[i + 1][j + 1].blue) / 4;
				} else if (i == 0 && j == width - 1) {
					new_pixel.red = (old_pixel[i][j].red + old_pixel[i + 1][j].red + old_pixel[i][j - 1].red
							+ old_pixel[i + 1][j - 1].red) / 4;
					new_pixel.green = (old_pixel[i][j].green + old_pixel[i + 1][j].green + old_pixel[i][j - 1].green
							+ old_pixel[i + 1][j - 1].green) / 4;
					new_pixel.blue = (old_pixel[i][j].blue + old_pixel[i + 1][j].blue + old_pixel[i][j - 1].blue
							+ old_pixel[i + 1][j - 1].blue) / 4;
				} else if (i == height - 1 && j == 0) {
					new_pixel.red = (old_pixel[i][j].red + old_pixel[i - 1][j].red + old_pixel[i][j + 1].red
							+ old_pixel[i - 1][j + 1].red) / 4;
					new_pixel.green = (old_pixel[i][j].green + old_pixel[i - 1][j].green + old_pixel[i][j + 1].green
							+ old_pixel[i - 1][j + 1].green) / 4;
					new_pixel.blue = (old_pixel[i][j].blue + old_pixel[i - 1][j].blue + old_pixel[i][j + 1].blue
							+ old_pixel[i - 1][j + 1].blue) / 4;
				} else if (i == height - 1 && j == width - 1) {
					new_pixel.red = (old_pixel[i - 1][j - 1].red + old_pixel[i - 1][j].red + old_pixel[i][j - 1].red
							+ old_pixel[i][j].red) / 4;
					new_pixel.green = (old_pixel[i - 1][j - 1].green + old_pixel[i - 1][j].green + old_pixel[i][j - 1].green
							+ old_pixel[i][j].green) / 4;
					new_pixel.blue = (old_pixel[i - 1][j - 1].blue + old_pixel[i - 1][j].blue + old_pixel[i][j - 1].blue
							+ old_pixel[i][j].blue) / 4;
				} else if (i == 0 && j < width - 1) {
					new_pixel.red = (old_pixel[i][j - 1].red + old_pixel[i][j].red + old_pixel[i][j + 1].red
							+ old_pixel[i + 1][j - 1].red + old_pixel[i + 1][j].red + old_pixel[i + 1][j + 1].red) / 6;
					new_pixel.green = (old_pixel[i][j].green + old_pixel[i][j - 1].green + old_pixel[i][j + 1].green
							+ old_pixel[i + 1][j - 1].green + old_pixel[i + 1][j].green + old_pixel[i + 1][j + 1].green) / 6;
					new_pixel.blue = (old_pixel[i][j].blue + old_pixel[i][j - 1].blue + old_pixel[i][j + 1].blue
							+ old_pixel[i + 1][j - 1].blue + old_pixel[i + 1][j].blue + old_pixel[i + 1][j + 1].blue) / 6;
				} else if (i == height - 1 && j > 0 && j < width - 1) {
					new_pixel.red = (old_pixel[i][j].red + old_pixel[i][j - 1].red + old_pixel[i][j + 1].red
							+ old_pixel[i - 1][j - 1].red + old_pixel[i - 1][j].red + old_pixel[i - 1][j + 1].red) / 6;
					new_pixel.green = (old_pixel[i][j].green + old_pixel[i][j - 1].green + old_pixel[i][j + 1].green
							+ old_pixel[i - 1][j - 1].green + old_pixel[i - 1][j].green + old_pixel[i - 1][j + 1].green) / 6;
					new_pixel.blue = (old_pixel[i][j].blue + old_pixel[i][j - 1].blue + old_pixel[i][j + 1].blue
							+ old_pixel[i - 1][j - 1].blue + old_pixel[i - 1][j].blue + old_pixel[i - 1][j + 1].blue) / 6;
				} else if (j == 0 && i < height - 1) {
					new_pixel.red = (old_pixel[i - 1][j].red + old_pixel[i][j].red + old_pixel[i + 1][j].red
							+ old_pixel[i - 1][j + 1].red + old_pixel[i][j + 1].red + old_pixel[i + 1][j + 1].red) / 6;
					new_pixel.green = (old_pixel[i - 1][j].green + old_pixel[i][j].green + old_pixel[i + 1][j].green
							+ old_pixel[i - 1][j + 1].green + old_pixel[i][j + 1].green + old_pixel[i + 1][j + 1].green) / 6;
					new_pixel.blue = (old_pixel[i - 1][j].blue + old_pixel[i][j].blue + old_pixel[i + 1][j].blue
							+ old_pixel[i - 1][j + 1].blue + old_pixel[i][j + 1].blue + old_pixel[i + 1][j + 1].blue) / 6;
				} else if (j == width - 1 && i > 0 && i < height - 1) {
					new_pixel.red = (old_pixel[i - 1][j].red + old_pixel[i][j].red + old_pixel[i + 1][j].red
							+ old_pixel[i - 1][j - 1].red + old_pixel[i][j - 1].red + old_pixel[i + 1][j - 1].red) / 6;
					new_pixel.green = (old_pixel[i - 1][j].green + old_pixel[i][j].green + old_pixel[i + 1][j].green
							+ old_pixel[i - 1][j - 1].green + old_pixel[i][j - 1].green + old_pixel[i + 1][j - 1].green) / 6;
					new_pixel.blue = (old_pixel[i - 1][j].blue + old_pixel[i][j].blue + old_pixel[i + 1][j].blue
							+ old_pixel[i - 1][j - 1].blue + old_pixel[i][j - 1].blue + old_pixel[i + 1][j - 1].blue) / 6;
				} else {
					new_pixel.red = (old_pixel[i - 1][j - 1].red + old_pixel[i - 1][j].red + old_pixel[i - 1][j + 1].red
							+ old_pixel[i][j - 1].red + old_pixel[i][j].red + old_pixel[i][j + 1].red
							+ old_pixel[i + 1][j - 1].red + old_pixel[i + 1][j].red + old_pixel[i + 1][j + 1].red) / 9;
					new_pixel.green = (old_pixel[i - 1][j - 1].green + old_pixel[i - 1][j].green
							+ old_pixel[i - 1][j + 1].green + old_pixel[i][j - 1].green + old_pixel[i][j].green
							+ old_pixel[i][j + 1].green + old_pixel[i + 1][j - 1].green + old_pixel[i + 1][j].green
							+ old_pixel[i + 1][j + 1].green) / 9;
					new_pixel.blue = (old_pixel[i - 1][j - 1].blue + old_pixel[i - 1][j].blue + old_pixel[i - 1][j + 1].blue
							+ old_pixel[i][j - 1].blue + old_pixel[i][j].blue + old_pixel[i][j + 1].blue
							+ old_pixel[i + 1][j - 1].blue + old_pixel[i + 1][j].blue + old_pixel[i + 1][j + 1].blue) / 9;
				}
				new_pic[i][j].red = new_pixel.red;
				new_pic[i][j].blue = new_pixel.blue;
				new_pic[i][j].green = new_pixel.green;
			}
		}
		copy(new_pic, old_pixel);
	}

	public void makeBlur(RGBColor[][] old_pixel, int blurTime) {
		for (int i = 0; i < blurTime; i++)
			makeBlur(old_pixel);
	}

	/**
	 * Convert image to inverse
	 * 
	 * @param old_pixel
	 */
	public void makeInverse(RGBColor[][] old_pixel) {
		int height = old_pixel.length, width = old_pixel[0].length;
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				old_pixel[i][j].red = 255 - old_pixel[i][j].red;
				old_pixel[i][j].blue = 255 - old_pixel[i][j].blue;
				old_pixel[i][j].green = 255 - old_pixel[i][j].green;
			}
		}
	}

	/**
	 * Convert image to grayscale
	 * 
	 * @param old_pixel
	 */
	public void makeGray(RGBColor[][] old_pixel) {
		int height = old_pixel.length, width = old_pixel[0].length;
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				int average = (old_pixel[i][j].blue + old_pixel[i][j].green + old_pixel[i][j].red) / 3;
				old_pixel[i][j].red = average;
				old_pixel[i][j].green = average;
				old_pixel[i][j].blue = average;
			}
		}
	}

	/**
	 * Convert image to sepia
	 * 
	 * @param old_pixel
	 */
	public void makeSepia(RGBColor[][] old_pixel) {
		int height = old_pixel.length, width = old_pixel[0].length;
		RGBColor new_ = new RGBColor();
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				new_.red = (int) (old_pixel[i][j].red * .393 + old_pixel[i][j].green * .769 + old_pixel[i][j].blue + .189);
				new_.green = (int) (old_pixel[i][j].red * .349 + old_pixel[i][j].green * .686 + old_pixel[i][j].blue
						+ .168);
				new_.blue = (int) (old_pixel[i][j].red * .272 + old_pixel[i][j].green * .534 + old_pixel[i][j].blue + .131);
				new_.blue = (new_.blue > 255) ? 255 : new_.blue;
				new_.green = (new_.green > 255) ? 255 : new_.green;
				new_.red = (new_.red > 255) ? 255 : new_.red;
				old_pixel[i][j].red = new_.red;
				old_pixel[i][j].green = new_.green;
				old_pixel[i][j].blue = new_.blue;
			}
		}
	}

	/**
	 * Reflect image vertically
	 * 
	 * @param old_pixel
	 */
	public void makeVerticalReflect(RGBColor[][] old_pixel) {
		int height = old_pixel.length, width = old_pixel[0].length;
		RGBColor tmp;
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width / 2; j++) {
				tmp = old_pixel[i][j];
				old_pixel[i][j] = old_pixel[i][width - 1 - j];
				old_pixel[i][width - 1 - j] = tmp;
			}
		}
	}

	/**
	 * Reflect image horizontally
	 * 
	 * @param old_pixel
	 */
	public void makeHorizontalReflect(RGBColor[][] old_pixel) {
		int height = old_pixel.length, width = old_pixel[0].length;
		RGBColor tmp;
		for (int i = 0; i < height / 2; i++) {
			for (int j = 0; j < width; j++) {
				tmp = old_pixel[i][j];
				old_pixel[i][j] = old_pixel[height - 1 - i][j];
				old_pixel[height - 1 - i][j] = tmp;
			}
		}
	}

	/**
	 * Detect edges
	 * 
	 * @param old_pixel
	 */
	public void makeEdges(RGBColor[][] old_pixel) {
		int height = old_pixel.length, width = old_pixel[0].length;
		RGBColor[][] new_pic = new RGBColor[height][width];
		RGBColor gx = new RGBColor(), gy = new RGBColor(), after = new RGBColor();
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				new_pic[i][j] = new RGBColor();
			}
		}
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				if (i == 0 && j == 0) {
					gx.red = old_pixel[i][j + 1].red * 2 + old_pixel[i + 1][j + 1].red;
					gx.green = old_pixel[i][j + 1].green * 2 + old_pixel[i + 1][j + 1].green;
					gx.blue = old_pixel[i][j + 1].blue * 2 + old_pixel[i + 1][j + 1].blue;
					gy.red = old_pixel[i + 1][j].red * 2 + old_pixel[i + 1][j + 1].red;
					gy.green = old_pixel[i + 1][j].green * 2 + old_pixel[i + 1][j + 1].green;
					gy.blue = old_pixel[i + 1][j].blue * 2 + old_pixel[i + 1][j + 1].blue;
				} else if (i == 0 && j == width - 1) {
					gx.red = old_pixel[i][j - 1].red * -2 + old_pixel[i + 1][j - 1].red * -1;
					gx.green = old_pixel[i][j - 1].green * -2 + old_pixel[i + 1][j - 1].green * -1;
					gx.blue = old_pixel[i][j - 1].blue * -2 + old_pixel[i + 1][j - 1].blue * -1;
					gy.red = old_pixel[i + 1][j - 1].red + old_pixel[i + 1][j].red * 2;
					gy.green = old_pixel[i + 1][j - 1].green + old_pixel[i + 1][j].green * 2;
					gy.blue = old_pixel[i + 1][j - 1].blue + old_pixel[i + 1][j].blue * 2;
				} else if (i == height - 1 && j == 0) {
					gx.red = old_pixel[i - 1][j + 1].red + old_pixel[i][j + 1].red * 2;
					gx.green = old_pixel[i - 1][j + 1].green + old_pixel[i][j + 1].green * 2;
					gx.blue = old_pixel[i - 1][j + 1].blue + old_pixel[i][j + 1].blue * 2;
					gy.red = old_pixel[i - 1][j].red * -2 + old_pixel[i - 1][j + 1].red * -1;
					gy.green = old_pixel[i - 1][j].green * -2 + old_pixel[i - 1][j + 1].green * -1;
					gy.blue = old_pixel[i - 1][j].blue * -2 + old_pixel[i - 1][j + 1].blue * -1;
				} else if (i == height - 1 && j == width - 1) {
					gx.red = old_pixel[i - 1][j - 1].red * -1 + old_pixel[i][j - 1].red * -2;
					gx.green = old_pixel[i - 1][j - 1].green * -1 + old_pixel[i][j - 1].green * -2;
					gx.blue = old_pixel[i - 1][j - 1].blue * -1 + old_pixel[i][j - 1].blue * -2;
					gy.red = old_pixel[i - 1][j - 1].red * -1 + old_pixel[i - 1][j].red * -2;
					gy.green = old_pixel[i - 1][j - 1].green * -1 + old_pixel[i - 1][j].green * -2;
					gy.blue = old_pixel[i - 1][j - 1].blue * -1 + old_pixel[i - 1][j].blue * -2;
				} else if (i == 0 && j > 0 && j < width - 1) {
					gx.red = old_pixel[i][j - 1].red * -2 + old_pixel[i][j + 1].red * 2 + old_pixel[i + 1][j - 1].red * -1
							+ old_pixel[i + 1][j + 1].red;
					gx.green = old_pixel[i][j - 1].green * -2 + old_pixel[i][j + 1].green * 2
							+ old_pixel[i + 1][j - 1].green * -1 + old_pixel[i + 1][j + 1].green;
					gx.blue = old_pixel[i][j - 1].blue * -2 + old_pixel[i][j + 1].blue * 2
							+ old_pixel[i + 1][j - 1].blue * -1 + old_pixel[i + 1][j + 1].blue;
					gy.red = old_pixel[i + 1][j - 1].red + old_pixel[i + 1][j].red * 2
							+ old_pixel[i + 1][j + 1].red;
					gy.green = old_pixel[i + 1][j - 1].green + old_pixel[i + 1][j].green * 2
							+ old_pixel[i + 1][j + 1].green;
					gy.blue = old_pixel[i + 1][j - 1].blue + old_pixel[i + 1][j].blue * 2
							+ old_pixel[i + 1][j + 1].blue;
				} else if (i == height - 1 && j > 0 && j < width - 1) {
					gx.red = old_pixel[i - 1][j - 1].red * -1 + old_pixel[i - 1][j + 1].red
							+ old_pixel[i][j - 1].red * -2 + old_pixel[i][j + 1].red * 2;
					gx.green = old_pixel[i - 1][j - 1].green * -1 + old_pixel[i - 1][j + 1].green
							+ old_pixel[i][j - 1].green * -2 + old_pixel[i][j + 1].green * 2;
					gx.blue = old_pixel[i - 1][j - 1].blue * -1 + old_pixel[i - 1][j + 1].blue
							+ old_pixel[i][j - 1].blue * -2 + old_pixel[i][j + 1].blue * 2;
					gy.red = old_pixel[i - 1][j - 1].red * -1 + old_pixel[i - 1][j].red * -2
							+ old_pixel[i - 1][j + 1].red * -1;
					gy.green = old_pixel[i - 1][j - 1].green * -1 + old_pixel[i - 1][j].green * -2
							+ old_pixel[i - 1][j + 1].green * -1;
					gy.blue = old_pixel[i - 1][j - 1].blue * -1 + old_pixel[i - 1][j].blue * -2
							+ old_pixel[i - 1][j + 1].blue * -1;
				} else if (j == 0 && i > 0 && i < height - 1) {
					gx.red = old_pixel[i - 1][j + 1].red + old_pixel[i][j + 1].red * 2
							+ old_pixel[i + 1][j + 1].red;
					gx.green = old_pixel[i - 1][j + 1].green + old_pixel[i][j + 1].green * 2
							+ old_pixel[i + 1][j + 1].green;
					gx.blue = old_pixel[i - 1][j + 1].blue + old_pixel[i][j + 1].blue * 2
							+ old_pixel[i + 1][j + 1].blue;

					gy.red = old_pixel[i - 1][j].red * -2 + old_pixel[i - 1][j + 1].red * -1 + old_pixel[i + 1][j].red * 2
							+ old_pixel[i + 1][j + 1].red;
					gy.green = old_pixel[i - 1][j].green * -2 + old_pixel[i - 1][j + 1].green * -1
							+ old_pixel[i + 1][j].green * 2 + old_pixel[i + 1][j + 1].green;
					gy.blue = old_pixel[i - 1][j].blue * -2 + old_pixel[i - 1][j + 1].blue * -1
							+ old_pixel[i + 1][j].blue * 2 + old_pixel[i + 1][j + 1].blue;
				} else if (j == width - 1 && i > 0 && i < height - 1) {
					gx.red = old_pixel[i - 1][j - 1].red * -1 + old_pixel[i][j - 1].red * -2
							+ old_pixel[i + 1][j - 1].red * -1;

					gx.green = old_pixel[i - 1][j - 1].green * -1 + old_pixel[i][j - 1].green * -2
							+ old_pixel[i + 1][j - 1].green * -1;

					gx.blue = old_pixel[i - 1][j - 1].blue * -1 + old_pixel[i][j - 1].blue * -2
							+ old_pixel[i + 1][j - 1].blue * -1;

					gy.red = old_pixel[i - 1][j - 1].red * -1 + old_pixel[i - 1][j].red * -2
							+ old_pixel[i + 1][j - 1].red + old_pixel[i + 1][j].red * 2;
					gy.green = old_pixel[i - 1][j - 1].green * -1 + old_pixel[i - 1][j].green * -2
							+ old_pixel[i + 1][j - 1].green + old_pixel[i + 1][j].green * 2;
					gy.blue = old_pixel[i - 1][j - 1].blue * -1 + old_pixel[i - 1][j].blue * -2
							+ old_pixel[i + 1][j - 1].blue + old_pixel[i + 1][j].blue * 2;
				} else {
					gx.red = old_pixel[i - 1][j - 1].red * -1 + old_pixel[i - 1][j + 1].red + old_pixel[i][j - 1].red * -2
							+ old_pixel[i][j + 1].red * 2 + old_pixel[i + 1][j - 1].red * -1 + old_pixel[i + 1][j + 1].red;

					gx.green = old_pixel[i - 1][j - 1].green * -1 + old_pixel[i - 1][j + 1].green
							+ old_pixel[i][j - 1].green * -2 + old_pixel[i][j + 1].green * 2
							+ old_pixel[i + 1][j - 1].green * -1 + old_pixel[i + 1][j + 1].green;

					gx.blue = old_pixel[i - 1][j - 1].blue * -1 + old_pixel[i - 1][j + 1].blue
							+ old_pixel[i][j - 1].blue * -2 + old_pixel[i][j + 1].blue * 2 + old_pixel[i + 1][j - 1].blue * -1
							+ old_pixel[i + 1][j + 1].blue;

					gy.red = old_pixel[i - 1][j - 1].red * -1 + old_pixel[i - 1][j].red * -2
							+ old_pixel[i - 1][j + 1].red * -1 + old_pixel[i + 1][j - 1].red + old_pixel[i + 1][j].red * 2
							+ old_pixel[i + 1][j + 1].red;

					gy.green = old_pixel[i - 1][j - 1].green * -1 + old_pixel[i - 1][j].green * -2
							+ old_pixel[i - 1][j + 1].green * -1 + old_pixel[i + 1][j - 1].green
							+ old_pixel[i + 1][j].green * 2 + old_pixel[i + 1][j + 1].green;

					gy.blue = old_pixel[i - 1][j - 1].blue * -1 + old_pixel[i - 1][j].blue * -2
							+ old_pixel[i - 1][j + 1].blue * -1 + old_pixel[i + 1][j - 1].blue + old_pixel[i + 1][j].blue * 2
							+ old_pixel[i + 1][j + 1].blue;
				}
				after.red = (int) Math.sqrt(gx.red * gx.red + gy.red * gy.red);
				after.green = (int) Math.sqrt(gx.green * gx.green + gy.green * gy.green);
				after.blue = (int) Math.sqrt(gx.blue * gx.blue + gy.blue * gy.blue);
				after.red = (after.red > 255) ? 255 : after.red;
				after.blue = (after.blue > 255) ? 255 : after.blue;
				after.green = (after.green > 255) ? 255 : after.green;
				new_pic[i][j].red = after.red;
				new_pic[i][j].green = after.green;
				new_pic[i][j].blue = after.blue;
			}
		}
		copy(new_pic, old_pixel);
	}

	/** Draw the old old_pixel and the filtered old_pixel */
	public ImageResource helper() {
		DirectoryResource dr = new DirectoryResource();
		ImageResource outImage = null;
		for (File f : dr.selectedFiles()) {
			ImageResource inImage = new ImageResource(f);
			int height = inImage.getHeight(), width = inImage.getWidth();
			RGBColor[][] old_pixel = new RGBColor[height][width];
			get_RGB_value(inImage, old_pixel);
			makeBlur(old_pixel, 90);
			outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());
			set_RGB_value(outImage, old_pixel);
			inImage.draw();
			outImage.draw();
		}
		return outImage;
	}

	public static void main(String[] args) {
		filter bigFile = new filter();
		bigFile.helper();
	}
}
