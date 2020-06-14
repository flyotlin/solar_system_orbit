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
			planetName[i].setFont(new Font("�L�n������", 40));
			planetDetail[i].setFont(new Font("�L�n������", 24));
			planetName[i].setTextFill(javafx.scene.paint.Color.WHITE);
			planetDetail[i].setTextFill(javafx.scene.paint.Color.WHITE);
		}
		
		planetName[0].setText("�Ӷ�(Sun)");
		planetName[1].setText("���P(Mercury)");
		planetName[2].setText("���P(Venus)");
		planetName[3].setText("�a�y(Earth)");
		planetName[4].setText("���P(Mars)");
		planetName[5].setText("��P(Jupiter)");
		planetName[6].setText("�g�P(Saturn)");
		planetName[7].setText("�Ѥ��P(Uranus)");
		planetName[8].setText("�����P(Neptune)");
		planetName[9].setText("��G(Moon)");
		
		
		planetDetail[0].setText("�Ӷ��O�Ӷ��t�ߤ@����P�A�i�H���O�Ӷ��t���D�ǬP�C");
		planetDetail[1].setText("���P�O�E�j��P���̾a��Ӷ�����P�A����۷�j�ѡC");
		planetDetail[2].setText("���P�O���Ӷ��ĤG�񪺦�P�AĬ���H�{�����P�D�_�۫Ҥ����R�B�C");
		planetDetail[3].setText("�a�y�O���Ӷ��ĤT�񪺦�P�A�j�𤤪�����P�L�q��\n"
				+ "�G��ƺҳy�����u�ūǮ����v�O�����a��ū׭��n���@�ΡA\n"
				+ "�O�Ӷ��t���ߤ@�s���G�A������P�A�o�]�O������ڭ̯�b�a�y�W�ͦs�C");
		planetDetail[4].setText("���P�O�Ӷ��~�򪺲ĥ|����P�A\n���P�b��þ���ܤ��N��ԯ�����l�C");
		planetDetail[5].setText("��P�O�Ӷ��t�̤j����P�A�`�O�]�Ť��̫G���P��C");
		planetDetail[6].setText("�g�P�O�Ӷ��t�ĤG�j����P�A�̦��W���N�O�����g�P���C");
		planetDetail[7].setText("�Ѥ��P�H��þ���ܤ����ѪŤ����Q�Կմ��]�ѯ��z���������^�өR�W�A\n"
				+ "�Ѥ��P���r��b�O�X�G�������D�����A�i�H�����O���ۦ��઺�C");
		planetDetail[8].setText("�����P�O�Ӷ��t���ĥ|�j��P�A�ߤ��P���S����y�D���ɷ|����\n"
				+ "�i�J�����P�M�Ѥ��P�����A�H�ܩ�����P�����Ӷ��t�̥~�򪺦�P�C");
		planetDetail[9].setText("��y�O�a�y�ߤ@���ѵM�ìP�A��m�����a�y�A�G�׶Ȧ���Ӷ��A\n"
				+ "�ҥH�b�e�P�Ǥ������@�u���a�A�a�y�M��G���ޤO�]�y���a�y������{�H�C");

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
