package main;

import java.awt.Color;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.util.Callback;

public class Navigator {
	public Pagination pagination = new Pagination();
	private double HEIGHT = 800;
	private double WIDTH = 1280;
	
	public Navigator(Pagination pagination) {
		
//		pagination.setPageCount(pageCount);
		pagination.getStyleClass().add(Pagination.STYLE_CLASS_BULLET);
	
//		pagination.setTranslateX(translateX - pagination.getWidth() - 150);
//		pagination.setTranslateY(translateY - pagination.getHeight()/2 + 250);
		
		pagination.setPageFactory(new Callback<Integer, Node>() {
			@Override
			public Node call(Integer pageIndex) {
//				if(pageIndex == 0) {
//					ThreeDController.planet.setMaterial("/resources/earth/earth-d.jpg");
//					ThreeDController.planet.setRadius(100);
//					ThreeDController.planet.setPosition(WIDTH/2 - 50 + 50, HEIGHT/2 - 50 - 100);
//				}
//				else if(pageIndex == 1) {
//					ThreeDController.planet.setMaterial("/resources/earth/mars.jpg");
//					ThreeDController.planet.setRadius(80);
//					ThreeDController.planet.setPosition(WIDTH/2 - 40 + 50, HEIGHT/2 - 40 - 100);
//				}
				setPlanetMaterial(pageIndex);
				return createPage(pageIndex);
			}
		});
	}
	
	public Pagination getPagination() {
		return pagination;
	}
	
	private VBox createPage(int pageIndex) {
		Label[] planetName = new Label[10];
		Label[] planetDetail = new Label[10];
		
		for(int i = 0; i < 10; i++) {
			planetName[i] = new Label();
			planetDetail[i] = new Label();
			planetName[i].setFont(new Font("微軟正黑體", 40));
			planetDetail[i].setFont(new Font("微軟正黑體", 24));
			planetName[i].setTextFill(javafx.scene.paint.Color.WHITE);
			planetDetail[i].setTextFill(javafx.scene.paint.Color.WHITE);
		}
		
		planetName[0].setText("太陽(Sun)");
		planetName[1].setText("水星(Mercury)");
		planetName[2].setText("金星(Venus)");
		planetName[3].setText("地球(Earth)");
		planetName[4].setText("火星(Mars)");
		planetName[5].setText("木星(Jupiter)");
		planetName[6].setText("土星(Saturn)");
		planetName[7].setText("天王星(Uranus)");
		planetName[8].setText("海王星(Neptune)");
		planetName[9].setText("月亮(Moon)");
		
		
		planetDetail[0].setText("太陽是太陽系唯一的恆星，可以說是太陽系的主序星。");
		planetDetail[1].setText("水星是九大行星中最靠近太陽的行星，岩質相當古老。");
		planetDetail[2].setText("金星是離太陽第二近的行星，蘇美人認為金星主宰著帝王的命運。");
		planetDetail[3].setText("地球是離太陽第三近的行星，大氣中的水氣與微量的\n"
				+ "二氧化碳造成的「溫室效應」是維持地表溫度重要的作用，\n"
				+ "是太陽系中唯一存有液態水的行星，這也是為什麼我們能在地球上生存。");
		planetDetail[4].setText("火星是太陽外圍的第四顆行星，\n火星在希臘神話中代表戰神的兒子。");
		planetDetail[5].setText("木星是太陽系最大的行星，常是夜空中最亮的星體。");
		planetDetail[6].setText("土星是太陽系第二大的行星，最有名的就是它的土星環。");
		planetDetail[7].setText("天王星以希臘神話中的天空之神烏拉諾斯（天神宙斯的祖父）來命名，\n"
				+ "天王星的字轉軸是幾乎平行於黃道面的，可以說它是躺著自轉的。");
		planetDetail[8].setText("海王星是太陽系的第四大行星，冥王星的特殊公轉軌道有時會插隊\n"
				+ "進入海王星和天王星之間，以至於海王星成為太陽系最外圍的行星。");
		planetDetail[9].setText("月球是地球唯一的天然衛星，位置接近於地球，亮度僅次於太陽，\n"
				+ "所以在占星學中佔有一席之地，地球和月亮的引力也造成地球的潮汐現象。");

		VBox page = new VBox(planetName[pageIndex], planetDetail[pageIndex]);
		page.maxWidth(200);
        return page;
	}
	
	private void setPlanetMaterial(int pageIndex) {
		String[] planetMaterialURL = new String[10];
		planetMaterialURL[0] = new String("/resources/planets/sun.jpg");
		planetMaterialURL[1] = new String("/resources/planets/mercury.jpg");
		planetMaterialURL[2] = new String("/resources/planets/venus.jpg");
		planetMaterialURL[3] = new String("/resources/planets/earth.jpg");
		planetMaterialURL[4] = new String("/resources/planets/mars.jpg");
		planetMaterialURL[5] = new String("/resources/planets/jupiter.jpg");
		planetMaterialURL[6] = new String("/resources/planets/saturn.jpg");
		planetMaterialURL[7] = new String("/resources/planets/uranus.jpg");
		planetMaterialURL[8] = new String("/resources/planets/neptune.jpg");
		planetMaterialURL[9] = new String("/resources/planets/moon.jpg");
		
		double[] planetRadius = new double[10];
		planetRadius[0] = 109;
		planetRadius[1] = 0.3829;
		planetRadius[2] = 0.9499;
		planetRadius[3] = 1;
		planetRadius[4] = 0.532;
		planetRadius[5] = 11.209;
		planetRadius[6] = 9.4492;
		planetRadius[7] = 4.007;
		planetRadius[8] = 3.829;
		planetRadius[9] = 0.273;
//		planetRadius[pageIndex]
		ThreeDController.planet.setMaterial(planetMaterialURL[pageIndex]);
		ThreeDController.planet.setRadius(100);
		ThreeDController.planet.setPosition(WIDTH/2 - 50 + 50, HEIGHT/2 - 50);
	}
}
