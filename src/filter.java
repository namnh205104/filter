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
	 * @param height
	 * @param width
	 */
	public void copy(RGBColor[][] old_, RGBColor[][] new_, int height, int width) {
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				new_[i][j] = old_[i][j];
			}
		}
	}

	/**
	 * Take RGB color from the input old_pixel to the array of pixels
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
	 * Setup the RGB from the array of pixels to the output old_pixel
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
	 * Blur old_pixel
	 * 
	 * @param old_pixel
	 * @param height
	 * @param width
	 */
	public void makeBlur(RGBColor[][] old_pixel, int height, int width) {
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
				new_pic[i][j] = new_pixel;
				new_pic[i][j].blue = new_pixel.blue;
				new_pic[i][j].green = new_pixel.green;
			}
		}
		copy(new_pic, old_pixel, height, width);
	}

	/**
	 * Convert old_pixel to inverse
	 * 
	 * @param old_pixel
	 * @param height
	 * @param width
	 */
	public void makeInverse(RGBColor[][] old_pixel, int height, int width) {
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				old_pixel[i][j].red = 255 - old_pixel[i][j].red;
				old_pixel[i][j].blue = 255 - old_pixel[i][j].blue;
				old_pixel[i][j].green = 255 - old_pixel[i][j].green;
			}
		}
	}

	/**
	 * Convert old_pixel to grayscale
	 * 
	 * @param old_pixel
	 * @param height
	 * @param width
	 */
	public void makeGray(RGBColor[][] old_pixel, int height, int width) {
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
	 * Convert old_pixel to sepia
	 * 
	 * @param old_pixel
	 * @param height
	 * @param width
	 */
	public void makeSepia(RGBColor[][] old_pixel, int height, int width) {
		RGBColor new_ = new RGBColor();
		// int red,green,blue;
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
	 * Reflect old_pixel vertically
	 * 
	 * @param old_pixel
	 * @param height
	 * @param width
	 */
	public void makeVerticalReflect(RGBColor[][] old_pixel, int height, int width) {
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
	 * Reflect old_pixel horizontally
	 * 
	 * @param old_pixel
	 * @param height
	 * @param width
	 */
	public void makeHorizontalReflect(RGBColor[][] old_pixel, int height, int width) {
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
	 * @param height
	 * @param width
	 */
	public void makeEdges(RGBColor[][] old_pixel, int height, int width) {
		RGBColor[][] new_pic = new RGBColor[height][width];
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				new_pic[i][j] = new RGBColor();
			}
		}
		int gx_red, gx_green, gx_blue, gy_red, gy_green, gy_blue, after_red, after_green, after_blue;
		for (int i = 0; i < height; i++)
		{
			 for (int j = 0; j < width; j++)
			 {
				  if (i == 0 && j == 0)
				  {
						gx_red=old_pixel[i][j+1].red * 2 + old_pixel[i+1][j+1].red *1;
						gx_green=(old_pixel[i][j+1].green*2 + old_pixel[i+1][j+1].green*1);
						gx_blue=(old_pixel[i][j+1].blue*2+old_pixel[i+1][j+1].blue*1);
  
						gy_red=(old_pixel[i+1][j].red*2+old_pixel[i+1][j+1].red*1);
						gy_green=(old_pixel[i+1][j].green*2+old_pixel[i+1][j+1].green*1);
						gy_blue=(old_pixel[i+1][j].blue*2+old_pixel[i+1][j+1].blue*1);
				  }
				  else if(i == 0 && j == width - 1)
				  {
						gx_red=(old_pixel[i][j-1].red*-2 +old_pixel[i+1][j-1].red*-1);
						gx_green=(old_pixel[i][j-1].green*-2 +old_pixel[i+1][j-1].green*-1);
						gx_blue=( old_pixel[i][j-1].blue*-2 +old_pixel[i+1][j-1].blue*-1);
  
						gy_red=(old_pixel[i+1][j-1].red*1+old_pixel[i+1][j].red*2);
						gy_green=(old_pixel[i+1][j-1].green*1+old_pixel[i+1][j].green*2);
						gy_blue=( old_pixel[i+1][j-1].blue*1+old_pixel[i+1][j].blue*2);
				  }
				  else if(i == height - 1 && j == 0)
				  {
						gx_red=(old_pixel[i-1][j+1].red *1+old_pixel[i][j+1].red*2);
						gx_green=(old_pixel[i-1][j+1].green *1+old_pixel[i][j+1].green*2);
						gx_blue=(old_pixel[i-1][j+1].blue *1+old_pixel[i][j+1].blue*2);
  
						gy_red=(old_pixel[i-1][j].red*-2 + old_pixel[i-1][j+1].red *-1);
						gy_green=(old_pixel[i-1][j].green*-2 + old_pixel[i-1][j+1].green *-1);
						gy_blue=(old_pixel[i-1][j].blue*-2 + old_pixel[i-1][j+1].blue *-1);
				  }
				  else if(i == height - 1 && j == width - 1)
				  {
						gx_red=(old_pixel[i-1][j-1].red*-1 +old_pixel[i][j-1].red*-2);
						gx_green=(old_pixel[i-1][j-1].green*-1 + old_pixel[i][j-1].green*-2);
						gx_blue=(old_pixel[i-1][j-1].blue*-1 + old_pixel[i][j-1].blue*-2);
  
						gy_red=(old_pixel[i-1][j-1].red*-1 + old_pixel[i-1][j].red*-2);
						gy_green=(old_pixel[i-1][j-1].green*-1 + old_pixel[i-1][j].green*-2);
						gy_blue=(old_pixel[i-1][j-1].blue*-1 + old_pixel[i-1][j].blue*-2);
				  }
				  else if(i==0 &&j>0 && j<width - 1)
				  {
						gx_red=(old_pixel[i][j-1].red*-2 + old_pixel[i][j+1].red*2+old_pixel[i+1][j-1].red*-1+old_pixel[i+1][j+1].red*1);
						gx_green=(old_pixel[i][j-1].green*-2 + old_pixel[i][j+1].green*2+old_pixel[i+1][j-1].green*-1+old_pixel[i+1][j+1].green*1);
						gx_blue=(old_pixel[i][j-1].blue*-2 + old_pixel[i][j+1].blue*2+old_pixel[i+1][j-1].blue*-1+old_pixel[i+1][j+1].blue*1);
  
						gy_red=(old_pixel[i+1][j-1].red*1+old_pixel[i+1][j].red*2+old_pixel[i+1][j+1].red*1);
						gy_green=(old_pixel[i+1][j-1].green*1+old_pixel[i+1][j].green*2+old_pixel[i+1][j+1].green*1);
						gy_blue=(old_pixel[i+1][j-1].blue*1+old_pixel[i+1][j].blue*2+old_pixel[i+1][j+1].blue*1);
				  }
				  else if(i==height-1 && j>0 && j<width - 1)
				  {
						gx_red=(old_pixel[i-1][j-1].red*-1 + old_pixel[i-1][j+1].red *1+old_pixel[i][j-1].red*-2 + old_pixel[i][j+1].red*2);
						gx_green=(old_pixel[i-1][j-1].green*-1 + old_pixel[i-1][j+1].green *1+old_pixel[i][j-1].green*-2 + old_pixel[i][j+1].green*2);
						gx_blue=(old_pixel[i-1][j-1].blue*-1 + old_pixel[i-1][j+1].blue *1+old_pixel[i][j-1].blue*-2 + old_pixel[i][j+1].blue*2);
  
						gy_red=(old_pixel[i-1][j-1].red*-1 + old_pixel[i-1][j].red*-2 + old_pixel[i-1][j+1].red *-1);
						gy_green=(old_pixel[i-1][j-1].green*-1 + old_pixel[i-1][j].green*-2 + old_pixel[i-1][j+1].green *-1);
						gy_blue=(old_pixel[i-1][j-1].blue*-1 + old_pixel[i-1][j].blue*-2 + old_pixel[i-1][j+1].blue *-1);
				  }
				  else if(j==0 && i>0 && i<height - 1)
				  {
						gx_red=(old_pixel[i-1][j+1].red *1 + old_pixel[i][j+1].red*2 + old_pixel[i+1][j+1].red*1);
						gx_green=(old_pixel[i-1][j+1].green *1+ old_pixel[i][j+1].green*2+old_pixel[i+1][j+1].green*1);
						gx_blue=(old_pixel[i-1][j+1].blue *1 + old_pixel[i][j+1].blue*2 + old_pixel[i+1][j+1].blue*1);
  
						gy_red=(old_pixel[i-1][j].red*-2 + old_pixel[i-1][j+1].red *-1+old_pixel[i+1][j].red*2+old_pixel[i+1][j+1].red*1);
						gy_green=(old_pixel[i-1][j].green*-2 + old_pixel[i-1][j+1].green *-1+old_pixel[i+1][j].green*2+old_pixel[i+1][j+1].green*1);
						gy_blue=(old_pixel[i-1][j].blue*-2 + old_pixel[i-1][j+1].blue *-1+old_pixel[i+1][j].blue*2+old_pixel[i+1][j+1].blue*1);
				  }
				  else if(j==width-1 && i>0 && i<height - 1)
				  {
						gx_red=(old_pixel[i-1][j-1].red*-1 +old_pixel[i][j-1].red*-2 +old_pixel[i+1][j-1].red*-1);
  
						gx_green=(old_pixel[i-1][j-1].green*-1 +old_pixel[i][j-1].green*-2 +old_pixel[i+1][j-1].green*-1);
  
						gx_blue=(old_pixel[i-1][j-1].blue*-1 +old_pixel[i][j-1].blue*-2 +old_pixel[i+1][j-1].blue*-1);
  
						gy_red=(old_pixel[i-1][j-1].red*-1 + old_pixel[i-1][j].red*-2 + old_pixel[i+1][j-1].red*1+old_pixel[i+1][j].red*2);
  
						gy_green=(old_pixel[i-1][j-1].green*-1 + old_pixel[i-1][j].green*-2 + old_pixel[i+1][j-1].green*1+old_pixel[i+1][j].green*2);
  
						gy_blue=(old_pixel[i-1][j-1].blue*-1 + old_pixel[i-1][j].blue*-2 + old_pixel[i+1][j-1].blue*1+old_pixel[i+1][j].blue*2);
				  }
				  else
				  {
						gx_red=(old_pixel[i-1][j-1].red*-1 + old_pixel[i-1][j].red*0 + old_pixel[i-1][j+1].red *1+
											old_pixel[i][j-1].red*-2 + old_pixel[i][j].red*0 + old_pixel[i][j+1].red*2+
											old_pixel[i+1][j-1].red*-1+old_pixel[i+1][j].red*0+old_pixel[i+1][j+1].red*1);
  
						gx_green=(old_pixel[i-1][j-1].green*-1 + old_pixel[i-1][j].green*0 + old_pixel[i-1][j+1].green *1+
											old_pixel[i][j-1].green*-2 + old_pixel[i][j].green*0 + old_pixel[i][j+1].green*2+
											old_pixel[i+1][j-1].green*-1+old_pixel[i+1][j].green*0+old_pixel[i+1][j+1].green*1);
  
						gx_blue=(old_pixel[i-1][j-1].blue*-1 + old_pixel[i-1][j].blue*0 + old_pixel[i-1][j+1].blue *1+
											old_pixel[i][j-1].blue*-2 + old_pixel[i][j].blue*0 + old_pixel[i][j+1].blue*2+
											old_pixel[i+1][j-1].blue*-1+old_pixel[i+1][j].blue*0+old_pixel[i+1][j+1].blue*1);
  
  
						gy_red=(old_pixel[i-1][j-1].red*-1 + old_pixel[i-1][j].red*-2 + old_pixel[i-1][j+1].red *-1+
											old_pixel[i+1][j-1].red*1+old_pixel[i+1][j].red*2+old_pixel[i+1][j+1].red*1);
  
						gy_green=(old_pixel[i-1][j-1].green*-1 + old_pixel[i-1][j].green*-2 + old_pixel[i-1][j+1].green *-1+
											old_pixel[i+1][j-1].green*1+old_pixel[i+1][j].green*2+old_pixel[i+1][j+1].green*1);
  
						gy_blue=(old_pixel[i-1][j-1].blue*-1 + old_pixel[i-1][j].blue*-2 + old_pixel[i-1][j+1].blue *-1+
											old_pixel[i+1][j-1].blue*1+old_pixel[i+1][j].blue*2+old_pixel[i+1][j+1].blue*1);
				  }
				after_red = (int) Math.sqrt(gx_red * gx_red + gy_red * gy_red);
				after_green = (int) Math.sqrt(gx_green * gx_green + gy_green * gy_green);
				after_blue = (int) Math.sqrt(gx_blue * gx_blue + gy_blue * gy_blue);
				after_red = (after_red > 255) ? 255 : after_red;
				after_blue = (after_blue > 255) ? 255 : after_blue;
				after_green = (after_green > 255) ? 255 : after_green;
				new_pic[i][j].red = after_red;
				new_pic[i][j].green = after_green;
				new_pic[i][j].blue = after_blue;
			}
		}
		copy(new_pic, old_pixel, height, width);
	}

	/**
	 * Draw the old old_pixel and the filtered old_pixel
	 * 
	 * @return
	 */
	public ImageResource helper() {
		DirectoryResource dr = new DirectoryResource();
		ImageResource outImage = null;
		for (File f : dr.selectedFiles()) {
			ImageResource inImage = new ImageResource(f);
			int height = inImage.getHeight(), width = inImage.getWidth();
			RGBColor[][] old_pixel = new RGBColor[height][width];
			get_RGB_value(inImage, old_pixel);
			makeEdges(old_pixel, height, width);
			outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());
			// String oldName=inImage.getFileName();
			set_RGB_value(outImage, old_pixel);
			// outImage.setFileName("edges_"+oldName);
			inImage.draw();
			// outImage.save();
			outImage.draw();
		}
		return outImage;
	}

	public static void main(String[] args) {
		// ImageResource ir=new ImageResource();
		filter bigFile = new filter();
		// bigFile.select_and_convert();
		bigFile.helper();
		// bigFile.makeInverse(ir).draw();
	}
}
