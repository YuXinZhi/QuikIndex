package com.example.quikindex.adapter;

import java.util.ArrayList;

import com.example.quikindex.R;
import com.example.quikindex.bean.Person;

import android.content.Context;
import android.text.TextUtils;
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

		}

		ViewHolder mViewHolder = ViewHolder.getHolder(view);

		Person p = persons.get(position);

		String str = null;

		String currentLetter = p.getPinyin().charAt(0) + "";
		// 根据上一个首字母，决定当前是否显示字母
		if (position == 0) {
			str = currentLetter;
		} else {
			// 上一个人的拼音首字母
			String preLetter = persons.get(position - 1).getPinyin().charAt(0) + "";
			if (!TextUtils.equals(preLetter, currentLetter)) {
				str = currentLetter;
			}
		}

		// 根据str是否为空，决定是否显示索引栏
		mViewHolder.mIndex.setVisibility(str == null ? View.GONE : View.VISIBLE);
		mViewHolder.mIndex.setText(currentLetter);
		
		System.out.println(p.getName());
		mViewHolder.mName.setText(p.getName());
		
		return view;
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
				holder.mName = (TextView) view.findViewById(R.id.tv_name);
				view.setTag(holder);
				return holder;
			}
		}
	}

}
