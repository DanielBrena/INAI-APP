package inai.brena.com.inaiapp.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CheckBox;

/**
 * Created by DanielBrena on 25/10/15.
 */
public class MiCheckbox extends CheckBox {

    public MiCheckbox(Context context)
    {
        super(context);
    }

    public MiCheckbox(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    public MiCheckbox(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
    }

    @Override
    public void setPressed(boolean pressed)
    {
        if (pressed && getParent() instanceof View && ((View) getParent()).isPressed())
        {
            return;
        }
        super.setPressed(pressed);
    }
}
