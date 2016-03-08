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
