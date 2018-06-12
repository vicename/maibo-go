# maibo-go
 
贡献人:贤哥 中霞 达畅

众人拾柴火焰高

## 基础信息

本库大多数工具类均使用统一前缀:`Dif`,这是为了和其他类库做区分,在使用的时候先无脑`Dif`一下,说不定会有惊喜(并没有)

本库集成了一个通用RecyclerView的Item点击接口:`OnItemRecyclerClickListener`,其名字经过多次修改,最终使用此名字,使用的时候直接输入`OnItemClick`,然后IDE会帮助补全出来一堆类似接口,此库的接口一般排在第三或第四位(这是远程库的一个劣势,代码补全优先级不高),但仍建议普通的Item点击都使用此接口,以免一堆类似的接口扔项目里造成浪费

本库集成了若干个接口简化类,是为了"MMP我就是想要一个回调方法,为啥这个接口里面有这么多回调啊"这种场景而写,大家可以尽情添加,让代码更优雅.条件:1,要常用;2,要能有效减少空回调方法

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

### 监听简化

位于`listener_simpler`包

- `TextWatcherSimpler`文字输入监听简化,只保留`onTextChanged(CharSequence s, int start, int before, int count)`方法
- `AnimatorListenerSimplerEnd` 动画监听,只保留`onAnimationEnd(Animator animation)`方法

### Base类

不多解释,一看类名就懂

- DifBaseActivity
- ApplicationBase
- DifBaseLazyFragment
- DifBaseDialog
- DifDefine

### Adapter

#### RecyclerView的Adapter

位于`adapter/base_recycler`包,用于RecyclerView单布局,用法一看就懂

- BaseRecyclerViewAdapter<T>
- BaseViewHolder<T>
- OnItemRecyclerClickListener


#### 其他Adapter,比较鸡肋,考虑去掉


