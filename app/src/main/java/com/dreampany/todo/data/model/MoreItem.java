package com.dreampany.todo.data.model;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.dreampany.frame.data.model.BaseItem;
import com.dreampany.frame.data.util.TextUtil;
import com.dreampany.todo.R;
import com.dreampany.todo.data.enums.MoreType;
import com.google.common.base.Objects;

import java.io.Serializable;
import java.util.List;

import eu.davidea.flexibleadapter.FlexibleAdapter;
import eu.davidea.flexibleadapter.items.IFlexible;
import eu.davidea.viewholders.FlexibleViewHolder;

/**
 * Created by air on 10/18/17.
 */

public class MoreItem extends BaseItem<MoreItem.ViewHolder> implements IFlexible<MoreItem.ViewHolder> {

    private MoreType type;
    private int layoutId;

    public MoreItem(MoreType type) {
        this.type = type;
        this.layoutId = R.layout.item_more;
    }

    public MoreType getType() {
        return type;
    }

    @Override
    public boolean equals(Object inObject) {
        if (MoreItem.class.isInstance(inObject)) {
            MoreItem item = (MoreItem) inObject;
            return type.equals(item.type);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(type);
    }

    @Override
    public int getLayoutRes() {
        return layoutId;
    }

    @Override
    public boolean filter(Serializable constraint) {
        return false;
    }

    @Override
    public ViewHolder createViewHolder(View view, FlexibleAdapter adapter) {
        return new ViewHolder(view, adapter);
    }

    @Override
    public void bindViewHolder(FlexibleAdapter<IFlexible> adapter, ViewHolder holder, int position, List<Object> payloads) {
        switch (type) {
            case APPS:
                holder.icon.setImageResource(R.drawable.ic_apps_black_24dp);
                holder.title.setText(TextUtil.getString(holder.getContext(), R.string.title_more_apps));
                break;
            case RATE_US:
                holder.icon.setImageResource(R.drawable.ic_rate_review_black_24dp);
                holder.title.setText(TextUtil.getString(holder.getContext(), R.string.title_rate));
                break;
            case ABOUT_US:
                holder.icon.setImageResource(R.drawable.ic_info_black_24dp);
                holder.title.setText(TextUtil.getString(holder.getContext(), R.string.title_about));
                break;
            case FEEDBACK:
                holder.icon.setImageResource(R.drawable.ic_feedback_black_24dp);
                holder.title.setText(TextUtil.getString(holder.getContext(), R.string.title_feedback));
                break;
            case SETTINGS:
                holder.icon.setImageResource(R.drawable.ic_settings_black_24dp);
                holder.title.setText(TextUtil.getString(holder.getContext(), R.string.title_settings));
                break;
        }
    }

    static final class ViewHolder extends FlexibleViewHolder {

        ImageView icon;
        TextView title;

        ViewHolder(View view, FlexibleAdapter adapter) {
            super(view, adapter);
            icon = view.findViewById(R.id.viewIcon);
            title = view.findViewById(R.id.viewTitle);
        }

        Context getContext() {
            return itemView.getContext();
        }
    }

}
