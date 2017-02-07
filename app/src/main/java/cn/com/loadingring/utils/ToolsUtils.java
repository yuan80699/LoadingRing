package cn.com.loadingring.utils;

import android.graphics.Point;

import java.util.LinkedList;
import java.util.List;

public class ToolsUtils {
	/**
	 * ����Բ�ϵĸ���
	 * @param centerX Բ��X����
	 * @param centerY Բ��Y����
	 * @param radius �뾶
	 * @return
	 */
	public static List<Point> initPointsCircular(float centerX, float centerY, float radius) {
		List<Point> points = new LinkedList<Point>();
		for (int i = 0; i < 360; i += 1) {
			int x = (int) (centerX - radius * Math.sin(Math.PI * (i - 180) / 180));
			int y = (int) (centerY + radius * Math.cos(Math.PI * (i - 180) / 180));
			points.add(new Point(x, y));
		}
		return points;
	}
}
