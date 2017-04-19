# ScalingSeekBar
A ScalingSeekBar is an extension of ProgressBar that adds a draggable thumb. The user can touch the thumb and drag left or right to set the current progress level. Placing focusable widgets to the left or right of a ScalingSeekBar is discouraged &amp; it’ll display in textview.
# Demo
![demo](https://raw.githubusercontent.com/pavithra-tr/ScalingSeekBar/master/app/src/main/assets/demo.jpg)
# Installation
## Gradle
Add this dependency in your project's build.gradle file which is in your app folder
```css
compile 'com.github.pavithra:scalingseekbar:1.0.0'
```
## Maven
Add this dependency in your project's build.gradle file which is in your app folder
```css
<dependency>
  <groupId>com.github.pavithra</groupId>
  <artifactId>scalingseekbar</artifactId>
  <version>1.0.0</version>
  <type>pom</type>
</dependency>
```
## XML Layout
Add ScalingSeekBar into your xml layout.
If you want scaling you can add view in xml (refer activity_main.xml)
```css
<com.mobileapp.itech.customseekbar.ScaleCustomSeekBar
        android:id="@+id/seekBar0"
        android:layout_width="match_parent"
        android:layout_height="35dp"
        android:layout_below="@+id/linear_layout"
        android:layout_marginLeft="10dp"
        android:layout_marginRight="10dp"
        android:max="50"
        android:progress="0"
        android:progressDrawable="@android:color/transparent"
        android:thumb="@drawable/seek_thumb_normal"
        android:thumbOffset="12dp" />
        
<TextView
  android:id="@+id/textView"
  android:layout_width="wrap_content"
  android:layout_height="wrap_content"
  android:layout_below="@+id/vertical_line"
  android:text=""
  android:textColor="@color/blue" />
```
## MainActivity.java
### * Implementation
```css
public class MainActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener
```
If you want change the span value and color depends upon your requirements
```css
    private float totalSpan = 1500;
    private float redSpan = 300;
    private float blueSpan = 300;
    private float greenSpan = 300;
    private float yellowSpan = 300;
    private float orangeSpan=300;
    int progressValue = 0;
    private ArrayList<ProgressItem> progressItemList;
    private ProgressItem mProgressItem;
```
### * Initialize your view
```css
mSeekbar = ((ScaleCustomSeekBar) findViewById(R.id.seekBar0));
mTextView = (TextView)findViewById(R.id.textView);
initDataToSeekbar();
mSeekbar.setOnSeekBarChangeListener(this);
```
### *initDataToSeekbar()
```css
 private void initDataToSeekbar() {
        progressItemList = new ArrayList<ProgressItem>();
        // red span
        mProgressItem = new ProgressItem();
        mProgressItem.progressItemPercentage = ((redSpan / totalSpan) * 100);
        Log.i("Mainactivity", mProgressItem.progressItemPercentage + "");
        mProgressItem.color = R.color.red;
        progressItemList.add(mProgressItem);
        // blue span
        mProgressItem = new ProgressItem();
        mProgressItem.progressItemPercentage = (blueSpan / totalSpan) * 100;
        mProgressItem.color = R.color.blue;
        progressItemList.add(mProgressItem);
        // green span
        mProgressItem = new ProgressItem();
        mProgressItem.progressItemPercentage = (greenSpan / totalSpan) * 100;
        mProgressItem.color = R.color.green;
        progressItemList.add(mProgressItem);
        // yellow span
        mProgressItem = new ProgressItem();
        mProgressItem.progressItemPercentage = (yellowSpan / totalSpan) * 100;
        mProgressItem.color = R.color.yellow;
        progressItemList.add(mProgressItem);
        // greyspan
        mProgressItem = new ProgressItem();
        mProgressItem.progressItemPercentage = (orangeSpan / totalSpan) * 100;
        mProgressItem.color = R.color.orange;
        progressItemList.add(mProgressItem);

        mSeekbar.initData(progressItemList);
        mSeekbar.invalidate();
    }
```
### * Listener
```css
@Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

        progressValue = progress;
        // To set the text while changing the thumb
        RelativeLayout.LayoutParams p = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        p.addRule(RelativeLayout.BELOW, seekBar.getId());
        Rect thumbRect = mSeekbar.getSeekBarThumb().getBounds();
        p.setMargins(thumbRect.centerX(),0, 0, 0);
        mTextView.setLayoutParams(p);
        mTextView.setText("..."+progressValue + "...");
        mTextView.setTextColor(getResources().getColor(R.color.white));
        mTextView.setBackgroundResource(R.drawable.background_fill);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        mTextView.setVisibility(View.VISIBLE);
        final Animation animationFadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_out);
        mTextView.startAnimation(animationFadeIn);
    }
```
### * background_fill.xml
Add this xml file to drawable folder 
```css
<?xml version="1.0" encoding="utf-8"?>
<shape xmlns:android="http://schemas.android.com/apk/res/android" >

    <gradient
        android:angle="90"
        android:centerColor="#FF555550"
        android:endColor="#F5555552"
        android:startColor="#FF555550" />

    <corners android:radius="10dp" />

    <stroke
        android:width="1dp"
        android:color="#50999999" />
    <stroke
        android:width="1dp"
        android:color="#70555555" />

</shape>
```
### * fade_out.xml
Add this xml file to anim folder 
```css
<?xml version="1.0" encoding="utf-8"?>
<set xmlns:android="http://schemas.android.com/apk/res/android"
    android:interpolator="@android:anim/linear_interpolator" >

    <alpha
        android:duration="2000"
        android:fromAlpha="1.0"
        android:toAlpha="0.1" />

</set>
```
