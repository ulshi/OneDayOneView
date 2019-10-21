package com.cupster.textview;

import android.animation.FloatEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.format.DateUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.CharacterStyle;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.text.style.MetricAffectingSpan;
import android.text.style.StyleSpan;
import android.text.style.TextAppearanceSpan;
import android.text.style.UpdateAppearance;
import android.util.Log;
import android.util.Property;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.TextView;

import org.xml.sax.XMLReader;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 *
 */
public class TextViewActivity extends AppCompatActivity {
//1、android:autoLink设置是否当文本为URL链接/email/电话号码/map时，文本显示为可点击的链接。可选值(none/web/email/phone/map/all)
//
//2、android:autoText如果设置，将自动执行输入值的拼写纠正。此处无效果，在显示输入法并输入的时候起作用。
//3、android:bufferType指定getText()方式取得的文本类别。选项editable 类似于StringBuilder可追加字符，
//
//也就是说getText后可调用append方法设置文本内容。spannable 则可在给定的字符区域使用样式，参见这里1、这里2。
//
//4、android:capitalize设置英文字母大写类型。此处无效果，需要弹出输入法才能看得到，参见EditView此属性说明。
//
//5、android:cursorVisible设定光标为显示/隐藏，默认显示。
//
//6、android:digits设置允许输入哪些字符。如“1234567890.+-*/% ()”
//
//7、android:drawableBottom在text的下方输出一个drawable，如图片。如果指定一个颜色的话会把text的背景设为该颜色，并且同时和background使用时覆盖后者。
//
//8、android:drawableLeft在text的左边输出一个drawable，如图片。
//
//9、android:drawablePadding设置text与drawable(图片)的间隔，与drawableLeft、drawableRight、drawableTop、drawableBottom一起使用，可设置为负数，单独使用没有效果。
//
//10、android:drawableRight在text的右边输出一个drawable。
//
//11、android:drawableTop在text的正上方输出一个drawable。
//
//12、android:editable设置是否可编辑。
//
//13、android:editorExtras设置文本的额外的输入数据。
//
//14、android:ellipsize设置当文字过长时,该控件该如何显示。有如下值设置：”start”—?省略号显示在开头;”end”——省略号显示在结尾;”middle”—-省略号显示在中间;
//
//”marquee” ——以跑马灯的方式显示(动画横向移动)
//
//15、android:freezesText设置保存文本的内容以及光标的位置。
//
//16、android:gravity设置文本位置，如设置成“center”，文本将居中显示。
//
//17、android:hintText为空时显示的文字提示信息，可通过textColorHint设置提示信息的颜色。此属性在EditView中使用，但是这里也可以用。
//
//18、android:imeOptions附加功能，设置右下角IME动作与编辑框相关的动作，如actionDone右下角将显示一个“完成”，而不设置默认是一个回车符号。这个在EditView中再详细
//
//说明，此处无用。
//
//19、android:imeActionId设置IME动作ID。
//
//20、android:imeActionLabel设置IME动作标签。
//
//21、android:includeFontPadding设置文本是否包含顶部和底部额外空白，默认为true。
//
//22、android:inputMethod为文本指定输入法，需要完全限定名(完整的包名)。例如：com.google.android.inputmethod.pinyin，但是这里报错找不到。
//
//23、android:inputType设置文本的类型，用于帮助输入法显示合适的键盘类型。在EditView中再详细说明，这里无效果。
//
//24、android:linksClickable设置链接是否点击连接，即使设置了autoLink。
//
//25、android:marqueeRepeatLimit在ellipsize指定marquee的情况下，设置重复滚动的次数，当设置为marquee_forever时表示无限次。
//
//26、android:ems设置TextView的宽度为N个字符的宽度。这里测试为一个汉字字符宽度
//
//27、android:maxEms设置TextView的宽度为最长为N个字符的宽度。与ems同时使用时覆盖ems选项。
//
//28、android:minEms设置TextView的宽度为最短为N个字符的宽度。与ems同时使用时覆盖ems选项。
//
//29、android:maxLength限制显示的文本长度，超出部分不显示。
//
//30、android:lines设置文本的行数，设置两行就显示两行，即使第二行没有数据。
//
//31、android:maxLines设置文本的最大显示行数，与width或者layout_width结合使用，超出部分自动换行，超出行数将不显示。
//
//32、android:minLines设置文本的最小行数，与lines类似。
//
//33、android:lineSpacingExtra设置行间距。
//
//34、android:lineSpacingMultiplier设置行间距的倍数。如”1.2”
//
//35、android:numeric如果被设置，该TextView有一个数字输入法。此处无用，设置后唯一效果是TextView有点击效果，此属性在EdtiView将详细说明。
//
//36、android:password以小点”.”显示文本
//
//37、android:phoneNumber设置为电话号码的输入方式。
//
//38、android:privateImeOptions设置输入法选项，此处无用，在EditText将进一步讨论。
//
//39、android:scrollHorizontally设置文本超出TextView的宽度的情况下，是否出现横拉条。
//
//40、android:selectAllOnFocus如果文本是可选择的，让他获取焦点而不是将光标移动为文本的开始位置或者末尾位置。TextView中设置后无效果。
//
//41、android:shadowColor指定文本阴影的颜色，需要与shadowRadius一起使用。
//
//42、android:shadowDx设置阴影横向坐标开始位置。
//43、android:shadowDy设置阴影纵向坐标开始位置。
//
//44、android:shadowRadius设置阴影的半径。设置为0.1就变成字体的颜色了，一般设置为3.0的效果比较好。
//
//45、android:singleLine设置单行显示。如果和layout_width一起使用，当文本不能全部显示时，后面用“…”来表示。如android:text="test_ singleLine "
//
//android:singleLine="true" android:layout_width="20dp"将只显示“t…”。如果不设置singleLine或者设置为false，文本将自动换行
//
//46、android:text设置显示文本.
//
//47、android:textAppearance设置文字外观。如“?android:attr/textAppearanceLargeInverse”这里引用的是系统自带的一个外观，?表示系统是否有这种外观，否则使用默认的
//
//外观。可设置的值如下：
//
//textAppearanceButton/textAppearanceInverse/textAppearanceLarge/textAppearanceLargeInverse/textAppearanceMedium/textAppearanceMediumInverse/textAppearanceSmal
//
//l/textAppearanceSmallInverse
//
//48、android:textColor设置文本颜色
//
//49、android:textColorHighlight被选中文字的底色，默认为蓝色
//
//50、android:textColorHint设置提示信息文字的颜色，默认为灰色。与hint一起使用。
//
//51、android:textColorLink文字链接的颜色.
//
//52、android:textScaleX设置文字之间间隔，默认为1.0f。
//
//53、android:textSize设置文字大小，推荐度量单位”sp”，如”15sp”
//
//54、android:textStyle设置字形[bold(粗体) 0, italic(斜体) 1, bolditalic(又粗又斜) 2] 可以设置一个或多个，用“|”隔开
//
//55、android:typeface设置文本字体，必须是以下常量值之一：normal 0, sans 1, serif 2, monospace(等宽字体) 3]
//
//56、android:height设置文本区域的高度，支持度量单位：px(像素)/dp/sp/in/mm(毫米)
//
//57、android:maxHeight设置文本区域的最大高度
//
//58、android:minHeight设置文本区域的最小高度
//
//59、android:width设置文本区域的宽度，支持度量单位：px(像素)/dp/sp/in/mm(毫米)，与layout_width的区别看这里。
//
//60、android:maxWidth设置文本区域的最大宽度
//
//61、android:minWidth设置文本区域的最小宽度

