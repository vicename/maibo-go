# maibo-library

众人拾柴火焰高

## 基础信息

## 集成和发布

## 类说明

### 工具类

工具类位于`utils`包中

- 字符串相关
  - `DifStrConverter` 字符串转换,包括加密
  - `DifStrMatcher` 字符串检查,如电话号码校验
  - `DifStrUtils` 字符串处理,如获取汉字长度等
- View及Window相关
  - `DifViewSetter` 设置View,在自定义View的时候可用
  - `DifViewUtils` View处理,如删除本View,同时也包括Dp和Px的转换
  - `DifWindowUtils` 屏幕相关,包括全屏,横屏等,也包含获取屏幕宽高的方法
- 文件及序列化
  - `DifSerializableUtils` 序列化
  - `SPUtils` SharedPrefences简化
- 其他
  - `DifCommonUtils` 
  - `DifImageUtils` 图片处理
  - `DifGsonUtils` Gson处理
  - `DifDateUtils` 日期处理
  - `DifHackUtils` Root相关处理
  - `DifPhoneStateUtils` 设置信息获取,如网络状态等
  - `DifSystemUtils` 系统工具,如获取版本号
  - `DifTimeCounter` 倒计时
  - `DifWorker` 线程池和子线程,也包含是否主线程的判断
  - `Logger` Log类
  - `ToastUtils` Toast类

### Base类

不多解释,一看类名就懂

- DifBaseActivity
- ApplicationBase
- DifBaseLazyFragment
- DifBaseDialog
- DifDefine

