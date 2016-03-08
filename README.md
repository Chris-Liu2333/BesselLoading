# BesselLoading
一个利用贝塞尔曲线实现的Loading动画

##使用方法

在布局文件中加入BesselLoading控件，并设置其圆颜色circlecolor，圆半径radius，以及播次一次的时间duration。
```xml
<com.mouse.cookie.besselloadinglib.BesselLoading
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        custom:duration="1800"
        custom:circlecolor="#00aadd"
        custom:radius="12dp"
        android:layout_alignParentTop="true"
        android:layout_centerHorizontal="true"/>
```

##效果
![image](https://github.com/cookiemouse/BesselLoading/blob/master/gif/Animation.gif)

##实现方法
  该控件继承自View<br>
1、首先绘制三个定点圆<br>
2、然后绘制运动的圆，其实现方式是利用属性动画，让其从左至右往返运动<br>
3、在定点圆与运动圆中画贝塞尔曲线，并根据两圆之间的距离重绘曲线（主要是两端点及偏移点的计算，具体实现请见源码）<br>