    TextView animation_drawable ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_view);
        //drawable 动画
        animation_drawable = findViewById(R.id.animation_drawable);
        //字体
        TextView font_tv1 = findViewById(R.id.font_tv1);
        TextView font_tv2 = findViewById(R.id.font_tv2);
        TextView font_tv3 = findViewById(R.id.font_tv3);
        try {
            Typeface typeface_Ruthie = Typeface.createFromAsset(getAssets(), "fonts/Ruthie.ttf");//钢笔签字风格
            font_tv1.setTypeface(typeface_Ruthie);
            Typeface typeface_icomoon = Typeface.createFromAsset(getAssets(), "icomoon.ttf");
            font_tv2.setTypeface(typeface_icomoon);
            Typeface typefac_Nutso2e = Typeface.createFromAsset(getAssets(), "Nutso2.otf");
            font_tv3.setTypeface(typefac_Nutso2e);
        }catch (Exception e){
            e.printStackTrace();
        }
        //渐变
        TextView gradient = findViewById(R.id.gradient_tv);
        Shader shader = new LinearGradient(0,0,0,gradient.getTextSize(), Color.RED ,Color.BLUE, Shader.TileMode.CLAMP);
        gradient.getPaint().setShader(shader);
        TextView gradient2 = findViewById(R.id.gradient_tv2);
        Shader shader2 = new LinearGradient(0,0,0,gradient2.getTextSize(), Color.YELLOW ,Color.BLUE, Shader.TileMode.MIRROR);
        gradient2.getPaint().setShader(shader2);
        TextView gradient3 = findViewById(R.id.gradient_tv3);
        Shader shader3 = new LinearGradient(0,0,0,gradient3.getTextSize(), Color.RED ,Color.GREEN, Shader.TileMode.REPEAT);
        gradient3.getPaint().setShader(shader3);

        //图片填充
        TextView iconFill = findViewById(R.id.icon_filled);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources() , R.mipmap.cheetah_tile);
        Shader iconShader = new BitmapShader( bitmap , Shader.TileMode.REPEAT ,Shader.TileMode.REPEAT );
        iconFill.getPaint().setShader(iconShader);

        //使用html
        TextView tvHtml = findViewById(R.id.html_tv);
        String htmlDoc =  getString(R.string.from_html_text);
        Spanned spanned = Html.fromHtml(htmlDoc, new ResouroceImageGetter(this), null);
        Log.e("onCreate: ","spanned = null "+ spanned +"  tvhtml == null "+tvHtml );
        tvHtml.setText(spanned);
        tvHtml.setMovementMethod(LinkMovementMethod.getInstance());

        //数学、分数格式
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            TextView fraction = findViewById(R.id.fraction);
            Typeface typeface = Typeface.createFromAsset(getAssets(), "Nutso2.otf");//可以使用分数式  数学算式
            fraction.setTypeface(typeface);
            String html = getString(R.string.fraction_text);
            fraction.setText(Html.fromHtml(html, null, new FractionTagHandler()));
        }

        //span  string
        TextView spanTv = findViewById(R.id.span_str);
        SpannableStringBuilder builder = new SpannableStringBuilder()
                .append(formatString(this,"big red", R.style.BigRedTextAppearance))
                .append("\n")
                .append(formatString(this, "mid green", R.style.MediumGreenTextAppearance))
                .append("\n")
                .append(formatString(this, "small blue", R.style.SmallBlueTextAppearance));
        CharSequence styledString = builder.subSequence(0, builder.length());
        spanTv.setText(styledString);

        //跑马灯渐变 高亮
        TextView rainbow = findViewById(R.id.rainbow_tv);
        highlight(rainbow  , "highlight");

        //跑马灯渐变  高亮 动态
        TextView anim_rainbow = findViewById(R.id.rainbow_tv_animate);
        animRainbow(anim_rainbow ,"Animated rainbow span");

        //可点击的span
        TextView clickSpan = findViewById(R.id.click_span);
        setClickSpan(clickSpan ,"go to settings");

        //line edittext
        LinedEditText linedEditText = findViewById(R.id.underline_et);
        final TextView textView = (TextView) findViewById(R.id.text_emoji);
        findViewById(R.id.render_emoji_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                renderEmoji(textView);
                view.setEnabled(false);
            }
        });

    }



    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        changeAnimation(Operation.START);
    }

    @Override
    protected void onPause() {
        super.onPause();
        changeAnimation(Operation.STOP);
    }

    private void changeAnimation(Operation operation) {
        Drawable[] drawables = animation_drawable.getCompoundDrawables();
        for (Drawable drawable : drawables) {
            if (drawable != null && drawable instanceof Animatable) {
                Animatable animatable = ((Animatable) drawable);
                switch (operation) {
                    case START:
                        animatable.start();
                        break;
                    case STOP:
                        animatable.stop();
                        break;
                }
            }
        }
    }




    private static class ResouroceImageGetter implements Html.ImageGetter {
        private final Context context;

        public ResouroceImageGetter(Context context) {
            this.context = context;
        }

        @Override
        public Drawable getDrawable(String source) {
            int path = context.getResources().getIdentifier(source, "drawable",
                    BuildConfig.APPLICATION_ID);
            Drawable drawable = context.getResources().getDrawable(path);
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),
                    drawable.getIntrinsicHeight());
            return drawable;
        }
    }

    private static class FractionTagHandler implements Html.TagHandler {
        @Override
        public void handleTag(boolean opening, String tag, Editable output, XMLReader xmlReader) {
            if (!"afrc".equalsIgnoreCase(tag)) {
                return;
            }

            int len = output.length();
            if (opening) {
                output.setSpan(new FractionSpan(), len, len, Spannable.SPAN_MARK_MARK);
            } else {
                Object obj = getLast(output, FractionSpan.class);
                int where = output.getSpanStart(obj);

                output.removeSpan(obj);

                if (where != len) {
                    output.setSpan(new FractionSpan(), where, len, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                }
            }
        }

        private Object getLast(Editable text, Class kind) {
            Object[] objs = text.getSpans(0, text.length(), kind);

            if (objs.length == 0) {
                return null;
            } else {
                for (int i = objs.length - 1; i >= 0; --i) {
                    if(text.getSpanFlags(objs[i]) == Spannable.SPAN_MARK_MARK) {
                        return objs[i];
                    }
                }
                return null;
            }
        }
    }

    private static class FractionSpan extends MetricAffectingSpan {
        private static final String FONT_FEATURE_SETTINGS = "afrc";

        @Override
        public void updateMeasureState(TextPaint textPaint) {
            textPaint.setFontFeatureSettings(FONT_FEATURE_SETTINGS);
        }

        @Override
        public void updateDrawState(TextPaint textPaint) {
            textPaint.setFontFeatureSettings(FONT_FEATURE_SETTINGS);
        }
    }

    private static SpannableString formatString(Context context, String text, int style) {
//        String text = context.getString(textId);
        SpannableString spannableString = new SpannableString(text);
        spannableString.setSpan(new TextAppearanceSpan(context, style), 0, text.length(), 0);
        return spannableString;
    }


    private void highlight(TextView tv ,String lightWhich) {
        String text = tv.getText().toString();
        SpannableString spannableString = new SpannableString(text);

        Pattern pattern = Pattern.compile(lightWhich.toLowerCase());
        Matcher matcher = pattern.matcher(text.toLowerCase());
        while (matcher.find()) {
            spannableString.setSpan(new StyleSpan(Typeface.BOLD), matcher.start(), matcher.end(), 0);
            spannableString.setSpan(new RainbowSpan(this), matcher.start(), matcher.end(), 0);
        }
        tv.setText(spannableString);
    }

    private static class RainbowSpan extends CharacterStyle implements UpdateAppearance {
        private final int[] colors;

        public RainbowSpan(Context context) {
            colors = context.getResources().getIntArray(R.array.rainbow);
        }

        @Override
        public void updateDrawState(TextPaint paint) {
            paint.setStyle(Paint.Style.FILL);
            Shader shader = new LinearGradient(0, 0, 0, paint.getTextSize() * colors.length, colors, null,
                    Shader.TileMode.MIRROR);//随机组合  、还有线性顺序 or ...
            Matrix matrix = new Matrix();
            matrix.setRotate(90);
            shader.setLocalMatrix(matrix);
            paint.setShader(shader);
        }
    }

    private void animRainbow(final TextView textView , String highlight){
        String text = textView.getText().toString();

        AnimatedColorSpan span = new AnimatedColorSpan(this);

        final SpannableString spannableString = new SpannableString(text);
        String substring = highlight.toLowerCase();
        int start = text.toLowerCase().indexOf(substring);
        int end = start + substring.length();
        spannableString.setSpan(span, start, end, 0);

        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(
                span, ANIMATED_COLOR_SPAN_FLOAT_PROPERTY, 0, 100);
        objectAnimator.setEvaluator(new FloatEvaluator());
        objectAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                textView.setText(spannableString);
            }
        });
        objectAnimator.setInterpolator(new LinearInterpolator());
        objectAnimator.setDuration(DateUtils.MINUTE_IN_MILLIS * 3);
        objectAnimator.setRepeatCount(ValueAnimator.INFINITE);
        objectAnimator.start();
    }

    private static final Property<AnimatedColorSpan, Float> ANIMATED_COLOR_SPAN_FLOAT_PROPERTY
            = new Property<AnimatedColorSpan, Float>(Float.class, "ANIMATED_COLOR_SPAN_FLOAT_PROPERTY") {
        @Override
        public void set(AnimatedColorSpan span, Float value) {
            span.setTranslateXPercentage(value);
        }
        @Override
        public Float get(AnimatedColorSpan span) {
            return span.getTranslateXPercentage();
        }
    };
    private static class AnimatedColorSpan extends CharacterStyle implements UpdateAppearance {
        private final int[] colors;
        private Shader shader = null;
        private Matrix matrix = new Matrix();
        private float translateXPercentage = 0;

        public AnimatedColorSpan(Context context) {
            colors = context.getResources().getIntArray(R.array.rainbow);
        }

        public void setTranslateXPercentage(float percentage) {
            translateXPercentage = percentage;
        }

        public float getTranslateXPercentage() {
            return translateXPercentage;
        }

        @Override
        public void updateDrawState(TextPaint paint) {
            paint.setStyle(Paint.Style.FILL);
            float width = paint.getTextSize() * colors.length;
            if (shader == null) {
                shader = new LinearGradient(0, 0, 0, width, colors, null,
                        Shader.TileMode.MIRROR);
            }
            matrix.reset();
            matrix.setRotate(90);
            matrix.postTranslate(width * translateXPercentage, 0);
            shader.setLocalMatrix(matrix);
            paint.setShader(shader);
        }
    }

    private void setClickSpan(TextView textView, String goToSettings) {
        String text = textView.getText().toString();

        int start = text.indexOf(goToSettings);
        int end = start + goToSettings.length();

        SpannableString spannableString = new SpannableString(text);
        spannableString.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View view) {
                view.getContext().startActivity(new Intent(android.provider.Settings.ACTION_SETTINGS));
            }
        }, start, end, 0);
        textView.setText(spannableString);

        textView.setMovementMethod(new LinkMovementMethod());
    }



    private void renderEmoji(TextView textView) {
        String text = textView.getText().toString();
        SpannableString spannableString = new SpannableString(text);

        // Icon font
        Pattern pattern = Pattern.compile("\u26F7");    // skier
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(
                    getResources().getColor(R.color.blue));
            IconFontSpan iconFontSpan = new IconFontSpan(textView.getContext());
            spannableString.setSpan(iconFontSpan, matcher.start(), matcher.end(), 0);
            spannableString.setSpan(foregroundColorSpan, matcher.start(), matcher.end(), 0);
        }

        // ImageSpan from resource
        pattern = Pattern.compile(":octopus:");
        matcher = pattern.matcher(text);

        Bitmap octopus = null;
        int size = (int) (-textView.getPaint().ascent());
        while (matcher.find()) {
            if (octopus == null) {//D:\code\OneDayOneView\textview\src\main\res\drawable-nodpi\icon_html_use.png
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.icon_html_use);
                octopus = Bitmap.createScaledBitmap(bitmap, size, size, true);
                bitmap.recycle();
            }
            ImageSpan span = new ImageSpan(this, octopus, ImageSpan.ALIGN_BASELINE);
            spannableString.setSpan(span, matcher.start(), matcher.end(), 0);
        }

        // ImageSpan with dynamic drawable
        pattern = Pattern.compile(":speed_(\\d\\d\\d?):");
        matcher = pattern.matcher(text);

        while (matcher.find()) {
            SpeedSignDrawable drawable = new SpeedSignDrawable(textView, matcher.group(1));
            ImageSpan span = new ImageSpan(drawable, ImageSpan.ALIGN_BASELINE);
            spannableString.setSpan(span, matcher.start(), matcher.end(), 0);
        }

        textView.setText(spannableString);
    }

    private static class IconFontSpan extends MetricAffectingSpan {
        private static Typeface typeface = null;

        public IconFontSpan(Context context) {
            if (typeface == null) {
                typeface = Typeface.createFromAsset(
                        context.getAssets(), "icomoon.ttf");
            }
        }

        @Override
        public void updateMeasureState(TextPaint textPaint) {
            textPaint.setTypeface(typeface);
        }

        @Override
        public void updateDrawState(TextPaint textPaint) {
            textPaint.setTypeface(typeface);
        }
    }

    private static class SpeedSignDrawable extends Drawable {
        private final float ascent;
        private final float descent;
        private final float textSize;
        private final float strokeWidth;
        private final String number;
        private Paint paint;

        public SpeedSignDrawable(TextView textView, String number) {
            this.ascent = textView.getPaint().ascent();
            this.descent = textView.getPaint().descent();
            this.textSize = textView.getTextSize();
            this.strokeWidth = textView.getPaint().getStrokeWidth();

            this.number = number;

            int size = (int) -ascent;
            this.setBounds(0, 0, size, size);

            this.paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        }

        @Override
        public void draw(Canvas canvas) {
            int size = (int) -ascent;

            // Draw the circle
            paint.setStyle(Paint.Style.FILL);
            paint.setColor(Color.YELLOW);
            canvas.drawCircle(size/2, size/2, size/2 , paint);

            // Draw the ring
            paint.setStyle(Paint.Style.STROKE);
            paint.setColor(Color.RED);
            float ringWidth = size / 10;
            paint.setStrokeWidth(ringWidth);
            canvas.drawCircle(size/2, size/2, size/2 - ringWidth/2, paint);

            // Draw the text
            float ratio = 0.4f;
            paint.setStyle(Paint.Style.FILL);
            paint.setColor(Color.BLACK);
            paint.setTextAlign(Paint.Align.CENTER);
            paint.setTextSize(textSize * ratio);
            paint.setStrokeWidth(strokeWidth);
            paint.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
            int x = size / 2;
            int y = (int) (size/2 - ((descent + ascent)/2) * ratio);
            canvas.drawText(number, x, y, paint);
        }

        @Override
        public void setAlpha(int alpha) {
        }

        @Override
        public void setColorFilter(ColorFilter cf) {
        }

        @Override
        public int getOpacity() {
            return PixelFormat.UNKNOWN;
        }
    }

    private enum Operation {
        START, STOP
    }



}
