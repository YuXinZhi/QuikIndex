package com.example.quikindex.adapter;

import java.util.ArrayList;

import com.example.quikindex.R;
import com.example.quikindex.bean.Person;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class PersonAdapter extends BaseAdapter {
	private Context mContext;
	private ArrayList<Person> persons;

	public PersonAdapter(Context context, ArrayList<Person> persons) {
		this.mContext = context;
		this.persons = persons;
	}

	@Override
	public int getCount() {
		return persons.size();
	}

	@Override
	public Object getItem(int position) {
		return persons.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;
		if (convertView == null) {
			// LayoutInflater.from(mContext).inflate(parser, root, attachToRoot)
			view = view.inflate(mContext, R.layout.item_main_list, null);

		} else {

		}
		ViewHolder mViewHolder = ViewHolder.getHolder(view);

		Person p = persons.get(position);

		String letter = p.getPinyin().charAt(0) + "";

		mViewHolder.mIndex.setText(letter);
		mViewHolder.mName.setText(p.getName());
		return null;
	}

	static class ViewHolder {
		TextView mIndex;
		TextView mName;

		public static ViewHolder getHolder(View view) {
			Object tag = view.getTag();
			if (tag != null) {
				return (ViewHolder) tag;
			} else {
				ViewHolder holder = new ViewHolder();
				holder.mIndex = (TextView) view.findViewById(R.id.tv_index);
				holder.mIndex = (TextView) view.findViewById(R.id.tv_name);
				view.setTag(tag);
				return holder;
			}
		}
	}

}
