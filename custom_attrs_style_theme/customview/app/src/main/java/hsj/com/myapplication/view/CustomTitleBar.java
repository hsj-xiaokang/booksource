package hsj.com.myapplication.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import hsj.com.myapplication.R;


/**自己定义控件UI和属性
 * http://www.cnblogs.com/whoislcj/p/5714760.html
 */
public class CustomTitleBar extends RelativeLayout implements View.OnClickListener,PopupMenu.OnMenuItemClickListener {
    //上下文对象
    private Context context;
    //左边
    private Button titleBarLeftBtn;
    //右边
    private Button titleBarRightBtn;
    //中间标题
    private TextView titleBarTitle;
    //三种事件的标志
    private final static int TITLEBAR_LEFT_BTN = 0;
    private final static int TITLEBAR_TITLE = 1;
    private final static int TITLEBAR_RIGHT_BTN = 2;

    private PopupMenu popup;

    //右边是文字还是按钮菜单
    private boolean text_right = Boolean.FALSE;
    private boolean menu_right = Boolean.FALSE;


    //事件处理
    private SelfCustomUIAttrsEvents selfCustomUIAttrsEvents;

    //构造函数
    public CustomTitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);

        this.context = context;

        //加载自己定义的布局
        LayoutInflater.from(context).inflate(R.layout.my_custom_view, this, true);

        titleBarLeftBtn = (Button) findViewById(R.id.title_bar_left);
        titleBarLeftBtn.setOnClickListener(this);
        titleBarRightBtn = (Button) findViewById(R.id.title_bar_right);
        titleBarRightBtn.setOnClickListener(this);
        titleBarTitle = (TextView) findViewById(R.id.title_bar_title);
        titleBarTitle.setOnClickListener(this);
        //绑定自己定义的属性到自己定义的布局
        TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.CustomTitleBar);

        if (attributes != null) {
            //处理titleBar背景色，设置自定义的UI的背景色
            int titleBarBackGround = attributes.getResourceId(R.styleable.CustomTitleBar_title_background_color, Color.GREEN);
            setBackgroundResource(titleBarBackGround);

            //先处理左边按钮
            //获取是否要显示左边按钮
            boolean leftButtonVisible = attributes.getBoolean(R.styleable.CustomTitleBar_left_button_visible, true);
            if (leftButtonVisible) {
                titleBarLeftBtn.setVisibility(View.VISIBLE);
            } else {
                titleBarLeftBtn.setVisibility(View.GONE);
            }

            //设置左边按钮的文字
            String leftButtonText = attributes.getString(R.styleable.CustomTitleBar_left_button_text);
            if (!TextUtils.isEmpty(leftButtonText)) {
                titleBarLeftBtn.setText(leftButtonText);
                //设置左边按钮文字颜色
                int leftButtonTextColor = attributes.getColor(R.styleable.CustomTitleBar_left_button_text_color, Color.WHITE);
                titleBarLeftBtn.setTextColor(leftButtonTextColor);
            } else {
                //设置左边图片icon 这里是二选一 要么只能是文字 要么只能是图片
                int leftButtonDrawable = attributes.getResourceId(R.styleable.CustomTitleBar_left_button_drawable, R.mipmap.titlebar_back_icon);
                if (leftButtonDrawable != -1) {
                    titleBarLeftBtn.setBackgroundResource(leftButtonDrawable);
                }
            }


            //处理标题
            //先获取标题是否要显示图片icon
            int titleTextDrawable = attributes.getResourceId(R.styleable.CustomTitleBar_title_text_drawable, -1);
            if (titleTextDrawable != -1) {
                titleBarTitle.setBackgroundResource(titleTextDrawable);
            } else {
                //如果不是图片标题 则获取文字标题
                String titleText = attributes.getString(R.styleable.CustomTitleBar_title_text);
                if (!TextUtils.isEmpty(titleText)) {
                    titleBarTitle.setText(titleText);
                }
                //获取标题显示颜色
                int titleTextColor = attributes.getColor(R.styleable.CustomTitleBar_title_text_color, Color.WHITE);
                titleBarTitle.setTextColor(titleTextColor);
            }



            //先处理右边按钮
            //获取是否要显示右边按钮
            boolean rightButtonVisible = attributes.getBoolean(R.styleable.CustomTitleBar_right_button_visible, true);
            if (rightButtonVisible) {
                titleBarRightBtn.setVisibility(View.VISIBLE);
            } else {
                titleBarRightBtn.setVisibility(View.GONE);
            }
            //设置右边按钮的文字
            String rightButtonText = attributes.getString(R.styleable.CustomTitleBar_right_button_text);
            if (!TextUtils.isEmpty(rightButtonText)) {
                text_right = Boolean.TRUE;
                titleBarRightBtn.setText(rightButtonText);
                //设置右边按钮文字颜色
                int rightButtonTextColor = attributes.getColor(R.styleable.CustomTitleBar_right_button_text_color, Color.WHITE);
                titleBarRightBtn.setTextColor(rightButtonTextColor);
            } else {
                menu_right = Boolean.TRUE;
                //设置右边图片icon 这里是二选一 要么只能是文字 要么只能是图片
                int rightButtonDrawable = attributes.getResourceId(R.styleable.CustomTitleBar_right_button_drawable, -1);
                if (rightButtonDrawable != -1) {
                    titleBarRightBtn.setBackgroundResource(rightButtonDrawable);
                }
            }
            /**
             * 生效
             */
            attributes.recycle();
        }
    }



    public Button getTitleBarLeftBtn() {
        return titleBarLeftBtn;
    }

    public Button getTitleBarRightBtn() {
        return titleBarRightBtn;
    }

    public TextView getTitleBarTitle() {
        return titleBarTitle;
    }

    public void setSelfCustomUIAttrsEvents(SelfCustomUIAttrsEvents selfCustomUIAttrsEvents) {
        this.selfCustomUIAttrsEvents = selfCustomUIAttrsEvents;
    }
    private boolean checkSelfCustomUIAttrsEventsIsNull(){
        return this.selfCustomUIAttrsEvents == null ? true:false;
    }
    private void assertSelfCustomUIAttrsEvents() throws NullPointerException{
        if(checkSelfCustomUIAttrsEventsIsNull()){
            throw new NullPointerException("selfCustomUIAttrsEvents is null[请实现SelfCustomUIAttrsEvents方法便于响应相关的操作]!");
        }
    }

    /**
     * 处理三种事件
     *  see:TITLEBAR_LEFT_BTN
     *      TITLEBAR_TITLE
     *      TITLEBAR_RIGHT_BTN
     * @param eventFlag
     */
    public void doEvents(int eventFlag){
        assertSelfCustomUIAttrsEvents();
        switch (eventFlag){
            case TITLEBAR_LEFT_BTN:
                selfCustomUIAttrsEvents.titleBarLeftBtnEvent();
                break;
            case TITLEBAR_TITLE:
                selfCustomUIAttrsEvents.titleBarTitleEvent();
                break;
            case TITLEBAR_RIGHT_BTN:
                //文字才执行
                if(text_right){
                    selfCustomUIAttrsEvents.titleBarRightBtnEvent();
                }else{
                    checkPopupMenuExesixtAndShow();
                }
                break;
            default:break;
        }
    }

    /**
     * 检查菜单存不存在不存在重新创建
     * @return
     */
    private void checkPopupMenuExesixtAndShow(){
        if(popup == null){
            //创建弹出式菜单对象（最低版本11）
            popup =  new PopupMenu(this.context, titleBarRightBtn);//第二个参数是绑定的那个view
            //获取菜单填充器
            MenuInflater inflater = popup.getMenuInflater();
            //填充菜单
            inflater.inflate(R.menu.main, popup.getMenu());
            //绑定菜单项的点击事件
            popup.setOnMenuItemClickListener(this);
        }
        //显示(这一行代码不要忘记了)
        popup.show();
    }

    /**
     * 点击事件
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.title_bar_left:
                doEvents(TITLEBAR_LEFT_BTN);
                break;
            case R.id.title_bar_title:
                doEvents(TITLEBAR_TITLE);
                break;
            case R.id.title_bar_right:
                doEvents(TITLEBAR_RIGHT_BTN);
                break;
            default:break;
        }
    }


    /**
     * 菜单的单击事件
     * @param item
     * @return
     */
    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_item:
                Toast.makeText(this.context, "You clicked Add", Toast.LENGTH_SHORT).show();
                break;
            case R.id.remove_item:
                Toast.makeText(this.context, "You clicked Remove", Toast.LENGTH_SHORT).show();
                break;
            default:
        }
        return true;
    }
}