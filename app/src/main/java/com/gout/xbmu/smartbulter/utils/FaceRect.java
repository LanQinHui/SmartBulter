package com.gout.xbmu.smartbulter.utils;

import android.graphics.Point;
import android.graphics.Rect;
/**
 * 项目名: SmartBulter
 * 包名:   com.gout.xbmu.smartbulter.utils
 * 文件名: FaceRect
 * Created by LanQinHui on 2017/9/17.
 * 描述： TODO
 *
 * @author MatrixCV
 *         FaceRect是用于表示人脸检测的结果，其中包括了 人脸的角度、得分、检测框位置、关键点
 */
public class FaceRect {
    public float score;

    public Rect bound = new Rect();
    public Point point[];

    public Rect raw_bound = new Rect();
    public Point raw_point[];

    @Override
    public String toString() {
        return bound.toString();
    }
}