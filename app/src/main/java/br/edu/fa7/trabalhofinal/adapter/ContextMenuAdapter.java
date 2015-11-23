package br.edu.fa7.trabalhofinal.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.edu.fa7.trabalhofinal.R;
import br.edu.fa7.trabalhofinal.model.ContextMenuItem;

/**
 * Created by bruno on 31/08/15.
 */
public class ContextMenuAdapter extends BaseAdapter {

    private Context mContext;
    private List<ContextMenuItem> mList;
    private LayoutInflater mLayoutInflater;

    public ContextMenuAdapter(Context context, List<ContextMenuItem> mList) {
        this.mContext = context;
        this.mList = mList;
        this.mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;

        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.context_menu, parent, false);
            holder = new ViewHolder();
            convertView.setTag(holder);

            holder.contextMenuIcon = (ImageView) convertView.findViewById(R.id.context_menu_icon);
            holder.contextMenuLabel = (TextView) convertView.findViewById(R.id.context_menu_label);
            holder.contextMenuDivider = convertView.findViewById(R.id.context_menu_divider);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.contextMenuIcon.setImageResource(mList.get(position).getIcon());
        holder.contextMenuLabel.setText(mList.get(position).getLabel());

        // BACKGROUND
        if (position == 0) {
            ((ViewGroup) convertView).getChildAt(0).setBackgroundResource(R.drawable.context_menu_top_background);
        } else if (position == mList.size() - 1) {
            ((ViewGroup) convertView).getChildAt(0).setBackgroundResource(R.drawable.context_menu_bottom_background);
        } else {
            ((ViewGroup) convertView).getChildAt(0).setBackgroundResource(R.drawable.context_menu_middle_background);
        }

        holder.contextMenuDivider.setVisibility(position == mList.size() - 2 ? View.VISIBLE : View.GONE);

        return convertView;

    }

    public static class ViewHolder {
        ImageView contextMenuIcon;
        TextView contextMenuLabel;
        View contextMenuDivider;
    }
}
