# ParamUtil 通过反射读取被 @Param 标注的字段名作为跳转参数的 key，
# 混淆会重命名字段导致取参失败（如 GL 页面标题为空），必须保留字段名
-keepattributes *Annotation*
-keepclassmembers class * {
    @me.hgj.jetpackmvvm.util.Param <fields>;
}
